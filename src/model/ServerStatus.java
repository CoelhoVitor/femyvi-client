
package model;

import java.awt.Color;
import javax.swing.JLabel;

public class ServerStatus {
    
    private boolean online;
    
    private JLabel labelServer;

    public ServerStatus() {
    }

    public ServerStatus(boolean online, JLabel labelServer) {
        this.online = online;
        this.labelServer = labelServer;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
        if (online) {
            this.labelServer.setText("Online");
            this.labelServer.setForeground(Color.GREEN);
        } else {
            this.labelServer.setText("Offline");
            this.labelServer.setForeground(Color.RED);
        }
    }

    public JLabel getLabelServer() {
        return labelServer;
    }

    public void setLabelServer(JLabel labelServer) {
        this.labelServer = labelServer;
    }
    
}
