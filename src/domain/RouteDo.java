package domain;

public class RouteDo{
    private String routeid;
    private String departuredest;
    private String arrivaldest;
    private double distance;
    private double price;
    
    public RouteDo(){
        this("","","",0.0,0.0);
    }
    
    public RouteDo(String routeid){
        this.routeid = routeid;
    }
    
    public RouteDo(String routeid, String departuredest, String arrivaldest, double distance,double price) {
        this.routeid = routeid;
        this.departuredest = departuredest;
        this.arrivaldest= arrivaldest;
        this.distance = distance;
        this.price = price;
    }

    public String getRouteid() {
        return routeid;
    }

    public String getDeparturedest() {
        return departuredest;
    }

    public String getArrivaldest() {
        return arrivaldest;
    }

    public double getDistance() {
        return distance;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public void setDeparturedest(String departuredest) {
        this.departuredest = departuredest;
    }

    public void setArrivaldest(String arrivaldest) {
        this.arrivaldest = arrivaldest;
    }
    
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RouteDo{" + "routeid=" + routeid + ", departuredest=" + departuredest + ", arrivaldest=" + arrivaldest +", distance=" + distance + ", price=" + price + '}';
    }
    
    
}