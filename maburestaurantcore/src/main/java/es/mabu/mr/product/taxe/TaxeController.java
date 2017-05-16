package es.mabu.mr.product.taxe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.InvalidValueException;
import es.mabu.mr.product.exception.TaxeHasProductsException;

@RestController
@RequestMapping("/public/taxe")
public class TaxeController {

	@Autowired
	TaxeService taxeService;

	@RequestMapping(method = RequestMethod.GET)
	List<Taxe> getProducts() {
		return taxeService.findAllTaxes();
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	Taxe createTaxe(@RequestBody Taxe taxe) throws DuplicatedInstanceException, InvalidValueException {
		return taxeService.createTaxe(taxe.getName(), taxe.getValue());
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{taxeId}")
	void deleteTaxe(@PathVariable Long taxeId) throws TaxeHasProductsException {
		taxeService.removeTaxe(taxeId);
	}

	@RequestMapping(method = RequestMethod.PATCH, produces = "application/json")
	Taxe updateTaxe(@RequestBody Taxe taxe) throws DuplicatedInstanceException, InvalidValueException {
		return taxeService.updateTaxe(taxe);
	}

}
