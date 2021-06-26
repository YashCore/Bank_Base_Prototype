import java.io.Serializable;

public class Account implements Serializable{

    // Needed for serialization
    private static final long serialVersionUID = 3657539789471203231L;
    private String userCode = null;
    private String userID= null;
    private String userPsw = null;
    private User user= null;
    private float balance = 0;

    //----------------------------------------CONSTRUCTOR----------------------------------------
    public Account(){
    }

    public Account(String userID, String userPsw) {
        this.userCode = generateCode();
        this.userID = userID;
        this.userPsw = userPsw;
        balance = 0;
    }

    //----------------------------------------GETTER & SETTER----------------------------------------
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    } 

    public String getUserPsw() {
        return userPsw;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getUserID() {
        return userID;
    }

    public float getBalance() {
        return balance;
    }
    
    public void setBalance(float balance){
        this.balance = balance;
    }

    //----------------------------------------METHOD----------------------------------------

    // function to generate a random string  
    private static String generateCode(){
        final int N = 16; 

        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(N); 

        for (int i = 0; i < N; i++) { 

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                        .charAt(index)); 
        } 

        return sb.toString(); 
    }

    

  
}
