package makemyhall.model;

public class MyUserAccount {
	 
	   private String email;
	   private String userName;
	   private int phonenumber;
	   private String password;
	 
	   public MyUserAccount() {
	 
	   }
	 
	   public MyUserAccount(String email,String userName, int phonenumber, String password) {

	       this.email = email;
	       this.userName= userName;
	       this.phonenumber = phonenumber;
	       this.password = password;
	   }
	
	 
	   public String getEmail() {
	       return email;
	   }
	 
	   public void setEmail(String email) {
	       this.email = email;
	   }
	 
	   public String getUserName() {
	       return userName;
	   }
	 
	   public void setUserName(String userName) {
	       this.userName = userName;
	   }
	 
	   public int getphonenumber() {
	       return phonenumber;
	   }
	 
	   public void setphonenumber(int phonenumber) {
	       this.phonenumber = phonenumber;
	   }
	 
	   public String getPassword() {
	       return password;
	   }
	 
	   public void setPassword(String password) {
	       this.password = password;
	   }
	 
	}