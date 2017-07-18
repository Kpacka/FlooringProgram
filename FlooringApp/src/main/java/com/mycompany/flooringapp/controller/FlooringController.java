/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.controller;

import com.mycompany.flooringapp.dao.FlooringPersistenceException;
import com.mycompany.flooringapp.dao.IncorrectInputException;
import com.mycompany.flooringapp.dto.Order;
import com.mycompany.flooringapp.service.FlooringLogic;
import com.mycompany.flooringapp.view.FlooringView;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KylePackard
 */
public class FlooringController {

    FlooringLogic logic;
    FlooringView view;
    private boolean again = true;

    public FlooringController(FlooringView myView, FlooringLogic logic) {
        this.view = myView;
        this.logic = logic;
    }

    public void run() {
        //Methods to set program into known good state before user interaction
        try {
            logic.readTaxAndProducts();
        } catch (FlooringPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
            again = false;
        }
        logic.setOrderNumberFromFile();
        //End set-up Methods
        while (again) {
            view.printTopMenu();
            view.printOptions();

            switch (view.returnSelection()) {
                case 1:
                    try {
                        displayOrders();
                    } catch (FlooringPersistenceException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        addOrder();
                    } catch (IncorrectInputException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }

                    break;
                case 3: {
                    try {
                        editOrder();
                    } catch (FlooringPersistenceException | IncorrectInputException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }
                }
                break;
                case 4: {
                    try {
                        deleteOrder();
                    } catch (FlooringPersistenceException | IncorrectInputException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }
                }
                break;
                case 5:

                    saveWork();
               
                    break;
                case 6:
                    again = false;
                    //Conditionals for saving??? print quit??
                    break;
            }

            

        }
        view.printGoodbye();
    }

    public void displayOrders() throws FlooringPersistenceException {
        view.printDisplayOrdersMenu();
        LocalDate date = view.getDate();
        HashMap<String, Order> display;
        view.printDateForOrders(date);
        display = logic.returnOrdersByDate(date);
        view.printOrderMap(display);
    }

    public void addOrder() throws IncorrectInputException {

        Order newOrder = view.getNewOrder();
        Order returnedOrder = new Order();

        returnedOrder = logic.addNewOrder(newOrder);
        //Servivce addNewOrder(fills fields) and return to print
        //view.printNewOrder
        //Service.addNewOrder

        view.printNewOrder(returnedOrder);
    }

    public void editOrder() throws FlooringPersistenceException, IncorrectInputException {
        view.printEditMenu();
        Order updated;
        LocalDate date = view.getDate();
        HashMap<String, Order> orderMap;
        orderMap = logic.returnOrdersByDate(date);
        int userPick = view.getOrderForUpdate(orderMap);
        String userPickString = String.valueOf(userPick);
        try {
            updated = view.updateOrder(orderMap.get(userPickString));
        } catch (Exception ex) {
            throw new IncorrectInputException("Order Number is not in file");
        }
        
        Order updatedPrint = new Order();
        updatedPrint = logic.updateOrder(updated, date);
        view.printNewOrder(updatedPrint);
    }

    public void deleteOrder() throws FlooringPersistenceException, IncorrectInputException {
        view.printDeleteMenu();
        LocalDate date = view.getDate();
        HashMap<String, Order> orderMap;
        orderMap = logic.returnOrdersByDate(date);
        int userPick = view.getOrderForUpdate(orderMap);
        String userPickString = String.valueOf(userPick);
        boolean userCorrect = false;
        for (String key : orderMap.keySet()) {
            if (userPickString.equals(key)) {
                userCorrect = true;
            }
        }
        if (!userCorrect) {
            throw new IncorrectInputException("Order Number Problem");
        }
        boolean yesNo = view.getYesOrNo();
        if (yesNo) {

            logic.deleteOrder(date, userPickString);
//            } catch (Exception ex){
//                throw new IncorrectInputException("Order Number doesn't Exist");
//            }
        } else {
            view.printNotDeleted();
        }
        
    }
    public void saveWork(){
        boolean success = logic.saveWork();
        if (success){
            view.printSaved();
        }
    }

}
