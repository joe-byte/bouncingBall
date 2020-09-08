
package hu.sagi.utkozogolyo;

import java.awt.EventQueue;



public class UtkozoGolyo {
    
    static MainFrame mainFrame;
    static final int B_WIDTH = 640;
    static final int B_HEIGHT =480;

    public UtkozoGolyo() {
  
            mainFrame = new MainFrame(B_WIDTH, B_HEIGHT);
    }
    


    public static void main(String[] args) {
        
            EventQueue.invokeLater(() -> {
            UtkozoGolyo ex = new UtkozoGolyo();
            mainFrame.setVisible(true);
        });
    }
    
}
