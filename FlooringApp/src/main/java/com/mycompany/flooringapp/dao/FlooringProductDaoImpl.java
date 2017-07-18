/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import com.mycompany.flooringapp.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author KylePackard
 */
public class FlooringProductDaoImpl implements FlooringProductDao {

    private ArrayList<Product> productList = new ArrayList();
    public static final String INVENTORY_FILE = "products.txt";
    public static final String DELIMITER = ",";

    @Override
    public void readProductInfo() throws FlooringPersistenceException {
        Scanner scanner;
        ArrayList<Product> listFromFile = new ArrayList();

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-- Could not load product data into memory.\n-- Check your product.txt file.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if (!"ProductType".equals(currentTokens[0])) {

                Product currentInventoryFromFile = new Product();
                currentInventoryFromFile.setProductName(currentTokens[0]);
                currentInventoryFromFile.setPricePerSquareFoot(new BigDecimal(currentTokens[1]));
                currentInventoryFromFile.setLaborPerSquareFoot(new BigDecimal(currentTokens[2]));
                listFromFile.add(currentInventoryFromFile);
            }
        }
        scanner.close();
        productList = listFromFile;
    }

    @Override
    public Product getProduct(String name) {
        for (Product p : productList) {
            String pName = p.getProductName();
            if (pName.equals(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public BigDecimal getPPF(String name) {
        Product product = getProduct(name);
        return product.getPricePerSquareFoot();
    }

    @Override
    public BigDecimal getLCPF(String name) {
        Product product = getProduct(name);
        return product.getLaborPerSquareFoot();
    }

    @Override
    public ArrayList<Product> getList() {
        return productList;
    }

    @Override
    public String getProducts() {
        String returnString = "";
        for (Product product : productList) {
            returnString += "-" + product.getProductName() + "\n";
        }
        returnString += "=====================";
        return returnString;
    }
}
