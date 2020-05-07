package pizzashop.repository;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryMockitoTest {
    private static PaymentRepository payRepo;
    private static Payment payment;

    @BeforeAll
    public static void setUp(){
        payRepo = new PaymentRepository();
        payRepo.getAll().clear();
        payment = mock(Payment.class);
    }

    @AfterEach
    public void clear(){
        payRepo.getAll().clear();
    }

    @Test
    void add() {
        when(payment.getTableNumber()).thenReturn(4);
        when(payment.getType()).thenReturn(PaymentType.Card);
        when(payment.getAmount()).thenReturn(10.5);


        payRepo.add(new Payment(payment.getTableNumber(),payment.getType(),payment.getAmount()));

        assertEquals(payment.getAmount(), payRepo.getAll().get(0).getAmount());
        assertEquals(payment.getTableNumber(), payRepo.getAll().get(0).getTableNumber());
        assertEquals(payment.getType(), payRepo.getAll().get(0).getType());
    }

    @Test
    void getAll() {
        when(payment.getTableNumber()).thenReturn(4);
        when(payment.getType()).thenReturn(PaymentType.Cash);
        when(payment.getAmount()).thenReturn(10.5);

        payRepo.add(new Payment(payment.getTableNumber(),payment.getType(),payment.getAmount()));

        int size= payRepo.getAll().size();
        assertEquals(1, size);
    }
}