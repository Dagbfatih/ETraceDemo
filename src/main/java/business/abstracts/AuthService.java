package business.abstracts;

import core.utilities.results.Result;
import entities.concrete.UserForLogin;
import entities.concrete.UserForRegister;

import java.io.IOException;

public interface AuthService {
    Result login(UserForLogin user);

    Result register(UserForRegister user);
}
