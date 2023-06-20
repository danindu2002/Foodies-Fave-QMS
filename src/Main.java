import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static int burgerStock = 50;
    public static int newBurgersCount;
    public static String answer;
    public static Scanner input = new Scanner(System.in);

    // declaring a 2-dimensional array for storing queue data
    public static String[][] cashiers = new String[][]
    {
        new String[2], new String[3], new String[5]
    };

    public static void main(String[] args)
    {
        menuController();
    }

    static void menuController()
    {
        // Printing the menu
        System.out.println("""
                
                ---- Foodies Fave Queue Management System ----
                
                100 or VFQ: View all Queues
                101 or VEQ: View all Empty Queues
                102 or ACQ: Add customer to a Queue
                103 or RCQ: Remove a customer from a Queue
                104 or PCQ: Remove a served customer
                105 or VCS: View Customers Sorted in alphabetical order
                106 or SPD: Store Program Data into file
                107 or LPD: Load Program Data from file
                108 or STK: View Remaining burgers Stock
                109 or AFS: Add burgers to Stock
                999 or EXT: Exit the Program
                """);

        System.out.print("Select a menu option: ");
        String option = input.next();

        // selecting each option and building their functionalities

        switch(option.toUpperCase())
        {
            case "100":
            case "VFQ":
                // Printing the cashier view with ALL queues
                cashierHeader();
                cashierQueue();
                loopController();
                break;

            case "101":
            case "VEQ":
                // Printing the cashier view with EMPTY queues
                cashierHeader();
                int totalQueues = cashiers[0].length + cashiers[1].length + cashiers[2].length;
                int emptyQueues = 0;
                for(int i = 0; i < cashiers[2].length; i++)
                {
                    for(int j = 0; j < cashiers.length; j++)
                    {
                        if(i >= cashiers[j].length)
                        {
                            System.out.print("      ");
                            continue;
                        }
                        if(cashiers[j][i] == null)
                        {
                            System.out.print("  X   ");
                            emptyQueues++;
                        }
                        else
                        {
                            System.out.print("      ");
                        }
                    }
                    System.out.println("");
                }
                System.out.println(emptyQueues + " queues are Empty out of " + totalQueues);

                loopController();
                break;

            case "102":
            case "ACQ":
                // adding customers to queue and validating user inputs
                addCustomersToQueue();
                break;

            case "103":
            case "RCQ":
                //todo
                break;

            case "104":
            case "PCQ":
                //todo
                break;

            case "105":
            case "VCS":
                //todo
                break;

            case "107":
            case "LPD":
                //todo
                break;

            case "108":
            case "STK":
                // displaying remaining burger stock and low stocks alert
                System.out.println("Remaining Burger Stock: "+ burgerStock);
                if(burgerStock <= 10)
                {
                    System.out.println("""
                            
                            *** Alert ***
                            You are running out of stocks, please refill !""");
                }
                loopController();
                break;

            case "109":
            case "AFS":
                // getting and validating newBurgersCount
                addBurgersToStock();
                break;

            case "999":
            case "EXT":
                // exiting the program with a default message
                System.out.println("Thank you for using Foodies Fave QMS!");
                System.exit(0);

            default:
                // validating user input for options
                System.out.println("Please enter a valid option from the below menu.");
                menuController();
        }
    }

    static void loopController()
    {
        // Controlling the iterative process by asking user a question
        System.out.println("");
        System.out.print("Enter 'y' to continue or Enter 'n' to exit: ");
        answer = input.next();

        if (answer.equalsIgnoreCase("y"))
        {
            menuController();
        }
        else if (answer.equalsIgnoreCase("n"))
        {
            System.out.println("Thank you for using Foodies Fave QMS!");
            System.exit(0);
        }
        else
        {
            System.out.print("Invalid answer");
            loopController();
        }
    }

    static void cashierHeader()
    {
        // Printing the cashier header
        for(int i = 0; i < 3; i++)
        {
            if(i == 1)
            {
                System.out.println("*    Cashiers    *");
                continue;
            }
            for(int j = 0; j < 18; j++)
            {
                System.out.print("*");
            }
        System.out.println("");
        }
    }
    static void cashierQueue()
    {
        // Printing the cashier queue
        for(int i = 0; i < cashiers[2].length; i++)
        {
            for(int j = 0; j < cashiers.length; j++)
            {
                if(i >= cashiers[j].length)
                {
                    System.out.print("      ");
                    continue;
                }
                if(cashiers[j][i] == null)
                {
                    System.out.print("  X   ");
                }
                else
                {
                    System.out.print("  O   ");
                }
            }
            System.out.println("");
        }
        System.out.println("""
                X – Not Occupied
                O – Occupied""");
    }

    static void addCustomersToQueue()
    {
        boolean loopAgain = true;
        while(loopAgain)
        {
            // checking if the customer can be served with the remaining stock or not
            if (burgerStock >= 5)
            {
                System.out.print("Enter Name: ");
                String name = input.next();

                // validating if the customer name is only alphabetical or not
                if (name.matches("^[a-zA-Z]*$") )
                {
                    // validating user input only as an integer
                    try
                    {
                        System.out.print("Enter Queue: ");
                        int queueNumber = input.nextInt();

                        // checking the queue number is one of a cashier
                        if(queueNumber >= 1 && queueNumber <=3)
                        {
                            int actualQueueNumber = queueNumber - 1;
                            int occupiedQueuePositions = 0;

                            for (int k = 0; k < cashiers[actualQueueNumber].length; k++)
                            {
                                // adding customer name to a empty queue position only if it is empty
                                if (cashiers[actualQueueNumber][k] == null)
                                {
                                    cashiers[actualQueueNumber][k] = name;
                                    System.out.println(name + " added to the queue " + queueNumber + " successfully");
                                    loopAgain = false;
                                    loopController();
                                    break;
                                }
                                else
                                {
                                    occupiedQueuePositions++;

                                    // giving a message if the particular queue is full
                                    if (occupiedQueuePositions == cashiers[actualQueueNumber].length)
                                    {
                                        System.out.println("Queue is full, Please try again");
                                    }
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Invalid Queue number");
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Integer required");
                    }
                }
                else
                {
                    System.out.println("Please enter alphabetical letters only");
                }
            }
            else
            {
                System.out.println("Burger stock is insufficient, Please Refill!");
            }
        }
    }

    static void addBurgersToStock()
    {
        boolean typeError = true;
        while (typeError)
        {
            // validating and getting only integer inputs
            try
            {
                System.out.print("Enter the amount of burgers to be added: ");
                newBurgersCount = input.nextInt();

                // checking if it is a positive integer or not
                if(newBurgersCount > 0)
                {
                    burgerStock += newBurgersCount;

                    // checking if the new burger stock exceeds the maximum stock
                    if(burgerStock <= 50)
                    {
                        System.out.println("Stocks updated successfully. Total Burger Stock: " + burgerStock);
                        typeError = false;
                        loopController();
                    }
                    else
                    {
                        burgerStock -= newBurgersCount;

                        // showing the maximum amount of burgers that can be added
                        System.out.println("Maximum stock count exceeded, " + (50 - burgerStock) + " burgers can be added");
                    }
                }
                else
                {
                    System.out.println("Positive integer required");
                }
            }
            catch (Exception e)
            {
                System.out.println("Integer required");
                input.next();
            }
        }
    }

}
