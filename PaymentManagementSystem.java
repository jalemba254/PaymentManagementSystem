
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class PaymentManagementSystem {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Payment> payments = new ArrayList<>();
    static final String FILE_NAME = "payments.dat";

    static class Payment implements Serializable {
        private static final long serialVersionUID = 1L;

        String id;
        String customer;
        double amount;
        String method;
        String status;
        String date;

        Payment(String id, String customer, double amount, String method) {
            this.id = id;
            this.customer = customer;
            this.amount = amount;
            this.method = method;
            this.status = "PENDING";
            this.date = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
            );
        }
    }

    public static void main(String[] args) {

        loadPayments();

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
                    viewPayments();
                    break;
                case "3":
                    searchPayment();
                    break;
                case "4":
                    updatePayment();
                    break;
                case "5":
                    paymentReport();
                    break;
                case "6":
                    running = false;
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    static void registerPayment() {

        System.out.println("\n--- REGISTER PAYMENT ---");

        System.out.print("Customer name: ");
        String customer = scanner.nextLine().trim();

        if (customer.isEmpty()) {
            System.out.println("Customer name is required.");
            return;
        }

        System.out.print("Payment amount: ");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());

            if (!Double.isFinite(amount) || amount <= 0) {
                System.out.println("Enter a valid positive amount.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid payment amount.");
            return;
        }

        System.out.print("Payment method (M-Pesa, Bank, Cash): ");
        String method = scanner.nextLine().trim();

        if (method.isEmpty()) {
            System.out.println("Payment method is required.");
            return;
        }

        String id = String.format("PAY-%04d", nextPaymentNumber());

        Payment payment = new Payment(id, customer, amount, method);

        payments.add(payment);
        savePayments();

        System.out.println("\nPayment registered successfully!");
        System.out.println("Transaction ID: " + id);
    }

    static int nextPaymentNumber() {
        int highest = 0;

        for (Payment p : payments) {
            try {
                int number = Integer.parseInt(p.id.substring(4));
                highest = Math.max(highest, number);
            } catch (Exception e) {
                // Ignore invalid transaction IDs.
            }
        }

        return highest + 1;
    }

    static void viewPayments() {

        System.out.println("\n--- ALL PAYMENTS ---");

        if (payments.isEmpty()) {
            System.out.println("No payments recorded yet.");
            return;
        }

        for (Payment p : payments) {
            displayPayment(p);
        }
    }

    static void searchPayment() {

        System.out.print("\nEnter transaction ID: ");
        String id = scanner.nextLine().trim();

        for (Payment p : payments) {
            if (p.id.equalsIgnoreCase(id)) {
                displayPayment(p);
                return;
            }
        }

        System.out.println("Transaction not found.");
    }

    static void updatePayment() {

        System.out.print("\nEnter transaction ID: ");
        String id = scanner.nextLine().trim();

        for (Payment p : payments) {

            if (p.id.equalsIgnoreCase(id)) {

                System.out.println("1. Mark as PAID");
                System.out.println("2. Mark as FAILED");
                System.out.println("3. Mark as PENDING");
                System.out.print("Choose status: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        p.status = "PAID";
                        break;
                    case "2":
                        p.status = "FAILED";
                        break;
                    case "3":
                        p.status = "PENDING";
                        break;
                    default:
                        System.out.println("Invalid status.");
                        return;
                }

                savePayments();
                System.out.println("Payment status updated.");
                return;
            }
        }

        System.out.println("Transaction not found.");
    }

    static void paymentReport() {

        int paid = 0;
        int pending = 0;
        int failed = 0;
        double total = 0;

        for (Payment p : payments) {

            if (p.status.equals("PAID")) {
                paid++;
                total += p.amount;
            } else if (p.status.equals("PENDING")) {
                pending++;
            } else if (p.status.equals("FAILED")) {
                failed++;
            }
        }

        System.out.println("\n--- PAYMENT REPORT ---");
        System.out.println("Total transactions: " + payments.size());
        System.out.println("Successful payments: " + paid);
        System.out.println("Pending payments: " + pending);
        System.out.println("Failed payments: " + failed);
        System.out.printf("Total collected: KSh %.2f%n", total);
    }

    static void displayPayment(Payment p) {

        System.out.println("\n------------------------------");
        System.out.println("Transaction ID: " + p.id);
        System.out.println("Customer: " + p.customer);
        System.out.printf("Amount: KSh %.2f%n", p.amount);
        System.out.println("Method: " + p.method);
        System.out.println("Status: " + p.status);
        System.out.println("Date: " + p.date);
        System.out.println("------------------------------");
    }

    static void savePayments() {

        try (ObjectOutputStream output =
                 new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            output.writeObject(payments);

        } catch (IOException e) {
            System.out.println("Error saving payments: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static void loadPayments() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Starting with a new payment record.");
            return;
        }

        try (ObjectInputStream input =
                 new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            payments = (ArrayList<Payment>) input.readObject();

            System.out.println("Payment records loaded successfully.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading payment records: " + e.getMessage());
        }
    }
}
