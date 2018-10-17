package makemyhall.config;

import makemyhall.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
   @Autowired
   private MyUserDetailsService myUserDetailsService;
 
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
       auth.userDetailsService(myUserDetailsService);
   }
 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
 
       http.csrf().disable();

       http.authorizeRequests().antMatchers("/", "/signup", "/login", "/logout").permitAll();
       http.authorizeRequests().antMatchers("/userInfo").access("hasRole('ROLE_USER')");
       http.authorizeRequests().and().formLogin()//
               // Submit URL of login page.
               .loginProcessingUrl("/j_spring_security_check") // Submit URL
               .loginPage("/login")//
               .defaultSuccessUrl("/userInfo")//
               .failureUrl("/login?error=true")//
               .usernameParameter("username")//
               .passwordParameter("password");
 
       http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
 
       http.apply(new SpringSocialConfigurer()).signupUrl("/signup");
 
   }
    
   @Override
   public UserDetailsService userDetailsService() {
       return myUserDetailsService;
   }
    
}
