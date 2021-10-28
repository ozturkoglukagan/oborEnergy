public class EnergyPrice {
    
    public int day;
    public int month;
    public int year;
    
    public String date=day+"/"+month+"/"+year;
    
    public double hour;
    
    public double value;

    public EnergyPrice(){
        this.date="error";
        this.hour=0;
        this.value=0;
    }
    
    public EnergyPrice(String date,double time,double value){
        this.date=date;
        this.hour=time;
        this.value=value;
    }
    

    public void setValue(double value){
        this.value=value;
    }

    public double getValue(){
        return this.value;

    }
    
}
