package dataAccess.abstracts;

import entities.concrete.User;

import java.util.List;
import java.util.function.Function;

public interface UserDao {
    void add(User user);

    void delete(User user);

    void update(User user);

    List<User> getAll();

    List<User> getAll(Function<List<User>, List<User>> filter);

    User get(Function<User, Boolean> filter);
}
