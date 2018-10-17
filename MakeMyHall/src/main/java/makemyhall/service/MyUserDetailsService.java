package makemyhall.service;

import makemyhall.dao.MyUserAccountDAO;
import makemyhall.model.MyUserAccount;
import makemyhall.user.MySocialUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;
 
// Service to Get user info from Database.
@Service
public class MyUserDetailsService implements UserDetailsService {
 
  @Autowired
  private MyUserAccountDAO myUserAccountDAO;
 
  public MyUserDetailsService() {
 
  }
 
  // (This method is used by Spring Security API).
  @Override
  public UserDetails loadUserByUserEmail(String email) throws UserEmailNotFoundException {
 
      MyUserAccount myUserAccount = myUserAccountDAO.findByEmail(email);
 
      if (myUserAccount == null) {
          throw new UsernameNotFoundException("No user found with this Email address: " + email);
      }
      // Note: SocialUserDetails extends UserDetails.
      SocialUserDetails principal = new MySocialUserDetails(myUserAccount);
 
      return principal;
  }
 
}