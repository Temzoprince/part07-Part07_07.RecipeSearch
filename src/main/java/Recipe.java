import java.util.ArrayList;

public class Recipe {
    private String name;
    private int cookingTime;
    private ArrayList<String> ingrediants;

    public Recipe(String name, int cookingTime) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingrediants = new ArrayList<>();
    }

    public void addIngrediant(String ingrediant) {
        ingrediants.add(ingrediant);
    }

    public String getName() {
        return this.name;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public ArrayList<String> getIngrediants() {
        return this.ingrediants;
    }

    @Override
    public String toString() {
        return this.name + ", cooking time: " + this.cookingTime;
    }
}
