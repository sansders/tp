package ui;

import order.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionUi extends Ui {

    public void promptItemQuantity() {
        System.out.println("Please enter the quantity of the item: ");
    }

    public void promptMoreOrderEntries() {
        System.out.println("Do you have more items to add? (yes/no/cancel)");
    }

    public void promptPayment() {
        System.out.println("Please use /pay command to add payment for the order.");
    }

    public void printItemNotFound() {
        System.out.println("The entered item cannot be found.");
    }

    public void printChangeGiven(Double change) {
        System.out.printf("The calculated change is $%.2f.\n", change);
    }

    public void printOrderList(ArrayList<Order> orders) {

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("================================================");

        for (int i = 0; i < orders.size(); i++) {

            System.out.println("Order " + (i + 1));
            System.out.println("Order ID: " + orders.get(i).getOrderId());
            System.out.println("Order status: " + orders.get(i).getStatus());
            System.out.println("Order time: " + orders.get(i).getFormatDateTime());

            for (int j = 0; j < orders.get(i).getOrderEntries().size(); j++) {
                System.out.println((j + 1) + ". "
                        + orders.get(i).getOrderEntries().get(j).getItem().getName()
                        + " x" + orders.get(i).getOrderEntries().get(j).getQuantity());
            }

            String subtotal = df.format(orders.get(i).getSubTotal());
            System.out.println("\nSubtotal: $" + subtotal);
            System.out.println("================================================");

        }
    }

    public void printOrderAdded(Double total) {
        DecimalFormat df = new DecimalFormat("#.00");
        String subtotal = df.format(total);
        System.out.println("\nSubtotal: $" + subtotal);
        System.out.println("Order has been added successfully. Total amount: $" + subtotal);
    }


//    public void printMissingOrderArgumentError() {
//        System.out.println("Please enter item index or quantity after flags.");
//    }
//
//    public void printInvalidOrderFlagError() {
//        System.out.println("Please use correctly formatted flags to add order.");
//    }
//
//    public void printInvalidOrderQuantityError() {
//        System.out.println("Quantity must be more than 0.");
//    }
//
//    public void printInvalidMultipleFormatError() {
//        System.out.println("Wrong format to add multiple orders.");
//    }
//
//    public void printNegativeIntegerError() {
//        System.out.println("Please enter positive numbers only.");
//    }
//
//    public void printMissingOrderIdError() {
//        System.out.println("Please enter the order ID to refund.");
//    }
//
//    public void printInvalidOrderIdError() {
//        System.out.println("Please enter a valid order ID to refund.");
//    }
//
//    public void printDuplicateRefundRequestError() {
//        System.out.println("Order is already refunded.");
//    }
//
//    public void printInvalidPaymentError() {
//        System.out.println("Invalid payment command");
//    }
//
//    public void printMissingPaymentFlagError() {
//        System.out.println("Please use correctly formatted flags to add payment.");
//    }
//
//    public void printMissingPaymentArgumentError() {
//        System.out.println("Please enter both the payment type and amount received.");
//    }

    public void printInsufficientAmountError() {
        System.out.println("Insufficient amount. Payment amount must be more than or equals to subtotal.");
    }

//    public void printInvalidPaymentTypeError() {
//        System.out.println("Please enter a valid payment type (Card/Cash/Others).");
//    }
//
//    public void printItemNotExistError() {
//        System.out.println("No such item exists.");
//    }
//
//    public void printSimilarItemError() {
//        System.out.println("Your input referenced multiple similar items. " +
//                "Please try again with a more specific item name.");
//    }

    public void helpAddOder(){
        System.out.println("/addorder -i <number> -q <number>");
    }

    @Override
    public void printError(Flags.Error error) {
        System.out.print("Error: ");
        switch (error) {
        case INPUT_EMPTY:
            System.out.println("Input is empty. Please enter something.");
            break;
        case DOUBLE_OVERFLOW:
            System.out.println("Double overflow! Please enter a double within the valid range.");
            break;
        case INTEGER_OVERFLOW:
            System.out.println("Integer overflow! Please enter an integer within the valid range.");
            break;
        case ARGUMENT_NOT_FOUND:
            System.out.println("Please enter item index or quantity after flags.");
            break;
        case ARGUMENT_INVLAID:
            System.out.println("Please use correctly formatted flags to add order.");
            break;
        case QUANTITY_INVLAID:
            System.out.println("Quantity must be more than 0.");
            break;
        case MULTIPLE_ORDER_FORMAT_INVALID:
            System.out.println("Wrong format to add multiple orders.");
            break;
        case NOT_POSITIVE_INTEGER:
            System.out.println("Please enter positive numbers only.");
            break;
        case ORDER_ID_NOT_FOUND:
            System.out.println("Please enter the order ID to refund.");
            break;
        case DUPLICATE_REFUND_REQUEST:
            System.out.println("Order is already refunded.");
            break;
        case PAYMENT_COMMAND_INVALID:
            System.out.println("Invalid payment command");
            break;
        case PAYMENT_FLAG_NOT_FOUND:
            System.out.println("Please use correctly formatted flags to add payment.");
            break;
        case PAYMENT_ARGUMENT_NOT_FOUND:
            System.out.println("Please enter both the payment type and amount received.");
            break;
        case PAYMENT_TYPE_INVALID:
            System.out.println("Please enter a valid payment type (Card/Cash/Others).");
            break;
        case ITEM_NOT_FOUND:
            System.out.println("No such item exists.");
            break;
        case DUPLICATE_ITEM_FOUND:
            System.out.println("Your input referenced multiple similar items. " +
                    "Please try again with a more specific item name.");
            break;
        }
    }
}


