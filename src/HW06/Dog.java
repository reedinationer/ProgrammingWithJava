package HW06;

/*
Dog.java
Since a Dog is also a Pet, this class must inherit from parent class Pet. This class is concrete.

Variables:
double droolRate
Constructors:
Dog(String name, double health, int painLevel, double droolRate)
droolRate - If droolRate is less than or equal to zero, set drool rate to 0.5
Dog(String name, double health, int painLevel)
Default droolRate is 5.0
Methods:
getters for all instance fields, which should be camelCase with the variable name, e.g. a variable named hello should have a getter getHello()
int treat():
Should heal()
Returns the time taken (in minutes) to treat the pet. Round values up.
if droolRate is less than 3.5, the minutes for treatment is (painLevel*2)/health
if droolRate is in between 3.5 and 7.5 inclusive, the minutes for treatment is painLevel/health
if droolRate is greater than 7.5, the minutes for treatment is painLevel/(health*2)
void speak():
Calls parent method
Prints “bark” number of times of the painLevel
e.g.: if painLevel = 3
Prints “bark bark bark”
ALL UPPERCASE if painLevel is greater than 5, not inclusive
boolean equals(Object o):
Uses the equals() method in Pet as part of the decision-making with the additional condition of droolRate being the same
 */

public class Dog extends Pet {
    double droolRate;

    public Dog(String name, double health, int painLevel, double droolRate){
        super(name, health, painLevel);
        if (droolRate <= 0){
            this.droolRate = 0.5;
        } else {
            this.droolRate = droolRate;
        }
    }

    public Dog(String name, double health, int painLevel){
        this(name, health, painLevel, 5.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Dog){
            Dog d = (Dog) obj;
            return d.getDroolRate() == this.droolRate;
        } else {
            return false;
        }
    }

    public double getDroolRate() {
        return droolRate;
    }

    public void speak(){
        super.speak();
        for (int i=0; i < this.painLevel; i++){
            if (this.painLevel > 5){
                System.out.print("BARK");
            } else {
                System.out.print("bark");
            }
            if (i != this.painLevel - 1){
                System.out.print(" ");
            }
        }
        System.out.print("\n");
    }

    @Override
    public int treat() {
        int result;
        if (this.droolRate < 3.5) result = (int) Math.ceil((this.painLevel * 2) / this.health);
        else if (this.droolRate <= 7.5) result = (int) Math.ceil(this.painLevel / this.health);
        else result = (int) Math.ceil(this.painLevel / (this.health * 2));
        heal();
        return result;
    }
}
