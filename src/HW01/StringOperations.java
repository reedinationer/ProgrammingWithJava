package HW01;

/*
    a) Create a new `String` object and assign it your name. Print it out.
    b) Pick the first letter in your name, and replace it with 'A'. Then, replace the last letter in your name with 'Z'.
       Print out the result. Recall that, in Java, strings are *immutable*, meaning you cannot change a String in-place.
       Do NOT just hard-code a new String with the first and last letters changed.
    c) Lastly, let's work with some URLs. Declare a new `String` and give it the value of some web address, in the
       form `www.name.tld`, such as `www.gatech.edu` or `www.stackoverflow.com`. Print out this address.
    d) This last operation could be a little tricky. Create a substring of the variable that's just the "name" section,
       and concatenate the integer "1331" to the end. For example, `www.gatech.edu` would become gatech1331. Print out this
       final result. **Note**: the String class has a `.length()` method which you'll likely find useful here but is not necessary.
*/

public class StringOperations {
    public static void main(String[] args) {
        String name = "Reed";
        System.out.println(name);
        System.out.println("A" + name.substring(1, 3) + "Z");

        String url = "www.google.com";
        System.out.println(url);
        char[] manipulatedURL = new char[url.length()];
        boolean firstDot = false;
        boolean secondDot = false;
        char thisChar;
        for (int i = 0; i < url.length(); i++){
            thisChar = url.charAt(i);
            if (thisChar == '.'){
                if (!firstDot){
                    firstDot = true;
                }
                else {
                    secondDot = true;
                }
            } else {
                if (firstDot && !secondDot){
                    manipulatedURL[i] = thisChar;
                }
            }
        }
        String text = String.valueOf(manipulatedURL);
        System.out.println(text.trim() + "1331");
    }
}