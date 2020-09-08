
package hu.sagi.utkozogolyo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Formatter;


public class Golyo {
   float középpontX, középpontY;  // A labda középpontja
   float sebességX, sebességY; // A labda sebessége x és y irányokba
   float sugár;         // A labda  sugara
   private Color szín;  // A labda színe
   private static final Color DEFAULT_SZÍN = Color.BLUE;
   private float sürűség = 7.8F;
   
   private StringBuilder sb = new StringBuilder();
   private Formatter formatter = new Formatter(sb);
   
     /**
    * A kunstruktor átalakítja a polár koordinátában megadott sebességet 
    * Descartes koordináta értékeké
    */
   public Golyo(float kpx, float kpy, float sugár, float sebesség, float szögFokokban,
         Color szín) {
      this.középpontX = kpx;
      this.középpontY = kpy;
      // Convert (speed, angle) to (x, y), with y-axis inverted
      this.sebességX = (float)(sebesség * Math.cos(Math.toRadians(szögFokokban)));
      this.sebességY = (float)(-sebesség * (float)Math.sin(Math.toRadians(szögFokokban)));
      String xv =String.format("Xv: %3.0f", sebességX);
      String yv =String.format("Yv: %3.0f", sebességY);
      System.out.println(xv + "    " + yv);
      
      String x =String.format("X: %3.0f", középpontX);
      String y =String.format("Y: %3.0f", középpontY);
      System.out.println(x + "    " + y);
      
      this.sugár = sugár;
      this.szín = szín;
   }
   
   public Golyo(float kpx, float kpy, float sugár, float sebesség, float sebességIránySzögF) {
      this(kpx, kpy, sugár, sebesség, sebességIránySzögF, DEFAULT_SZÍN);
   }
   
   public void rajzolás(Graphics g) {
      g.setColor(szín);
      g.fillOval((int)(középpontX - sugár), (int)(középpontY - sugár), (int)(2 * sugár), (int)(2 * sugár));
      Toolkit.getDefaultToolkit().sync();
   }
   
   public void mozgatásFalÉrzékelés(GamePanel panel) {
      // A labda mozgásának korlátainak kiszámítása
      float labdaMinX = panel.kezdőX + sugár;
      float labdaMinY = panel.kezdőY + sugár;
      float labdaMaxX = panel.szélesség - panel.kezdőX - 1 - sugár;
      float labdaMaxY = panel.magasság - panel.kezdőY - 1 - sugár;
      // A labda mozgatása
      középpontX += sebességX;
      középpontY += sebességY;
      
      String x =String.format("X: %3.0f", középpontX);
      String y =String.format("Y: %3.0f", középpontY);
      System.out.println(x + "    " + y);
      
      if (középpontX < labdaMinX ) {
          sebességX = -sebességX ;
          if (középpontX < 0){
            középpontX = labdaMinX-középpontX;
          }else 
              középpontX = 2*labdaMinX-középpontX;
      } else if (középpontX > labdaMaxX ) {
          sebességX = -sebességX ;
          középpontX = 2*labdaMaxX-középpontX;
      }   
      
      if (középpontY < labdaMinY ) {
          sebességY = -sebességY ;
          if (középpontX < 0){
            középpontY = labdaMinY-középpontY;
          }else
             középpontY = 2*labdaMinY-középpontY; 
      } else if (középpontY > labdaMaxY ) {
          sebességY = -sebességY ;
          középpontY = 2*labdaMaxY-középpontY;
      } 
 
   }
   
   public float getsebesség() {
    
        return 0;
   }
   
   public float getsebességIránySzögF() {
    
        return 0;
   }

   public float getTömeg() {
    
        return 0;
   }
    
   public float getKinetikusEnergia() {
    
        return 0;
   }

    @Override
    public String toString() {
        sb.delete(0, sb.length());
        formatter.format("Kp X=%3.0f  Y=%3.0f", középpontX,középpontY );
        return sb.toString();
    }
   
   
}
