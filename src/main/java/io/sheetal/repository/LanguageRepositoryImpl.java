package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


import io.sheetal.entity.Language;

@Repository
public class LanguageRepositoryImpl implements LanguageRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Language> findAllLanguages() {
		TypedQuery<Language> query=em.createQuery("SELECT l from Language l ORDER BY l.languageName ASC",Language.class);
		List<Language> languageList=query.getResultList();
		return languageList;
	}

	@Override
	public Language findOneLanguage(String languageId) {
		return em.find(Language.class, languageId);
	}

	@Override
	public Language findByLanguageName(String languageName) {
		TypedQuery<Language> query=em.createQuery("SELECT l from Language l where l.languageName=:languageName",Language.class);
		query.setParameter("languageName",languageName);
		List<Language> languageList=query.getResultList();
		if(languageList!=null && languageList.size()==1)
			return languageList.get(0);
		else
			return null;
	}

	@Override
	public Language create(Language language) {
		em.persist(language);
		return language;
	}

	@Override
	public Language update(Language language) {
		return em.merge(language);
	}

	@Override
	public void delete(Language language) {
		em.remove(language);		
	}

}
