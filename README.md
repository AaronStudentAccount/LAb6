# LAb6
This application allows you to create different types of coffee and calculate their cost.

You can run this by downloading all the files listed and running it in you IDE of choice. Click run on Main.java and things should work.


- Main.java: The main method of the program.Takes in user input to place coffee orders, manage inventory, update order logs,
 and ending the order process. Also contains methods for reading and writing Inventory and orderLogs.
- Coffee.java: This interface defines the common methods for all types of coffee.
- CoffeeDecorator: This abstract class implements the Coffee interface and serves as a base class for coffee decorators.
- CoffeeOrder: This class constructs a new CoffeeOrder, calculates total of the order and prints receipt of all items and final cost.
- BlackCoffee.java: This class implements the Coffee interface for black coffee. It has methods to get the cost and ingredients of the coffee.
- Espresso.java: This class implements the Coffee interface for espresso coffee. It has methods to get the cost and ingredients of the coffee.
- WithFlavor.java: This class implements the Coffee interface for espresso coffee. It has methods to get the cost and ingredients of the coffee.
- WithHotWater.java: This class implements the Coffee interface for the additive "Hot Water" for your coffee. It has methods to get the cost and ingredients.
- WithMilk.java: This class implements the Coffee interface for the additive "Milk" for your coffee. It has methods to get the cost and ingredients.
- WithSugar.java: This class implements the Coffee interface for the additive "Sugar" for your coffee. It has methods to get the cost and ingredients.
- WithWhippedCream:  This class implements the Coffee interface for the additive "Whipped Cream" for your coffee. It has methods to get the cost and ingredients.
- Statistics.java:  This class is not yet implemented but would print statistics about your order like total cost and average cost.
- Ingredients.java: An enumeration of all ingredients that are not yet implemented
- Inventory.txt: This file is the inventory and needs to be moved to outside the src file in you IDE.
