/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.service;

import com.mycompany.flooringapp.dao.FlooringOrdersDao;
import com.mycompany.flooringapp.dao.FlooringPersistenceException;
import com.mycompany.flooringapp.dao.FlooringProductDao;
import com.mycompany.flooringapp.dao.FlooringTaxesDao;
import com.mycompany.flooringapp.dao.IncorrectInputException;
import com.mycompany.flooringapp.dto.Order;
import com.mycompany.flooringapp.dto.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KylePackard
 */
public class FlooringLogicImpl implements FlooringLogic {

    FlooringProductDao productDao;
    FlooringTaxesDao taxDao;
    FlooringOrdersDao orderDao;
    Boolean isTraining;

    ArrayList<LocalDate> datesUpdated = new ArrayList();
    int todaysOrderNumber = 0;

    public FlooringLogicImpl(FlooringProductDao productDao, FlooringTaxesDao taxDao, FlooringOrdersDao orderDao, String training) {
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.orderDao = orderDao;
        if(training.equals("TRAINING")){
            isTraining = true;
        }
        else {
            isTraining = false;
        }
    }

    @Override
    public void readTaxAndProducts() throws FlooringPersistenceException {
        productDao.readProductInfo();
        taxDao.readTaxes();
        try {
            orderDao.readOrdersIntoMap(LocalDate.now());
        } catch (FlooringPersistenceException e) {
            // No current file for today
        }
    }

    @Override
    public HashMap<String, Order> returnOrdersByDate(LocalDate date) throws FlooringPersistenceException {

        return orderDao.returnOrderMap(date);
    }

    @Override
    public Order addNewOrder(Order order) throws IncorrectInputException {
        int orderNumber = 0;
        BigDecimal taxRate;
        Product product;
        orderNumber = todaysOrderNumber;

        order.setOrderNumber(orderNumber);
        //throw new Excepions
        try {
            taxRate = taxDao.getTaxRate(order.getState());
        } catch (Exception e) {
            throw new IncorrectInputException("State is not in system.\nOptions:\n" + taxDao.getStates());
        }
        try {
            product = productDao.getProduct(order.getProductType());
            order.constructOrder(taxRate, product.getPricePerSquareFoot(), product.getLaborPerSquareFoot());
        } catch (Exception e) {
            throw new IncorrectInputException("Product is not in system.\nOptions:\n" + productDao.getProducts());
        }

        orderDao.addOrderToday(order);
        todaysOrderNumber++;
        return order;
    }

    private int getTodaysOrderNumber(HashMap<String, Order> map) {
        //Possibly Stream Here
        int highestOrder = 0;
        for (String key : map.keySet()) {

            if (map.get(key).getOrderNumber() > highestOrder) {
                highestOrder = map.get(key).getOrderNumber();
            }

        }
        return highestOrder + 1;
    }

    @Override
    public void setOrderNumberFromFile() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String todayString = today.format(formatter);
        int orderNum;
        try {
            orderNum = getTodaysOrderNumber(orderDao.getMapByDate(LocalDate.now()));
        } catch (FlooringPersistenceException ex) {
            orderNum = 1;
        }
        todaysOrderNumber = orderNum;
    }

    @Override
    public boolean saveWork() {
        
        if(isTraining){
            System.out.println("Cannot Save In Training");
            return false;
        }
        else{
        try {
            orderDao.writeOrdersByDate();
        } catch (FlooringPersistenceException ex) {

            return false;
        }
        }
        return true;
    }

    @Override
    public Order updateOrder(Order order, LocalDate date) throws FlooringPersistenceException, IncorrectInputException {
 
        BigDecimal taxRate = null;
        try {
            taxRate = taxDao.getTaxRate(order.getState());
        } catch (Exception ex) {
            throw new IncorrectInputException("State is not in system.\nOptions:\n" + taxDao.getStates());
        }
        try {
            Product product = productDao.getProduct(order.getProductType());
            order.constructOrder(taxRate, product.getPricePerSquareFoot(), product.getLaborPerSquareFoot());
        } catch (Exception e) {
            throw new IncorrectInputException("Product is not in system.\nOptions:\n" + productDao.getProducts());
        }
      
        orderDao.updateOrder(order, date);
        return order;
    }

    @Override
    public void deleteOrder(LocalDate date, String key) throws FlooringPersistenceException {
        orderDao.deleteOrder(date, key);
    }
    
    //Methods for Test
    @Override
    public int returnOrderNumber(){
        return todaysOrderNumber;
    }
}
