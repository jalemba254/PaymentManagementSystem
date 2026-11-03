
import java.util.ArrayList;

public class PaymentService {

    private ArrayList<Payment> payments;
    private boolean persistenceEnabled;

    // Normal application constructor
    public PaymentService(ArrayList<Payment> payments) {
        this(payments, true);
    }

    // Constructor for automated tests
    public PaymentService(ArrayList<Payment> payments,
                          boolean persistenceEnabled) {
        this.payments = payments;
        this.persistenceEnabled = persistenceEnabled;
    }

    private void savePayments() {
        if (persistenceEnabled) {
            PaymentStorage.savePayments(payments);
        }
    }

    public boolean registerPayment(String customer, double amount,
                                   String method) {

        if (customer == null || customer.trim().isEmpty()) {
            System.out.println("Customer name is required.");
            return false;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Enter a valid positive amount.");
            return false;
        }

        if (method == null ||
                !(method.equalsIgnoreCase("M-Pesa")
                || method.equalsIgnoreCase("Bank")
                || method.equalsIgnoreCase("Cash"))) {

            System.out.println("Invalid payment method.");
            return false;
        }

        String id = String.format("PAY-%04d", nextPaymentNumber());

        Payment payment = new Payment(
            id, customer.trim(), amount, method.trim()
        );

        payments.add(payment);
        savePayments();

        System.out.println("Payment registered successfully!");
        System.out.println("Transaction ID: " + id);

        return true;
    }

    private int nextPaymentNumber() {

        int highest = 0;

        for (Payment payment : payments) {
            try {
                int number = Integer.parseInt(
                    payment.getId().substring(4)
                );

                highest = Math.max(highest, number);

            } catch (Exception e) {
                // Ignore invalid transaction IDs.
            }
        }

        return highest + 1;
    }

    public void viewPayments() {

        if (payments.isEmpty()) {
            System.out.println("No payments recorded yet.");
            return;
        }

        for (Payment payment : payments) {
            displayPayment(payment);
        }
    }

    public void searchPayment(String id) {

        for (Payment payment : payments) {
            if (payment.getId().equalsIgnoreCase(id)) {
                displayPayment(payment);
                return;
            }
        }

        System.out.println("Transaction not found.");
    }

    public void updatePayment(String id, String choice) {

        for (Payment payment : payments) {

            if (payment.getId().equalsIgnoreCase(id)) {

                switch (choice) {
                    case "1":
                        payment.setStatus("PAID");
                        break;
                    case "2":
                        payment.setStatus("FAILED");
                        break;
                    case "3":
                        payment.setStatus("PENDING");
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

    public void paymentReport() {

        int paid = 0;
        int pending = 0;
        int failed = 0;
        double total = 0;

        for (Payment payment : payments) {

            if (payment.getStatus().equals("PAID")) {
                paid++;
                total += payment.getAmount();
            } else if (payment.getStatus().equals("PENDING")) {
                pending++;
            } else if (payment.getStatus().equals("FAILED")) {
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

    private void displayPayment(Payment payment) {

        System.out.println("\n------------------------------");
        System.out.println("Transaction ID: " + payment.getId());
        System.out.println("Customer: " + payment.getCustomer());
        System.out.printf("Amount: KSh %.2f%n", payment.getAmount());
        System.out.println("Method: " + payment.getMethod());
        System.out.println("Status: " + payment.getStatus());
        System.out.println("Date: " + payment.getDate());
        System.out.println("------------------------------");
    }
}
