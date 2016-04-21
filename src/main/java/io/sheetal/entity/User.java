package io.sheetal.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table
@Data
@NamedQuery(name = "User.findByEmail", query = "SELECT u from User u WHERE u.email=:pEmail")
public class User implements Serializable {

	@Id
	@GenericGenerator(strategy = "uuid2",name = "myuuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	@Column(unique=true)
	private String email;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<UserRole> userRoles;
	
	
	
	
}
  