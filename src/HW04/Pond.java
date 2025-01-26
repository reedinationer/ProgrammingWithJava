package HW04;

public class Pond {
    public static void main(String[] args) {
        Frog frog1 = new Frog("Peepo");
        Frog frog2 = new Frog("Pepe", 10, 15);
        Frog frog3 = new Frog("Peepaw", 4.6, 5);
        Frog frog4 = new Frog("Peeper", 60, 100);
        Fly fly1 = new Fly(1, 3);
        Fly fly2 = new Fly(6f);
        Fly fly3 = new Fly(10, 0.5f);
        frog1.setSpecies("1331 Frogs");
        System.out.println(frog1);
        frog1.eat(fly2);
        System.out.println(fly2);
        frog1.grow(8);
        frog1.eat(fly2);
        System.out.println(fly2);
        System.out.println(frog1);
        System.out.println(frog4);
        frog3.grow(4);
        System.out.println(frog3);
        System.out.println(frog2);
    }
}

/*

Failed: Should have printed a fly as such
    expected:<m a speedy fly with 11.00 speed and 7.00 mass.> ac
tual:<m dead, but I used to be a fly with a speed of 11.00>
    These strings are only 30.8% similar, less than or equal to
the minimum allowed 95.0% similarity.

Failed: expected:<m [a speedy fly with 7.63 speed and 11
.58 mass.]> but was:<m [dead, but I used to be a fly with a spee
d of 7.63]>

Main method must act as such:
Must create at least 4 Frog objects:
Frog with name Peepo.
Frog with name Pepe, age 10 months, and tongueSpeed of 15.
Frog with name Peepaw, age 4.6 years, and tongueSpeed of 5.
Frog of your liking :)
Must create at least 3 Fly objects:
Fly with 1 mass and speed of 3.
Fly with 6 mass.
Fly of your liking :)
Perform the following operations in this order:
Set the species of any Frog to “1331 Frogs”
Print out on a new line the description of the Frog named Peepo given by the toString method.
Have the Frog named Peepo attempt to eat the Fly with a mass of 6.
Print out on a new line the description of the Fly with a mass of 6 given by the toString method.
Have the Frog named Peepo grow by 8 months.
Have the Frog named Peepo attempt to eat the Fly with a mass of 6.
Print out on a new line the description of the Fly with a mass of 6 given by the toString method.
Print out on a new line the description of the Frog named Peepo given by the toString method.
Print out on a new line the description of your own Frog given by the toString method.
Have the Frog named Peepaw grow by 4 months.
Print out on a new line the description of the Frog named Peepaw given by the toString method.
Print out on a new line the description of the Frog named Pepe given by the toString method.
 */
