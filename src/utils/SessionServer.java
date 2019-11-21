package utils;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import model.ServerStatus;

public class SessionServer {

    private static SessionServer instance = null;

    private ServerStatus server1;

    private ServerStatus server2;

    private ServerStatus serverSg;

    private JButton loginButton;

    private JTable filesTable;

    private JButton newFileButton;

    public SessionServer() {
    }

    public static void updateComponents() {
        if (instance.loginButton != null) {
            instance.loginButton.setEnabled(instance.allServersUp());
        }

        if (instance.filesTable != null) {
            instance.filesTable.setEnabled(instance.allServersUp());
        }
        
        if (instance.newFileButton != null) {
            instance.newFileButton.setEnabled(instance.allServersUp());
        }

    }

    public static void setInstance(ServerStatus s1, ServerStatus s2, ServerStatus sg) {
        instance = new SessionServer();
        instance.server1 = s1;
        instance.server2 = s2;
        instance.serverSg = sg;
        instance.loginButton = null;
        instance.newFileButton = null;
        instance.filesTable = null;
    }

    public static SessionServer getInstance() {
        if (instance == null) {
            instance = new SessionServer();
        }
        return instance;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
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

    public ServerStatus getServerSg() {
        return serverSg;
    }

    public void setServerSg(ServerStatus serverSg) {
        this.serverSg = serverSg;
    }

    private boolean allServersUp() {
        return instance.server1.isOnline()
                && instance.server2.isOnline()
                && instance.serverSg.isOnline();
    }

    public JTable getFilesTable() {
        return filesTable;
    }

    public void setFilesTable(JTable filesTable) {
        this.filesTable = filesTable;
    }

    public JButton getNewFileButton() {
        return newFileButton;
    }

    public void setNewFileButton(JButton newFileButton) {
        this.newFileButton = newFileButton;
    }

}
