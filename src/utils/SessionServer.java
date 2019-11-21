package utils;

import model.ServerStatus;

public class SessionServer {

    private static SessionServer instance = null;
    
    private ServerStatus server1;
    
    private ServerStatus server2;

    public SessionServer() {
    }
    
    public static void setInstance(ServerStatus s1, ServerStatus s2) {
        instance = new SessionServer();
        instance.server1 = s1;
        instance.server2 = s2;
    }

    public static SessionServer getInstance() {
        if (instance == null) {
            instance = new SessionServer();
        }
        return instance;
    }

    public ServerStatus getServer1() {
        return server1;
    }

    public void setServer1(ServerStatus server1) {
        this.server1 = server1;
    }

    public ServerStatus getServer2() {
        return server2;
    }

    public void setServer2(ServerStatus server2) {
        this.server2 = server2;
    }
    
}
