package HW04;

public class Fly {
    private double mass;
    private double speed;
    final static private double DEFAULT_MASS = 5d;
    final static private double DEFAULT_SPEED = 10d;

    public Fly(double mass){
        this(mass, DEFAULT_SPEED);
    }

    public Fly(){
        this(DEFAULT_MASS, DEFAULT_SPEED);
    }

    public Fly(double mass, double speed){
        this.mass = mass;
        this.speed = speed;
    }

    public double getMass() {
        return mass;
    }

    public double getSpeed() {
        return speed;
    }
    public void setMass(double mass){
        this.mass = mass;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public String toString(){
        if (this.mass > 0){
            return String.format("I’m a speedy fly with %.2f speed and %.2f mass.", this.speed, this.mass);
        } else {
            return String.format("I’m dead, but I used to be a fly with a speed of %.2f", this.speed);
        }
    }
    public boolean isDead(){
        return this.mass == 0;
    }
    public void grow(int newMass){
        for (int m=0; m < newMass; m++){
            if (this.mass < 20){
                this.mass += 1;
                this.speed += 1;
            } else {
                this.mass += 1;
                this.speed -= 0.5f;
            }
        }
    }
}
