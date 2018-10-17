package makemyhall.form;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
 
public class MyUserAccountForm {
 
    private String email;
    private String userName;
    private int phonenumber;
    private String password;
    private String signInProvider;
    private String providerUserId;
 
    public MyUserAccountForm() {
 
    }
 
    public MyUserAccountForm(Connection<?> connection) {
        UserProfile socialUserProfile = connection.fetchUserProfile();
        this.email = socialUserProfile.getEmail();
        this.userName = socialUserProfile.getName();
 
        ConnectionKey key = connection.getKey();
        this.signInProvider = key.getProviderId();
        this.providerUserId = key.getProviderUserId();
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
 
    public String getSignInProvider() {
        return signInProvider;
    }
 
    public void setSignInProvider(String signInProvider) {
        this.signInProvider = signInProvider;
    }
 
    public String getProviderUserId() {
        return providerUserId;
    }
 
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
 
}
