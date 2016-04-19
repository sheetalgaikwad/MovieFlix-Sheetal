package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.ProgramType;

@Repository
public class ProgramTypeRepositoryImpl implements ProgramTypeRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ProgramType> findAllProgramTypes() {
		TypedQuery<ProgramType> query=em.createQuery("SELECT p from ProgramType p ORDER BY p.programType ASC",ProgramType.class);
		List<ProgramType> programTypeList=query.getResultList();
		return programTypeList;
	}

	@Override
	public ProgramType findOneProgramType(String programTypeId) {
		return em.find(ProgramType.class, programTypeId);
	}

	@Override
	public ProgramType findByProgramType(String programType) {
		TypedQuery<ProgramType> query=em.createQuery("SELECT p from ProgramType p where p.programType=:programType",ProgramType.class);
		query.setParameter("programType",programType);
		List<ProgramType> programTypeList=query.getResultList();
		if(programTypeList!=null && programTypeList.size()==1)
			return programTypeList.get(0);
		else
			return null;
	}

	@Override
	public ProgramType create(ProgramType programType) {
		em.persist(programType);
		return programType;
	}

	@Override
	public ProgramType update(ProgramType programType) {
		return em.merge(programType);
	}

	@Override
	public void delete(ProgramType programType) {
		em.remove(programType);
		
	}

}
