package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.WriterRoles;

public interface WriterRoleRepository {

	public List<WriterRoles> findAllWriterRoles();
	public WriterRoles findOneWriterRole(String writerRoleId);
	public WriterRoles findByWriterRoleType(String writerRoleType);
	public WriterRoles create(WriterRoles writerRole);
	public WriterRoles update(WriterRoles writerRole);
	public void delete(WriterRoles writerRole);
}
