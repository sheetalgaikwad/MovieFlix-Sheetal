package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.WriterRoles;
import io.sheetal.exception.WriterRoleAlreadyExistsException;
import io.sheetal.exception.WriterRoleNotFoundException;

import io.sheetal.repository.WriterRoleRepository;

@Service
@Transactional
public class WriterRoleServiceImpl implements WriterRoleService{

	@Autowired 	
	private WriterRoleRepository repository;
	
	@Override
	public List<WriterRoles> findAllWriterRoles() {
		return repository.findAllWriterRoles();
	}

	@Override
	public WriterRoles findOneWriterRole(String writerRoleId) throws WriterRoleNotFoundException {
		WriterRoles writerRole=repository.findOneWriterRole(writerRoleId);
		if(writerRole==null)
			throw new WriterRoleNotFoundException();
		else
			return writerRole;
	}

	@Override
	public WriterRoles create(WriterRoles writerRole) throws WriterRoleAlreadyExistsException {
		WriterRoles existingWriterRole=repository.findByWriterRoleType(writerRole.getWriterRoleType());
		if(existingWriterRole!=null)
			throw new WriterRoleAlreadyExistsException();
		else
			return repository.create(writerRole);
	}

	@Override
	public WriterRoles update(WriterRoles writerRole) throws WriterRoleNotFoundException {
		WriterRoles existingWriterRole=repository.findOneWriterRole(writerRole.getWriterRolesId());
		if(existingWriterRole==null)
			throw new WriterRoleNotFoundException();
		else
			return repository.update(writerRole);
	}

	@Override
	public void delete(String writerRoleId) throws WriterRoleNotFoundException {
		WriterRoles existingWriterRole=repository.findOneWriterRole(writerRoleId);
		if(existingWriterRole==null)
			throw new WriterRoleNotFoundException();
		else
			repository.delete(existingWriterRole);
		
	}
}
