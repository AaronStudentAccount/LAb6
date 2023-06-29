/*  Lab 6
 *  This program is a coffee ordering system that allows users to place new orders,
 * update the inventory, and view order logs. Additionally, it keeps track of the
 * inventory of coffee ingredients and the list of coffee orders. Such orders are displayed
 * at the end of the order along with an optional discount.
 *  CS160-1001
 *  @author   Aaron Johnson
 *  Date      6/28/2023
 *  @version  17.0.6
 *  @since    17.0.6
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * The main method of the program.Takes in user input to place coffee orders, manage inventory, update order logs,
 * and ending the order process
 *
 * @param args
 */

public class Main {
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>();
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt";

    public static void main(String[] args) {

        inventory = readInventory(inventoryFile);
        System.out.println(inventory);
        System.out.println("Welcome to Java Coffee Co.!");
        System.out.println("Try out discount code to get 10% any order $15 or more!");

        try (Scanner input = new Scanner(System.in)) {


            boolean exitProgram = false;
            while (!exitProgram) {
                System.out.println("\nPlease select an option:");
                System.out.println("\t1. New Order");
                System.out.println("\t2. Reload Inventory");
                System.out.println("\t3. Update Inventory");
                System.out.println("\t4. Update Order Log");
                System.out.println("\t5. Exit Application");

                int option = 0;
                while (option < 1 || option > 5) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 5) System.out.println("Please enter a valid option.");
                    }
                }

                input.nextLine();

                switch (option) {
                    case 1 -> {

                        CoffeeOrder order = buildOrder();
                        orders.add(order);
                        System.out.println(order.printOrder());
                    }
                    case 2 -> {
                        inventory = readInventory(inventoryFile);
                        System.out.println("Current Inventory:");

                        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                            System.out.println(String.format("\t%s: %d", entry.getKey(), entry.getValue()));
                        }
                    }
                    case 3 -> writeInventory(inventoryFile);
                    case 4 -> writeOrderLog(logFile);
                    case 5 -> {
                        exitProgram = true;
                        if (orders.size() > 0) {
                            writeInventory(inventoryFile);
                            writeOrderLog(logFile);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    /**
     * Method builds a coffee order by taking user input for coffee type and additional ingredients.
     *
     * @return the created CoffeeOrder object
     */
    private static CoffeeOrder buildOrder() {


        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            while (addCoffee) {

                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                Coffee coffee;

                int option = 0;
                while (option < 1 || option > 2) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2) System.out.println("Please enter a valid option.");
                    }
                }
                input.nextLine();
                if (option == 2) {


                    if (isInInventory("Espresso")) {
                        inventory.put("Espresso", inventory.get("Espresso") - 1);
                    } else {
                        System.out.println("Out of Espresso.");
                        continue;
                    }
                     coffee = new Espresso();
                } else {

                    if (isInInventory("Black Coffee")) {
                        coffee = new BlackCoffee();
                        inventory.put("Black Coffee", inventory.get("Black Coffee") - 1);
                    } else {
                        System.out.println("Out of Black Coffee.");
                        continue;
                    }
                }

                while (option != 0) {
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    try {
                        option = input.nextInt();
                        input.nextLine();
                        coffee = switch (option) {
                            case 1 -> {
                                if (isInInventory("CARAMEL Syrup") || isInInventory("MOCHA Syrup") || isInInventory("VANILLA Syrup")) {
                                    System.out.println("Please select a flavor:");
                                    for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                        System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                                    }
                                    int max = WithFlavor.Syrup.values().length;
                                    option = 0;
                                    while (option < 1 || option > max) {
                                        if (!input.hasNextInt()) {
                                            System.out.println("Please enter a valid number.");
                                            input.nextLine();
                                        } else {
                                            option = input.nextInt();
                                            if (option < 1 || option > max)
                                                System.out.println("Please enter a valid option.");
                                        }
                                    }

                                    input.nextLine();
                                    WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                                    inventory.put("Syrup", inventory.get("Syrup") - 1);
                                    yield new WithFlavor(coffee, flavor);

                                } else {
                                    System.out.println("Out of Syrup.");
                                    yield coffee;

                                }
                            }

                            case 2 -> {
                                if (isInInventory("Hot Water")) {
                                    inventory.put("Hot Water", inventory.get("Hot Water") - 1);
                                    yield new WithHotWater(coffee);
                                } else {
                                    System.out.println("Out of Hot Water.");
                                    yield coffee;
                                }
                            }

                            case 3 -> {
                                if (isInInventory("Milk")) {
                                    inventory.put("Milk", inventory.get("Milk") - 1);
                                    yield new WithMilk(coffee);
                                } else {
                                    System.out.println("Sorry, we are out of Milk.");
                                    yield coffee;
                                }
                            }


                            case 4 -> {
                                if (isInInventory("Sugar")) {
                                    inventory.put("Sugar", inventory.get("Sugar") - 1);
                                    yield new WithSugar(coffee);
                                } else {
                                    System.out.println("Sorry, we are out of Sugar.");
                                    yield coffee;
                                }
                            }

                            case 5 -> {
                                if (isInInventory("Whipped Cream")) {
                                    inventory.put("Whipped Cream", inventory.get("Whipped Cream") - 1);
                                    yield new WithWhippedCream(coffee);
                                } else {
                                    System.out.println("Sorry, we are out of Whipped Cream.");
                                    yield coffee;
                                }
                            }

                            case 0 -> {
                                order.addCoffee(coffee);

                                System.out.println("Would you like to order another coffee (Y or N)?");
                                String yn = input.nextLine();
                                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                                    System.out.println("Please enter Y or N.");
                                    yn = input.nextLine();
                                }
                                addCoffee = !yn.equalsIgnoreCase("N");
                                //addCoffee = !yn.equalsIgnoreCase("Y");
                                yield coffee;
                            }

                            default -> {
                                if (option != 0) {
                                    System.out.println("Please enter valid option.");
                                }
                                yield coffee;

                            }
                        };
                    } catch (Exception e) {
                        System.out.println("Error building order: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }

    /**
     * Method reads the current inventory of items in Inventory.txt file
     *
     * @param filePath the path of the inventory file
     * @return the created Inventory map
     */
    private static Map<String, Integer> readInventory(String filePath) {
        Map<String, Integer> inventory = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String lineInput;
            while ((lineInput = reader.readLine()) != null) {

                String[] parts = lineInput.split("=");
                String ingredientName = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());
                inventory.put(ingredientName, quantity);
            }
            System.out.println("Success: Inventory loaded.");

        } catch (Exception e) {
            System.out.println("Error reading inventory: " + e.getMessage());
        }
        return inventory;
    }
    /**
     * Writes the inventory to Inventory.txt.
     * @param filePath the path of the inventory file
     */
    private static void writeInventory(String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.write(entry.getKey() + "= " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Success: Inventory saved.");
        } catch (Exception e) {
            System.out.println("Error writing inventory: " + e.getMessage());
        }
    }
    /**
     * Reads the order log from OrderLog.txt.
     * @param filePath the path of the order log file
     * @return a list of CoffeeOrder objects as orderLog
     */

    private static List<CoffeeOrder> readOrderLog(String filePath) {
        List<CoffeeOrder> orderLog = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            CoffeeOrder order = null;
            while ((line = reader.readLine()) != null) {

                 order = new CoffeeOrder();
                String[] coffees = line.split("=");
                for (String coffee : coffees) {

                }
                orderLog.add(order);
            }
            System.out.println("Success: Order log loaded.");
        } catch (Exception e) {
            System.out.println("Error reading order log: " + e.getMessage());
        }
        return orderLog;
    }

    /**
     * Writes the order log to OrderLog.txt.
     * @param filePath the path of the order log file
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            System.out.println("Success: Order log saved.");

        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }
    /**
     * Checks if an ingredient is available in the inventory file .
     * @param ingredient (name of ingredient)
     * @return true if the ingredient is available, false otherwise
     */
    private static boolean isInInventory(String ingredient) {

        if (!inventory.containsKey(ingredient)) {
            return false;
        }
        int quantity = inventory.get(ingredient);
        return quantity > 0;
    }
    /**
     * Updates the order log file.
     * @param filePath the path of the order log file
     */
    private static void updateOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            System.out.println("Success: Order log saved.");
            orders.clear();
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());

        }
    }
}