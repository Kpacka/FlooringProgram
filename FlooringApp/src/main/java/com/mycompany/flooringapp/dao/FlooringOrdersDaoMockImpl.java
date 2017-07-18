/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import com.mycompany.flooringapp.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author KylePackard
 */
public class FlooringOrdersDaoMockImpl implements FlooringOrdersDao {

    HashMap<String, Order> myOrderList = new HashMap();
    HashMap<String, Order> todaysOrderMap = new HashMap();
    HashMap<String, HashMap<String, Order>> orderMap = new HashMap();

    public static final String INVENTORY_FILE = "mockOrders_";
    public static final String DELIMITER = ",";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");



    @Override
    public HashMap<String, Order> getMapByDate(LocalDate date) throws FlooringPersistenceException {
        Scanner scanner;
        HashMap<String, Order> mapFromFile = new HashMap();
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE + date.format(formatter) + ".txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-- Could not find file\n-- Try again", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order toAdd = new Order();
            String toCheck = currentTokens[0];
            if (!toCheck.equals("OrderNumber")) {

                toAdd.setOrderNumber(Integer.valueOf(currentTokens[0]));
                toAdd.setCustomerName(currentTokens[1]);
                toAdd.setState(currentTokens[2]);
                toAdd.setTaxRate(new BigDecimal(currentTokens[3]));
                toAdd.setProductType(currentTokens[4]);
                toAdd.setArea(new BigDecimal(currentTokens[5]));
                toAdd.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                toAdd.setLaborPerSquareFoot(new BigDecimal(currentTokens[7]));
                toAdd.setMaterialCost(new BigDecimal(currentTokens[8]));
                toAdd.setLaborCost(new BigDecimal(currentTokens[9]));
                toAdd.setTotalTax(new BigDecimal(currentTokens[10]));
                toAdd.setTotalPrice(new BigDecimal(currentTokens[11]));

                mapFromFile.put(currentTokens[0], toAdd);

            }
        }
        scanner.close();
        myOrderList = mapFromFile;
        return myOrderList;
    }

    @Override
    public void addOrderToday(Order newOrder) {

        String key = String.valueOf(newOrder.getOrderNumber());
        todaysOrderMap.put(key, newOrder);
        //Added Into HASHASH
        orderMap.put(LocalDate.now().format(formatter), todaysOrderMap);

    }

    @Override
    public void writeOrdersByDate() throws FlooringPersistenceException {

        PrintWriter out;
        for (String date : orderMap.keySet()) {

            try {
                out = new PrintWriter(new FileWriter(INVENTORY_FILE + date + ".txt"));
            } catch (IOException e) {
                throw new FlooringPersistenceException(
                        "Could not save student data.", e);
            }

            HashMap<String, Order> orderList = this.orderMap.get(date);
            out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            for (String key : orderList.keySet()) {

                out.println(String.valueOf(orderList.get(key).getOrderNumber()) + DELIMITER
                        + orderList.get(key).getCustomerName() + DELIMITER
                        + orderList.get(key).getState() + DELIMITER
                        + orderList.get(key).getTaxRate().toString() + DELIMITER
                        + orderList.get(key).getProductType() + DELIMITER
                        + orderList.get(key).getArea().toString() + DELIMITER
                        + orderList.get(key).getCostPerSquareFoot().toString() + DELIMITER
                        + orderList.get(key).getLaborPerSquareFoot().toString() + DELIMITER
                        + orderList.get(key).getMaterialCost().toString() + DELIMITER
                        + orderList.get(key).getLaborCost().toString() + DELIMITER
                        + orderList.get(key).getTotalTax().toString() + DELIMITER
                        + orderList.get(key).getTotalPrice().toString());

                out.flush();
            }
            out.close();
        }
//        
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(INVENTORY_FILE + LocalDate.now().format(formatter)+ ".txt"));
//        } catch (IOException e) {
//            throw new FlooringPersistenceException(
//                    "Could not save student data.", e);
//        }
//
//        HashMap<String, Order> orderList = this.todaysOrderMap;
//        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
//        for (String key : orderList.keySet()) {
//
//            out.println(String.valueOf(orderList.get(key).getOrderNumber()) +  DELIMITER
//                    + orderList.get(key).getCustomerName() +  DELIMITER
//                    + orderList.get(key).getState() +  DELIMITER
//                    + orderList.get(key).getTaxRate().toString() +  DELIMITER
//                    + orderList.get(key).getProductType() +  DELIMITER
//                    + orderList.get(key).getArea().toString() +  DELIMITER
//                    + orderList.get(key).getCostPerSquareFoot().toString() +  DELIMITER
//                    + orderList.get(key).getLaborPerSquareFoot().toString() +  DELIMITER
//                    + orderList.get(key).getMaterialCost().toString() +  DELIMITER
//                    + orderList.get(key).getLaborCost().toString() +  DELIMITER
//                    + orderList.get(key).getTotalTax().toString() +  DELIMITER
//                    + orderList.get(key).getTotalPrice().toString());
//                    
//            out.flush();
//        }
//      out.close();

    }

    @Override
    public void readOrdersIntoMap(LocalDate date) throws FlooringPersistenceException {
        HashMap<String, Order> loadedMap;
        loadedMap = getMapByDate(date);
        todaysOrderMap = loadedMap;
        readOrdersIntoDatedMap(todaysOrderMap, date);
        //orderMap.put(date.format(formatter), loadedMap);
    }

    private void readOrdersIntoDatedMap(HashMap<String, Order> updatedmap, LocalDate date) {
        //**Takes FULLY updated map, will REPLACE not append
        orderMap.put(date.format(formatter), updatedmap);
    }

    @Override
    public HashMap<String, Order> returnOrderMap(LocalDate date) throws FlooringPersistenceException {
        if (orderMap.containsKey(date.format(formatter))) {
            return orderMap.get(date.format(formatter));
        } else {
            HashMap<String, Order> map = getMapByDate(date);
            //*******
            readOrdersIntoDatedMap(map, date);
            //******
            return map;
        }

    }

    @Override
    public void updateOrder(Order order, LocalDate date) {
        HashMap<String, Order> updatedOrder;
        updatedOrder = orderMap.get(date.format(formatter));
        updatedOrder.replace(String.valueOf(order.getOrderNumber()), order);
        orderMap.replace(date.format(formatter), updatedOrder);
    }

    @Override
    public void deleteOrder(LocalDate date, String key){
    HashMap<String, Order> updatedOrder;
    updatedOrder = orderMap.get(date.format(formatter));
    updatedOrder.remove(key);
    
    orderMap.replace(date.format(formatter), updatedOrder);
    
    }
    //Method for test

    
        public HashMap<String, HashMap<String, Order>> getCurrentOrderMap(){
        return orderMap;
    }

}

