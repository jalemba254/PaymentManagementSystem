package com.jalemba.payments;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    void shouldRegisterValidPayment() {

        ArrayList<Payment> testPayments = new ArrayList<>();
        PaymentService service = new PaymentService(testPayments, false);

        boolean result = service.registerPayment(
            "Test Customer", 2500, "M-Pesa"
        );

        assertTrue(result);
        assertEquals(1, testPayments.size());
        assertEquals("Test Customer", testPayments.get(0).getCustomer());
        assertEquals(2500, testPayments.get(0).getAmount());
        assertEquals("M-Pesa", testPayments.get(0).getMethod());
        assertEquals("PENDING", testPayments.get(0).getStatus());
    }

    @Test
    void shouldRejectInvalidAmount() {

        ArrayList<Payment> testPayments = new ArrayList<>();
        PaymentService service = new PaymentService(testPayments, false);

        boolean result = service.registerPayment(
            "Test Customer", -100, "M-Pesa"
        );

        assertFalse(result);
        assertTrue(testPayments.isEmpty());
    }

    @Test
    void shouldRejectInvalidPaymentMethod() {

        ArrayList<Payment> testPayments = new ArrayList<>();
        PaymentService service = new PaymentService(testPayments, false);

        boolean result = service.registerPayment(
            "Test Customer", 2500, "Bitcoin"
        );

        assertFalse(result);
        assertTrue(testPayments.isEmpty());
    }

    @Test
    void shouldRejectEmptyCustomerName() {

        ArrayList<Payment> testPayments = new ArrayList<>();
        PaymentService service = new PaymentService(testPayments, false);

        boolean result = service.registerPayment(
            " ", 2500, "M-Pesa"
        );

        assertFalse(result);
        assertTrue(testPayments.isEmpty());
    }

    @Test
    void shouldGenerateSequentialPaymentIds() {

        ArrayList<Payment> testPayments = new ArrayList<>();
        PaymentService service = new PaymentService(testPayments, false);

        service.registerPayment("Customer One", 1000, "Cash");
        service.registerPayment("Customer Two", 2000, "Bank");

        assertEquals("PAY-0001", testPayments.get(0).getId());
        assertEquals("PAY-0002", testPayments.get(1).getId());
    }

    @Test
    void shouldUpdatePaymentStatusToPaid() {

        ArrayList<Payment> testPayments = new ArrayList<>();
        PaymentService service = new PaymentService(testPayments, false);

        service.registerPayment("Test Customer", 3000, "M-Pesa");

        String paymentId = testPayments.get(0).getId();

        service.updatePayment(paymentId, "1");

        assertEquals("PAID", testPayments.get(0).getStatus());
    }
@Test
void shouldUpdatePaymentStatusToFailed() {

    ArrayList<Payment> testPayments = new ArrayList<>();
    PaymentService service = new PaymentService(testPayments, false);

    service.registerPayment("Test Customer", 3000, "M-Pesa");

    String paymentId = testPayments.get(0).getId();

    service.updatePayment(paymentId, "2");

    assertEquals("FAILED", testPayments.get(0).getStatus());
}

@Test
void shouldUpdatePaymentStatusToPending() {

    ArrayList<Payment> testPayments = new ArrayList<>();
    PaymentService service = new PaymentService(testPayments, false);

    service.registerPayment("Test Customer", 3000, "M-Pesa");
    testPayments.get(0).setStatus("PAID");

    String paymentId = testPayments.get(0).getId();

    service.updatePayment(paymentId, "3");

    assertEquals("PENDING", testPayments.get(0).getStatus());
}
}
