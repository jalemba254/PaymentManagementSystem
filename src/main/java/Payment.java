
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String customer;
    private double amount;
    private String method;
    private String status;
    private String date;

    public Payment(String id, String customer, double amount, String method) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.method = method;
        this.status = "PENDING";

        this.date = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        );
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
