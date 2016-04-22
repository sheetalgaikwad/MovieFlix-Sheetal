package io.sheetal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Program;
import io.sheetal.exception.ProgramAlreadyExistsException;
import io.sheetal.exception.ProgramNotFoundException;

import io.sheetal.repository.ProgramRepository;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService{

	@Autowired 	
	private ProgramRepository repository;
	
	@Override
	public List<Program> findAllPrograms() {
		return repository.findAllPrograms();
	}

	@Override
	public Program findOneProgram(String programId) throws ProgramNotFoundException {
		Program program=repository.findOneProgram(programId);
		if(program==null)
			throw new ProgramNotFoundException();
		else
			return program;
	}

	@Override
	public Program create(Program program) throws ProgramAlreadyExistsException {
		Program existingProgram=repository.findByProgramTitle(program.getTitle());
		if(existingProgram!=null)
			throw new ProgramAlreadyExistsException();
		else
			return repository.create(program);
	}

	@Override
	public Program update(Program program) throws ProgramNotFoundException {
		Program existingProgram=repository.findOneProgram(program.getProgramId());
		if(existingProgram==null)
			throw new ProgramNotFoundException();
		else
			return repository.update(program);
	}

	@Override
	public void delete(String programId) throws ProgramNotFoundException {
		Program existingProgram=repository.findOneProgram(programId);
		if(existingProgram==null)
			throw new ProgramNotFoundException();
		else
			repository.delete(existingProgram);
		
	}	

	@Override
	public List<Program> findByGenre(String genre) {
		return repository.findByGenre(genre);
	}
}
