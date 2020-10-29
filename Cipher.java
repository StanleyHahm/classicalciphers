/**
NAME: Stanley Hahm
ID: A14609365
EMAIL: sthahm@ucsd.edu

In this code below, we are trying to convert messages into
ciphered messages and deciphering them afterwards. The two forms
of encryption we're using are the Caesar Shift, which uses a simple
shift in the alphabet to make a new code one letter at a time,
and the Vigenère Cipher, which uses Caesar Shifts for every elemnent
in a word or phrase.
*/

/**
This class uses multiples methods. The Caesar shift encoding
and decoding methods utilize the isLowerCase method that has
char as its parameter. The Vigenère shift encoding and
decoding methods utilize the isLowerCase method that has string
as its parameter.
*/
public class Cipher{
  /**
  The static finals are to keep track of the constants used in both
  ciphering methods
  */

  private static final int LOWERCASE_A = 97;
  private static final int LOWERCASE_Z = 122;
  private static final int MODULO_VALUE = 26;

  /**
  Take in char letter. Check if letter's ASCII number is within
  the lowercase letter range

  @param letter: the individual letter from the Caesar shift
  methods
  @return true: if letter is lower case, false: if letter is not lower
  case or not a letter
  */
  public static boolean isLowerCase(char letter){
    //holds the ASCII char value
    int letterVal = letter;

    //checks if ASCII char value is in range of lowercase letters
    if ((letterVal >= LOWERCASE_A) && (letterVal <= LOWERCASE_Z)){
      return true;
    }
    else{
      return false;
    }
  }

  /**
  Take in string. Check if each element in the
  string ASCII number is within the lowercase letter range

  @param str: the string from Vigenère shift methods
  @return true: if the whole string is lower case, false: if at
  least one element in the string is not lowercase or not a letter
  */
  public static boolean isLowerCase(String str){
    //checks every letter in string is lowercase
    for(int i = 0; i < str.length(); i++){
      //this holds the ASCII letter value for each element in string
      char letterVal = str.charAt(i);
      if((letterVal >= LOWERCASE_A) && (letterVal <= LOWERCASE_Z)){
        continue;
      }
      else{
        return false;
      }
    }
    return true;
  }

  /**
  Take in char for plaintext and char for the key. Utilize
  the key to shift the plaintext to the new code called newChar.

  @param plaintext: this is the original English message
  @param key: this is the variable used to shift the plaintext
  @return newChar: this is the new code after plaintext is shifted
  by key
  */
  public static char caesarShiftEncode(char plaintext, char key){
    //newDigit is to hold the new ASCII value for the ciphered text
    int newDigit;
    //initialize newChar to equal plaintext in case if statement fails
    //then the original plaintext is outputed
    char newChar = plaintext;

    //if the plaintext text is lowercase, then everything is shifted to
    //ciphered text by the key
    if((isLowerCase(plaintext) == true) && (isLowerCase(key) == true)){
      newDigit = (plaintext - LOWERCASE_A) + (key - LOWERCASE_A);
      newDigit = newDigit % MODULO_VALUE;
      newChar = (char)(newDigit + LOWERCASE_A);

    }
    return newChar;
  }

  /**
  Take in char for ciphertext and char for the key. Utilize
  the key to shift the ciphertext back to plaintext called newChar.

  @param ciphertext: this is the ciphered English message
  @param key: this is the variable used to shift the ciphertext
  @return newChar: this is the original message after ciphertext is
  shifted by key
  */
  public static char caesarShiftDecode(char ciphertext, char key){
    //newDigit is to hold the new ASCII value for the ciphered text
    int newDigit;
    //initialize newChar to equal ciphertext in case if statement fails
    //then the original cipheredtext is outputed
    char newChar = ciphertext;

    //if the ciphered text is lowercase, then everything is shifted to
    //original text by the key
    if((isLowerCase(ciphertext) == true) && (isLowerCase(key) == true)){
      newDigit = (ciphertext - LOWERCASE_A) - (key - LOWERCASE_A);
      newDigit = newDigit % MODULO_VALUE;
      newChar = (char)(newDigit + LOWERCASE_A + MODULO_VALUE);

    }
    return newChar;
  }

  /**
  Take in string for plaintext and string for the key. Utilize
  the key to shift the plaintext to the new code called newText.
  Since this is a string w/ potentially multiple elements, it runs
  through a for loop.

  @param plaintext: this is the original English message in string
  @param key: this is the variable used to shift the plaintext
  @return newText: this is the new code after plaintext is shifted
  by key
  */
  public static String vigenereEncode(String plaintext, String key){
    //this will be the output string and is initalized empty
    String newText = "";
    //value to keep track of the ASCII value for each letter in
    //the string
    int charValue;

    //if every letter in string is lower case, then shift the plaintext
    //by the key for each letter
    if((isLowerCase(plaintext) == true) && (isLowerCase(key) == true)){
      //this is the variable that keeps track of the key and loops it
      //if it reaches its max length
      int keyLoop = 0;

      //goes through each letter in string
      for(int i = 0; i < plaintext.length(); i++){
        charValue = ((plaintext.charAt(i) - LOWERCASE_A));
        charValue = charValue + (key.charAt(keyLoop) - LOWERCASE_A);
        charValue = charValue % MODULO_VALUE;
        keyLoop += 1;
        if (keyLoop == key.length()){
          keyLoop = 0;
        }
        newText += (char)(charValue + LOWERCASE_A);
      }
    }
    //if the string has not a lowercase letter or not a letter,
    //the code outputs null
    else{
      return null;
    }
    return newText;
  }

  /**
  Take in string for ciphertext and string for the key. Utilize
  the key to shift the ciphertext back to plaintext called newText.
  Since this is a string w/ multiple elements, it will use a for loop.

  @param ciphertext: this is the ciphered English message
  @param key: this is the variable used to shift the ciphertext
  @return newChar: this is the original message after ciphertext is shifted
  by key
  */
  public static String vigenereDecode(String ciphertext, String key){
    //this will be the output string and is initalized empty
    String newText = "";

    //value to keep track of the ASCII value for each letter in
    //the string
    int charValue;

    if((isLowerCase(ciphertext) == true) && isLowerCase(key) == true) {
      //this is the variable that keeps track of the key and loops it
      //if it reaches its max length
      int keyLoop = 0;

      for(int i = 0; i < ciphertext.length(); i++){
        charValue = (ciphertext.charAt(i) - LOWERCASE_A);
        charValue = charValue - (key.charAt(keyLoop) - LOWERCASE_A);
        charValue = charValue % MODULO_VALUE;
        keyLoop += 1;
        if (keyLoop == key.length()){
          keyLoop = 0;
        }
        newText += (char)(charValue + LOWERCASE_A);
      }
    }
    //if the string has not a lowercase letter or not a letter,
    //the code outputs null
    else{
      return null;
    }
    return newText;
  }

  /**
  This is my main method and is used for testing.

  @param text1: Vigenère encoding example used in PA3.pdf
  @param key1: string key used in example
  @param text2: Vigenère decoding example used in PA3.pdf
  @param text3: Caesar Shift encoding example used in PA3.pdf
  @param key2: char key used in example
  @param text4: Caesar Shift decoding example used in PA3.pdf
  */
  public static void main(String[] args){
    /**
    //this is testing the Vigènere Encode
    String text1 = "pau3cao";
    String key1 = "zzz";
    System.out.println(vigenereEncode(text1, key1));
    //this is testing the Vigènere Decode
    String text2 = "yyyyy";
    System.out.println(vigenereDecode(text2, key1));
    */
    //this is testing the Caesar Shift Encode
    char text3 = 99;
    char key2 = 122;
    System.out.println(caesarShiftEncode(text3,key2));
    //this is testing the Caesar Shift Decode
    char text4 = 121;
    System.out.println(caesarShiftDecode(text4,key2));

  }
}
