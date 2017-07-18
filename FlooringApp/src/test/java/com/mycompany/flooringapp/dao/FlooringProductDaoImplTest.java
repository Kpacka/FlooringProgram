/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import com.mycompany.flooringapp.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class FlooringProductDaoImplTest {
    
    ArrayList<Product> myProdList = new ArrayList();
    FlooringProductDaoImpl test = new FlooringProductDaoImpl();
    
    public FlooringProductDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        test.readProductInfo();
        myProdList = test.getList();
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readProductInfo method, of class FlooringProductDaoImpl.
     */
    @Test
    public void testReadProductInfo() throws Exception {
        
        assertEquals(4, myProdList.size());
    }

    /**
     * Test of getProduct method, of class FlooringProductDaoImpl.
     */
    @Test
    public void testGetProduct() {
        
        Product tester = new Product();
        tester.setLaborPerSquareFoot(BigDecimal.valueOf(2.20));
        tester.setPricePerSquareFoot(BigDecimal.valueOf(2.25));
        tester.setProductName("Carpet");
        
        Product toTest = test.getProduct("Carpet");
        
        assertEquals(tester.getProductName(), toTest.getProductName());
        assertEquals(tester.getPricePerSquareFoot(), toTest.getPricePerSquareFoot());
        assertNotEquals(tester.getLaborPerSquareFoot(), toTest.getLaborPerSquareFoot());

    }

    /**
     * Test of getPPF method, of class FlooringProductDaoImpl.
     */
    @Test
    public void testGetPPF() {
        Product toTest = test.getProduct("Wood");
        assertEquals(BigDecimal.valueOf(5.15), toTest.getPricePerSquareFoot());
        
        
    }

    /**
     * Test of getLCPF method, of class FlooringProductDaoImpl.
     */
    @Test
    public void testGetLCPF() {
        Product toTest = test.getProduct("Tile");
        assertEquals(BigDecimal.valueOf(4.15), toTest.getLaborPerSquareFoot());
    }
    
}
