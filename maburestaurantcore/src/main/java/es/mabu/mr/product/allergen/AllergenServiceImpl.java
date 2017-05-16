package es.mabu.mr.product.allergen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;

@Service
public class AllergenServiceImpl implements AllergenService {

	@Autowired
	AllergenRepository allergenRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Allergen> findAllAllergens() {
		return allergenRepository.findAll();
	}

	@Override
	@Transactional
	public Allergen createAllergen(String name) throws DuplicatedInstanceException {

		if (allergenRepository.findByName(name) != null) {
			throw new DuplicatedInstanceException("There is a allergen with name '" + name + "'");
		}
		return allergenRepository.save(new Allergen(name));
	}

	@Override
	@Transactional
	public void removeAllergen(Long allergenId) {
		allergenRepository.delete(allergenRepository.findOne(allergenId));

	}

	@Override
	@Transactional
	public Allergen updateAllergen(Allergen allergen) throws DuplicatedInstanceException {

		if (allergenRepository.findByName(allergen.getName()) != null) {
			throw new DuplicatedInstanceException("There is a allergen with name '" + allergen.getName() + "'");
		}
		return allergenRepository.save(allergen);
	}

	@Override
	public Allergen findAllerenByName(String name) {
		return allergenRepository.findByName(name);
	}
}
