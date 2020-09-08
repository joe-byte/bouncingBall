
package hu.sagi.utkozogolyo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;



/**
 *
 */
public class GamePanel  {
    
    int kezdőX, kezdőY;  // A panel kezdő pontjai
    int szélesség,magasság;  // A panel szélesszége, magassága
    private Color háttérszín;   // A panel háttérszíne
    private Color szegélyszín;   // A panel szegélyszíne
    private static final Color DEFAULT_HÁTTÉR_SZÍN = Color.BLACK;
    private static final Color DEFAULT_SZEGÉLY_SZÍN = Color.YELLOW;

    public GamePanel() {
        
    }

    public GamePanel(int kezdőX, int kezdőY, int szél , int magas) {
        this.kezdőX = kezdőX;
        this.kezdőY = kezdőY;
        this.szélesség = szél;
        this.magasság = magas;
        this.háttérszín = háttérszín;
        this.szegélyszín = szegélyszín;
    }
    
    
    public GamePanel(int kezdőX, int kezdőY, int szél, int magas, Color háttérszín, Color szegélyszín) {
        this.kezdőX = kezdőX;
        this.kezdőY = kezdőY;
        this.szélesség = szél;
        this.magasság = magas;
        this.háttérszín = háttérszín;
        this.szegélyszín = szegélyszín;
    }
    
       // A panel újboli beállítása kezdeti helyzetbe állítása ha engedélyezve van a átméretezés
    
   public void set(int x, int y, int szél, int magas) {
      kezdőX = x;
      kezdőY = y;
      szélesség = szél;
      magasság =  magas;
   }

   /* A panel megrajzolésa.
     */
   public void rajzolás(Graphics g) {
      g.setColor(szegélyszín);
      g.fillRect(kezdőX, kezdőY, szélesség, magasság );
      g.setColor(háttérszín);
      g.fillRect(kezdőX+1, kezdőY+1, szélesség - 2, magasság - 2);
      Toolkit.getDefaultToolkit().sync();
   }
    
}
