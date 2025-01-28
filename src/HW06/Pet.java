package HW06;

/*
Pet.java
This class represents any pet that would seek consultation from the clinic.

Variables:
String name
double health
A percentage value ranging from 0.0 to 1.0
int painLevel
Ranges from 1 to 10
Constructor:
Pet(String name, double health, int painLevel)
health
If health passed in is greater than 1.0, set health to 1.0
If health passed in is less than 0.0, set health to 0.0
painLevel
If painLevel passed in is greater than 10, set pain level to 10
If painLevel passed in is less than 1, set pain level to 1
Methods:
getters for all instance fields, which should be camelCase with the variable name, e.g. a variable named hello should have a getter getHello()
int treat():
Should be an abstract method that returns the time taken (in minutes) to treat the pet
void speak():
This method prints “Hello! My name is “ with the pet’s name
If painLevel is greater than 5 prints the message in UPPERCASE
boolean equals(Object o):
Two Pet objects are equal if their names are the same
Note: You can assume you will not encounter two pets with the same name
heal():
Should be protected to prevent access by external classes
Sets health to 1.0
Sets painLevel to 1
 */

public abstract class Pet {
    String name;
    double health; // Ranges from 0.0 to 1.0
    int painLevel; // Ranges from 1 to 10

    public Pet(String name, double health, int painLevel){
        this.name = name;
        if (health < 0.0) this.health = 0.0;
        else this.health = Math.min(health, 1.0);
        if (painLevel < 1) this.painLevel = 1;
        else this.painLevel = Math.min(painLevel, 10);
    }

    protected void heal(){
        this.health = 1.0;
        this.painLevel = 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pet){
            Pet otherPet = (Pet) obj;
            return this.name.equals(otherPet.getName());
        }
        return false;
    }

    public abstract int treat();

    public void speak(){
        if (this.painLevel > 5){
            System.out.printf("Hello! My name is %s\n".toUpperCase(), this.name.toUpperCase());
        } else {
            System.out.printf("Hello! My name is %s\n", this.name);
        }
    }

    public double getHealth() {
        return health;
    }

    public int getPainLevel() {
        return painLevel;
    }

    public String getName() {
        return name;
    }
}
