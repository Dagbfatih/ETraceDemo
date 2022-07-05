package business.abstracts;

import core.utilities.results.DataResult;
import core.utilities.results.Result;
import entities.concrete.User;

import java.util.List;

public interface UserService {
    Result add(User user);

    Result delete(User user);

    Result update(User user);

    DataResult<List<User>> getAll();
    DataResult<User> get(int id);

    DataResult<User> getByEmail(String email);
}
