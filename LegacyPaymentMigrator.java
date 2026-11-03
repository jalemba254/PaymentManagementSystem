
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LegacyPaymentMigrator {

    public static void main(String[] args) {

        String backupFile = "payments-pre-package-migration.dat";
        String outputFile = "payments-migrated.dat";

        try (ObjectInputStream input =
                 new ObjectInputStream(
                     new FileInputStream(backupFile))) {

            ArrayList<Payment> oldPayments =
                (ArrayList<Payment>) input.readObject();

            ArrayList<com.jalemba.payments.Payment> newPayments =
                new ArrayList<>();

            for (Payment oldPayment : oldPayments) {

                com.jalemba.payments.Payment newPayment =
                    new com.jalemba.payments.Payment(
                        oldPayment.getId(),
                        oldPayment.getCustomer(),
                        oldPayment.getAmount(),
                        oldPayment.getMethod()
                    );

                newPayment.setStatus(oldPayment.getStatus());
                newPayment.setDate(oldPayment.getDate());

                newPayments.add(newPayment);
            }

            try (ObjectOutputStream output =
                     new ObjectOutputStream(
                         new FileOutputStream(outputFile))) {

                output.writeObject(newPayments);
            }

            System.out.println("Migration completed successfully.");
            System.out.println("Records migrated: " + newPayments.size());
            System.out.println("Output file: " + outputFile);

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Migration failed: " + e.getMessage());
        }
    }
}
