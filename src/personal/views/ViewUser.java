package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() throws Exception{
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String firstName = prompt("Имя: ");
                    String lastName = prompt("Фамилия: ");
                    String phone = prompt("Номер телефона: ");
                    userController.saveUser(new User(firstName, lastName, phone));
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(id);
                        System.out.println(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    userController.readUsers().forEach(System.out::println);
                    break;
                case UPDATE:
                    String idUpd = prompt("Идентификатор пользователя: ");
                    String firstNameUpd = prompt("Имя: ");
                    String lastNameUpd = prompt("Фамилия: ");
                    String phoneUpd = prompt("Номер телефона: ");
                    userController.editUser(new User(idUpd, firstNameUpd, lastNameUpd, phoneUpd));
                    break;
                case DELETE:
                    String idDel = prompt("Идентификатор пользователя: ");
                    userController.deleteUser(idDel);
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
