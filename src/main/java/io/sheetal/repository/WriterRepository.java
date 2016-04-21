package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Writer;

public interface WriterRepository {

	public List<Writer> findAllWriters();
	public Writer findOneWriter(String writerId);
	public Writer findByWriterName(String writerName);
	public Writer create(Writer writer);
	public Writer update(Writer writer);
	public void delete(Writer writer);
}
