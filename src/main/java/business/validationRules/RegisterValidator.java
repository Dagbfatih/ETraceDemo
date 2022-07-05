package business.validationRules;

import core.utilities.results.Result;
import core.utilities.validation.Validator;
import entities.concrete.UserForRegister;

import java.util.ArrayList;
import java.util.List;

public class RegisterValidator extends Validator<UserForRegister> {

    @Override
    public List<Result> validate(UserForRegister entity) {
        List<Result> results = new ArrayList<>();
        results.add(mustGreaterThan("Şifre", entity.getPassword(), 6));
        results.add(mustCompatibleWithEmailFormat(entity.getEmail()));
        results.add(mustGreaterThanOrEqualTo("İsim", entity.getFirstName(), 2));
        results.add(mustGreaterThanOrEqualTo("Soyad", entity.getLastName(), 2));

        return results;
    }


}
