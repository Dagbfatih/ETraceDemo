package business.validationRules;

import core.utilities.results.Result;
import core.utilities.validation.Validator;
import entities.concrete.UserForLogin;

import java.util.ArrayList;
import java.util.List;

public class LoginValidator extends Validator<UserForLogin> {

    @Override
    public List<Result> validate(UserForLogin entity) {

        return new ArrayList<>();
    }
}
