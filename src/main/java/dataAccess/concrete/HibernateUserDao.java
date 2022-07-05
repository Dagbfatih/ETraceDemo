package dataAccess.concrete;

import dataAccess.abstracts.UserDao;
import entities.concrete.User;
import localDb.UserDb;

import java.util.List;
import java.util.function.Function;

public class HibernateUserDao implements UserDao {
    UserDb userDb = new UserDb();

    @Override
    public void add(User user) {
        userDb.users.add(user);
        System.out.println("user: " + user.getFirstName() + " " + user.getLastName() + " added");
    }

    @Override
    public void delete(User user) {
        userDb.users.remove(user);
        System.out.println("user: " + user.getFirstName() + " " + user.getLastName() + " added");
    }

    @Override
    public void update(User user) {
        userDb.users.set(userDb.users.indexOf(user), user);
        System.out.println("user: " + user.getFirstName() + " " + user.getLastName() + " added");
    }

    @Override
    public List<User> getAll() {
        return userDb.users;
    }

    @Override
    public List<User> getAll(Function<List<User>, List<User>> filter) {
        return filter.apply(userDb.users);
    }

    @Override
    public User get(Function<User, Boolean> filter) {
        for (User user :
                userDb.users) {
            if (filter.apply(user)) {
                return user;
            }
        }
        return null;
    }
}
