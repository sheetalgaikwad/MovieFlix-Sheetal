package io.sheetal.repository;

import java.util.List;

import io.sheetal.entity.Language;

public interface LanguageRepository {

	public List<Language> findAllLanguages();
	public Language findOneLanguage(String languageId);
	public Language findByLanguageName(String languageName);
	public Language create(Language language);
	public Language update(Language language);
	public void delete(Language language);
}
