package HW06;

/*
Cat.java
Since a Cat is also a Pet, this class must inherit from parent class Pet. This class is concrete.

Variables:
int miceCaught
Constructors:
Cat(String name, double health, int painLevel, int miceCaught)
miceCaught
If miceCaught passed in is less than 0, set miceCaught to 0
Cat(String name, double health, int painLevel)
Default miceCaught is 0
Methods:
getters for all instance fields, which should be camelCase with the variable name, e.g. a variable named hello should have a getter getHello()
int treat():
Should heal()
Returns the time taken (in minutes) to treat the pet. Round all values up.
if number of miceCaught is less than 4, the minutes for treatment is equal to (painLevel *2)/health
if miceCaught is in between 4 and 7 inclusive the minutes for treatment equals painLevel/health
if miceCaught is greater than 7, the minutes for treatment equals painLevel/(health*2)
void speak():
Calls parent method
Prints “meow” number of times of miceCaught
Eg: if miceCaught = 3
Print “meow meow meow”
ALL UPPERCASE if painLevel is greater than 5, not inclusive
boolean equals(Object o):
Uses the equals() method in Pet as part of the decision-making with the additional condition of miceCaught being the same
 */

public class Cat extends Pet {
    private int miceCaught;

    public Cat(String name, double health, int painLevel, int miceCaught){
        super(name, health, painLevel);
        this.miceCaught = Math.max(0, miceCaught);
    }

    public Cat(String name, double health, int painLevel){
        this(name, health, painLevel, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Cat){
            Cat c = (Cat) obj;
            return c.getMiceCaught() == this.miceCaught;
        } else {
            return false;
        }
    }

    public void speak(){
        super.speak();
        for (int i=0; i < this.painLevel; i++){
            if (this.painLevel > 5){
                System.out.print("MEOW");
            } else {
                System.out.print("meow");
            }
            if (i != this.painLevel - 1){
                System.out.print(" ");
            }
        }
        System.out.print("\n");
    }

    public int getMiceCaught() {
        return miceCaught;
    }

    @Override
    public int treat() {
        int result;
        if (this.miceCaught < 4) result = (int) Math.ceil((this.painLevel * 2) / this.health);
        else if (this.miceCaught <= 7) result = (int) Math.ceil(this.painLevel / this.health);
        else result = (int) Math.ceil(this.painLevel / (this.health * 2));
        heal();
        return result;
    }
}
