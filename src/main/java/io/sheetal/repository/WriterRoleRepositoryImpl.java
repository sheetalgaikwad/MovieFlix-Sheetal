package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.WriterRoles;

@Repository
public class WriterRoleRepositoryImpl implements WriterRoleRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<WriterRoles> findAllWriterRoles() {
		TypedQuery<WriterRoles> query=em.createQuery("SELECT wr from WriterRoles wr ORDER BY wr.writerRoleType ASC",WriterRoles.class);
		List<WriterRoles> writerRoleList=query.getResultList();
		return writerRoleList;
	}

	@Override
	public WriterRoles findOneWriterRole(String writerRoleId) {
		return em.find(WriterRoles.class, writerRoleId);
	}

	@Override
	public WriterRoles findByWriterRoleType(String writerRoleType) {
		TypedQuery<WriterRoles> query=em.createQuery("SELECT wr from WriterRoles wr where wr.writerRoleType=:writerRoleType",WriterRoles.class);
		query.setParameter("writerRoleType",writerRoleType);
		List<WriterRoles> writerRoleList=query.getResultList();
		if(writerRoleList!=null && writerRoleList.size()==1)
			return writerRoleList.get(0);
		else
			return null;
	}

	@Override
	public WriterRoles create(WriterRoles writerRole) {
		em.persist(writerRole);
		return writerRole;
	}

	@Override
	public WriterRoles update(WriterRoles writerRole) {
		return em.merge(writerRole);
	}

	@Override
	public void delete(WriterRoles writerRole) {
		em.remove(writerRole);
		
	}

}
