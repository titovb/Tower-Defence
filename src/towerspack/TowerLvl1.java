/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerspack;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import mobspack.Mob;
import td.MFrame;
import td.Point;

/**
 *
 * @author 1
 */
public class TowerLvl1 extends Tower{
    
    public TowerLvl1(MFrame frame, Point center, ArrayList<Mob> mobs) {
        super(frame, center, mobs);
        this.attackDamage = 30;
        this.currentPriceOfUpdate = 100;
        this.currentLvl = 1;
    }
    
    @Override
    public void paint(Graphics g){
        Image img;
        
        String adr = "images\\towers\\tlvl1.png";
        
        img = Toolkit.getDefaultToolkit().getImage(adr);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(img, (int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2), (int)size.getX(), (int)size.getY(), frame.getGamePanel().getPanel());
        
        Ellipse2D rangeEllipse = new Ellipse2D.Double();
        rangeEllipse.setFrameFromCenter(center.getX(), center.getY(), center.getX()+range, center.getY()+range);
        g2.setColor(Color.RED);
        g2.draw(rangeEllipse);
        
        if(labeled){
            g2.setColor(Color.GREEN);
            g2.fillOval((int)center.getX()-5, (int)(center.getY()-size.getY()/2)-5, 10, 10);
        }
    }
    
}
