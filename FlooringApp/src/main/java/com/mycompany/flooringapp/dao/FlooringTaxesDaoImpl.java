/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.dao;

import static com.mycompany.flooringapp.dao.FlooringProductDaoImpl.DELIMITER;
import static com.mycompany.flooringapp.dao.FlooringProductDaoImpl.INVENTORY_FILE;
import com.mycompany.flooringapp.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author KylePackard
 */
public class FlooringTaxesDaoImpl implements FlooringTaxesDao {

    private Map<String, BigDecimal> taxList = new HashMap();
    public static final String INVENTORY_FILE = "taxes.txt";
    public static final String DELIMITER = ",";

    @Override
    public void readTaxes() throws FlooringPersistenceException {
        Scanner scanner;
        Map<String, BigDecimal> mapFromFile = new HashMap();
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-- Could not load tax data into memory.\n-- Check your taxes.txt file", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if (!"State".equals(currentTokens[0])) {

                mapFromFile.put(currentTokens[0], new BigDecimal(currentTokens[1]));

            }
        }
        scanner.close();
        taxList = mapFromFile;
    }

    @Override
    public BigDecimal getTaxRate(String state) throws IncorrectInputException {
        if (taxList.containsKey(state)) {
            BigDecimal taxRate = taxList.get(state);
            return taxRate;
        }
        else{
            throw new IncorrectInputException("Cannot Find State");
        }
        
    }
    @Override
    public String getStates(){
        String toReturn = "";
        toReturn = taxList.keySet().stream().map((state) -> "-" + state + "\n").reduce(toReturn, String::concat);
        toReturn += "=====================";
        return toReturn;
    }
}
