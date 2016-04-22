package io.sheetal.repository;

import java.util.Date;
import java.util.List;

import io.sheetal.entity.Program;

public interface ProgramRepository {

	public List<Program> findAllPrograms();
	public Program findOneProgram(String programId);
	public Program findByProgramTitle(String programTitle);
	public List<Program> findByGenre(String genre);
	public Program create(Program program);
	public Program update(Program program);
	public void delete(Program program);
}
