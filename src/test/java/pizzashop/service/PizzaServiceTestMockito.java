package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validators.VPayment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceTestMockito {
    private static PaymentRepository payRepo;
    private static MenuRepository menuRepo;
    private static PizzaService service;
    private static VPayment validator;

    @BeforeAll
    public static void setUp(){
        payRepo = mock(PaymentRepository.class);
        menuRepo = mock(MenuRepository.class);
        service = new PizzaService(menuRepo, payRepo);
        validator = new VPayment();
    }

    @Test
    void add() {
        VPayment spiedValidator = spy(validator);
        Payment payment = new Payment(4, PaymentType.Cash, 10.5);
        assert (spiedValidator.validate(payment).equals(new ArrayList<String>()));

        when(payRepo.getAll()).thenReturn(Arrays.asList(payment));
        doNothing().when(payRepo).add(any());
        service.addPayment(4, PaymentType.Cash, 10.5);

        assertEquals(service.getPayments().get(0).getAmount(), payment.getAmount());
        assertEquals(service.getPayments().get(0).getTableNumber(), payment.getTableNumber());
        assertEquals(service.getPayments().get(0).getType(), payment.getType());
    }

    @Test
    void getAll() {
        Payment payment1=new Payment(4, PaymentType.Card,10.5);

        when(payRepo.getAll()).thenReturn(Arrays.asList(payment1));

        verify(payRepo,never()).getAll();

        double totalAmountCash=service.getTotalAmount(PaymentType.Cash);
        double totalAmountCard=service.getTotalAmount(PaymentType.Card);
        assertEquals(0,totalAmountCash);
        assertEquals(10.5,totalAmountCard);

        verify(payRepo,times(2)).getAll();
    }
}