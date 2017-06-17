/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobspack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import td.MFrame;

/**
 *
 * @author 1
 */
public class MobLvl6 extends Mob{
    public MobLvl6(MFrame frame){
        super(frame);
        this.attackDamage = 60;
        this.maxHealthPoints = 600;
        this.currentHealthPoints = maxHealthPoints;
        this.currentLvl = 6;
        this.priceForKill = 60;
    }
    
    private int lengthOfHP(){
        double currentProcentOfHP = currentHealthPoints / (maxHealthPoints/100);
        return (int)(currentProcentOfHP*(size.getX()/100));
    }
    
    @Override
    public void paint(Graphics g) {
        Image img;
        
        String adr = "images\\mobs\\mlvl6.png";
        
        img = Toolkit.getDefaultToolkit().getImage(adr);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(img, (int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2), (int)size.getX(), (int)size.getY(), frame.getGamePanel().getPanel());
        g2.setColor(Color.BLACK);
        g2.drawRect((int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2-5), (int)size.getX(), 5);
        if(lengthOfHP()>=(size.getX()/100)*60)
            g2.setColor(Color.GREEN);
        if(lengthOfHP()<(size.getX()/100)*60&&lengthOfHP()>=(size.getX()/100)*30)
            g2.setColor(Color.ORANGE);
        if(lengthOfHP()<=(size.getX()/100)*30)
            g2.setColor(Color.RED);
        g2.fillRect((int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2-5), lengthOfHP(), 5);
        
    }
}
