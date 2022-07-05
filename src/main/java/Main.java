import business.abstracts.AuthService;
import business.abstracts.UserService;
import business.concretes.AuthManager;
import business.concretes.UserManager;
import entities.concrete.UserForRegister;

public class Main {

    public static void main(String[] args) {
        AuthService authService = new AuthManager();
        UserService userService = new UserManager();

        var result = authService.register(new UserForRegister(
                "Fatih",
                "Dağ",
                "dagbfatih@hotmail.com",
                "1234567"));
        System.out.println(result.getMessage());
        System.out.println(userService.getAll().getData());
    }
}
