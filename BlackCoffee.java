/**
 * Implementation of the Coffee interface for Black Coffee.
 */
import java.util.List;
import java.util.ArrayList;

public class BlackCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.0;
    }
    /**
     * Returns the list of ingredients for Black Coffee.
     *
     * @return the list of ingredients for Black Coffee
     */
    @Override
    public List<String> getIngredients() {
          List<String> ingredients = new ArrayList<String>();
          ingredients.add("Black Coffee");
          return ingredients;
    }

    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}
