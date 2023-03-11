package order;

import app.Command;
import item.ItemList;
import utility.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class Order implements OrderInterface {

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<OrderEntry> orderEntries;
    private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");

    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = new ArrayList<>();
    }

    public Order(ArrayList<OrderEntry> orderEntries) {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = orderEntries;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime.format(FORMATTER);
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(ArrayList<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    @Override
    public double getSubTotal() {

        double subtotal = 0;

        for (OrderEntry i : this.orderEntries) {
            subtotal += (i.getQuantity() * i.getItem().getPrice());
        }

        return subtotal;

    }

    public void addOrder(Command command, Parser parser, ItemList listOfItems) {

        command.mapArgumentAlias("item", "i");
        command.mapArgumentAlias("items", "I");

        if (command.getArgumentMap().get("item") != null) {
            handleAddOrder(command, listOfItems);
        } else {
            handleMultipleAddOrders(command, listOfItems);
        }

    }

    public void handleAddOrder(Command command, ItemList listOfItems) {

        command.mapArgumentAlias("item", "i");
        command.mapArgumentAlias("quantity", "q");

        int itemIndex = Integer.parseInt(command.getArgumentMap().get("item").trim());
        int quantity;

        // Checks if quantity of orderEntry is specified
        // If not specified, it defaults to 1
        if (command.getArgumentMap().get("quantity") != null) {
            quantity = Integer.parseInt(command.getArgumentMap().get("quantity").trim());
        } else {
            quantity = 1;
        }

        OrderEntry orderEntry = new OrderEntry(listOfItems.getItems().get(itemIndex), quantity);
        this.orderEntries.add(orderEntry);

    }

    public void handleMultipleAddOrders(Command command, ItemList listOfItems) {

        command.mapArgumentAlias("items", "I");

        String[] ordersArguments = command.getArgumentMap().get("items").split(",");

        for (String orderString : ordersArguments) {

            if (orderString.charAt(0) == '[') {
                orderString = orderString.substring(1);
            }

            if (orderString.substring(orderString.length() - 1).equals("]")) {
                orderString = orderString.substring(0, orderString.length() - 1);
            }

            orderString = orderString.trim();
            int itemIndex = Integer.parseInt(orderString.split(" ")[0]);
            int quantity = Integer.parseInt(orderString.split(" ")[1]);

            OrderEntry orderEntry = new OrderEntry(listOfItems.getItems().get(itemIndex), quantity);
            this.orderEntries.add(orderEntry);
        }

    }
}
