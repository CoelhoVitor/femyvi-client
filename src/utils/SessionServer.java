package utils;

public class SessionServer {

    private static SessionServer instance = null;

    public SessionServer() {
    }

    public static void setInstance() {
        instance = new SessionServer();
    }

    public static SessionServer getInstance() {
        if (instance == null) {
            instance = new SessionServer();
        }
        return instance;
    }
    
}
