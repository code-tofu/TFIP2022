package auto;

public class Car{
	
	private String registration;
	private String make;
	boolean started = false;
    private int counter = 0;
    public String pubcomment = "";

	public Car(){}
    //note that 

    public String getRegistration() { return registration; }
    public void setRegistration(String registration) { this.registration = registration;}
    // note that this is void because the setter is accessing the object's members, rather than returning a value to the accessor

    public String getMake() { return make;}
    public void setMake(String make) { this.make = make;}

    public boolean isStarted() { return started;}
    public void setStarted(boolean started) {
        //redundant to start() - not good, because no validation and no counter increase - remove this so that Started is a read only property. hence:
        if (this.started) {
            this.start();
        } else {
            this.stop();
        }
        //this.started = started;
    } 



    public void start(){
        if(this.started){
            System.out.println("Car is Running");
        }else{
            this.started = true;
            this.counter++;
            System.out.println("Vroom Vroom!");
        }
    }
	
    public void stop(){
        if (!this.started) {
            System.out.println("Car is stopped");
        }
        if (!this.started) {
            System.out.println("Sputter...stop");
        }
    }

    public boolean needToService(){
        return (this.counter >5);
    }

    public void serviceCar(){
        System.out.println("Servicing Car...");
        this.counter = 0;
    }
    
}