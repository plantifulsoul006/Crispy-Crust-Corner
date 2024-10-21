import java.util.*;

public class AzharsCrispyCrustCorner { 
    public static void main(String[] args) throws PizzaNotFoundException {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;
        PizzaStore pizzaStore = new PizzaStore(); 
        PizzaService pizzaService = new PizzaService(pizzaStore);
        System.out.println("Welcome to Azhar's Crispy Crust Corner!!!");
        System.out.println("Choose your role to login (1/2): ");

        while(isRunning) {
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            String userType = in.nextLine();
            if (userType.equalsIgnoreCase("1")) {
                System.out.println("Welcome to admin console!");
                boolean isAdminLoggedIn = true;
                while (isAdminLoggedIn) {
                    System.out.println("1. Add Pizza");
                    System.out.println("2. Update Price");
                    System.out.println("3. Delete Pizza");
                    System.out.println("4. View All Pizza");
                    System.out.println("5. Search Pizza");
                    System.out.println("6. Exit");
                    System.out.println("Select an option: ");
                    int choice = -1;
                    try {
                        choice = in.nextInt();
                        in.nextLine(); 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        in.nextLine(); 
                        continue; 
                    }

                    switch (choice) {
                        case 1:
                            System.out.println("~Add New Pizza Menu~");
                            System.out.println("Enter pizza ID: ");
                            int pizzaId = in.nextInt();
                            in.nextLine(); 
                            if (pizzaService.isPizzaIdExists(pizzaId)) {
                                System.out.println("A pizza with ID "+ pizzaId +" already exists. Please try again!");
                                break; 
                            }
                            System.out.println("Enter pizza name: ");
                            String pizzaName = in.nextLine();
                            System.out.println("Enter pizza price: ");
                            double pizzaPrice = in.nextDouble();
                            in.nextLine(); 
                            System.out.println("Enter pizza size: ");
                            String pizzaSize = in.nextLine();
                            
                            System.out.println("Enter topping details:");
                            System.out.println("Enter topping name: ");
                            String toppingName = in.nextLine();
                            System.out.println("Enter topping spice level: ");
                            String spiceLevel = in.nextLine();
                            System.out.println("Enter topping description: ");
                            String toppingDescription = in.nextLine();
                            
                            Topping topping = new Topping(toppingName, spiceLevel, toppingDescription);
                            
                            System.out.println("Enter pizza base details:");
                            System.out.println("Enter base name: ");
                            String baseName = in.nextLine();
                            System.out.println("Enter base type: ");
                            String baseType = in.nextLine();
                            System.out.println("Enter base description: ");
                            String baseDescription = in.nextLine();
                            
                            PizzaBase pizzaBase = new PizzaBase(baseName, baseType, baseDescription);
                           
                            Pizza pizza = new Pizza(pizzaId, pizzaName, pizzaPrice, pizzaSize, topping, pizzaBase);

                            pizzaService.addNewPizza(pizza);
                            pizzaStore.addPizza(pizza);
                            System.out.println("Pizza added successfully!");
                            System.out.println("Pizza Details: " + pizza.toString());
                            break;

                        case 2:
                            System.out.println("~Update Pizza Menu~");
                            System.out.println("Enter the name of pizza to update: ");
                            String pizzaNameToUpdate = in.nextLine();
                            try {
                                Pizza pizzaToUpdate = pizzaService.getPizzaByName(pizzaNameToUpdate);
                                System.out.println("Enter the new price: ");
                                double newPrice = in.nextDouble();
                                in.nextLine();
                                pizzaService.updatePrice(pizzaToUpdate, newPrice);
                                System.out.println("Pizza price updated successfully!");
                                System.out.println("Pizza Details: " + pizzaToUpdate.toString());
                            } catch (PizzaNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            System.out.println("Enter the ID of pizza to delete: ");
                            int pizzaIdToDelete = in.nextInt();
                            in.nextLine(); 
                            
                            try {
                                pizzaService.deletePizzaByID(pizzaIdToDelete); 
                                System.out.println("Pizza deleted successfully!");
                            } catch (PizzaNotFoundException e) {
                                System.out.println(e.getMessage()); 
                            }

                            break;
                        case 4:
                            Set<Pizza> uniquePizzas = new HashSet<>(pizzaService.getAllPizzas());
                            if (uniquePizzas.isEmpty()) {
                                System.out.println("No pizzas available.");
                            } else {
                                System.out.println("All Pizzas:");
                                for (Pizza p : uniquePizzas) {
                                    System.out.println(p.toString());
                                }
                            }
                            break;
                        case 5: 
                            System.out.println("Search Pizza by: ");
                            System.out.println("1. Name");
                            System.out.println("2. ID");
                            System.out.println("3. Size");
                            int searchChoice = in.nextInt();
                            in.nextLine(); 
                            switch (searchChoice) {
                                case 1:
                                    System.out.println("Enter the name of the pizza to search: ");
                                    String searchName = in.nextLine();
                                    Pizza foundPizza1 = pizzaService.getPizzaByName(searchName);
                                    if (foundPizza1 != null) {
                                        System.out.println("Pizza found: " + foundPizza1.toString());
                                    } else {
                                        System.out.println("Pizza with name "+ searchName +" not found.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter the ID of the pizza to search: ");
                                    int searchID = in.nextInt();
                                    in.nextLine(); 
                                    Pizza foundPizza2 = pizzaService.getPizzaByID(searchID);
                                    if (foundPizza2 != null) {
                                        System.out.println("Pizza found: " + foundPizza2.toString());
                                    } else {
                                        System.out.println("Pizza with ID "+ searchID +" not found.");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter the size of the pizza to search: ");
                                    String searchSize = in.nextLine();
                                    Pizza foundPizza3 = pizzaService.getPizzaBySize(searchSize);
                                    if (foundPizza3 != null) {
                                        System.out.println("Pizza found: " + foundPizza3.toString());
                                    } else {
                                        System.out.println("Pizza with size " + searchSize + " not found.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice! Please try again.");
                            }
                            break;
                        case 6:
                            isAdminLoggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid choice! Please try again.");
                    }
                }
            } else if (userType.equalsIgnoreCase("2")) {
                System.out.println("Welcome, Customer!");
                System.out.println("Please enter yor details: ");
                System.out.println("Customer ID: ");
                int customerID = in.nextInt();
                in.nextLine();
                System.out.println("Name: ");
                String customerName = in.nextLine();
                System.out.println("Email: ");
                String email = in.nextLine();
                System.out.println("Mobile: ");
                long mobile = in.nextLong();
                in.nextLine();
                
                System.out.println("Please enter your address details: ");
                System.out.println("Door number: ");
                int doorNumber = in.nextInt();
                in.nextLine(); 
                System.out.println("Street: ");
                String street = in.nextLine();
                System.out.println("City: ");
                String city = in.nextLine();
                System.out.println("District: ");
                String district = in.nextLine();
                System.out.println("State: ");
                String state = in.nextLine();
                Address address = new Address(doorNumber, street, city, district, state);
                Customer customer = new Customer(customerID, customerName, email, mobile, address);
                pizzaStore.addCustomer(customer);
                System.out.println("Your details added successfully!");

                boolean isCustomerLoggedIn = true;
                while (isCustomerLoggedIn) {
                    System.out.println("1. Order Pizza");
                    System.out.println("2. Pay Bill");
                    System.out.println("3. View All Pizza");
                    System.out.println("4. View Your Orders");
                    System.out.println("5. Search Pizza");
                    System.out.println("6. Exit");
                    System.out.println("Select an option: ");
                    int choice = in.nextInt();
                    in.nextLine(); 

                    switch (choice) {
                        case 1:
                            System.out.println("Order Pizza:");
                            System.out.println("Select a pizza from the menu:");
                            Set<Pizza> uniquePizzas = new HashSet<>(pizzaService.getAllPizzas());
                            if (uniquePizzas.isEmpty()) {
                                System.out.println("No pizzas available.");
                            } else {
                                System.out.println("Available Pizzas:");
                                for (Pizza pizza : uniquePizzas) {
                                    System.out.println(pizza.toString());
                                }
                                System.out.println("Enter the ID of the pizza you want to order:");
                                int pizzaID = in.nextInt();
                                in.nextLine();
                                try {
                                    Pizza selectedPizza = pizzaService.getPizzaByID(pizzaID);
                                    System.out.println("Enter the quantity:");
                                    int quantity = in.nextInt();
                                    in.nextLine();
                                    Order order = new Order();
                                    for (int i = 0; i < quantity; i++) {
                                        order.addPizza(selectedPizza);
                                    }
                                    customer.addOrder(order);
                                    System.out.println("Pizza ordered successfully!");
                                } catch (PizzaNotFoundException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;

                        case 2:
                            System.out.println("Your Payable Amount: $" + customer.getPayableAmount());
                            System.out.println("Choose payment method:");
                            System.out.println("1. Cash");
                            System.out.println("2. Credit Card");
                            int paymentMethod = in.nextInt();
                            in.nextLine(); 

                            switch (paymentMethod) {
                                case 1:
                                    System.out.println("Please pay the amount in cash at the counter. Thank you!");
                                    break;
                                case 2:
                                    System.out.println("Please enter your card details:");
                                    System.out.println("Card Number: ");
                                    String cardNumber = in.nextLine();
                                    System.out.println("Expiry Date (MM/YYYY): ");
                                    String expiryDate = in.nextLine();
                                    System.out.println("CVV: ");
                                    int cvv = in.nextInt();
                                    in.nextLine(); 
                                    break;
                                default:
                                    System.out.println("Invalid payment method!");
                            }
                            break;
                        case 3:
                            Set<Pizza> allPizzas = new HashSet<>(pizzaService.getAllPizzas());
                            if (allPizzas.isEmpty()) {
                                System.out.println("No pizzas available.");
                            } else {
                                System.out.println("All Pizzas:");
                                for (Pizza p : allPizzas) {
                                    System.out.println(p.toString());
                                }
                            }
                            break;
                        case 4:
                            List<Order> customerOrders = customer.getOrders();
                            if (customerOrders.isEmpty()) {
                                System.out.println("You haven't placed any orders yet.");
                            } else {
                                System.out.println("Your Order History:");
                                for (Order order : customerOrders) {
                                    System.out.println(order.toString());
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Search Pizza by: ");
                            System.out.println("1. Name");
                            System.out.println("2. ID");
                            System.out.println("3. Size");
                            int searchChoice = in.nextInt();
                            in.nextLine(); 
                            switch (searchChoice) {
                                case 1:
                                    System.out.println("Enter the name of the pizza to search: ");
                                    String searchName = in.nextLine();
                                    Pizza foundPizza1 = pizzaService.getPizzaByName(searchName);
                                    if (foundPizza1 != null) {
                                        System.out.println("Pizza found: " + foundPizza1.toString());
                                    } else {
                                        System.out.println("Pizza with name "+ searchName +" not found.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter the ID of the pizza to search: ");
                                    int searchID = in.nextInt();
                                    in.nextLine(); 
                                    Pizza foundPizza2 = pizzaService.getPizzaByID(searchID);
                                    if (foundPizza2 != null) {
                                        System.out.println("Pizza found: " + foundPizza2.toString());
                                    } else {
                                        System.out.println("Pizza with ID "+ searchID +" not found.");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter the size of the pizza to search: ");
                                    String searchSize = in.nextLine();
                                    Pizza foundPizza3 = pizzaService.getPizzaBySize(searchSize);
                                    if (foundPizza3 != null) {
                                        System.out.println("Pizza found: " + foundPizza3.toString());
                                    } else {
                                        System.out.println("Pizza with size " + searchSize + " not found.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice! Please try again.");
                            }
                            break;
                        case 6:
                            isCustomerLoggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            } else {
                System.out.println("Invalid user type! Please try again.");
            }
            System.out.println("Do you want to continue? (yes/no): ");
            String continueOption = in.nextLine();
            if (!continueOption.equalsIgnoreCase("yes")) {
                isRunning = false;
            }
        }
        in.close();
    }
}