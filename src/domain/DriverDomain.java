package domain;

public class DriverDomain{
    private String staffid;
    private String staffname;
    private String email;
    private int contactno;
    private String gender;
    private int icno;
    private String address;
    private String city;
    private String state;
    private int postcode;
    private String position;
            
    public DriverDomain(){
        
    }
    
    public DriverDomain(String staffid, String staffname, String email, int contactno,String gender,int icno,String address, String city,String state,int postcode, String position){ //for add user
        this.staffid = staffid;
        this.staffname = staffname;
        this.email = email;
        this.contactno = contactno;
        this.gender = gender;
        this.icno = icno;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.position = position;
  
    }
   
    public String getStaffid(){
        return staffid;
    }
    
    public String getStaffname(){
        return staffname;
    }
    
    public String getEmail(){
        return email;
    }
    
      public int getContactno(){
        return contactno;
    }
    
    public String getGender(){
        return gender;
    }
    
    public int getIcno(){
        return icno;
    }
    
        public String getAddress(){
        return address;
    }
    
    public String getCity(){
        return city;
    }
    
      public String getState(){
        return state;
    }
    
    public int getPostcode(){
        return postcode;
    }
    
    public String getPosition(){
        return position;
    }
    
    public void setStaffid(String staffid){
        this.staffid = staffid;
    }
    
    public void setStaffname(String staffname){
          this.staffname = staffname;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
      public void setContactno(int contactno){
       this.contactno = contactno;
    }
    
    public void setGender(String gender){
           this.gender = gender;
    }
    
    public void setIcno(int icno){
       this.icno = icno;
    }
    
     public void setAddress(String address){
           this.address = address;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
      public void setState(String state){
       this.state = state;
    }
    
    public void setPostcode(int postcode){
           this.postcode = postcode;
    }
    
    public void setPosition(String position){
       this.position = position;
    }
    
    public String toString(){
        return "This is a driver object.";
    }
}