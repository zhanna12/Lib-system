package kz.iitu.library.Services;



import kz.iitu.library.models.User;

import java.util.List;

public interface UserServ {

    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(Long id, User user);
}
