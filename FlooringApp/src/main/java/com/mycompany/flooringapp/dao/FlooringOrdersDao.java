/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import com.mycompany.flooringapp.dto.Order;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author KylePackard
 */
public interface FlooringOrdersDao {
    
    public HashMap<String, Order> getMapByDate(LocalDate date) throws FlooringPersistenceException;
    
    public void addOrderToday(Order newOrder);
    
 
    
    public void writeOrdersByDate() throws FlooringPersistenceException;
    
    public void readOrdersIntoMap(LocalDate date) throws FlooringPersistenceException;
    
    public HashMap<String, Order> returnOrderMap (LocalDate date) throws FlooringPersistenceException;
    
    public void updateOrder(Order order,  LocalDate date);
    
    public void deleteOrder(LocalDate date, String key);

}
