package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Writer;
import io.sheetal.exception.WriterAlreadyExistsException;
import io.sheetal.exception.WriterNotFoundException;
import io.sheetal.repository.WriterRepository;

@Service
@Transactional
public class WriterServiceImpl implements WriterService{

	@Autowired 	
	private WriterRepository repository;
	
	@Override
	public List<Writer> findAllWriters() {
		return repository.findAllWriters();
	}

	@Override
	public Writer findOneWriter(String writerId) throws WriterNotFoundException {
		Writer writer=repository.findOneWriter(writerId);
		if(writer==null)
			throw new WriterNotFoundException();
		else
			return writer;
	}

	@Override
	public Writer create(Writer writer) throws WriterAlreadyExistsException {
		Writer existingWriter=repository.findByWriterName(writer.getWriterName());
		if(existingWriter!=null)
			throw new WriterAlreadyExistsException();
		else
			return repository.create(writer);
	}

	@Override
	public Writer update(Writer writer) throws WriterNotFoundException {
		Writer existingWriter=repository.findOneWriter(writer.getWriterId());
		if(existingWriter==null)
			throw new WriterNotFoundException();
		else
			return repository.update(writer);
	}

	@Override
	public void delete(String writerId) throws WriterNotFoundException {
		Writer existingWriter=repository.findOneWriter(writerId);
		if(existingWriter==null)
			throw new WriterNotFoundException();
		else
			repository.delete(existingWriter);
		
	}
}
