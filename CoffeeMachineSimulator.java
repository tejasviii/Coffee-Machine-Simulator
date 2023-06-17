import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachineSimulator {
    private int water;
    private int milk;
    private int beans;
    private int money;
    private Map<String, Integer> coffeesSold;

    public CoffeeMachineSimulator() {
        water = 0;
        milk = 0;
        beans = 0;
        money = 0;
        coffeesSold = new HashMap<>();
        coffeesSold.put("Espresso", 0);
        coffeesSold.put("Latte", 0);
        coffeesSold.put("Cappuccino", 0);
    }

    public void buyCoffee(String coffeeType) {
        int waterRequired, milkRequired, beansRequired, price;

        switch (coffeeType) {
            case "Espresso":
                waterRequired = 250;
                milkRequired = 0;
                beansRequired = 16;
                price = 4;
                break;
            case "Latte":
                waterRequired = 350;
                milkRequired = 75;
                beansRequired = 20;
                price = 7;
                break;
            case "Cappuccino":
                waterRequired = 200;
                milkRequired = 100;
                beansRequired = 12;
                price = 6;
                break;
            default:
                System.out.println("Invalid coffee type!");
                return;
        }

        if (water < waterRequired || milk < milkRequired || beans < beansRequired) {
            System.out.println("Insufficient ingredients to make the coffee!");
        } else {
            System.out.println("Preparing coffee...");
            water -= waterRequired;
            milk -= milkRequired;
            beans -= beansRequired;
            money += price;
            coffeesSold.put(coffeeType, coffeesSold.get(coffeeType) + 1);
            System.out.println("Here's your " + coffeeType + "! Enjoy!");
        }
    }

    public void fillIngredients(int waterAmount, int milkAmount, int beansAmount) {
        water += waterAmount;
        milk += milkAmount;
        beans += beansAmount;
    }

    public void takeMoney() {
        System.out.println("Taking money from the money box: $" + money);
        money = 0;
    }

    public void showIngredients() {
        System.out.println("Current ingredient levels:");
        System.out.println("Water: " + water + " ml");
        System.out.println("Milk: " + milk + " ml");
        System.out.println("Beans: " + beans + " g");
    }

    public void showAnalytics() {
        int totalCoffeesSold = coffeesSold.values().stream().mapToInt(Integer::intValue).sum();
        int totalEarnings = money;
        int totalWaterConsumed = coffeesSold.get("Espresso") * 250 +
                coffeesSold.get("Latte") * 350 +
                coffeesSold.get("Cappuccino") * 200;
        int totalMilkConsumed = coffeesSold.get("Latte") * 75 +
                coffeesSold.get("Cappuccino") * 100;
        int totalBeansConsumed = coffeesSold.get("Espresso") * 16 +
                coffeesSold.get("Latte") * 20 +
                coffeesSold.get("Cappuccino") * 12;

        System.out.println("Coffee Machine Analytics:");
        System.out.println("Total coffees sold: " + totalCoffeesSold);
        System.out.println("Total earnings: $" + totalEarnings);
        System.out.println("Total water consumed: " + totalWaterConsumed + " ml");
        System.out.println("Total milk consumed: " + totalMilkConsumed + " ml");
        System.out.println("Total beans consumed: " + totalBeansConsumed + " g");
    }

    public static void main(String[] args) {
        CoffeeMachineSimulator coffeeMachine = new CoffeeMachineSimulator();
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n==== Coffee Machine Simulator ====");
            System.out.println("1. Buy a coffee");
            System.out.println("2. Fill ingredients");
            System.out.println("3. Take money");
            System.out.println("4. Show ingredients");
            System.out.println("5. Analytics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter coffee type (Espresso, Latte, Cappuccino): ");
                    String coffeeType = scanner.nextLine();
                    coffeeMachine.buyCoffee(coffeeType);
                    break;
                case "2":
                    System.out.print("Enter the amount of water to add (in ml): ");
                    int waterAmount = scanner.nextInt();
                    System.out.print("Enter the amount of milk to add (in ml): ");
                    int milkAmount = scanner.nextInt();
                    System.out.print("Enter the amount of beans to add (in g): ");
                    int beansAmount = scanner.nextInt();
                    coffeeMachine.fillIngredients(waterAmount, milkAmount, beansAmount);
                    break;
                case "3":
                    coffeeMachine.takeMoney();
                    break;
                case "4":
                    coffeeMachine.showIngredients();
                    break;
                case "5":
                    coffeeMachine.showAnalytics();
                    break;
                case "6":
                    System.out.println("Exiting the simulation...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (!choice.equals("6"));

        scanner.close();
    }
}
