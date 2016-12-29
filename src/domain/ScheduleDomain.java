package domain; 

import java.io.Serializable;
import java.sql.Date;

import java.util.Objects;

public class ScheduleDomain implements Serializable {
    private String scheduleid;
    private Date departuredate;
    private String departuretime;
    private Date arrivaldate;
    private String arrivaltime;
    private String staffid;
    private String busid;
    private String routeid;
    private String status;
    private int totalseat;

    public ScheduleDomain() {
    }

    public  ScheduleDomain(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public ScheduleDomain(String scheduleid, Date departuredate,String departuretime,Date arrivaldate,String arrivaltime,String staffid,String busid, String routeid,String status, int totalseat) {
        this.scheduleid=scheduleid;
        this.departuredate=departuredate;
        this.departuretime=departuretime;
        this.arrivaldate=arrivaldate;
        this.arrivaltime=arrivaltime;
        this.staffid=staffid;
        this.busid=busid;
        this. routeid= routeid;
        this. status= status;
        this.totalseat =totalseat;
        
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime= departuretime;
    }
    
     public Date getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }
    
     public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }
    
     public String getBusid() {
        return busid;
    }

    public void setBusid(String busid) {
        this.busid= busid;
    }
    
     public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid= staffid;
    }

     public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid= routeid;
    }
    
     public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status= status;
    }
    
    public int getTotalSeat(){
        return totalseat;
    }
    
    public void setTotalSeat(int totalseat){
        this.totalseat = totalseat;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.scheduleid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScheduleDomain other = (ScheduleDomain) obj;
        if (!Objects.equals(this.scheduleid, other.scheduleid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleDomain{" + "scheduleid=" + scheduleid + ", departuredate=" + departuredate + ", departuretime=" + departuretime +",arrivaldate="+arrivaldate+",arrivaltime="+arrivaltime+",busid="+busid+"staffid="+staffid+",routeid=" +routeid+",status="+status+",totalseat="+totalseat+'}';
    }
    
}
