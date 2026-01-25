package repository.interfaces;

import entity.User;

public interface IUserRepository extends IRepository<User> {
    User findByUsername(String username);
}
