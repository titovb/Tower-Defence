
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
    
    private int lengthOfHealthPointsBar(){
        double currentProcentOfHealthPoints = currentHealthPoints / (maxHealthPoints/100);
        int maxProcents = 100;
        return (int)(currentProcentOfHealthPoints*(size.getX()/maxProcents));
    }
    
    private Color chooseColorForHealthPointsBar(){
        int maxProcents = 100;
        int minProcentForGreenColor = 61;
        int maxProcentForOrangeColor = 60;
        int minProcentForOrangeColor = 31;
        int maxProcentForRedColor = 30;
        Color returningColor =  null;
        
        if(lengthOfHealthPointsBar() >= (size.getX()/maxProcents) * minProcentForGreenColor)
                returningColor = Color.GREEN;
        if(lengthOfHealthPointsBar() <= (size.getX()/maxProcents) * maxProcentForOrangeColor && 
           lengthOfHealthPointsBar() >= (size.getX()/maxProcents) * minProcentForOrangeColor)
                returningColor = Color.ORANGE;
        if(lengthOfHealthPointsBar() <= (size.getX()/maxProcents) * maxProcentForRedColor)
                returningColor = Color.RED;
        
        return returningColor;
    }
    
    public void paintCastle(Graphics g){
        Image img;
        
        String adr = "images\\castle\\Castle.png";
        img = Toolkit.getDefaultToolkit().getImage(adr);
        
        g.drawImage(img, (int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2), (int)size.getX(), (int)size.getY(), frame.getGamePanel().getPanel());
        
        g.setColor(Color.BLACK);
        g.drawRect((int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2+20), (int)size.getX(), 5);
        
        g.setColor(chooseColorForHealthPointsBar());
        g.fillRect((int)(center.getX()-size.getX()/2), (int)(center.getY()-size.getY()/2+20), lengthOfHealthPointsBar(), 5);
    }
    
}
