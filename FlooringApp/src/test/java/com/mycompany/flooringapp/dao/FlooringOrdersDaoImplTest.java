/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import com.mycompany.flooringapp.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KylePackard
 */
public class FlooringOrdersDaoImplTest {
    
    FlooringOrdersDaoImpl dao = new FlooringOrdersDaoImpl();
    HashMap<String, Order> testMap = new HashMap();
    
    LocalDate date = LocalDate.of(1111, 11, 11);
    
    
    
    public FlooringOrdersDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FlooringPersistenceException {
        testMap = dao.getMapByDate(date);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveCurrentWork method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testSaveCurrentWork() {
    }

    /**
     * Test of getMapByDate method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testGetMapByDate() throws Exception {
        //test class level map created using this method
        assertEquals(1, testMap.size());
        
        //assert there is no file with LocalDate.MIN
        try{
            dao.getMapByDate(LocalDate.MIN);
            fail();
        } catch (Exception e){
         
        }
    }

    /**
     * Test of addOrderToday method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testAddOrderToday() {
        //will fail if an order is already added for TODAY
        Order newOrder = new Order();
        newOrder.setCustomerName("Dao Test");
        newOrder.setProductType("Wood");
        newOrder.setState("OH");
        newOrder.setArea(new BigDecimal("100"));
        
        dao.addOrderToday(newOrder);
        
        HashMap<String, HashMap<String,Order>> mapForTest  = dao.getCurrentOrderMap();
        
        assertEquals(1, mapForTest.size());
    }

    /**
     * Test of writeOrdersByDate method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testWriteOrdersByDate() throws Exception {
        //Test In Service using Mock Dao
    }

    /**
     * Test of readOrdersIntoMap method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testReadOrdersIntoMap() throws Exception {
        dao.readOrdersIntoMap(LocalDate.of(1111, 11, 11));
        HashMap<String, HashMap<String,Order>> mapForTest  = dao.getCurrentOrderMap();
        
        assertEquals(1, mapForTest.size());
    }

    /**
     * Test of returnOrderMap method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testReturnOrderMap() throws Exception {
        HashMap<String,Order> mapForTest = dao.returnOrderMap(date);
        assertEquals(mapForTest.size(), testMap.size());
                
    }

    /**
     * Test of updateOrder method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testUpdateOrder() throws FlooringPersistenceException {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Dao Test");
        newOrder.setProductType("Wood");
        newOrder.setState("OH");
        newOrder.setArea(new BigDecimal("100"));
        dao.readOrdersIntoMap(date);
        dao.updateOrder(newOrder, date);
        HashMap< String, HashMap<String,Order>> mapForTest = dao.getCurrentOrderMap();
        assertEquals("Dao Test", mapForTest.get("11111111").get("1").getCustomerName());
        
        
    }
    /**
     * Test of deleteOrder method, of class FlooringOrdersDaoImpl.
     */
    @Test
    public void testDeleteOrder() throws FlooringPersistenceException {
        dao.readOrdersIntoMap(date);
        dao.deleteOrder(date, "1");
        HashMap< String, HashMap<String,Order>> mapForTest = dao.getCurrentOrderMap();
        assertEquals(0, mapForTest.get("111111111").size());
    }
    
}
