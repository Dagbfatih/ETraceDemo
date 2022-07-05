package business.concretes;

import business.abstracts.AuthService;
import business.abstracts.EmailService;
import business.abstracts.UserService;
import business.validationRules.LoginValidator;
import business.validationRules.RegisterValidator;
import core.business.BusinessRules;
import core.utilities.results.ErrorResult;
import core.utilities.results.Result;
import core.utilities.results.SuccessResult;
import entities.concrete.User;
import entities.concrete.UserForLogin;
import entities.concrete.UserForRegister;

import java.util.Arrays;

public class AuthManager implements AuthService {
    UserService userService;
    LoginValidator loginValidator;
    RegisterValidator registerValidator;
    EmailService emailService;

    public AuthManager(UserService userServiceInstance,
                       LoginValidator loginValidatorInstance,
                       RegisterValidator registerValidatorInstance,
                       EmailService emailServiceInstance) {
        userService = userServiceInstance;
        loginValidator = loginValidatorInstance;
        registerValidator = registerValidatorInstance;
        emailService = emailServiceInstance;

    }

    @Override
    public Result login(UserForLogin userForLogin) {
        final User user = userService.getByEmail(userForLogin.getEmail()).getData();
        if (user == null) {
            return new ErrorResult(userForLogin.getEmail() + " adresine kayıtlı bir kullanıcı bulunamadı");
        }

        if (!user.getPassword().equals(userForLogin.getPassword())) {
            return new ErrorResult("Parola yanlış");
        }

        return new SuccessResult("Başarıyla giriş yapıldı");
    }

    @Override
    public Result register(UserForRegister userForRegister) {
        var validationResult = registerValidator.createValidationMessages(registerValidator.validate(userForRegister));
        var isUserAlreadyExists = checkIfUserExists(userForRegister.getEmail());

        var result = BusinessRules.Run(Arrays.asList(validationResult, isUserAlreadyExists));

        if (!result.isSuccess()) {
            return result;
        }

        var user = new User(
                userForRegister.getFirstName(),
                userForRegister.getLastName(),
                userForRegister.getEmail(),
                userForRegister.getPassword());
        userService.add(user);

        emailService.sendConfirmationMail(user.getFirstName() + user.getLastName(), user.getEmail());
        var codeResult = emailService.receiveMail(user.getFirstName() + user.getLastName(), user.getEmail());
        if (!codeResult.isSuccess()) {
            return codeResult;
        }

        return new SuccessResult("Kayıt başarılı");
    }

    private Result checkIfUserExists(String email) {
        if (userService.getByEmail(email).getData() != null) {
            return new ErrorResult("Bu email'e kayıtlı bir hesap var.");
        }
        return new SuccessResult();
    }

}
