package io.sheetal.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Genre;
import io.sheetal.entity.Language;
import io.sheetal.entity.Program;

@Repository
public class ProgramRepositoryImpl implements ProgramRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Program> findAllPrograms() {
		TypedQuery<Program> query=em.createQuery("SELECT p from Program p ORDER BY p.title ASC",Program.class);
		List<Program> programList=query.getResultList();
		return programList;
	}

	@Override
	public Program findOneProgram(String programId) {
		return em.find(Program.class, programId);
	}

	@Override
	public Program findByProgramTitle(String programTitle) {
		TypedQuery<Program> query=em.createQuery("SELECT p from Program p where p.title=:programTitle",Program.class);
		query.setParameter("programTitle",programTitle);
		List<Program> programList=query.getResultList();
		if(programList!=null && programList.size()==1)
			return programList.get(0);
		else
			return null;
	}

	@Override
	public Program create(Program program) {
		em.persist(program);
		return program;
	}

	@Override
	public Program update(Program program) {
		return em.merge(program);
	}

	@Override
	public void delete(Program program) {
		em.remove(program);
		
	}
	
	public List<Program> findByGenre(String genreType) {
		
		TypedQuery<Genre> query=em.createQuery("SELECT g from Genre g where g.genreType=:genreType",Genre.class);
		query.setParameter("genreType",genreType);
		Genre g=query.getSingleResult();
		String genreId=g.getGenreId();		
		
		TypedQuery<Program> query2=em.createQuery("SELECT p from Program p INNER JOIN Program-Genre g ON p.programId=g.program_programId where g.genreId=:genreId",Program.class);
		query2.setParameter("genreId",genreId);
		List<Program> programList=query2.getResultList();
		return programList;
	}

}
