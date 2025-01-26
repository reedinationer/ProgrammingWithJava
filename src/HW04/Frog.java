package HW04;

public class Frog {
    private String name;
    private int age; // in months
    private double tongueSpeed;
    private boolean isFroglet;
    private static String species = "Rare Pepe";
    private final static int DEFAULT_AGE = 5;
    private final static double DEFAULT_TONGUE_SPEED = 5;

    public Frog(String name, int age, double tongueSpeed){
        this.name = name;
        this.age = age;
        this.tongueSpeed = tongueSpeed;
        if (this.age > 1 && this.age < 7){
            this.isFroglet = true;
        }
    }
    public Frog(String name, double ageInYears, double tongueSpeed){
        this(name, 0, tongueSpeed);
        this.age = (int) Math.round(ageInYears * 12);
        if (this.age > 1 && this.age < 7){
            this.isFroglet = true;
        }
    }
    public Frog(String name){
        this(name, DEFAULT_AGE, DEFAULT_TONGUE_SPEED);
    }
    public void grow(int numMonths){
        for (int i=0; i < numMonths; i++){
            if (this.age < 12){
                this.tongueSpeed += 1;
            } else if (this.age >= 30 && this.tongueSpeed > 5){
                this.tongueSpeed -= 1;
            }
            this.age += 1;
        }
        if (this.age > 1 && this.age < 7){
            this.isFroglet = true;
        }
    }
    public void grow(){
        grow(1);
    }
    public void eat(Fly f){
        if (f.isDead()){
            return;
        } else if (this.tongueSpeed > f.getSpeed()){
            // The fly has been caught
            if (f.getMass() >= 0.5 * this.age){
                grow();
            }
            f.setMass(0);
        } else {
            // The fly was not caught
            f.grow(1);
        }
    }
    public String toString(){
        if (this.isFroglet){
            return String.format("My name is %s and I’m a rare froglet! I’m %d months old and my tongue has a speed of %.2f.", this.name, this.age, this.tongueSpeed);
        } else {
            return String.format("My name is %s and I’m a rare frog. I’m %d months old and my tongue has a speed of %.2f.", this.name, this.age, this.tongueSpeed);
        }
    }

    public static void setSpecies(String species) {
        Frog.species = species;
    }
    public static String getSpecies(){
        return species;
    }
}
