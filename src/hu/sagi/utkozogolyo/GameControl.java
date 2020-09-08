
package hu.sagi.utkozogolyo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class GameControl extends JPanel{
    
   private int játékterületszélesség;
   private int játékterületmagasság;
   private GamePanel gPanel; 
   private JátékTér jtér;
   private JátékKontrolPanel KontrolPanel;
   private Golyo golyo;
   private static final int UPDATE_RATE = 10;
   private int contpmag;
   
    public GameControl(int jtszélesség, int jtmagasság) {
        játékterületszélesség = jtszélesség;
        játékterületmagasság = jtmagasság;
        
       Random rand = new Random();
      int sugár = 20;
      int középpontX = rand.nextInt(játékterületszélesség - sugár * 2 - 20) + sugár + 10;
      int középpontY = rand.nextInt(játékterületmagasság - sugár * 2 - 20) + sugár + 10;
      int sebesség = 11;
      int sebességIránySzögF = rand.nextInt(360);
      golyo = new Golyo(középpontX, középpontY, sugár, sebesség, sebességIránySzögF, Color.BLUE);
        
        //this.setBorder(new LineBorder(Color.blue, 3));

        gPanel = new GamePanel(0, 0, játékterületszélesség, 
                játékterületmagasság, Color.BLACK, Color.RED);
        KontrolPanel = new JátékKontrolPanel();
        jtér = new JátékTér();
        
        setLayout(new BorderLayout());
        add(BorderLayout.SOUTH, KontrolPanel);
        add(BorderLayout.CENTER, jtér );
        
        
        jtér.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
               Component c = (Component)e.getSource();
               Dimension dim = c.getSize();
                játékterületszélesség = dim.width;
                játékterületmagasság = dim.height;
                gPanel.set(0, 0, játékterületszélesség, játékterületmagasság);
            }
            
        });
        
        Jstart();      
    }

    private void Jstart() {
        Thread gameThread = new Thread() {
         public void run() {
            long kezdés_ms, vége_ms, pihen_ms;
            while (true) {
               kezdés_ms = System.currentTimeMillis();
               jFrissít();
              
               repaint();
               vége_ms = System.currentTimeMillis();
               pihen_ms = 1000 / UPDATE_RATE + vége_ms - kezdés_ms;
               if (pihen_ms < 2) pihen_ms = 2;
               
                try {
                    
                    Thread.sleep(pihen_ms);
                } catch (InterruptedException ex) {
                   String msg = String.format("Thread interrupted: %s", ex.getMessage());
                
                    JOptionPane.showMessageDialog(null, msg, "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
         }
      };
      gameThread.start();  
    }
    
    private void jFrissít() {
        golyo.mozgatásFalÉrzékelés(gPanel);
    }
    
    class JátékTér extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);   
       
            gPanel.rajzolás(g);
            golyo.rajzolás(g);
            Toolkit.getDefaultToolkit().sync();
        }
        @Override
        public Dimension getPreferredSize() {
            return (new Dimension(játékterületszélesség, játékterületmagasság));
        }
    }
    
    class JátékKontrolPanel extends JPanel { 
        
        public JátékKontrolPanel()  {
            
            ImageIcon szünetIcon;
            szünetIcon = new ImageIcon("src/resources/szünet.png");

            JButton  beáll = new JButton("Beállítás");

           JCheckBox szünetg = new JCheckBox("szünet", szünetIcon,true);
 
           beáll.setMargin(new Insets(2,8,3,8));
           beáll.setBackground(Color.ORANGE);
           szünetg.setBackground(Color.ORANGE);
           szünetg.setMargin(new Insets(0,2,0,2));
           this.setBackground(Color.CYAN);
           this.add(szünetg);
           this.add(beáll);
            
        }       
    }
   
}
