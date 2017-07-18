/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dto;

import java.math.BigDecimal;

/**
 *
 * @author KylePackard
 */
public class Product {
    String ProductName;
    BigDecimal PricePerSquareFoot;
    BigDecimal LaborPerSquareFoot;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public BigDecimal getPricePerSquareFoot() {
        return PricePerSquareFoot;
    }

    public void setPricePerSquareFoot(BigDecimal PricePerSquareFoot) {
        this.PricePerSquareFoot = PricePerSquareFoot;
    }

    public BigDecimal getLaborPerSquareFoot() {
        return LaborPerSquareFoot;
    }

    public void setLaborPerSquareFoot(BigDecimal LaborPerSquareFoot) {
        this.LaborPerSquareFoot = LaborPerSquareFoot;
    }
    
}