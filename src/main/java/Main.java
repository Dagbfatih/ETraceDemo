import business.abstracts.AuthService;
import business.abstracts.UserService;
import business.concretes.AuthManager;
import business.concretes.EmailManager;
import business.concretes.UserManager;
import business.validationRules.LoginValidator;
import business.validationRules.RegisterValidator;
import dataAccess.abstracts.UserDao;
import dataAccess.concrete.HibernateUserDao;
import entities.concrete.UserForLogin;
import entities.concrete.UserForRegister;
import localDb.UserDb;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new HibernateUserDao(new UserDb());
        UserService userService = new UserManager(userDao);
        AuthService authService = new AuthManager(userService, new LoginValidator(), new RegisterValidator(), new EmailManager());

        var result = authService.register(new UserForRegister(
                "Fatih",
                "Dağ",
                "dagbfatih@hotmail.com",
                "1234567"));
        System.out.println(result.getMessage());
        System.out.println();


        var result2 = authService.register(new UserForRegister(
                "Fatih",
                "Dağ",
                "dagbfatih2@hotmail.com",
                "1234567"));
        System.out.println(result2.getMessage());
        System.out.println();

        var loginResult = authService.login(new UserForLogin("dagbfatih@hotmail.com", "1234567"));
        System.out.println(loginResult.getMessage());
        System.out.println();

        // Print Users
        System.out.println("\nKullanıcılar\n-------------------------\n");
        userService.getAll().getData().forEach(u -> System.out.println(u.getFirstName() + " " + u.getLastName()));


    }
}
