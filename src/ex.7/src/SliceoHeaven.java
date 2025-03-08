import java.util.Scanner;
import java.time.Period;
import java.time.LocalDate;

public class SliceoHeaven {
    public String storeName;
    public String storeAddress;
    public String storeEmail;
    public String storePhone;
    public String storeMenu;
    public String storeIngredients;

    public double pizzaPrice;
    public String sides;
    public String drinks;

    private String orderID;
    private double orderTotal;
    private String pizzaIngredients;

    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;
    private static final String DEF_SIDES = "Salad";
    private static final String DEF_DRINKS = "Coca-Cola";

    // Blacklisted card number
    private static final long BLACKLISTED_NUMBER = 12345678901234L;

    public SliceoHeaven() {
        orderID = DEF_ORDER_ID;
        pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        orderTotal = DEF_ORDER_TOTAL;
        sides = DEF_SIDES;
        drinks = DEF_DRINKS;
    }

    public SliceoHeaven(String orderID, String pizzaIngredients, double orderTotal, String sides, String drinks) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
        this.sides = sides;
        this.drinks = drinks;
    }

    // Getters and Setters
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(String pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getSides() {
        return sides;
    }

    public void setSides(String sides) {
        this.sides = sides;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    // Updated takeOrder() method
    public void takeOrder(Scanner scanner) {
        // Ingredients selection
        System.out.println("Please pick any three of the following ingredients:\n" +
                "1. Mushroom\n" +
                "2. Paprika\n" +
                "3. Sun-dried tomatoes\n" +
                "4. Chicken\n" +
                "5. Pineapple\n" +
                "Enter any three choices (1, 2, 3,…) separated by spaces:");
    
        int[] ingChoice = new int[3];
        boolean validInput = false;
    
        while (!validInput) {
            String[] input = scanner.nextLine().split(" ");
            if (input.length != 3) {
                System.out.println("Invalid choice(s). Please pick only from the given list:");
                continue;
            }
    
            try {
                for (int i = 0; i < 3; i++) {
                    ingChoice[i] = Integer.parseInt(input[i]);
                    if (ingChoice[i] < 1 || ingChoice[i] > 5) {
                        System.out.println("Invalid choice(s). Please pick only from the given list:");
                        break;
                    }
                }
                validInput = true; // Assume all inputs are valid
                for (int choice : ingChoice) {
                    if (choice < 1 || choice > 5) {
                        validInput = false; // If any choice is invalid, reset flag
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice(s). Please pick only from the given list:");
                validInput = false; // Reset flag if parsing fails
            }
        }
    
        // Convert choices to ingredient names using switch-case
        String ing1 = getIngredientFromChoice(ingChoice[0]);
        String ing2 = getIngredientFromChoice(ingChoice[1]);
        String ing3 = getIngredientFromChoice(ingChoice[2]);
        this.pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;
    
        // Pizza size selection
        System.out.println("What size should your pizza be?\n" +
                "1. Large\n" +
                "2. Medium\n" +
                "3. Small\n" +
                "Enter only one choice (1, 2, or 3):");
        int sizeChoice = 0;
        while (sizeChoice < 1 || sizeChoice > 3) {
            sizeChoice = scanner.nextInt();
            if (sizeChoice < 1 || sizeChoice > 3) {
                System.out.println("Invalid choice. Please enter a valid number:");
            }
        }
        scanner.nextLine(); // Consume newline
        String pizzaSize = getPizzaSizeFromChoice(sizeChoice);
        this.setPizzaPrice(determinePizzaPrice(pizzaSize));
    
        // Extra cheese
        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.nextLine();
        if (extraCheese.equalsIgnoreCase("Y")) {
            this.orderTotal += 2.0;
        }
    
        // Side dish selection
        System.out.println("Following are the side dish that go well with your pizza:\n" +
                "1. Calzone\n" +
                "2. Garlic bread\n" +
                "3. Chicken puff\n" +
                "4. Muffin\n" +
                "5. Nothing for me\n" +
                "What would you like? Pick one (1, 2, 3,…):");
        int sideDishChoice = 0;
        while (sideDishChoice < 1 || sideDishChoice > 5) {
            sideDishChoice = scanner.nextInt();
            if (sideDishChoice < 1 || sideDishChoice > 5) {
                System.out.println("Invalid choice. Please enter a valid number:");
            }
        }
        scanner.nextLine(); // Consume newline
        String sideDish = getSideDishFromChoice(sideDishChoice);
        this.sides = sideDish;
    
        // Drink selection
        System.out.println("Choose from one of the drinks below. We recommend Coca Cola:\n" +
                "1. Coca Cola\n" +
                "2. Cold coffee\n" +
                "3. Cocoa Drink\n" +
                "4. No drinks for me\n" +
                "Enter your choice:");
        int drinkChoice = 0;
        while (drinkChoice < 1 || drinkChoice > 4) {
            drinkChoice = scanner.nextInt();
            if (drinkChoice < 1 || drinkChoice > 4) {
                System.out.println("Invalid choice. Please enter a valid number:");
            }
        }
        scanner.nextLine(); // Consume newline
        String drink = getDrinkFromChoice(drinkChoice);
        this.drinks = drink;
    
        // Payment option
        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.nextLine();
        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday(scanner);
        } else {
            makeCardPayment(scanner);
        }
        scanner.close();
    }
    
    // Helper method to convert ingredient choice to ingredient name
    private String getIngredientFromChoice(int choice) {
        String ingredient;
        switch (choice) {
            case 1:
                ingredient = "Mushroom";
                break;
            case 2:
                ingredient = "Paprika";
                break;
            case 3:
                ingredient = "Sun-dried tomatoes";
                break;
            case 4:
                ingredient = "Chicken";
                break;
            case 5:
                ingredient = "Pineapple";
                break;
            default:
                ingredient = "Unknown";
        }
        return ingredient;
    }
    
    // Helper method to convert pizza size choice to size name
    private String getPizzaSizeFromChoice(int choice) {
        String size;
        switch (choice) {
            case 1:
                size = "Large";
                break;
            case 2:
                size = "Medium";
                break;
            case 3:
                size = "Small";
                break;
            default:
                size = "Unknown";
        }
        return size;
    }
    
    // Helper method to convert side dish choice to side dish name
    private String getSideDishFromChoice(int choice) {
        String sideDish;
        switch (choice) {
            case 1:
                sideDish = "Calzone";
                break;
            case 2:
                sideDish = "Garlic bread";
                break;
            case 3:
                sideDish = "Chicken puff";
                break;
            case 4:
                sideDish = "Muffin";
                break;
            case 5:
                sideDish = "Nothing for me";
                break;
            default:
                sideDish = "Unknown";
        }
        return sideDish;
    }
    
    // Helper method to convert drink choice to drink name
    private String getDrinkFromChoice(int choice) {
        String drink;
        switch (choice) {
            case 1:
                drink = "Coca Cola";
                break;
            case 2:
                drink = "Cold coffee";
                break;
            case 3:
                drink = "Cocoa Drink";
                break;
            case 4:
                drink = "No drinks for me";
                break;
            default:
                drink = "Unknown";
        }
        return drink;
    }
    
    // Helper method to convert ingredient choice to ingredient name
    private String getIngredientFromChoice(int choice) {
        switch (choice) {
            case 1:
                return "Mushroom";
            case 2:
                return "Paprika";
            case 3:
                return "Sun-dried tomatoes";
            case 4:
                return "Chicken";
            case 5:
                return "Pineapple";
            default:
                return "Unknown";
        }
    }

    // Helper method to convert pizza size choice to size name
    private String getPizzaSizeFromChoice(int choice) {
        switch (choice) {
            case 1:
                return "Large";
            case 2:
                return "Medium";
            case 3:
                return "Small";
            default:
                return "Unknown";
        }
    }

    // Helper method to convert side dish choice to side dish name
    private String getSideDishFromChoice(int choice) {
        switch (choice) {
            case 1:
                return "Calzone";
            case 2:
                return "Garlic bread";
            case 3:
                return "Chicken puff";
            case 4:
                return "Muffin";
            case 5:
                return "Nothing for me";
            default:
                return "Unknown";
        }
    }

    // Helper method to convert drink choice to drink name
    private String getDrinkFromChoice(int choice) {
        switch (choice) {
            case 1:
                return "Coca Cola";
            case 2:
                return "Cold coffee";
            case 3:
                return "Cocoa Drink";
            case 4:
                return "No drinks for me";
            default:
                return "Unknown";
        }
    }

    // Updated isItYourBirthday() method
    public void isItYourBirthday(Scanner scanner) {
        System.out.println("Enter your birthday (yyyy-mm-dd):");
        LocalDate today = LocalDate.now();
        boolean validDate = false;

        while (!validDate) {
            String birthdate = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdate);
            int age = Period.between(birthday, today).getYears();

            if (age < 5 || age > 120) {
                System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
            } else {
                validDate = true;
                if (age < 18 && birthday.getDayOfYear() == today.getDayOfYear()) {
                    System.out.println("Congratulations! You pay only half the price for your order");
                } else {
                    System.out.println("Too bad! You do not meet the condition to get our 50% discount");
                }
            }
        }
    }

    // Updated makeCardPayment() method
    public void makeCardPayment(Scanner scanner) {
        boolean validCard = false;
        while (!validCard) {
            System.out.println("Enter your card number:");
            long cardNumber = scanner.nextLong();
            scanner.nextLine();

            if (Long.toString(cardNumber).length() != 14 || cardNumber == BLACKLISTED_NUMBER) {
                System.out.println("Invalid card or blacklisted. Please enter a valid card number:");
            } else {
                validCard = true;
                System.out.println("Card accepted");
            }
        }

        boolean validExpiry = false;
        while (!validExpiry) {
            System.out.println("Enter the card's expiry date (mm/yy):");
            String expiryDate = scanner.next();
            scanner.nextLine();

            if (!isValidExpiryDate(expiryDate)) {
                System.out.println("Invalid expiry date. Please enter a future date:");
            } else {
                validExpiry = true;
            }
        }

        System.out.println("Enter the card's CVV number:");
        int cvv = scanner.nextInt();
        scanner.nextLine();
    }

    // Helper method to validate expiry date
    private boolean isValidExpiryDate(String expiryDate) {
        try {
            int month = Integer.parseInt(expiryDate.substring(0, 2));
            int year = Integer.parseInt(expiryDate.substring(2, 4));
            LocalDate now = LocalDate.now();
            int currentYear = now.getYear() % 100; // Get last two digits of current year
            int currentMonth = now.getMonthValue();

            return year > currentYear || (year == currentYear && month >= currentMonth);
        } catch (Exception e) {
            return false;
        }
    }

    // Updated toString() method
    @Override
    public String toString() {
        return "********RECEIPT********\n" +
                "Order ID: " + getOrderID() + "\n" +
                "Order Total: $" + getOrderTotal();
    }

    // Dummy method for determining pizza price
    private double determinePizzaPrice(String size) {
        return 10.99;
    }

    // Special of the day method (unchanged)
    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, double specialPrice) {
        System.out.println("Today's special: " + pizzaOfTheDay);
        System.out.println("Side of the day: " + sideOfTheDay);
        System.out.println("Special price: $" + specialPrice);
    }

    // Main method
    public static void main(String[] args) {
        SliceoHeaven pizzaStore = new SliceoHeaven();
        pizzaStore.storeIngredients = "Mushrooms, Onions, Pepperoni";
        pizzaStore.setPizzaPrice(10.99);
        pizzaStore.setSides("Garlic Bread, Caesar Salad");
        pizzaStore.setDrinks("Coca-Cola, Pepsi, Water");

        try (Scanner scanner = new Scanner(System.in)) {
            pizzaStore.takeOrder(scanner);
            System.out.println(pizzaStore); // Using toString() to print receipt
        }
    }
}
