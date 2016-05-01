package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.ProgramType;
import io.sheetal.exception.ProgramTypeAlreadyExistsException;
import io.sheetal.exception.ProgramTypeNotFoundException;

import io.sheetal.repository.ProgramTypeRepository;

@Service
@Transactional
public class ProgramTypeServiceImpl implements ProgramTypeService{

	@Autowired 	
	private ProgramTypeRepository repository;
	
	@Override
	public List<ProgramType> findAllProgramTypes() {
		return repository.findAllProgramTypes();
	}

	@Override
	public ProgramType findOneProgramType(String programTypeId) throws ProgramTypeNotFoundException {
		ProgramType programType=repository.findOneProgramType(programTypeId);
		if(programType==null)
			throw new ProgramTypeNotFoundException();
		else
			return programType;
	}

	@Override
	public ProgramType create(ProgramType programType) throws ProgramTypeAlreadyExistsException {
		ProgramType existingProgramType=repository.findByProgramType(programType.getProgramType());
		if(existingProgramType!=null)
			throw new ProgramTypeAlreadyExistsException();
		else
			return repository.create(programType);
	}

	@Override
	public ProgramType update(ProgramType programType) throws ProgramTypeNotFoundException {
		ProgramType existingProgramType=repository.findOneProgramType(programType.getProgramTypeId());
		if(existingProgramType==null)
			throw new ProgramTypeNotFoundException();
		else
			return repository.update(programType);
	}

	@Override
	public void delete(String programTypeId) throws ProgramTypeNotFoundException {
		ProgramType existingProgramType=repository.findOneProgramType(programTypeId);
		if(existingProgramType==null)
			throw new ProgramTypeNotFoundException();
		else
			repository.delete(existingProgramType);
		
	}
}
