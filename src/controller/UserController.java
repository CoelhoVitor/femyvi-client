package controller;

import connection.Ports;
import connection.UserLogin;
import model.UserMessage;

public class UserController {

    public UserController() {
    }

    public static boolean SendUser(String login, String password) {
        // create user message
        UserMessage um = new UserMessage();

        um.setLogin(login);
        um.setPassword(password);

        // send user message
        UserLogin ul = new UserLogin(Ports.AUTH);
        boolean isValidUser = ul.run(um);
        
        return isValidUser;
    }

}
