package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryCSV implements Repository{
    private UserMapperCSV mapper = new UserMapperCSV();
    private FileOperation fileOperation;

    public RepositoryCSV(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {
        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        return id;
    }

    @Override
    public void updateUser(User user) throws Exception {
        deleteUser(user.getId());
        List<User> users = getAllUsers();
        saveUser(user, users);
        
    }

    @Override
    public void deleteUser(String id) throws Exception {
        List<User> users = getAllUsers(); 
        users.remove(findUser(id, users));
        saveUsers(users);
    }

    private User findUser(String id, List<User> users) throws Exception{
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new Exception("User not found");
    }

    private void saveUser(User user, List<User> users){
        users.add(user);
        saveUsers(users);
    }

    private void saveUsers(List <User> users){
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);

    }
    
}
