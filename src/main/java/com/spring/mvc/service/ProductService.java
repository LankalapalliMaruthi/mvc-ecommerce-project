package com.spring.mvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.entity.ProductEntity;
import com.spring.mvc.model.ProductModel;
import com.spring.mvc.repository.ProductRepository;

@Service

public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public void saveProductData(ProductModel productModel) {
		double price = productModel.getProPrice();
		String category = productModel.getProCategory();
		double dprice = 0.0;
		switch (category) {
		case "mobile":
			dprice = price * 0.1;
			break;
		case "laptop":
			dprice = price * 0.15;
			break;
		case "printer":
			dprice = price * 0.2;
			break;
		case "camera":
			dprice = price * 0.25;
			break;
		}
		// Read the data from Model set the data to entity
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProName(productModel.getProName());
		productEntity.setProPrice(productModel.getProPrice()); // Example for product price
		productEntity.setProCategory(productModel.getProCategory()); // Example for category
		productEntity.setProDescription(productModel.getProDescription()); // Example for description
		productEntity.setProBrand(productModel.getProBrand());

		productEntity.setProDisPrice(dprice);
		productEntity.setCreatedAt(LocalDate.now());
		productEntity.setCreatedBy(System.getProperty("user.name"));

		productRepository.save(productEntity);

	}

	public List<ProductEntity> getPro() {
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}

	public ProductEntity getProductById(Long id) {
		Optional<ProductEntity> product = productRepository.findById(id);
		return product.orElse(null); // Return the product if found, else return null
	}

	public void deleteProductById(Long id) {
		productRepository.deleteById(id);

	}
	// 3. Get Product By ID for editing
	/*
	 * public ProductModel gettingProductById(Long proId) { ProductEntity
	 * productEntity = productRepository.findById(proId) .orElseThrow(() -> new
	 * RuntimeException("Product not found"));
	 * 
	 * // Convert ProductEntity to ProductModel ProductModel productModel = new
	 * ProductModel(); productModel.setProId(productEntity.getProId());
	 * productModel.setProName(productEntity.getProName());
	 * productModel.setProCategory(productEntity.getProCategory());
	 * productModel.setProPrice(productEntity.getProPrice());
	 * productModel.setProBrand(productEntity.getProBrand());
	 * productModel.setProDescription(productEntity.getProDescription()); return
	 * productModel; }
	 */
	public ProductModel gettingProductById(Long proId) {		
		ProductEntity productEntity= productRepository.findById(proId).get();
		
		ProductModel productModel = new ProductModel();
        productModel.setProName(productEntity.getProName());
        productModel.setProBrand(productEntity.getProBrand());
        productModel.setProPrice(productEntity.getProPrice());
        productModel.setProCategory(productEntity.getProCategory());
        productModel.setProDescription(productEntity.getProDescription());
        
        return productModel;

	}

	// 4. Update Product Details
	public void updateProduct( Long id ,ProductModel updatedProduct) {

		ProductEntity productEntity = productRepository.findById(id).get();
		if (productEntity != null) {
			productEntity.setProName(updatedProduct.getProName());
			productEntity.setProCategory(updatedProduct.getProCategory());
			productEntity.setProPrice(updatedProduct.getProPrice());
			productEntity.setProBrand(updatedProduct.getProBrand());
			productEntity.setProDescription(updatedProduct.getProDescription());
			productRepository.save(productEntity); // Save the updated entity
		}
	}

}