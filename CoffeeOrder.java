/**
 * Constructs a new CoffeeOrder, calculates total of the order
 * and prints receipt of all items and final cost.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;
    private static final String[] DISCOUNT_CODE = {"DISCOUNTCODE", "CREATORCODE", "SEASONAL10"};
    /**
     * Constructs a new CoffeeOrder with the current date and time as the order date.
     */
    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }
    /**
     * Constructs a new CoffeeOrder with the specified order date.
     *
     * @param orderDate (the order date)
     */
    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<Coffee>();
        this.orderDate = orderDate;
    }

    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    public List<Coffee> getCoffees() { return coffees; }

    public LocalDateTime getOrderDate() { return orderDate; }
    /**
     * Calculates the total cost of the order, taking into account the potential discount.
     *
     * @return the total cost of the order
     */
    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }

        if (total > 15) {
            System.out.println("You are eligible for a 10% discount on your order using our discount code!");
            System.out.println("Enter discount code or type \"N\" to decline:");

            Scanner input = new Scanner(System.in);
            String discountCode = input.nextLine();
            boolean isDiscountApplied = false;
            for (String code : DISCOUNT_CODE) {
                if (discountCode.equals(code)) {
                    total = total * 0.9;
                    isDiscountApplied = true;
                    break;
                }


            }
            if (!isDiscountApplied && !discountCode.equals("N")) {
                System.out.println("Invalid discount code.");

            }
        }

        return total;
    }
    /**
     * Generates a string of the order receipt of coffees.
     *
     * @return the full order receipt as a string
     */
    public String printOrder() {
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"))));
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }

        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        return order.toString();
    }


}
