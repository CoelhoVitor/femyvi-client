
package model;

import javax.swing.JLabel;

public class ServerStatus {
    
    private static boolean server1;
    
    private static boolean server2;

    public ServerStatus() {
    }
    
    public static void handleLabel(JLabel s1, JLabel s2) {
        if (server1) {
            s1.setText("Online");
        } else {
            s1.setText("Offine");
        }
        
        if (server2) {
            s2.setText("Online");
        } else {
            s2.setText("Offine");
        }
    }

    public boolean isServer1() {
        return server1;
    }

    public void setServer1(boolean server1) {
        ServerStatus.server1 = server1;
    }

    public boolean isServer2() {
        return server2;
    }

    public void setServer2(boolean server2) {
        ServerStatus.server2 = server2;
    }
    
}
