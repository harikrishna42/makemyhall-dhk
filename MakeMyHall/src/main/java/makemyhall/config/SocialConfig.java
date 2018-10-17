package makemyhall.config;

import javax.sql.DataSource;

import makemyhall.dao.MyUserAccountDAO;
import makemyhall.signup.MyConnectionSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
 
@Configuration
@EnableSocial
// Load to Environment.
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {
 
  private boolean autoSignUp = false;
 
  @Autowired
  private DataSource dataSource;
 
  @Autowired
  private MyUserAccountDAO myUserAccountDAO;
  

  //@Override
  public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
 
     /* TwitterConnectionFactory tfactory = new TwitterConnectionFactory(//
              env.getProperty("twitter.consumer.key"), //
              env.getProperty("twitter.consumer.secret"));
 
      // tfactory.setScope(env.getProperty("twitter.scope"));
      
      cfConfig.addConnectionFactory(tfactory);*/
 
      FacebookConnectionFactory ffactory = new FacebookConnectionFactory(//
              env.getProperty("facebook.app.id"), //
              env.getProperty("facebook.app.secret"));
      
      
      ffactory.setScope(env.getProperty("facebook.scope"));
      
      // auth_type=reauthenticate
 
      cfConfig.addConnectionFactory(ffactory);
 
     /* // Linkedin
      LinkedInConnectionFactory lfactory = new LinkedInConnectionFactory(//
              env.getProperty("linkedin.consumer.key"), //
              env.getProperty("linkedin.consumer.secret"));
 
      lfactory.setScope(env.getProperty("linkedin.scope"));
      
      cfConfig.addConnectionFactory(lfactory);*/
 
      
      // Google
      GoogleConnectionFactory gfactory = new GoogleConnectionFactory(//
              env.getProperty("google.client.id"), //
              env.getProperty("google.client.secret"));
 
      gfactory.setScope(env.getProperty("google.scope"));
      
      cfConfig.addConnectionFactory(gfactory);
  }
 
  // The UserIdSource determines the userID of the user.
  //@Override
  public UserIdSource getUserIdSource() {
      return new AuthenticationNameUserIdSource();
  }
 

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
 
      JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
              connectionFactoryLocator,
 
              Encryptors.noOpText());
 
      if (autoSignUp) {
         
          ConnectionSignUp connectionSignUp = new MyConnectionSignUp(myUserAccountDAO);
          usersConnectionRepository.setConnectionSignUp(connectionSignUp);
      } else {
         
          usersConnectionRepository.setConnectionSignUp(null);
      }
      return usersConnectionRepository;
  }

  @Bean
  public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, //
          ConnectionRepository connectionRepository) {
      return new ConnectController(connectionFactoryLocator, connectionRepository);
  }
  
  
}

