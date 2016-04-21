package io.sheetal.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table
@Data
public class Program implements Serializable {
	
	@Id
	@GenericGenerator(strategy = "uuid2",name = "myuuid")
	@GeneratedValue(generator="myuuid")
	private String programId;
	private String title;
	private String year;	
	private Date released;
	private int runtime;
	private String plot;
	
	private int metascore;
	private double imdbRating;
	private BigInteger imdbVotes;
	private String imdbId;	
	
	@Column(columnDefinition="mediumBlob")
	private byte[] poster;
	
	@OneToMany(mappedBy="program")
	private List<Genre> genres;
	
	@OneToMany(mappedBy="program")
	private List<Language> languages;
	
	@OneToMany(mappedBy="program")
	private List<Country> countries;
	
	@OneToMany(mappedBy="program")
	private List<Actor> actors;
	
	@OneToMany(mappedBy="program")
	private List<Director> directors;
	
	@OneToMany(mappedBy="program")
	private List<Writer> writers;
	
	@OneToOne(cascade = {CascadeType.ALL})	
	private Rating rating;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Awards awards;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private ProgramType programType;
	
	public Program()
	{
		languages=new ArrayList<>();
	}
	
	public void add(Language language)
	{
		languages.add(language);
	}

}
