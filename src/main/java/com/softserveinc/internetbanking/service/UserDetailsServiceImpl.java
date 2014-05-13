package com.softserveinc.internetbanking.service;

import com.softserveinc.internetbanking.dao.jdbctemplate.UserJDBCTemplate;
import com.softserveinc.internetbanking.model.Role;
import com.softserveinc.internetbanking.model.User;
import com.softserveinc.internetbanking.model.UserStatus;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * UserDetailsServiceImpl realization of UserDetailsService 
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

        private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        private UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
        
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userJDBCTemplate.findUserByName(username); //our own User model class

		if(user!=null){
                    String role = userJDBCTemplate.findUserRole(user.getId());
			String password = user.getPassword();
			//additional information on the security object
			boolean enabled = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean credentialsNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonLocked = user.getStatus().equals(UserStatus.ACTIVE);
			
			//Let's populate user role
			Collection<GrantedAuthority> authorities = new ArrayList<>();
                        
			authorities.add(new SimpleGrantedAuthority(role));
			
			//Now let's create Spring Security User object
			org.springframework.security.core.userdetails.User securityUser = new 
					org.springframework.security.core.userdetails.User
                                        (username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			return securityUser;
		}else{
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}

}
