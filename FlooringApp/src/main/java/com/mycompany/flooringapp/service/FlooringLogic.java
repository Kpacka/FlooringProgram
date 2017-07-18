/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.service;

import com.mycompany.flooringapp.dao.FlooringPersistenceException;
import com.mycompany.flooringapp.dao.IncorrectInputException;
import com.mycompany.flooringapp.dto.Order;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author KylePackard
 */
public interface FlooringLogic {
    
    public void readTaxAndProducts()throws FlooringPersistenceException ;
    
    public HashMap<String, Order> returnOrdersByDate(LocalDate date) throws FlooringPersistenceException;
    
    public Order addNewOrder(Order order) throws IncorrectInputException;
    
    public void setOrderNumberFromFile();
    
    public boolean saveWork();
    
    public Order updateOrder(Order order, LocalDate date) throws FlooringPersistenceException, IncorrectInputException;
    
    public void deleteOrder(LocalDate date, String key) throws FlooringPersistenceException;
    
    public int returnOrderNumber();
}
