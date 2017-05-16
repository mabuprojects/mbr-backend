package es.mabu.mr.product.allergen;

import java.util.List;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;

public interface AllergenService {
	public List<Allergen> findAllAllergens();

	public Allergen createAllergen(String name) throws DuplicatedInstanceException;

	public void removeAllergen(Long allergenId);

	public Allergen findAllerenByName(String name);

	public Allergen updateAllergen(Allergen allergen) throws DuplicatedInstanceException;

}
