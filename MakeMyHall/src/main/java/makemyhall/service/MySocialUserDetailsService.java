package makemyhall.service;

import makemyhall.dao.MyUserAccountDAO;
import makemyhall.model.MyUserAccount;
import makemyhall.user.MySocialUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
 
@Service
public class MySocialUserDetailsService implements SocialUserDetailsService {
    
    
   @Autowired
   private MyUserAccountDAO myUserAccountDAO;
 
   // Loads the UserDetails by using the userID of the user.
   // (This method Is used by Spring Security API).
   //@Override
   public SocialUserDetails loadUserByUserEmail(String email) throws UsernameNotFoundException, DataAccessException {
 
       MyUserAccount account= myUserAccountDAO.findByEmail(email);
        
       MySocialUserDetails userDetails= new MySocialUserDetails(account);
        
       return userDetails;
   }
 
}
