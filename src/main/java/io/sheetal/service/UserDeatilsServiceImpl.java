package io.sheetal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.User;
import io.sheetal.entity.UserRole;
import io.sheetal.exception.UserNotFoundException;
import io.sheetal.repository.UserRepository;

@Service("userDetailsService")
public class UserDeatilsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user=repository.findByUserName(userName);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
		return (UserDetails) buildUserForAuthentication(user, authorities);
		
	}
	
	public void checkPermission(Set<GrantedAuthority> authorities)
	{		
		SimpleGrantedAuthority simple=new SimpleGrantedAuthority("admin");
		if(authorities.contains(simple))
			System.out.println("Permitted!");
		else
			System.out.println("Permission denied!");
	}
	
	//build user's authorities
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

		return result;
	}
	
	private User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		
		User u=new User();
		u.setId(user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setUserRoles(user.getUserRoles());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		
		return user;
		
	}

}
