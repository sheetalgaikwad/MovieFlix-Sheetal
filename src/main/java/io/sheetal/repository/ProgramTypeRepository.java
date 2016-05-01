package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.ProgramType;

public interface ProgramTypeRepository {

	public List<ProgramType> findAllProgramTypes();
	public ProgramType findOneProgramType(String ProgramTypeId);
	public ProgramType findByProgramType(String ProgramType);
	public ProgramType create(ProgramType ProgramType);
	public ProgramType update(ProgramType ProgramType);
	public void delete(ProgramType programType);
}
