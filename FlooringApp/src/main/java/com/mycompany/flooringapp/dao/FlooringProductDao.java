/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import com.mycompany.flooringapp.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author KylePackard
 */
public interface FlooringProductDao {
    
    public void readProductInfo() throws FlooringPersistenceException;
    public Product getProduct(String name);
    public BigDecimal getPPF(String name);
    public BigDecimal getLCPF(String name);
    public ArrayList<Product> getList();
    public String getProducts();
}
