package io.sheetal.entity;

import java.math.BigInteger;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
public class Program {
	
	@Id
	@GenericGenerator(strategy = "uuid2",name = "myuuid")
	@GeneratedValue(generator="myuuid")
	private String programId;
	private String title;
	private String year;	
	private Date released;
	private int runtime;
	private String plot;
	//private poster (jpeg);
	private int metascore;
	private double imdbRating;
	private BigInteger imdbVotes;
	private String imdbId;	
	
	@OneToMany
	private List<Genre> genres;
	
	 @OneToMany(cascade = {CascadeType.ALL})
	    @JoinTable(name="program_language", 
	          joinColumns=@JoinColumn(name="program_programId", referencedColumnName="programId"),
	          inverseJoinColumns=@JoinColumn(name="languages_languageId",referencedColumnName="languageId"))	
	private List<Language> languages;
	
	@OneToMany
	private List<Country> countries;
	
	@OneToMany
	private List<Actor> actors;
	
	@OneToMany
	private List<Director> directors;
	
	@OneToMany(cascade = {CascadeType.ALL})
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
