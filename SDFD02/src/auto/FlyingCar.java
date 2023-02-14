package auto;

public class FlyingCar extends Car{

    private float altitude = 0f;

    public FlyingCar() {     } // Removed so that all flying cars need a registration number

    public FlyingCar(String registration) {
        this.setRegistration(registration);
    }

    public float getAltitude() { return altitude;} //from line 5
    public void setAltitude(float altitude) {this.altitude = altitude;} //from input altitude set altitude (also can use altitude = altitude but bad practice)

    public void climb (float feet){
        if(this.altitude < 100){
            this.altitude += feet; 

            if(this.altitude > 100){
                this.altitude = 100; 
            }
        }
    }

    public void climb(){//overloaded default
        this.climb(5);
    }

    @Override() 
    public String getRegistration(){
        return "F-" + super.getRegistration();
        //if you don't include this, it calls itself
        //super refers to the parent's class]\
    }

}

 