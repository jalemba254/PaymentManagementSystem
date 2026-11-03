
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentManagementSystem {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Payment> payments =
        PaymentStorage.loadPayments();

    static PaymentService paymentService =
        new PaymentService(payments);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            System.out.println("\n====================================");
            System.out.println("     PAYMENT MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Register Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Search Payment");
            System.out.println("4. Update Payment Status");
            System.out.println("5. Payment Report");
            System.out.println("6. Exit");
            System.out.println("------------------------------------");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    registerPayment();
                    break;

                case "2":
                    paymentService.viewPayments();
                    break;

                case "3":
                    searchPayment();
                    break;

                case "4":
                    updatePayment();
                    break;

                case "5":
                    paymentService.paymentReport();
                    break;

                case "6":
                    running = false;
                    System.out.println(
                        "Thank you for using the system."
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid option. Try again."
                    );
            }
        }

        scanner.close();
    }

    static void registerPayment() {

        System.out.println("\n--- REGISTER PAYMENT ---");

        System.out.print("Customer name: ");
        String customer = scanner.nextLine();

        System.out.print("Payment amount: ");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Invalid payment amount.");
            return;
        }

        System.out.print(
            "Payment method (M-Pesa, Bank, Cash): "
        );

        String method = scanner.nextLine();

        paymentService.registerPayment(
            customer, amount, method
        );
    }

    static void searchPayment() {

        System.out.print("\nEnter transaction ID: ");
        String id = scanner.nextLine().trim();

        paymentService.searchPayment(id);
    }

    static void updatePayment() {

        System.out.print("\nEnter transaction ID: ");
        String id = scanner.nextLine().trim();

        System.out.println("1. Mark as PAID");
        System.out.println("2. Mark as FAILED");
        System.out.println("3. Mark as PENDING");

        System.out.print("Choose status: ");
        String choice = scanner.nextLine();

        paymentService.updatePayment(id, choice);
    }
}
