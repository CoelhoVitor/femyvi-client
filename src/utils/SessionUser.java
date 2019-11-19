package utils;

import model.UserMessage;

public class SessionUser {

    private static UserMessage instance = null;

    public SessionUser() {
    }
    
    public static void setInstance(String login, String password) {
        instance = new UserMessage();
        instance.setLogin(login);
        instance.setPassword(password);
    }
    
    public static UserMessage getInstance() {
        if (instance == null) {
            instance = new UserMessage();
        }
        return instance;
    }
    
}
