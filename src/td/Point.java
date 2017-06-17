
package td;


public class Point {
    private double x;
    private double y;
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public Point(Point obj){
        this(obj.x, obj.y);
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public double getX(){
        return this.x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public double getY(){
        return this.y;
    }
    
    public static Point rotatePointByCenter(Point obj, Point center, double angle){
        return new Point(center.getX()+(obj.getX()-center.getX())*Math.cos(angle)+
                (center.getY()-obj.getY())*Math.sin(angle),
                         center.getY()+(obj.getX()-center.getX())*Math.sin(angle)+
                (obj.getY()-center.getY())*Math.cos(angle));
    }
    
    @Override
    public boolean equals(Object obj){
        Point p = (Point)obj;
        return (this.x == p.x && this.y == p.y);
    }
}
