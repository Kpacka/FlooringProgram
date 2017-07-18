/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.service;

import com.mycompany.flooringapp.dao.FlooringOrdersDao;
import com.mycompany.flooringapp.dao.FlooringOrdersDaoMockImpl;
import com.mycompany.flooringapp.dao.FlooringProductDao;
import com.mycompany.flooringapp.dao.FlooringProductDaoImpl;
import com.mycompany.flooringapp.dao.FlooringTaxesDao;
import com.mycompany.flooringapp.dao.FlooringTaxesDaoImpl;
import com.mycompany.flooringapp.dao.IncorrectInputException;
import com.mycompany.flooringapp.dto.Order;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class FlooringLogicImplTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    LocalDate date;
    private FlooringLogic logic;

    public FlooringLogicImplTest() {

        FlooringTaxesDao tax = new FlooringTaxesDaoImpl();
        FlooringProductDao product = new FlooringProductDaoImpl();
        FlooringOrdersDao dao = new FlooringOrdersDaoMockImpl();
        logic = new FlooringLogicImpl(product, tax, dao, "PRODUCTION");

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {

        logic.readTaxAndProducts();
        logic.setOrderNumberFromFile();
        Order newOrder = new Order();
        newOrder.setArea(new BigDecimal("100"));
        newOrder.setCustomerName("Test");
        newOrder.setProductType("Wood");
        newOrder.setState("OH");
        Order testOrder = new Order();
        testOrder = logic.addNewOrder(newOrder);

    }

    @After
    public void tearDown() {

        date = LocalDate.now();

        File destroyFile = new File("mockOrders_" + date.format(formatter) + ".txt");
        destroyFile.delete();
    }

    /**
     * Test of readTaxAndProducts method, of class FlooringLogicImpl.
     */
    @Test
    public void testReadTaxAndProducts() throws Exception {
        //Tested with Dao Tests
    }

    /**
     * Test of returnOrdersByDate method, of class FlooringLogicImpl.
     */
    @Test
    public void testReturnOrdersByDate() throws Exception {

        //Tested in other Methods
    }

    /**
     * Test of addNewOrder method, of class FlooringLogicImpl.
     */
    @Test
    public void testAddNewOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setArea(new BigDecimal("100"));
        newOrder.setCustomerName("Test");
        newOrder.setProductType("Wood");
        newOrder.setState("OH");
        Order testOrder = new Order();
        testOrder = logic.addNewOrder(newOrder);
        HashMap<String, Order> mapToTest = logic.returnOrdersByDate(LocalDate.now());
        assertEquals(testOrder, mapToTest.get("2"));

    }

    /**
     * Test of setOrderNumberFromFile method, of class FlooringLogicImpl.
     */
    @Test
    public void testSetOrderNumberFromFile() {

        int num = logic.returnOrderNumber();
        assertEquals(2, num);
    }

    /**
     * Test of saveWork method, of class FlooringLogicImpl.
     */
    @Test
    public void testSaveWork() throws IncorrectInputException {

        Order newOrder = new Order();
        newOrder.setArea(new BigDecimal("100"));
        newOrder.setCustomerName("Test");
        newOrder.setProductType("Wood");
        newOrder.setState("OH");
        Order testOrder = new Order();
        testOrder = logic.addNewOrder(newOrder);
        try {
            logic.saveWork();
        } catch (Exception e) {
            fail();
        }

    }

    /**
     * Test of updateOrder method, of class FlooringLogicImpl.
     */
    @Test
    public void testUpdateOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setArea(new BigDecimal("100"));
        newOrder.setCustomerName("UpdateTest");
        newOrder.setProductType("Wood");
        newOrder.setState("OH");
        logic.updateOrder(newOrder, LocalDate.now());
        HashMap<String, Order> mapToTest = logic.returnOrdersByDate(LocalDate.now());

        assertEquals("UpdateTest", mapToTest.get("1").getCustomerName());
    }

    /**
     * Test of deleteOrder method, of class FlooringLogicImpl.
     */
    @Test
    public void testDeleteOrder() throws Exception {
        logic.deleteOrder(LocalDate.now(), "1");
        HashMap<String, Order> mapToTest = logic.returnOrdersByDate(LocalDate.now());
        assertEquals(0, mapToTest.size());

    }

}
