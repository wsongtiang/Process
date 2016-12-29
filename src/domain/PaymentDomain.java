package domain; 

import java.io.Serializable;
import java.sql.Date;

import java.util.Objects;

public class PaymentDomain implements Serializable {
    private String paymentid;
    private String paymentmethod;
  


    public PaymentDomain() {
    }

    public  PaymentDomain(String paymentid) {
        this.paymentid = paymentid;
    }

    public PaymentDomain(String paymentid, String paymentmethod) {
        this.paymentid=paymentid;
        this.paymentmethod=paymentmethod;
      
        
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }


    public String getDeparturetime() {
        return paymentmethod;
    }

    public void setDeparturetime(String paymentmethod) {
        this.paymentmethod= paymentmethod;
    }
    
  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.paymentid);
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
        final PaymentDomain other = (PaymentDomain) obj;
        if (!Objects.equals(this.paymentid, other.paymentid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PaymentDomain{" + "paymentid=" + paymentid + ", paymentmethod=" + paymentmethod +'}';
    }
    
}
