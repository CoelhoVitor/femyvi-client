package controller;

import connection.Ports;
import connection.UserLogin;
import model.UserMessage;

public class UserController {

    public UserController() {
    }
    
    public static void SendUser(String name, String password) {
        // create user message
        UserMessage um = new UserMessage();
        
        um.setName(name);
        um.setPassword(password);
        
        // send user message
        // arrumar porta
        UserLogin ul = new UserLogin(Ports.AUTH);
        ul.run(um);
        
    }
    
}
