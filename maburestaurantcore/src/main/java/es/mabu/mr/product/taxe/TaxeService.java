package es.mabu.mr.product.taxe;

import java.util.List;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.InvalidValueException;
import es.mabu.mr.product.exception.TaxeHasProductsException;

public interface TaxeService {

	public List<Taxe> findAllTaxes();

	public Taxe findTaxeByName(String name);

	public Taxe findTaxeById(Long id);

	public Taxe createTaxe(String name, Integer value) throws DuplicatedInstanceException, InvalidValueException;

	public void removeTaxe(Long taxeId) throws TaxeHasProductsException;

	public Taxe updateTaxe(Taxe taxe) throws DuplicatedInstanceException, InvalidValueException;

}
