public class SliceoHeaven {
    private String storeName = "Slice-o-Heaven";
    private String storeAddress = "123 Pizza Lane, Pizza City";
    private String storeEmail = "info@sliceoheaven.com";
    private String storePhone = "555-1234";
    private String storeMenu = "Pepperoni, Margherita, BBQ Chicken, Veggie Delight";
    private String pizzaIngredients = "Dough, Tomato Sauce, Cheese, Toppings";
    private double pizzaPrice = 10.99;
    private String sides = "Garlic Bread, Salad, Fries";
    private String drinks = "Soda, Water, Juice";
    private int orderID = 1;
    private double orderTotal;

    public void takeOrder() {
        System.out.println("Taking order...");
        System.out.println("Order ID: " + orderID);
        System.out.println("Order Details:");
        System.out.println("Pizza: " + storeMenu.split(",")[0]);
        System.out.println("Sides: " + sides.split(",")[0]);
        System.out.println("Drinks: " + drinks.split(",")[0]);
        
        orderTotal = pizzaPrice + 2.99 + 1.99;
        orderID++;
    }

    public void makePizza() {
        System.out.println("\nMaking pizza...");
        System.out.println("Using ingredients: " + pizzaIngredients);
        System.out.println("Pizza is ready!");
    }

    public void printReceipt() {
        System.out.println("\nPrinting receipt...");
        System.out.println("Store Name: " + storeName);
        System.out.println("Store Address: " + storeAddress);
        System.out.println("Store Email: " + storeEmail);
        System.out.println("Store Phone: " + storePhone);
        System.out.println("Order Total: $" + orderTotal);
        System.out(".printlnThank you for your order!");
    }

    public static void main(String[] args) {
        SliceoHeaven pizzeria = new SliceoHeaven();

        pizzeria.takeOrder();
        pizzeria.makePizza();
        pizzeria.printReceipt();
    }
}