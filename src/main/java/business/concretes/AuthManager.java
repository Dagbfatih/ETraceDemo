package business.concretes;

import business.abstracts.AuthService;
import business.abstracts.EmailService;
import business.abstracts.UserService;
import business.validationRules.LoginValidator;
import business.validationRules.RegisterValidator;
import core.utilities.results.ErrorResult;
import core.utilities.results.Result;
import core.utilities.results.SuccessResult;
import entities.concrete.User;
import entities.concrete.UserForLogin;
import entities.concrete.UserForRegister;

import java.io.IOException;

public class AuthManager implements AuthService {
    UserService userService = new UserManager();
    LoginValidator loginValidator = new LoginValidator();
    RegisterValidator registerValidator = new RegisterValidator();
    EmailService emailService = new EmailManager();

    @Override
    public Result login(UserForLogin userForLogin) {
        final User user = userService.getByEmail(userForLogin.getEmail()).getData();
        if (user == null) {
            return new ErrorResult("kullanıcı bulunamadı");
        }
        System.out.println("başarıyla giriş yapıldı");
        return new SuccessResult("başarıyla giriş yapıldı");
    }

    @Override
    public Result register(UserForRegister userForRegister) {
        var validations = registerValidator.validate(userForRegister);
        var result = registerValidator.createValidationMessages(validations);

        if (!result.isSuccess()){
            return result;
        }

        var user = new User(userForRegister.getFirstName(),
                userForRegister.getLastName(),
                userForRegister.getEmail(),
                userForRegister.getPassword());
        userService.add(user);
        emailService.sendConfirmationMail(user.getFirstName() + user.getLastName(), user.getEmail());
        var codeResult = emailService.receiveMail(user.getFirstName() + user.getLastName(), user.getEmail());
        if (!codeResult.isSuccess()) {
            return new ErrorResult("email code wrong");
        }

        return new SuccessResult("Successful");
    }
}
