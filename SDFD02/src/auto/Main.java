package auto;

public class Main{
    public static void main(String[] args) {
    
        Car myCar = new Car(); //calls the contstructor function

        myCar.setMake("Toyota");
        myCar.setRegistration("SMH1234D");
        myCar.pubcomment = "This is my car!"; //This is a public and get be accessed directly without a setter

        System.out.printf("make: %s, registration: %s, Comment: %s",myCar.getMake(), myCar.getRegistration(),myCar.pubcomment);

        
        FlyingCar myFlyingCar = new FlyingCar();
        myFlyingCar.setRegistration("SMH5678D"); //uses the parents method and attibute to set it's own
        System.out.printf("registration (override): %s",myFlyingCar.getRegistration());

        // child object can be parent object
        Car mynewCar = new FlyingCar(); 
        mynewCar = new ElectricCar(); 
        Car myothernewCar = new ElectricCar();
        
        if (mynewCar instanceof FlyingCar) {
            FlyingCar mynewFlyingCar = (FlyingCar)mynewCar; //casting into myFlyingCar2 not necessary
            System.out.println("mynewCar is flying car");
        } else if (mynewCar instanceof ElectricCar){
            ElectricCar mynewElectricCar = (ElectricCar)mynewCar; //have to create a new ec? not necessary
            System.out.println("mynewCar is a electric car");
        } else {
            System.out.println("mynewCar is a car");
        }

        //Try:
        // myFlyingCar = (FlyingCar)c; //force electric car to flying car, causing a cast error during runtime. This cannot be detected during compile time.

    }

}
