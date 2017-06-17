
package mobspack;

import java.awt.*;
import td.MFrame;

public class MobLvl1 extends Mob{
    
    public MobLvl1(MFrame frame){
        super(frame);
        super.attackDamage = 10;
        super.maxHealthPoints = 100;
        super.currentHealthPoints = maxHealthPoints;
        super.currentLvl = 1;
        super.priceForKill = 10;
    }
    
    private int lengthOfHP(){
        double currentProcentOfHP = currentHealthPoints / (maxHealthPoints/100);
        return (int)(currentProcentOfHP*(size.getX()/100));
    }
    
    @Override
    public void paint(Graphics g) {
        Image img;
        
        String adr = "images\\mobs\\mlvl1.png";
        
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
