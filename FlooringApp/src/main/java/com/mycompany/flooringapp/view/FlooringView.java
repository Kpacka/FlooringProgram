/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.view;

import com.mycompany.flooringapp.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 *
 * @author KylePackard
 */
public class FlooringView {

    UserIo io;

    public FlooringView(UserIo myIo) {
        this.io = myIo;
    }

    public void printTopMenu() {
        io.print("* * * * * * * * * * * * * * * * * *");
        io.print("*       <<Flooring Program>>      *");
    }

    public void printOptions() {
        io.print("* 1. Display Orders               *");
        io.print("* 2. Add Order                    *");
        io.print("* 3. Edit Order                   *");
        io.print("* 4. Remove Order                 *");
        io.print("* 5. Save                         *");
        io.print("* 6. Exit Program                 *");
        io.print("- - - - - - - - - - - - - - - - - -");
    }

    public int returnSelection() {
        return io.readInt("Select Your Option: ", 1, 6);
    }

    public LocalDate getDate() {
        boolean again;
        LocalDate parsed = LocalDate.of(0001, 1, 01);

        do {
            again = false;
            try {
                String dateString = io.readString("Date (yyyy-mm-dd): ");
                parsed = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
            } catch (Exception e) {
                io.print("ERROR-Incorrect Format");
                //possibly actually throw and catch exception
                again = true;
            }
        } while (again);
        return parsed;
    }

    public void printDisplayOrdersMenu() {

        io.print("- - - - - - - - - - - - - - - - - -");
        io.print("*        <<Display Orders>>       *");
        io.print("*             <by date>           *");
        io.print("- - - - - - - - - - - - - - - - - -");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("==========     ERROR     ==========");

        io.print(errorMsg);
    }

    public void printOrderMap(HashMap<String, Order> map) {

        for (String key : map.keySet()) {
            io.print("- - - - - - - - - - - - - - - - - -");
            io.print("- Order Number: " + Integer.toString(map.get(key).getOrderNumber()));
            io.print("- Customer Name: " + map.get(key).getCustomerName());
            io.print("- Product Type: " + map.get(key).getProductType());
            io.print("- State: " + map.get(key).getState());
            io.print("- Area (ft^2): " + map.get(key).getArea().toString());
            io.print("- Tax Rate: " + map.get(key).getTaxRate().toString());
            io.print("- Material Cost (ft^2): $" + map.get(key).getCostPerSquareFoot().toString());
            io.print("- Material Cost (total): $" + map.get(key).getMaterialCost().setScale(2, RoundingMode.HALF_EVEN).toString());
            io.print("- Labor Cost (ft^2): $" + map.get(key).getLaborPerSquareFoot().toString());
            io.print("- Labor Cost (total): $" + map.get(key).getLaborCost().setScale(2, RoundingMode.HALF_EVEN).toString());
            io.print("- Total Tax: $" + map.get(key).getTotalTax().setScale(2, RoundingMode.HALF_EVEN).toString());
            io.print("- Total Price: $" + map.get(key).getTotalPrice().setScale(2, RoundingMode.HALF_EVEN).toString());

        }

    }

    public void printDateForOrders(LocalDate date) {
        io.print("- - - - - - - - - - - - - - - - - -");
        io.print("*  <<All orders for " + date.format(DateTimeFormatter.ISO_DATE) + ">>  *");
    }

    public Order getNewOrder() {
        Order newOrder = new Order();
        io.print("- - - - - - - - - - - - - - - - - -");
        io.print("- -       << NEW ORDER >>       - -");
        io.print("        <Enter Information>        ");
        newOrder.setCustomerName(io.readString("Customer Name: "));
        newOrder.setProductType(io.readString("Product Type: "));
        newOrder.setState(io.readString("State: "));
        newOrder.setArea(new BigDecimal(io.readString("Area (ft^2): ")));
        io.print("- - - - - - - - - - - - - - - - - -");
        return newOrder;

    }

    public void printNewOrder(Order order) {
        io.print("Order Number: " + Integer.toString(order.getOrderNumber()));
        io.print("Total Material Cost: $" + order.getMaterialCost().setScale(2, RoundingMode.HALF_EVEN).toString());
        io.print("Total Labor Cost: $" + order.getLaborCost().setScale(2, RoundingMode.HALF_EVEN).toString());
        io.print("Total Taxes: $" + order.getTotalTax().setScale(2, RoundingMode.HALF_EVEN).toString());

        BigDecimal price = order.getTotalPrice();
        BigDecimal displayPrice = price.setScale(2, RoundingMode.HALF_EVEN);

        io.print("Total Price: $" + displayPrice.toString());
    }

    public int getOrderNumber() {
        return io.readInt("Order Number: ");

    }

    public int getOrderForUpdate(HashMap<String, Order> map) {
        int i = 0;
        for (String key : map.keySet()) {
            io.print("Order Number: " + map.get(key).getOrderNumber());
        }

        int pick = io.readInt("Pick which Order: ");
        io.print("- - - - - - - - - - - - - - - - - -");
        return pick;
    }

    public Order updateOrder(Order order) {

        Order newOrder = new Order();
        String name = io.readString("Customer Name (" + order.getCustomerName() + "): ");
        if (!name.equals("")) {
            newOrder.setCustomerName(name);
        } else {
            newOrder.setCustomerName(order.getCustomerName());

        }

        String state = io.readString("State (" + order.getState() + "): ");
        if (!state.equals("")) {
            newOrder.setState(state);
        } else {
            newOrder.setState(order.getState());
        }

        String product = io.readString("Product Type (" + order.getProductType() + "): ");
        if (!product.equals("")) {
            newOrder.setProductType(product);
        } else {
            newOrder.setProductType(order.getProductType());
        }

        String area = io.readString("Area (" + order.getArea() + "): ");
        if (!area.equals("")) {
            newOrder.setArea(new BigDecimal(area));

        } else {
            newOrder.setArea(order.getArea());
        }

        newOrder.setOrderNumber(order.getOrderNumber());
        io.print("- - - - - - - - - - - - - - - - - -");
        return newOrder;
    }

    public boolean getYesOrNo() {
        String answer = io.readString("Are you sure? (y/n): ");
        return answer.equals("y") || answer.equals("Y");
    }

    public void printNotDeleted() {
        io.print("Order Not Deleted");
    }

    public void printDeleteMenu() {
        io.print("- - - - - - - - - - - - - - - - - -");
        io.print("*         <<Delete Order>>        *");
        io.print("*           <Enter date>          *");
        io.print("- - - - - - - - - - - - - - - - - -");
    }

    public void printGoodbye() {
        io.print("GoodBye!");
    }

    public void printEditMenu() {
        io.print("- - - - - - - - - - - - - - - - - -");
        io.print("*          <<Edit Order>>         *");
        io.print("*           <Enter date>          *");
        io.print("- - - - - - - - - - - - - - - - - -");
    }

    public void printSaved() {
        io.print("Saved Successful");
    }
}
