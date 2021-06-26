import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Bank implements Serializable {
    
    // Needed for serialization
	private static final long serialVersionUID = 6964898040523543827L;

	// List of all the users
    private static ArrayList<Account> account = new ArrayList<Account>();
    
    // Varible to the logged user
    private Account currentUser = null;

    public String showBalance(){
        // returning a String with the user balance
        return ("Your balance is : "+currentUser.getBalance());
    }

    public int deposit(float deposit){
        // Support varibles
        Scanner input = new Scanner(System.in);

        // The user has 3 chance to enter the correct password
        int trys = 0;

        // to check if the user enters the right password
        boolean identifed = false;
        
        // Asking the user Password
        System.out.println("Enter your password:");
        String enterdPsw = input.nextLine();

        /*
         * While loop that continues untill the user  
         * enters the correct password or
         * the user mistakes the password for 3 times
         * -----------------------------------------
         * identifed varible is set for default to false
         * when the user enters the correct password the identifed is set to true
         * and the deposit is done 
         */ 
        while (!identifed) {

            // Checking if the user entered password matches the database
            if(enterdPsw.equals(currentUser.getUserPsw())){
                // Adding the deposit to the balance
                currentUser.setBalance(currentUser.getBalance()+deposit);
                identifed = true;
            }else{

                // Checking if the user has done the 3 chances
                if (trys<3) {

                    // Incrementing the trys counter and reasking the password
                    trys++;
                    System.out.println("Wrong Password!\nYou have "+trys+" left!");
                    System.out.println("Enter your password:");
                    enterdPsw = input.nextLine();
                }else{

                    // Blocking the user to do anything and sending it back to the servisce list
                    System.out.println("\n\n\n\n\n\n\n\n\nTo many trys failed! Wait 1 Minute.");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return -1;
                }
            }
        }
        return 0;
    }

    public int withdraw(float withdraw) {
        // Support varibles
        Scanner input = new Scanner(System.in);

        // The user has 3 chance to enter the correct password
        int trys = 0;

        // to check if the user enters the right password
        boolean identifed = false;
        
        // Asking the user Password
        System.out.println("Enter your password:");
        String enterdPsw = input.nextLine();

        /*
         * While loop that continues untill the user  
         * enters the correct password or
         * the user mistakes the password for 3 times
         * -----------------------------------------
         * identifed varible is set for default to false
         * when the user enters the correct password the identifed is set to true
         * and the withdraw is done 
         */ 
        while (!identifed) {

            // Checking if the user entered password matches the database
            if(enterdPsw.equals(currentUser.getUserPsw())){
                if(currentUser.getBalance()>(currentUser.getBalance()-withdraw)){
                    // Subtracting the withdraw from the balance
                    currentUser.setBalance(currentUser.getBalance()-withdraw);
                    identifed = true;
                }else
                    System.out.println("Your Account is down of some money!");
            }else{

                // Checking if the user has done the 3 chances
                if (trys<3) {

                    // Incrementing the trys counter and reasking the password
                    trys++;
                    System.out.println("Wrong Password!\nYou have "+trys+" left!");
                    System.out.println("Enter your password:");
                    enterdPsw = input.nextLine();
                }else{

                    // Blocking the user to do anything and sending it back to the servisce list
                    System.out.println("\n\n\n\n\n\n\n\n\nTo many trys failed! Wait 1 Minute.");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return -1;
                }
            }
        }
        
        return 0;
    }

    public int transfer(float transfer){
          // Support varibles
          Scanner input = new Scanner(System.in);

          // The user has 3 chance to enter the correct password
          int trys = 0, codeTrys = 0;

        
          // support varible
          String receiverCode = null;

          // to check if the user enters the right password
          boolean identifed = false;
          
          // Asking the user Password
          System.out.println("Enter your password:");
          String enterdPsw = input.nextLine();
  
          /*
           * While loop that continues untill the user  
           * enters the correct password or
           * the user mistakes the password for 3 times
           * -----------------------------------------
           * identifed varible is set for default to false
           * when the user enters the correct password the identifed is set to true
           * and then asks for the other user bank code 
           */ 
          while (!identifed) {
  
              // Checking if the user entered password matches the database
              if(enterdPsw.equals(currentUser.getUserPsw())){
                
                // Aquiring the Recevier Bank Code
                 System.out.println("Enter receiver Bank Code please:");
                 receiverCode = input.nextLine();
                 

                // searching for the code in the database 
                 for (int i = 0; i < account.size(); i++) {

                    /* Checking if the code matches the user inserted code
                     * else check if the for loop has searched in the whole database
                     */ 
                    if (account.get(i).getUserCode().equals(receiverCode)) {
                        identifed = true;
                        // subtracting the transfer amount from current user 
                         currentUser.setBalance(currentUser.getBalance()-transfer);

                        // adding transfer amount to the recevier user
                         account.get(i).setBalance(account.get(i).getBalance()+transfer);

                     }else{

                        // Checking if the user had used all off his chances
                        // else blovking the user from any actions and pulling him back
                         if (codeTrys <3) {

                            // incrementing trys counter and printing error msg
                             codeTrys++;
                             System.out.println("The enterd code is wrong!\n");
                         }else{
                            // Blocking the user to do anything and sending it back to the servisce list
                            System.out.println("\n\n\n\n\n\n\n\n\nTo many trys failed! Wait 1 Minute.");
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }  
                            return -1;
                         }

                     }
                 }

              }else if(codeTrys < 0){ // to let the user 3 trys 
  
                  // Checking if the user has done the 3 chances
                  if (trys<3) {
  
                      // Incrementing the trys counter and reasking the password
                      trys++;
                      System.out.println("Wrong Password!\nYou have "+trys+" left!");
                      System.out.println("Enter your password:");
                      enterdPsw = input.nextLine();
                  }else{
  
                      // Blocking the user to do anything and sending it back to the servisce list
                      System.out.println("\n\n\n\n\n\n\n\n\nTo many trys failed! Wait 1 Minute.");
                      try {
                          Thread.sleep(10000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      return -1;     // if the user mistakes 3 times
                  }
              }else{
  
                // Blocking the user to do anything and sending it back to the servisce list
                System.out.println("\n\n\n\n\n\n\n\n\nTo many trys failed! Wait 1 Minute.");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return -1;     // if the user mistakes 3 times
            }
          }
          return 0;  // successful


    }

    public void seeUserInfo(){

        // Prints all the user information
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"
                            +"User ID : " +currentUser.getUserID()
                            +"\nUser Bank Code: " +currentUser.getUserCode()
                            +"\nUser Name & Surname : " +currentUser.getUser().getName()+" "+currentUser.getUser().getSurname()
                            +"\nMobile Number : " +currentUser.getUser().getMobileNumber()
                            +"\nMail : " +currentUser.getUser().getMail()
                            +"\n---------------");
    }

    public boolean login(){ 
        // support variables
        Scanner input = new Scanner(System.in);
        String str = null;
        int trys = 0;
        // need to check if the user inserted the correct ID
        boolean identifed = false;
        
        
        while (!identifed) {
           
            // Requesting user ID and incrementing try counter
            System.out.println("\n\n\n\n\n\n\n\n--------------------LOGIN--------------------");  
            System.out.println("Enter user ID: ");
            str = input.nextLine();
            trys++;
            int key = searchUser(str);

            /* Checking if the user ID is registred in the data base
            * using a for loop which stops if the user ID is found 
            * which sets identifed to true so the user can enter the 
            * password.
            * Or the loops ends without founding the ID and print a error 
            * message and asks the user to re-enter or register after 3 trys
            * 
            */
            if(trys<3){
                switch (key) {
                    case -1:    //User not found means not registred
                            System.out.println(str+ " Does not exists!");

                            // Asking the user if he wants to re try
                            System.out.println("Do you want to re-enter?");
                            str = input.nextLine();
                            if(!(str.equalsIgnoreCase("yes")))
                                return false;
                        break;
                    case -2:    // if there aren't any account registered it will occure once
                            System.out.println("Not any account registered till now!");
                            identifed = true;
                        break;
                    
                    default: // if the ID is found in the database
                        System.out.println("Enter Password");
                        str = input.nextLine();
                        if(account.get(key).getUserPsw().equals(str)){
                            currentUser = account.get(key);
                            return true;
                        }
                        break;
                } 
            }else{ // 3 trys done wait
                System.out.println("Wait a minute to re try!");

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }              
        }
        return false;
    }

    public void openNewAccount() {
        // support varibales
        Scanner input = new Scanner(System.in);
        String name = null;
        String surname = null;
        String mail = null;
        String mobNumb = null;
        String id = null;
        String psw = "nul";
        String rePsw = "null";
        
        // Aquiring user data
        System.out.println("Name:");
        name = input.nextLine();
            
        System.out.println("Surname:");
        surname = input.nextLine();
         
        System.out.println("Mail");
        mail = input.nextLine(); 
        // TODO : checking for same mail alredy registered 
        System.out.println("Mobile Number");
        mobNumb = input.nextLine();
        // TODO : checking for the same ID already registerd
        System.out.println("Enter User ID");
        id = input.nextLine();
       
        // checking if the user rembers the password else re asking the password using while loop
        while (!(psw.equals(rePsw))) {
            System.out.println("Enter Password");
            psw = input.nextLine();
            
            System.out.println("Re enter Password");
            rePsw = input.nextLine();

            if(!psw.equals(rePsw))
                System.out.println("The two Passwords don't match. Please re enter the passwords");    
        }

        // Creating a new user
        User newUser = new User(name,surname,mail,mobNumb,id,psw);
        newUser.getAccount().setUser(newUser);
        account.add(newUser.getAccount());           
    }

    public int closeAccount() {
        // Support Variables    
        Scanner input = new Scanner(System.in);
        String str = null;
        int index = 0;
        int trys  = 0;
        // needs to check the user ID
        boolean identifed = false;

        /* while loop that continues until the inserted password is not correct
         * else if the user has attemped 3 times block the user from any service
         */

        while (!identifed) { 
            if(trys<3){

                // Aquiring password
                System.out.println("Enter Password");
                trys++;
                str = input.nextLine();

                // checking if the inserted psw matches the database one
                if(account.get(index).getUserPsw().equals(str)){
                    // stoping the while loop and removing the user
                    identifed = true;
                    account.remove(index);
                    return 0;
                }else{
                    // in case the password is wrong
                    System.out.println("Password does not match!");
                    str=input.nextLine();
                    if (str.equalsIgnoreCase("yes"))
                        identifed = true;
                    else
                        identifed = false;
                        return -1;
                }        
            }else if(trys==3){
                System.out.println("Wait a minute to re try!");

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                closeAccount();
            }
               
        }
        return 1;
    }


    /**
     * method that checks if the user it's in the database
     * 
     * @param id user id to search
     * @return default -1 not found
     */
    public int searchUser(String id){
        if(account.size() == 0){
            return -2;   // returns -2 if there is no account
        }else{
            for (int i = 0; i < account.size(); i++){
                if(account.get(i).getUserID().equalsIgnoreCase(id))
                    return i;   //returns index if it found the id in the database
            } 
        }
        return -1;  // returns -1 if the user is not found in the database
    }

    // Need to reset at launch
    public void resetCurrentUser(){
        currentUser = null;
    }



  // TOF: needs debbug
  public static Bank restoreAppData(){
    Bank bank= null;
    try {
        ObjectInputStream objStream = new ObjectInputStream(new FileInputStream("src/DXDB/database.ser"));
    
        do{
           Bank  restorebank = (Bank) objStream.readObject();
            bank.account.add(restorebank);
       }while(bank!=null);
       

        objStream.close();
    } catch(ClassNotFoundException classNot) {
        System.err.println("*-*-*-*-*-*-*-*-*CLASS NOT FOUND!*-*-*-*-*-*-*-*-*");
        classNot.printStackTrace();
    } catch (FileNotFoundException fileNot) {
        System.err.println("*-*-*-*-*-*-*-*-*FILE NOT FOUND!*-*-*-*-*-*-*-*-*");
        fileNot.printStackTrace();
    } catch (IOException ioErr) {
        System.err.println("*-*-*-*-*-*-*-*-*ERROR WHILE READING FILE!*-*-*-*-*-*-*-*-*");
        ioErr.printStackTrace();
    }
    return bank;
}

}


