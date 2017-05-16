package es.mabu.mr.product.taxe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.InvalidValueException;
import es.mabu.mr.product.Product;
import es.mabu.mr.product.ProductRepository;
import es.mabu.mr.product.exception.TaxeHasProductsException;

@Service
public class TaxeServiceImpl implements TaxeService {

	@Autowired
	TaxeRepository taxeRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Taxe> findAllTaxes() {
		return taxeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Taxe findTaxeById(Long id) {
		return taxeRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Taxe findTaxeByName(String name) {
		return taxeRepository.findByName(name);
	}

	@Override
	@Transactional
	public Taxe createTaxe(String name, Integer value) throws DuplicatedInstanceException, InvalidValueException {

		if (taxeRepository.findByName(name) != null) {
			throw new DuplicatedInstanceException("There is a taxe with name '" + name + "'");
		}

		if (value > 100 || value < 0) {
			throw new InvalidValueException("Value must be between 0 and 100");
		}

		return taxeRepository.save(new Taxe(name, value));

	}

	@Override
	@Transactional
	public void removeTaxe(Long taxeId) throws TaxeHasProductsException {
		List<Product> products = productRepository.findByTaxeId(taxeId);
		if (!products.isEmpty()) {
			throw new TaxeHasProductsException("This taxe has products");
		}
		taxeRepository.delete(taxeRepository.findOne(taxeId));

	}

	@Override
	@Transactional
	public Taxe updateTaxe(Taxe taxe) throws InvalidValueException, DuplicatedInstanceException {
		Taxe t = taxeRepository.findByName(taxe.getName());
		// There is not taxe with this name
		if (t == null) {
			if (taxe.getValue() > 100 && taxe.getValue() < 0) {
				throw new InvalidValueException("Value must be between 0 and 100");
			}
			return taxeRepository.save(taxe);

		} else {// There is taxe with this name

			if (t.getId() != taxe.getId()) {
				throw new DuplicatedInstanceException("There is a taxe with name '" + taxe.getName() + "'");
			}

			if (taxe.getValue() > 100 && taxe.getValue() < 0) {
				throw new InvalidValueException("Value must be between 0 and 100");
			}

			return taxeRepository.save(taxe);

		}

	}

}
