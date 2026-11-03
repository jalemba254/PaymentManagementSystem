
import java.util.ArrayList;
import java.io.*;

public class PaymentStorage {

    private static final String FILE_NAME = "payments.dat";

    public static void savePayments(ArrayList<Payment> payments) {

        try (ObjectOutputStream output =
                 new ObjectOutputStream(
                     new FileOutputStream(FILE_NAME))) {

            output.writeObject(payments);
            System.out.println("Payment records saved.");

        } catch (IOException e) {
            System.out.println(
                "Error saving payments: " + e.getMessage()
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Payment> loadPayments() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Starting with a new payment record.");
            return new ArrayList<>();
        }

        try (ObjectInputStream input =
                 new ObjectInputStream(
                     new FileInputStream(FILE_NAME))) {

            System.out.println("Payment records loaded successfully.");

            return (ArrayList<Payment>) input.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                "Error loading payments: " + e.getMessage()
            );

            return new ArrayList<>();
        }
    }
}
