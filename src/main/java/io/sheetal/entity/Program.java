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
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "program_genre",
    	joinColumns = @JoinColumn(name = "Program_programId"),
    inverseJoinColumns = @JoinColumn(name = "genreId"))
	private Set<Genre> genres;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "program_language",
    	joinColumns = @JoinColumn(name = "Program_programId"),
    inverseJoinColumns = @JoinColumn(name = "languageId"))
	private Set<Language> languages;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "program_country",
    	joinColumns = @JoinColumn(name = "Program_programId"),
    inverseJoinColumns = @JoinColumn(name = "countryId"))
	private Set<Country> countries;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "program_actor",
    	joinColumns = @JoinColumn(name = "Program_programId"),
    inverseJoinColumns = @JoinColumn(name = "actorId"))
	private Set<Actor> actors;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "program_director",
    	joinColumns = @JoinColumn(name = "Program_programId"),
    inverseJoinColumns = @JoinColumn(name = "directorId"))
	private Set<Director> directors;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "program_writer",
    	joinColumns = @JoinColumn(name = "Program_programId"),
    inverseJoinColumns = @JoinColumn(name = "writerId"))
	private Set<Writer> writers;
	
	@OneToOne(cascade = {CascadeType.ALL})	
	private Rating rating;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Awards awards;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private ProgramType programType;

}
