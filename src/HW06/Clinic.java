package HW06;

/*
Clinic.java
This is a class representing the vet clinic.

Variables
File patientFile
File with patient information
int day
Constructors
Clinic(File file)
File that contains patient info - assign to patientFile
Name
Type of pet (includes pet info)
Appointment Info
timeIn(military time)
health(before Treatment)
painLevel(before Treatment)
TimeOut(military time)
TimeOut(military time)
Day initialized to 1
Clinic(String fileName)
String includes filename extension – don't add “.csv”
Chains to the other constructor
Methods
String nextDay(File f) throws FileNotFoundException
String nextDay(String fileName) throws FileNotFoundException
Reads File f that contains the name, type of pet, and time of the appointments for the day
See example file “Appointments.csv” for the format
Eg: If there was a Cat Chloe, with a miceCaught count of 5, scheduled for 2:30 pm, Chloe’s information in Appointments.csv would look like:
Chloe,Cat,5,1430
You will have one file for each different day
Use a Scanner object to take in user input
Print “Consultation for [name] the [typeOfPet] at [time].\nWhat is the health of [name]?\n”
If typeOfPet is not valid (i.e. not a Dog or Cat, case-sensitive) throw InvalidPetException
Do not catch the exception in your code! The caller of the method should handle the exception.
Take in user input for health
If input is not a number, continue prompting user until they provide a number
Print “On a scale of 1 to 10, how much pain is [name] in right now?\n”
Take in user input for painLevel
If input is not a number, continue prompting user until they provide a number
Call speak()
Treat pet
Calculate time out (there exists a method for this)
Note: Don’t try to read the file and write to it at the same time – this method is intended only to read the file.
Don’t forget the increment the day!
Returns a String with patient information to be used when treating patients and updating the file.
The string being returned should hold the updated information for all patients seen in the day separated by a newline character.
Each appointment should be formatted as follows:
[Name],[Species],[DroolRate/MiceCaught],[Day],[EntryTime],[ExitTime],[InitialHealth],[InitialPainLevel]
E.x.: If there are 2 appointments on day 2:
Appointment 1 on Day 2:
Dog Dobie with droolRate 2.7
Entry time: 1715 (5:15 pm) and Exit time: 1735 (5:35 pm)
Health was 0.5 and painLevel was 5 before treating
Appointment 2 on Day 2:
Cat Marlin with miceCaught 84
Entry time: 1655 (4:55 pm) and Exit time: 1700 (5:00 pm)
Health was 0.4 and painLevel was 4 before treating
The output of nextDay would be:
Dobie,Dog,2.7,Day 2,1715,1735,0.5,5
Marlin,Cat,84,Day 2,1655, 1700,0.4,4

boolean addToFile(String patientInfo)
Consumes a string representing a single appointment
Eg. In format:
[Name],[Species],[DroolRate/MiceCaught],[Day],[EntryTime],[ExitTime],[InitialHealth],[InitialPainLevel]
Write info to patientFile
If old patient, only the appointment info should be added to the patient file, which includes:
Day #
Time in and time out
Health and pain
If new patient, all info should be added to the clinic’s patient file
Assume the vet will never see two different pets with the same name
See Patients.csv for an example
Returns true if the appointment info was successfully written, and false if an error occurs or a checked exception is caught
Note (cont’d): Don’t try to read the file and write to it at the same time – this method is intended to rewrite the file.

String addTime(String timeIn, int treatmentTime)
This method should only be accessible in the Clinic class
This method should calculate the time the patient’s appointment ends
Return timeOut
Remember: timeIn and timeOut should be represented in military time
You can assume that timeIn and timeOut will NOT go across multiple days (ex. timeIn = “23:30” and timeOut = “00:30”)
Example Output
User input is bolded

Example output for this entry: Chloe,Cat,5,1430

Consultation for Chloe the Cat at 1430.

What is the health of Chloe?

0.6

On a scale of 1 to 10, how much pain is Chloe in right now?

Six

Please enter a number

On a scale of 1 to 10, how much pain is Chloe in right now?

6

HELLO! MY NAME IS CHLOE

MEOW MEOW MEOW MEOW MEOW

Reuse your code when possible. Certain methods can be reused using certain keywords.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class Clinic {
    private File patientFile;
    private int day;

    public Clinic(File file){
        this.patientFile = file;
        this.day = 1;
    }

    public Clinic(String filename){
        this(new File(filename));
    }

    public String nextDay(File f) throws FileNotFoundException {
        this.day += 1;
        Scanner appointments = new Scanner(f);
        String line;
        String[] tokens;
        Pet currentlyTreating;
        int pain;
        double health;
        Scanner input = new Scanner(System.in);
        String timeOut;
        String result;
        String output = null;
        while (appointments.hasNextLine()) { // Iterate each appointment
            line = appointments.nextLine();
            tokens = line.split(",");
            if (!(Objects.equals(tokens[1], "Dog") || Objects.equals(tokens[1], "Cat"))){
                throw new InvalidPetException();
            }
            System.out.printf("Consultation for %s the %s at %s.\n", tokens[0], tokens[1], tokens[3]);
            while (true){
                System.out.printf("What is the health of %s?\n", tokens[0]);
                if (input.hasNextDouble()){
                    health = input.nextDouble();
                    break;
                } else {
                    line = input.next(); // Skip whatever was typed
                    System.out.println("Please enter a number");
                }
            }
            while (true){
                System.out.printf("On a scale of 1 to 10, how much pain is %s in right now?\n", tokens[0]);
                if (input.hasNextInt()){
                    pain = input.nextInt();
                    break;
                } else {
                    line = input.next(); // Skip whatever was typed
                    System.out.println("Please enter a number");
                }
            }
            if (tokens[1].equals("Dog")) {
                currentlyTreating = new Dog(tokens[0], health, pain, Double.parseDouble(tokens[2]));
            }
            else {
                currentlyTreating = new Cat(tokens[0], health, pain, Integer.parseInt(tokens[2]));
            }
            currentlyTreating.speak();
            timeOut = addTime(tokens[3], currentlyTreating.treat());
            // [Name],[Species],[DroolRate/MiceCaught],[Day],[EntryTime],[ExitTime],[InitialHealth],[InitialPainLevel]
            result = String.format("%s,%s,%s,Day %d,%s,%s,%.1f,%d", tokens[0], tokens[1], tokens[2], this.day, tokens[3], timeOut, health, pain);
            if (output == null){
                output = result;
            } else {
                output = String.format("%s\n%s", output, result); // Add next appointment information
            }
        }
        return output;
    }

    public String nextDay(String fileName) throws FileNotFoundException {
//        System.out.printf("Converting %s to a file\n", fileName);
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
//        File useFile = new File(String.format("src/HW06/%s", fileName));
        File useFile = new File(fileName);
        return nextDay(useFile);
    }

    public boolean addToFile(String patientInfo) {
        // First we will read in all the existing data from existing file into an array
        Scanner patientScanner = null;
        String line;
        String[] patientData;
        int lineCount = 0;
        try { // Read the data into patientData array
            patientScanner = new Scanner(this.patientFile);
            while (patientScanner.hasNextLine()) { // Scan to determine number of lines in file, only needed since we can't import Array classes
                line = patientScanner.nextLine();
                lineCount += 1;
            }
            patientData = new String[lineCount]; // Now we know how many lines to store so our array will hold that many
            patientScanner = new Scanner(this.patientFile);
            lineCount = 0;
            while (patientScanner.hasNextLine()) {
                line = patientScanner.nextLine();
                patientData[lineCount] = line;
                lineCount += 1;
            }
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (patientScanner != null) {
                patientScanner.close();
            }
        }
        // Now we will write to the file while adding the information
        Scanner input = new Scanner(patientInfo); // we will scan the string input to this function
        String[] tokens = input.nextLine().split(","); // and split it into tokens like shown below
        // [Name],[Species],[DroolRate/MiceCaught],[Day],[EntryTime],[ExitTime],[InitialHealth],[InitialPainLevel]
        Scanner existingData;
        String[] existingTokens;
        PrintWriter output = null;
        boolean newClient = true;
        try {
            output = new PrintWriter(this.patientFile);
            for (String patientDatum : patientData) {
                existingData = new Scanner(patientDatum);
                existingTokens = existingData.nextLine().split(",");
                if (existingTokens[0].equals(tokens[0])) {
                    // Pet already in database, just add appointment data
                    output.print(patientDatum); // Put already existing information
                    output.print(String.format(",%s,%s,%s,%s,%s\n", tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]));
                    newClient = false;
                } else {
                    // Simply rewrite the line to the output file
                    output.println(patientDatum);
                }
            }
            if (newClient) {
                output.println(patientInfo); // Add new patients at the end
            }
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (output != null) {
                output.close();
            }
        }
        return true;
    }

    private String addTime(String timeIn, int treatmentTime){
        // Failed: Correctly adds time within an hour. expected:<10
        //[]45> but was:<10[0]45>
        int hour = Integer.parseInt(timeIn.substring(0, 2));
        int minutes = Integer.parseInt(timeIn.substring(2)) + treatmentTime;
        if (minutes > 59){
            return String.format("%d%d", hour + (int) Math.floor((minutes / 60.)), minutes % 60);
        } else {
            return String.format("%d%d", hour, minutes);
        }
    }

}
