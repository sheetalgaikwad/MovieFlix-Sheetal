package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.WriterRoles;
import io.sheetal.exception.WriterRoleAlreadyExistsException;
import io.sheetal.exception.WriterRoleNotFoundException;

public interface WriterRoleService {

	public List<WriterRoles> findAllWriterRoles();
	public WriterRoles findOneWriterRole(String writerRoleId) throws WriterRoleNotFoundException;
	public WriterRoles create(WriterRoles writerRole) throws WriterRoleAlreadyExistsException;
	public WriterRoles update(WriterRoles writerRole) throws WriterRoleNotFoundException;
	public void delete(String writerRoleId) throws WriterRoleNotFoundException;
}
