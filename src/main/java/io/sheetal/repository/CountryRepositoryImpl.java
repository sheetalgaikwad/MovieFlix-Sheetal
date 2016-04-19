package io.sheetal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.sheetal.entity.Country;

@Repository
public class CountryRepositoryImpl implements CountryRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Country> findAllCountries() {
		TypedQuery<Country> query=em.createQuery("SELECT c from Country c ORDER BY c.countryName ASC",Country.class);
		List<Country> countryList=query.getResultList();
		return countryList;
	}

	@Override
	public Country findOneCountry(String countryId) {
		return em.find(Country.class, countryId);
	}

	@Override
	public Country findByCountryName(String countryName) {
		TypedQuery<Country> query=em.createQuery("SELECT c from Country c where c.countryName=:countryName",Country.class);
		query.setParameter("countryName",countryName);
		List<Country> countryList=query.getResultList();
		if(countryList!=null && countryList.size()==1)
			return countryList.get(0);
		else
			return null;
	}

	@Override
	public Country create(Country country) {
		em.persist(country);
		return country;
	}

	@Override
	public Country update(Country country) {
		return em.merge(country);
	}

	@Override
	public void delete(Country country) {
		em.remove(country);
		
	}

}
