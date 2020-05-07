package pizzashop.validators;

import pizzashop.model.Payment;
import java.util.ArrayList;

public class VPayment implements Validator<Payment> {
    @Override
    public ArrayList<String> validate(Payment entity) {
        ArrayList<String> message = new ArrayList<>();
        if (entity.getTableNumber() < 1 || entity.getTableNumber() > 8) {
            message.add("Table must be between 1-8");
        }
        if (entity.getType() == null) {
            message.add("Type must be cash or card");
        }
        if (entity.getAmount() <= 0) {
            message.add("Amount must be grater than 0");
        }
        return message;
    }
}