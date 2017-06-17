
package mobspack;

import java.awt.*;
import td.Field;
import td.MFrame;
import td.Point;

public abstract class Mob extends Thread{
    
    protected Point center;
    protected Point size;
    protected Point vision;
    protected int moveSpeed;
    protected int maxHealthPoints;
    protected int currentHealthPoints;
    protected int attackDamage;
    protected int currentLvl;
    protected int maxLvl;
    protected double angle;
    protected int priceForKill;
    protected MFrame frame;
    protected boolean dead = false;
    
    public Mob(MFrame frame){
        this.size = new Point(50, 50);
        this.center = new Point(0 + size.getX()/2, 90 - size.getY());
        this.vision = new Point(center.getX() + size.getX()/2 + 20, center.getY());
        this.attackDamage = 0;
        this.currentHealthPoints = 0;
        this.currentLvl = 0;
        this.maxHealthPoints = 0;
        this.maxLvl = 5;
        this.moveSpeed = 5;
        this.angle = Math.PI/2;
        this.frame = frame;
        this.priceForKill = 0;
    }
    
    public void setCurrentHealthPoints(int HP){
        this.currentHealthPoints = HP;
    }
    
    public int getCurrentHealthPoints(){
        return this.currentHealthPoints;
    }
    
    public boolean getDead(){
        return dead;
    }
    
    public int getPriceForKill(){
        return this.priceForKill;
    }
    
    public boolean containsPoint(Point p){
        Polygon polygon = new Polygon();
        polygon.addPoint((int)(center.getX()-size.getX()/2), (int)center.getY());
        polygon.addPoint((int)(center.getX()), (int)(center.getY()-size.getY()/2));
        polygon.addPoint((int)(center.getX()+size.getX()/2), (int)center.getY());
        polygon.addPoint((int)(center.getX()), (int)(center.getY()+size.getY()/2));
        if(polygon.contains(p.getX(), p.getY()))
            return true;
        return false;
        
    }
    
    public Point getSize(){
        return size;
    }
    
    public Point getCenter(){
        return center;
    }
    
    public void move(){
        center.setX(center.getX()+moveSpeed*Math.cos(angle-Math.PI/2));
        center.setY(center.getY()+moveSpeed*Math.sin(angle-Math.PI/2));
        vision.setX(vision.getX()+moveSpeed*Math.cos(angle-Math.PI/2));
        vision.setY(vision.getY()+moveSpeed*Math.sin(angle-Math.PI/2));
    }
    
    public boolean onRoad(){
        if(!Field.getRoadForMobs().contains(this.vision.getX(), this.vision.getY())){
            return false;
        }
        return true;
    }
    
    public void changeWay(){
        angle += Math.PI/2;
        if(angle == Math.PI*2){
            angle = 0;
        }
    }
    
    @Override
    public void run(){
        while(true){
            if(currentHealthPoints <= 0){
                break;
            }
            if(frame.getGamePanel().getField().getCastle().containsPoint(center)){
                frame.getGamePanel().getField().getCastle().setCurrentHealthPoints(
                    frame.getGamePanel().getField().getCastle().getCurrentHealthPoints()-attackDamage);
                frame.getGamePanel().getPanel().repaint();
                break;
            }
            if(onRoad()){
                move();  
                frame.getGamePanel().getPanel().repaint();
            }
            else{
                changeWay();
                vision = new Point(Point.rotatePointByCenter(vision, center, Math.PI/2));
                frame.getGamePanel().getPanel().repaint();
            }
            try{
                Thread.sleep(30);
            }catch(Exception e){}
        }
        dead = true;
    }
    
    public abstract void paint(Graphics g);
}
