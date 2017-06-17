/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerspack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import mobspack.Mob;
import td.MFrame;
import td.Point;
import static towerspack.Tower.size;

/**
 *
 * @author 1
 */
public class TowerLvl4 extends Tower{
    
    public TowerLvl4(MFrame frame, Point center, ArrayList<Mob> mobs) {
        super(frame, center, mobs);
        this.attackDamage = 120;
        this.currentPriceOfUpdate = 400;
        this.currentLvl = 4;
    }

    @Override
    public void paint(Graphics g) {
        Image img;
        
        String adr = "images\\towers\\tlvl4.png";
        
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
