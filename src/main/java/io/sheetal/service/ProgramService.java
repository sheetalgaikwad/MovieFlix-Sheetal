package io.sheetal.service;

import java.util.Date;
import java.util.List;

import io.sheetal.entity.Program;
import io.sheetal.exception.ProgramAlreadyExistsException;
import io.sheetal.exception.ProgramNotFoundException;

public interface ProgramService {

	public List<Program> findAllPrograms();
	public Program findOneProgram(String programId) throws ProgramNotFoundException;
	public List<Program> findByGenre(String genre); 
	public Program create(Program program) throws ProgramAlreadyExistsException;
	public Program update(Program program) throws ProgramNotFoundException;
	public void delete(String programId) throws ProgramNotFoundException;
}
