import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private ArrayList<Recipe> recipesList;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.recipesList = new ArrayList<>();
    }

    public void start() {
        this.fileReader();
    }

    public void fileReader() {
        System.out.print("File to read: ");
        String fileName = this.scanner.nextLine();

        try (Scanner fileReader = new Scanner(Paths.get(fileName))) {
            ArrayList<String> list = new ArrayList<>();
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();

                list.add(row);

                if (row.isEmpty()) {
                    this.convertListToRecipe(list);
                }
            }
            this.convertListToRecipe(list);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        while (true) {
            System.out.println("Commands:");
            System.out.println("list - lists the recipes");
            System.out.println("stop - stops the program");
            System.out.println("find name - searches recipes by name");
            System.out.println("find cooking time - searches recipes by cooking time");
            System.out.println("find ingredient - searches recipes by ingredient");

            System.out.println("");
            System.out.print("Enter command: ");
            String command = this.scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("list")) {
                System.out.println("");
                System.out.println("Recipes:");
                for (Recipe recipe : recipesList) {
                    System.out.println(recipe);
                }
            }

            if (command.equals("find name")) {
                System.out.print("Searched word: ");
                String word = scanner.nextLine();

                System.out.println("");
                System.out.println("Recipes:");
                for (Recipe recipe : recipesList) {
                    if (recipe.getName().contains(word)) {
                        System.out.println(recipe);
                    }
                }
            }

            if (command.equals("find cooking time")) {
                System.out.print("Max cooking time: ");
                int maxCookingTime = Integer.valueOf(scanner.nextLine());

                System.out.println("");
                System.out.println("Recipes:");
                for (Recipe recipe : recipesList) {
                    if (recipe.getCookingTime() <= maxCookingTime) {
                        System.out.println(recipe);
                    }
                }
            }

            if (command.equals("find ingredient")) {
                System.out.print("Ingredient: ");
                String searchIngredient = scanner.nextLine();

                System.out.println("");
                System.out.println("Recipes:");
                for (Recipe recipe : recipesList) {
                    if (recipe.getIngrediants().contains(searchIngredient)) {
                        System.out.println(recipe);
                    }
                }
            }

            System.out.println("");
        }
    }

    private void convertListToRecipe(ArrayList<String> input) {
        String recipeName = input.get(0);
        int recipeCookingTime = Integer.valueOf(input.get(1));
        recipesList.add(new Recipe(recipeName, recipeCookingTime));

        int lastAddedRecipe = recipesList.size() - 1;

        Recipe recipeToAddIngrediants = recipesList.get(lastAddedRecipe);

        for (int i = 2; i < input.size(); i++) {
            recipeToAddIngrediants.addIngrediant(input.get(i));
        }
        input.clear();
    }
}
