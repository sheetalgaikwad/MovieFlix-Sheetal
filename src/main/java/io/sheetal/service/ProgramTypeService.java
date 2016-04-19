package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.ProgramType;
import io.sheetal.exception.ProgramTypeAlreadyExistsException;
import io.sheetal.exception.ProgramTypeNotFoundException;

public interface ProgramTypeService {

	public List<ProgramType> findAllProgramTypes();
	public ProgramType findOneProgramType(String programTypeId) throws ProgramTypeNotFoundException;
	public ProgramType create(ProgramType programType) throws ProgramTypeAlreadyExistsException;
	public ProgramType update(ProgramType programType) throws ProgramTypeNotFoundException;
	public void delete(String programTypeId) throws ProgramTypeNotFoundException;
}
