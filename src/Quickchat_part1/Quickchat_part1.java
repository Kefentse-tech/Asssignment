/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Quickchat_part1;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Student
 */
 class login {
String storeUsername;
    String storePassword;
    String storephoneNumber;
   
    
    //Method to check username
    public boolean checkUsername (String username) {
       if(username.length()<=5 && username.contains("_")){
           System.out.println("username sucessfully captured");
           System.out.println("Welcome<user first name>,<userlast name> it is great to see you again");
           return true ;
       } else {
           System.out.println("username is not correctly formatted please ensure that your user name containand underscore and is");
           System.out.println("username incorrect, please try again");
           return false ;
       }
       
    }
    
    
    //Method to check password complexity
    public boolean checkPasswordComplexity (String password){
        boolean haveLength= password.length()>=8 ;
        boolean haveUppercase= password.matches (".*[A-Z].*");
        boolean haveNumbers= password.matches (".*[0-9].*");
        boolean haveSpecialCharacters=password.chars(). anyMatch(c-> !Character.isLetter(c));
        
        if (haveLength && haveUppercase && haveNumbers && haveSpecialCharacters){
            return true;
        }else{
        return false;
        }
    }
    
    
    //Cell PhoneNumber
    public boolean checkphoneNumber (String phoneNumber){
        if(phoneNumber.length()== 11 && phoneNumber.startsWith("+27") && phoneNumber.matches (".*[0-9].*")); {
     return true;
    }
     
             
     } 

    
// we are creating the registeruser method so the user can enter the required registration
 void registerUser(){
 
 Scanner input =new Scanner (System.in);
System.out.println("\n========REGISTER========");
     System.out.println("Enter Username: ");
     String username = input.nextLine();
     
     System.out.println("Enter Password: ");
     String password = input.nextLine();
     
     System.out.println("Enter phoneNumber: ");
     String phoneNumber = input.nextLine();
     
     storeUsername= username;
     storePassword= password;
     storephoneNumber= phoneNumber;
             
     if (checkUsername(username)&& checkPasswordComplexity(password)&& checkphoneNumber(phoneNumber)){
         System.out.println("successful registration");
     } else{
         System.out.println("failed registration");
     }
        
    }
 
 
   //we are creating a userlogin Method
    void userlogin () {
        
        
        Scanner input =new Scanner (System.in);
        System.out.println("\n========login========");
     System.out.println("Enter username: ");
     String username = input.nextLine();
     
     System.out.println("Enter password: ");
     String password = input.nextLine();
     
     System.out.println("Enter phoneNumber: ");
     String phoneNumber = input.nextLine();
     
     storeUsername= username;
     storePassword= password;
     storephoneNumber= phoneNumber;
             
     if (checkUsername(storeUsername)&& checkPasswordComplexity(storePassword)&& checkphoneNumber(storephoneNumber)){
         System.out.println("login successful");
     } else{
         System.out.println("login failed");
     }
        
    }
    
    //Method to check if the username,the password,and the cellphonenumber are correct
    public String registerUser (String Username, String password, String phoneNumber){
        boolean haveUsername= checkUsername (Username);
        boolean havePassword= checkPasswordComplexity (password);
        boolean haveNumber= checkphoneNumber (phoneNumber);
        
         if (!haveUsername){
             return "Incorrect Username";
         }
         if (!havePassword){
             return "Incorrect password";
         }
         if (!haveNumber){
             return "Incorrect number";
         }
         return "Username, Password, and CellPhoneNumber is correct";
    }
         
     // Logging in the user if the username and the password match the new password
     public boolean loginUser (String Username, String password,String newUsername, String newPassword){
         boolean correctLogin= newUsername.equals (Username)&& newPassword.equals (password);
         {
             return correctLogin;
         }
     }
     //What the app will show when the user either has correct login details or not 
     public String returnLoginStatus(String username,String password, String newUsername, String newPassword){
         if (loginUser(username, password, newUsername, newPassword)){
             return "Correct login";
         }else{
             return "Incorrect username or password";
         }
     }

}

       class message {

    private String messageID;
    private int numMessageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static String[] storedMessages = new String[100];
    private static int totalMessages = 0;

    
    public message(String messageID, int numMessageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.numMessageNumber = numMessageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    
    boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    String checkRecipientCell() {
        if (recipient.length() > 10) {
            return "Cell number cannot exceed 10 characters.";
        }
        if (!recipient.startsWith("+")) {
            return "Cell number must start with an international code (e.g. +27).";
        }
        // ✅ FIXED: return value matches what we check in sendMessages
        return "Cell number valid.";
    }

    String createMessageHash() {
        String idStart = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String first = words[0];
        String last = words[words.length - 1];
        String hash = idStart + ":" + numMessageNumber + ":" + first + last;
        return hash.toUpperCase();
    }

    String SentMessage(Scanner scanner) {
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1. Send");
        System.out.println("2. Store");
        System.out.println("3. Disregard");
        System.out.print("Choose: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                storeMessages();
                return "Message sent";
            case "2":
                storeMessages();
                return "Message stored";
            case "3":
                return "Message disregarded";
            default:
                return "Invalid choice. Message disregarded.";
        }
    }

   
    static String printMessages() {
        if (totalMessages == 0) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== All Sent Messages =====\n");
        for (int i = 0; i < totalMessages; i++) {
            sb.append(storedMessages[i]).append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessage() {
        return totalMessages;
    }

    public void storeMessages() {
        if (totalMessages < storedMessages.length) {
            String json = "{"
                    + "\"messageID\":\"" + messageID + "\","
                    + "\"messageNumber\":" + numMessageNumber + ","
                    + "\"recipient\":\"" + recipient + "\","
                    + "\"message\":\"" + messageText + "\","
                    + "\"messageHash\":\"" + messageHash + "\""
                    + "}";
            storedMessages[totalMessages] = json;
            totalMessages++;
        } else {
            System.out.println("Message storage is full.");
        }
    }

    String getMessageID()   { return messageID; }
    String getRecipient()   { return recipient; }
    String getMessage()     { return messageText; }
    String getMessageHash() { return messageHash; }
}
public class Quickchat_part1{
      static Scanner scanner = new Scanner(System.in);
    static int numMessageSent = 0;
    static int maxMessages = 0;
    static boolean loggedIn = false;
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scanner= new Scanner (System.in) ;
        // it is where the user can choose if they want to register , Loginor want to go to the menu 
        login loginApp=new login();
        int choice=scanner.nextInt();
        
        do {
            System.out.println("\n=====MENU=====");
            System.out.println("1.Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
              System.out.println("enter Menu Option");
             choice= scanner.nextInt();
             scanner.nextLine();
             switch (choice ){
                 case 1:
                     loginApp.registerUser();
                     break;
                 case 2:
                    loggedIn= loginApp.userlogin();
                    if(loggedIn){
                        runQuickChat();
                    }
                     break;
                 case 3:
                     System.out.println("goodbye");
                     break;
                 default:
                     System.out.println("invalid choice");
             }
        }while (choice!=3);
             scanner.close();
    }

 static void runQuickChat() {     
        System.out.println("\nWelcome to QuickChat.");
        System.out.print("How many messages would you like to send? ");
        maxMessages = Integer.parseInt(scanner.nextLine().trim());

        boolean running = true;
        while (running) {
            System.out.println("\n---- Menu -----");
            System.out.println("1. Send Message");
            System.out.println("2. Show recent sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            String menuChoice = scanner.nextLine().trim();

            switch (menuChoice) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    running = false;
                    System.out.println("Total messages sent: " + message.returnTotalMessage());
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    
    static void sendMessages(){  
        if (numMessageSent >= maxMessages) {
            System.out.println("Message limit of " + maxMessages + " reached.");
            return;
        }

        int remaining = maxMessages - numMessageSent;
        System.out.println("You can send " + remaining + " more message(s).");

        for (int i = 0; i < remaining; i++) {
            System.out.println("\n--- Message " + (numMessageSent + 1) + " ---");

            String recipient = "";
            while (true) {
                System.out.print("Enter recipient cell number (e.g. +27xxxxxxxx, max 10 chars): ");
                recipient = scanner.nextLine();
                if (recipient.length() <= 10 && recipient.startsWith("+")) break;
                System.out.println("Invalid. Must start with + and be max 10 characters.");
            }

            String messageText = "";
            while (true) {
                System.out.print("Enter your message (max 250 characters): ");
                messageText = scanner.nextLine();
                if (messageText.length() <= 250) break;
                System.out.println("Please enter a message of less than 250 characters.");
            }

            String messageID = generateMessageID();
            numMessageSent++;

            message msg = new message(messageID, numMessageSent, recipient, messageText);

            
            if (!msg.checkMessageID()) {
                System.out.println("Error: Message ID invalid.");
                numMessageSent--; // ✅ FIXED: was ++ should be --
                continue;
            }

            String cellCheck = msg.checkRecipientCell();
           
            if (!cellCheck.equals("Cell number valid.")) {
                System.out.println("Error: " + cellCheck);
                numMessageSent--;
                continue;
            }

            System.out.println("Message Hash: " + msg.getMessageHash());

            String result = msg.SentMessage(scanner);
            System.out.println(result);

            if (numMessageSent >= maxMessages) {
                System.out.println("\nMessage limit reached.");
                break;
            }
        }

        
        System.out.println(message.printMessages());
    }

    static String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }
}  
     
     
     
    
     
     
     
    

