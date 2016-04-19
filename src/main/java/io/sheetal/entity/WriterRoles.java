package io.sheetal.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table
@Data
public class WriterRoles {

	@Id
	@GenericGenerator(strategy = "uuid2",name = "myuuid")
	@GeneratedValue(generator="myuuid")
	private String writerRolesId;
	private String writerRoleType;
	
//	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//    @JoinTable(name="writer_writerroles", 
//	joinColumns={@JoinColumn(name="writer_writerId", referencedColumnName="writerId")},
//	inverseJoinColumns={@JoinColumn(name="writerRoles_writerRolesId", referencedColumnName="writerRolesId")})
	//private List<Writer> writers;
	
//	public Writer addWriter(Writer writer)
//	{
//		writers.add(writer);
//		return writer;
//	}
}
