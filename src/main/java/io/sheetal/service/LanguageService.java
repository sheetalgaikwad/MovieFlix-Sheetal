package io.sheetal.service;

import java.util.List;

import io.sheetal.entity.Language;
import io.sheetal.exception.LanguageAlreadyExistsException;
import io.sheetal.exception.LanguageNotFoundException;

public interface LanguageService {

	public List<Language> findAllLanguages();
	public Language findOneLanguage(String languageId) throws LanguageNotFoundException;
	public Language create(Language language) throws LanguageAlreadyExistsException;
	public Language update(Language language) throws LanguageNotFoundException;
	public void delete(String languageId) throws LanguageNotFoundException;
}
