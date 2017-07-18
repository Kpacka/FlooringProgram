/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import java.math.BigDecimal;
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
public class FlooringTaxesDaoImplTest {
    
    FlooringTaxesDaoImpl tax = new FlooringTaxesDaoImpl();
    
    public FlooringTaxesDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FlooringPersistenceException{
        
        tax.readTaxes();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTaxRate method, of class FlooringTaxesDaoImpl.
     */
    @Test
    public void testGetTaxRate() throws Exception {
        //readTaxes() will be tested if this method returns true
        BigDecimal oh = new BigDecimal("6.25");
        assertEquals(oh, tax.getTaxRate("OH"));
        try{
            BigDecimal taxes = tax.getTaxRate("oh");
            fail();
        }
        catch(IncorrectInputException e){
            
        }
    }
    
}
