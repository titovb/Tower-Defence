
package td;

import java.awt.*;

public class Castle {
    private Point center; //центральная точка
    private Point size; //размер
    private int maxHealthPoints; //максимальное количество хп
    private int currentHealthPoints; //текущее количество хп
    private MFrame frame;
    
    public Castle(MFrame frame){
        this.center = new Point(968, 517);
        this.size = new Point(300, 300);
        this.maxHealthPoints = 1000;
        this.currentHealthPoints = this.maxHealthPoints; 
        this.frame = frame;
    }
    
    public boolean containsPoint(Point point){
        Polygon polygon = new Polygon();
        polygon.addPoint((int)(center.getX()-size.getX()/2), (int)center.getY());
        polygon.addPoint((int)(center.getX()), (int)(center.getY()-size.getY()/2));
        polygon.addPoint((int)(center.getX()+size.getX()/2), (int)center.getY());
        polygon.addPoint((int)(center.getX()), (int)(center.getY()+size.getY()/2));
        if(polygon.contains(point.getX(), point.getY()))
            return true;
        return false;
    }
    
    public int getCurrentHealthPoints(){
        return this.currentHealthPoints;
    }
    
    public void setCurrentHealthPoints(int HP){
        this.currentHealthPoints = HP;
    }
    
    private int lengthOfHP(){
        double currentProcentOfHP = currentHealthPoints / (maxHealthPoints/100);
        return (int)(currentProcentOfHP*(size.getX()/100));
    }
    
    public void paint(Graphics g){
        Image img;
        
        String adr = "images\\castle\\Castle.png";
        img = Toolkit.getDefaultToolkit().getImage(adr);
        
        g.drawImage(img, (int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2), (int)size.getX(), (int)size.getY(), frame.getGamePanel().getPanel());
        
        g.setColor(Color.BLACK);
        g.drawRect((int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2+20), (int)size.getX(), 5);
        if(lengthOfHP()>=(size.getX()/100)*60)
            g.setColor(Color.GREEN);
        if(lengthOfHP()<(size.getX()/100)*60&&lengthOfHP()>=(size.getX()/100)*30)
            g.setColor(Color.ORANGE);
        if(lengthOfHP()<=(size.getX()/100)*30)
            g.setColor(Color.RED);
        g.fillRect((int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2+20), lengthOfHP(), 5);
    }
    
}
