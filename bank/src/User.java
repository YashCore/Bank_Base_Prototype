import java.io.Serializable;

public class User implements Serializable{

    // Needed for serialization
    private static final long serialVersionUID = 5949619936026181374L;
    private String name = null;
    private String surname = null;
    private String mail = null;
    private String mobileNumber = null;
    private Account account = null;
    
    //----------------------------------------CONSTRUCTOR----------------------------------------
    public User() {
    }

    public User(String name , String surname, String mail, String mobileNumber, String userID, String userPsw) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.mobileNumber = mobileNumber;
        account = new Account(userID,userPsw);
    }

    //----------------------------------------GETTER & SETTER----------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Account getAccount(){
        return account;
    }

    //----------------------------------------TO STRING----------------------------------------
    @Override
    public String toString() {
        return "User [account=" + account + ", mail=" + mail + ", mobileNumber=" + mobileNumber + ", name=" + name
                + ", surname=" + surname + "]";
    }

}
