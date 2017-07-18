/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import java.math.BigDecimal;

/**
 *
 * @author KylePackard
 */
public interface FlooringTaxesDao {
    public void readTaxes() throws FlooringPersistenceException;
    public BigDecimal getTaxRate(String state) throws IncorrectInputException;
    public String getStates();
}
