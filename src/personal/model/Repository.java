package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);
    void updateUser(User user) throws Exception;
    void deleteUser(String id) throws Exception;
}
