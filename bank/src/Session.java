import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Session {

    private void startSession(Bank bank) {
        // Support Variables
        Scanner input = new Scanner(System.in);
        String str = null;
        int choice = 0;


        do {

            // User View menu
            System.out.println("\n1. See Your Balance\n"+
                                "2. Deposit\n"+
                                "3. Withdraw\n"+
                                "4. Transfer\n"+
                                "5. See Your Information\n"+
                                "6. Close your account\n"+
                                "7. Logout\n");
            str = input.nextLine();

            // chceking if the choice is a number
            try {
                choice = Integer.parseInt(str);
            } catch (NumberFormatException nFormatException) {
                System.out.println("Type a number that's equivlent to one of the choices please");
            }

            // real menu
            switch (choice) {
                case 1:
                    System.out.println(bank.showBalance());                        
                    break;
            
                case 2:
                    // Aquiring amount and then calling the related bank procedure 
                    System.out.println("How much do you want to deposit?");
                    str = input.nextLine();
                    bank.deposit(Float.parseFloat(str));
                    break;
                    
                case 3:
                    // Aquiring amount and then calling the related bank procedure 
                    System.out.println("How much do you want to withdraw?");
                    str = input.nextLine();
                    bank.withdraw(Float.parseFloat(str));
                    break;    
    
                case 4:
                    // Aquiring amount and then calling the related bank procedure 
                    System.out.println("How much do you want to transfer?");
                    str = input.nextLine();
                    bank.transfer(Float.parseFloat(str));
                    break;

                case 5:
                    bank.seeUserInfo();
                    break;

                case 6:
                    bank.closeAccount();
                    break;

                case 7:
                    System.out.println("See You Again!");
                    // logout
                    bank.resetCurrentUser();
                    break;


                default:
                        choice = 0;
                        System.out.println("Wrong choice!\n");
                    break;
            }
        } while (choice!=7);
    }


    public void doLogin(){
        Scanner input = new Scanner(System.in);
        String str = null;
        boolean log = false;
        int choice = 0;
        Bank bank = Bank.restoreAppData(); 

        // bank that holds all the account
       // Bank bank = new Bank();

        do {
            // cheking if the user logged in
            if(log)
                startSession(bank); // starting a new session for the user

            // User View Menu    
            System.out.println("\n1.Login\n"+
                                "2.Register\n"+
                                "3.Exit\n");
            str = input.nextLine();

            // checking if the choice is a number
            try {
                choice = Integer.parseInt(str);
            } catch (NumberFormatException nFormatException) {
                System.out.println("Type a number that's equivlent to one of the choices please");
            }
            
            // Real Menu
            switch (choice) {
                case 1:
                    log = bank.login();
                    break;    
                case 2:
                    bank.openNewAccount();
                    break;
                case 3:
                    System.out.println("Thanks for testing!");
                    break;    
    
                default:
                    System.out.println("Wrong choice! Please choose a valid point!");
                    break;
            }

        } while (choice!=3);
        endSession(bank); // Saving session
        input.close();
    }  
   
  
    
    // Method that saves accounts on file
    private void endSession(Bank bank) {
        
        try {
            // stream to write
            ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream("src/DXDB/database.ser"));
            oStream.writeObject(bank);
        
            oStream.close();
        } catch (IOException e) {
            System.out.println("*-*-*-*-*-*-*-*-*ERROR SAVING ON FILE!*-*-*-*-*-*-*-*-*");
            e.printStackTrace();
        }
    }
}
