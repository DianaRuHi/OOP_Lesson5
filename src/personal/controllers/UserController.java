package personal.controllers;

import personal.model.Repository;
import personal.model.RepositoryFile;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    
    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> readUsers(){
        return repository.getAllUsers();
    }

    public void editUser(User user) throws Exception{
        repository.updateUser(user);
    }

    //
    public void deleteUser(String id) throws Exception{
        repository.deleteUser(id);
    }

}
