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
public class Order {

    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal totalTax;
    private BigDecimal totalPrice;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int key) {
        this.orderNumber = key;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborPerSquareFoot() {
        return laborPerSquareFoot;
    }

    public void setLaborPerSquareFoot(BigDecimal laborPerSquareFoot) {
        this.laborPerSquareFoot = laborPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void constructOrder(BigDecimal taxRate, BigDecimal pricePerFoot, BigDecimal laborPerFoot){
        setTaxRate(taxRate);
        setLaborPerSquareFoot(laborPerFoot);
        setCostPerSquareFoot(pricePerFoot);
        
        BigDecimal tax = taxRate.divide(new BigDecimal("100"));
        setLaborCost(area.multiply(laborPerSquareFoot));
        setMaterialCost(area.multiply(costPerSquareFoot));
        setTotalTax((laborCost.multiply(tax)).add(materialCost.multiply(tax)));
        setTotalPrice((laborCost.add(materialCost)).add(totalTax));
        
    }

}
