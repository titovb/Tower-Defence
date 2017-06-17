
package td;

import java.awt.*;
import towerspack.Tower;

public class InfoPanel {
    private Point startPoint;
    private Point size;
    private int currentGold;
    private boolean pressedTower = false;
    private MFrame frame;
    private Tower tower;
    
    public InfoPanel(MFrame frame){
        this.frame = frame;
        startPoint = new Point(0, frame.getHeight()/2);
        size = new Point(200, frame.getHeight());
        currentGold = 100;
    }
    
    public void setCurrentGold(int gold){
        this.currentGold = gold;
    }
    
    public int getCurrentGold(){
        return this.currentGold;
    }
    
    public void towerPressed(Tower t){
        this.tower = t;
        pressedTower = true;
    }
    
    public void towerReleased(){
        this.tower = null;
        pressedTower = false;
    }
    
    public void paint(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)startPoint.getX(), (int)startPoint.getY(), (int)size.getX(), (int)size.getY());
        g.setFont(new Font("Times New Roman",Font.BOLD, 15));
        g.setColor(Color.BLACK);
        String str = "Gold: " + currentGold;
        g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+30);
        if(pressedTower){
            str = "Tower attack: " + tower.getAttackDamage();
            g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+60);
            str = "Current LVL: " + tower.getCurrentLvl();
            g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+90);
            if(tower.getCurrentLvl()==tower.getMaxLvl())
            {
                str = "Full Apdate!";
                g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+120);
            }
            else{
                str = "Update Cost: " + tower.getCurrentPriceOFUpdate();
                g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+120);
                str = "For update tower press U!";
                g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+150);
            }
        }
        else{
            str = "For build tower click on map.";
            g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+60);
            str = "Tower costs 50 gold.";
            g.drawString(str, (int)startPoint.getX()+10, (int)startPoint.getY()+90);
        }
    }
}
