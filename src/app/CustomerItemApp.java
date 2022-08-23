package app;

import com.mohamed.model.Customer;
import com.mohamed.model.Item;
import com.mohamed.services.CustomerDAO;
import com.mohamed.services.ItemDAO;

import java.util.Arrays;
import java.util.Scanner;

public class CustomerItemApp {
    public static void main(String[] args) {
        CustomerDAO cDAO = new CustomerDAO();
        ItemDAO iDAO = new ItemDAO();

        try (Scanner sc = new Scanner(System.in)) {
            int input = 0;
            while(input != 9) {
                displayMenu();
                input = Integer.parseInt(sc.nextLine());
                operateMenu(sc, cDAO, iDAO, input);
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * Display menu options
     */
    public static void displayMenu() {

        System.out.println("Menu Options:");
        System.out.println("1. Add a customer");
        System.out.println("2. Delete a customer");
        System.out.println("3. Find customer by ID");
        System.out.println("4. Add an item");
        System.out.println("5. Delete an item");
        System.out.println("6. Show all items");
        System.out.println("9. Exit app");

    }

    /*
     * Run menu options depending on the input
     */
    public static void operateMenu(Scanner sc, CustomerDAO cDAO, ItemDAO iDAO, int input) {

        String line;
        String[] iInfo;
        String[] cInfo;
        int id;
        Customer customer;
        Item item;

        switch(input) {
            case 1:
                System.out.println("Please enter the id, email, first name and last name of the customer, seperated by a space:");
                line = sc.nextLine();
                cInfo = line.split(" ");
                customer = new Customer();
                customer.setId(Integer.parseInt(cInfo[0]));
                customer.setEmail(cInfo[1]);
                customer.setfName(cInfo[2]);
                customer.setlName(cInfo[3]);
                cDAO.addCustomer(customer);
                break;
            case 2:
                System.out.println("Please enter the id of the customer to be removed:");
                line = sc.nextLine();
                id = Integer.parseInt(line);
                cDAO.deleteCustomer(id);
                break;
            case 3:
                System.out.println("Please enter the id of the customer to be found:");
                line = sc.nextLine();
                id = Integer.parseInt(line);
                System.out.println(cDAO.getCustomer(id));
                break;
            case 4:
                System.out.println("Please enter the id, name and price of the item, seperated by a space:");
                line = sc.nextLine();
                iInfo = line.split(" ");
                item = new Item();
                item.setId(Integer.parseInt(iInfo[0]));
                item.setItemName(iInfo[1]);
                item.setPrice(Double.parseDouble(iInfo[2]));
                iDAO.addItem(item);
                break;
            case 5:
                System.out.println("Please enter the id of the item to be removed:");
                line = sc.nextLine();
                id = Integer.parseInt(line);
                iDAO.removeItem(id);
                break;
            case 6:
                System.out.println("Showing all items found in the database:");
                System.out.println(Arrays.toString(iDAO.getAllItems().toArray()));
                break;
            default:
        }
        System.out.println("-------------------------------");
    }

}


