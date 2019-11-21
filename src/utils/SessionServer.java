package utils;

import model.ServerStatus;

public class SessionServer {

    private static ServerStatus instance = null;

    public SessionServer() {
    }

    public static void setInstance(boolean server1, boolean server2) {
        instance = new ServerStatus();
        instance.setServer1(server1);
        instance.setServer2(server2);
    }

    public static ServerStatus getInstance() {
        if (instance == null) {
            instance = new ServerStatus();
        }
        return instance;
    }
    
}
