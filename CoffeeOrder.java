import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;

    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }

    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<Coffee>();
        this.orderDate = orderDate;
    }

    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    public List<Coffee> getCoffees() { return coffees; }

    public LocalDateTime getOrderDate() { return orderDate; }

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

            if (discountCode.equals("DISCOUNTCODE")) {
                total = total * 0.9;
            }
                else if (discountCode.equals("N")) {
                System.out.println("No discount Applied ");
            } else {
                System.out.println("Invalid discount code.");
            }
        }
        return total;
    }

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
