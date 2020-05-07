package pizzashop.validators;

import java.util.ArrayList;

public interface Validator<E> {
    ArrayList<String> validate(E entity);
}