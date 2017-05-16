package es.mabu.mr.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mabu.mr.product.Product;
import es.mabu.mr.product.ProductDto;
import es.mabu.mr.product.option.Option;
import es.mabu.mr.product.option.OptionDto;
import es.mabu.mr.product.optionline.OptionLine;
import es.mabu.mr.product.optionline.OptionLineDto;
import es.mabu.mr.product.optionlineprice.OptionLinePrice;
import es.mabu.mr.product.optionlineprice.OptionLinePriceDto;
import es.mabu.mr.product.productDetails.ProductDetails;
import es.mabu.mr.product.productDetails.ProductDetailsDto;
import es.mabu.mr.restaurant.RestaurantService;

@Service
public class ProductConversor {

	@Autowired
	RestaurantService restaurantService;

	public List<ProductDto> productToProductDto(List<Product> products) {
		List<ProductDto> pdtos = new ArrayList<>();

		for (Product p : products) {
			pdtos.add(productToProductDto(p));
		}
		return pdtos;
	}

	public ProductDto productToProductDto(Product product) {
		// PRODUCT DETAILS
		List<ProductDetailsDto> productDetailsDtos = new ArrayList<>();
		for (ProductDetails pds : product.getProductDetails()) {
			productDetailsDtos.add(new ProductDetailsDto(pds.getId(), pds.getRestaurant().getId(), pds.getState(),
					pds.getMainPage(), pds.getPrice(), pds.getModified(), pds.getModified()));

		}
		// OPTIONS
		List<OptionDto> os = new ArrayList<>();

		for (Option o : product.getOptions()) {
			// OPTION LINE
			List<OptionLineDto> oldto = new ArrayList<>();

			for (OptionLine ol : o.getOptionLines()) {
				// OPTION LINE PRICE
				List<OptionLinePriceDto> olpdto = new ArrayList<>();
				for (OptionLinePrice olp : ol.getOptionLinePrices()) {
					olpdto.add(new OptionLinePriceDto(olp.getId(), olp.getPriceAdded(), olp.getRestaurant().getId()));
				}
				oldto.add(new OptionLineDto(ol.getId(), ol.getName(), olpdto));
			}
			os.add(new OptionDto(o.getId(), o.getName(), oldto, o.isMain()));
		}

		return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getCategory(),
				product.getTaxe(), product.getCreated(), productDetailsDtos, os, product.getImageName());
	}

	public List<Product> productDtoToProduct(List<ProductDto> productDtos) {
		List<Product> ps = new ArrayList<>();

		for (ProductDto pdto : productDtos) {
			ps.add(productDtoToProduct(pdto));
		}
		return ps;
	}

	public Product productDtoToProduct(ProductDto pdto) {
		// PRODUCT DETAILS
		List<ProductDetails> productDetails = new ArrayList<>();

		for (ProductDetailsDto pddto : pdto.getProductDetails()) {
			productDetails.add(new ProductDetails(null, restaurantService.findRestaurantById(pddto.getRestaurantId()),
					pddto.getState(), pddto.getMainPage(), pddto.getPrice(), null, null));
		}

		// OPTIONS
		List<Option> os = new ArrayList<>();

		for (OptionDto odto : pdto.getOptions()) {
			// OPTION LINE
			List<OptionLine> ols = new ArrayList<>();

			for (OptionLineDto oldto : odto.getOptionLines()) {
				// OPTION LINE PRICE
				List<OptionLinePrice> olps = new ArrayList<>();

				for (OptionLinePriceDto olpdto : oldto.getOptionLinePrices()) {
					olps.add(new OptionLinePrice(olpdto.getPriceAdded(), null,
							restaurantService.findRestaurantById(olpdto.getRestaurantId())));
				}
				ols.add(new OptionLine(oldto.getName(), null, olps));
			}
			os.add(new Option(odto.getName(), ols, null, odto.isMain()));
		}

		return new Product(pdto.getName(), pdto.getDescription(), pdto.getCategory(), pdto.getTaxe(), null,
				productDetails, os);
	}

}
