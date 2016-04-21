package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Writer;
import io.sheetal.exception.WriterAlreadyExistsException;
import io.sheetal.exception.WriterNotFoundException;

public interface WriterService {

	public List<Writer> findAllWriters();
	public Writer findOneWriter(String writerId) throws WriterNotFoundException;
	public Writer create(Writer writer) throws WriterAlreadyExistsException;
	public Writer update(Writer writer) throws WriterNotFoundException;
	public void delete(String writerId) throws WriterNotFoundException;
}
