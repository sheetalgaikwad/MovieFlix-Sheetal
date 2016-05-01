package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Writer;

@Repository
public class WriterRepositoryImpl implements WriterRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Writer> findAllWriters() {
		TypedQuery<Writer> query=em.createQuery("SELECT w from Writer w ORDER BY w.writerName ASC",Writer.class);
		List<Writer> writerList=query.getResultList();
		return writerList;
	}

	@Override
	public Writer findOneWriter(String writerId) {
		return em.find(Writer.class, writerId);
	}

	@Override
	public Writer findByWriterName(String writerName) {
		TypedQuery<Writer> query=em.createQuery("SELECT w from Writer w where w.writerName=:writerName",Writer.class);
		query.setParameter("writerName",writerName);
		List<Writer> writerList=query.getResultList();
		if(writerList!=null && writerList.size()==1)
			return writerList.get(0);
		else
			return null;
	}

	@Override
	public Writer create(Writer writer){
		
		em.persist(writer);
		return writer;
	}

	@Override
	public Writer update(Writer writer) {
		return em.merge(writer);
	}

	@Override
	public void delete(Writer writer) {
		em.remove(writer);
		
	}

}
