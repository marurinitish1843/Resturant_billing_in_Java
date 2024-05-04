import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

class Product {
    // properties
    private String id;
    private String pname;
    private int qty;
    private double price;
    private double totalPrice;

    // constructor
    Product(String id, String pname, int qty, double price, double totalPrice) {
        this.id = id;
        this.pname = pname;
        this.qty = qty;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    // getter methods
    public String getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // displayFormat
    public static void displayFormat() {
        System.out.format("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("\nProduct ID \t\tName\t\tQuantity\t\tRate \t\t\t\tTotal Price\n");
        System.out.format("-----------------------------------------------------------------------------------------------------------------------------------\n");
    }

    // display
    public void display() {
        System.out.format("   %-9s             %-9s      %5d               %9.2f                       %14.2f\n", id, pname,
                qty, price, totalPrice);
    }
}

public class ShoppingBill {
    public static void main(String args[]) {
        // variables
        double discountRate = 2; // Discount rate in percentage
        double cgstRate = 12; // CGST rate in percentage
        double sgstRate = 12; // SGST rate in percentage
        String id = null;
        String productName = null;
        int quantity = 0;
        double price = 0.0;
        double totalPrice = 0.0;
        double overAllPrice = 0.0;
        double cgst, sgst, subtotal = 0.0, discount = 0.0;
        char choice = '\0';

        System.out.println("\t\t\t\t--------------------Invoice-----------------");
        System.out.println("\t\t\t\t\t " + "  " + "Metro Mart Grocery Shop");
        System.out.println("\t\t\t\t\t3/98 Hitech city Hyderabad");
        System.out.println("\t\t\t\t\t" + "    " + "Opposite Metro Walk");
        System.out.println("GSTIN: 03AWBPP8756K592" + "\t\t\t\t\t\t\tContact: (+91) 9966961834");

        // format of date and time
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        // prints current date and time
        System.out.println("Date: " + formatter.format(date) + "  " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1]
                + "\t\t\t\t\t\t (+91) 9848058105");

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Customer Name: ");
        String customername = scan.nextLine();

        // create Scanner class object
        // creating an ArrayList to store the product
        List<Product> productList = new ArrayList<Product>();
        do {
            // read input values
            System.out.println("Enter the product details: ");
            System.out.print("Product ID: ");
            id = scan.nextLine();
            System.out.print("Product Name: ");
            productName = scan.nextLine();
            System.out.print("Quantity: ");
            quantity = scan.nextInt();
            System.out.print("Price (per unit): ");
            price = scan.nextDouble();
            // calculate total price for a particular product
            totalPrice = price * quantity;
            // calculates overall price
            overAllPrice += totalPrice;
            // creates Product class object and add it to the List
            productList.add(new Product(id, productName, quantity, price, totalPrice));
            // ask for continue shopping?
            System.out.print("Want to add more items? (y or n): ");
            // reads a character Y or N
            choice = scan.next().charAt(0);
            // read remaining characters, don't store (no use)
            scan.nextLine();
        } while (choice == 'y' || choice == 'Y');
        // display all product with its properties
        Product.displayFormat();
        for (Product p : productList) {
            p.display();
        }
        // price calculation
        System.out.println("\n\t\t\t\t\t\t\t\t\t\tTotal Amount (Rs.) " + overAllPrice);
        // calculating discount
        discount = overAllPrice * discountRate / 100;
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t    Discount (Rs.) " + discount);
        // total amount after discount
        subtotal = overAllPrice - discount;
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t          Subtotal " + subtotal);
        // calculating tax
        sgst = overAllPrice * sgstRate / 100;
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t          SGST (%) " + sgst);
        cgst = overAllPrice * cgstRate / 100;
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t          CGST (%) " + cgst);
        // calculating amount to be paid by buyer
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t     Invoice Total " + (subtotal + cgst + sgst));
        System.out.println("\t\t\t\t----------------THANK YOU FOR SHOPPING, " + customername + "-----------------");
        System.out.println("\t\t\t\t                            VISIT AGAIN");
        // close Scanner
        scan.close();
    }
}
