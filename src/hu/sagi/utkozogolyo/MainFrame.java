
package hu.sagi.utkozogolyo;


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import static javax.swing.SwingUtilities.getRootPane;
import javax.swing.border.LineBorder;



public class MainFrame extends JFrame {
    
    
    
    private final String GTITLE = "Pattogó labda";
    

    public MainFrame(int jtszélesség, int jtmagasság)  {
        
               
        GameControl gC = new GameControl(jtszélesség,jtmagasság);
        //add(gC);
        
        setUndecorated(true);
        //getRootPane().setSize(600, 50);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME); 
        setContentPane(gC);
        setTitle(GTITLE);
        //setSize(jtszélesség+18,jtmagasság+25);
        setResizable(true); //beállítja, hogy a keret átméretezhető-e
        //az ablakot úgy méretezi, hogy az illeszkedjen az előnyös méretéhez 
        //és elrendezéséhez illetve azok gyrekeihez
        pack(); 
        /* A két módszer meghívásának sorrendje is fontos. A setResizable () 
         * megváltoztatja a keret behelyezését néhány platformon, 
         * ennek a metódusnak a hívása a pack() metódus után 
         * hibás eredményekhez vezethet
        */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        System.out.println("main" + this.getSize());
        //System.out.println(cp.getSize());
    }
    
    
    
    
}
