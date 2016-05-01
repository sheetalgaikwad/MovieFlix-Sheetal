package io.sheetal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sheetal.entity.Language;
import io.sheetal.exception.LanguageAlreadyExistsException;
import io.sheetal.exception.LanguageNotFoundException;

import io.sheetal.repository.LanguageRepository;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService{

	@Autowired 	
	private LanguageRepository repository;
	
	@Override
	public List<Language> findAllLanguages() {
		return repository.findAllLanguages();
	}

	@Override
	public Language findOneLanguage(String languageId) throws LanguageNotFoundException {
		Language language=repository.findOneLanguage(languageId);
		if(language==null)
			throw new LanguageNotFoundException();
		else
			return language;
	}

	@Override
	public Language create(Language language) throws LanguageAlreadyExistsException {
		Language existingLanguage=repository.findByLanguageName(language.getLanguageName());
		if(existingLanguage!=null)
			throw new LanguageAlreadyExistsException();
		else
			return repository.create(language);
	}

	@Override
	public Language update(Language language) throws LanguageNotFoundException {
		Language existingLanguage=repository.findOneLanguage(language.getLanguageId());
		if(existingLanguage==null)
			throw new LanguageNotFoundException();
		else
			return repository.update(language);
	}

	@Override
	public void delete(String languageId) throws LanguageNotFoundException {
		Language existingLanguage=repository.findOneLanguage(languageId);
		if(existingLanguage==null)
			throw new LanguageNotFoundException();
		else
			repository.delete(existingLanguage);
		
	}
}
