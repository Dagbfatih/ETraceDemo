package business.concretes;

import business.abstracts.UserService;
import core.utilities.results.DataResult;
import core.utilities.results.Result;
import core.utilities.results.SuccessDataResult;
import core.utilities.results.SuccessResult;
import dataAccess.abstracts.UserDao;
import entities.concrete.User;

import java.util.List;

public class UserManager implements UserService {
    UserDao userDao;

    public UserManager(UserDao userDaoInstance) {
        userDao = userDaoInstance;
    }

    @Override
    public Result add(User user) {
        userDao.add(user);
        return new SuccessResult("başarıyla eklendi");
    }

    @Override
    public Result delete(User user) {
        userDao.delete(user);
        return new SuccessResult("başarıyla silindi");
    }

    @Override
    public Result update(User user) {
        userDao.update(user);
        return new SuccessResult("başarıyla güncellendi");
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(userDao.getAll());
    }

    @Override
    public DataResult<User> get(int id) {
        return new SuccessDataResult<>(userDao.get(user -> user.getId() == id));
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<>(userDao.get(u -> u.getEmail().equals(email)));
    }
}
