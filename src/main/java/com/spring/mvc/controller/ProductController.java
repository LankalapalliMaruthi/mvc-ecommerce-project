package com.spring.mvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.spring.mvc.entity.ProductEntity;
import com.spring.mvc.model.ProductModel;
import com.spring.mvc.service.ProductService;

import jakarta.validation.Valid;
@Controller
public class ProductController {
	@Autowired
	ProductService s;
	@GetMapping("/productform")
public String getProductForm(Model model) {
	//give form with empty object
		ProductModel pm=new ProductModel();
		 
		model.addAttribute("productModel",pm);
		model.addAttribute("page", "productform");
		return "Index";
	
}
	@PostMapping("/saveProduct")
	public String saveProduct(@Valid @ModelAttribute("productModel") ProductModel productModel,
            BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "product";}
		s.saveProductData(productModel);
		return "Index";
	}
	@GetMapping("/getProduct")
	public String getProducts(Model model) {
		List<ProductEntity>products=s.getPro();
		model.addAttribute("products",products);
		model.addAttribute("page", "getProduct");
		return "Index";
		
	}
	 @GetMapping("/getId")
	    public String showProductForm(Model model) {
	        model.addAttribute("getData", new ProductEntity());
	        model.addAttribute("page", "getId");
	        return "Index";  // Thymeleaf form template
	    }
	  // Handle POST request to get product by ID
		/*
		 * @PostMapping("/setId") public String setId(@RequestParam Long id, Model
		 * model) { ProductEntity product = s.getProductById(id);
		 * 
		 * if (product != null) { model.addAttribute("products", product);
		 * 
		 * return "productId"; // Display product details } else {
		 * model.addAttribute("message", "Product not found");
		 * 
		 * // Return to the form if the product is not found }
		 * model.addAttribute("proId", id); return "productId"; }
		 */
	 
	 // Handle POST request to get product by ID
	    @PostMapping("/setId")
	    public String setId(@RequestParam Long id, Model model) {
	        ProductEntity product = s.getProductById(id);
	        if (product != null) {
	            model.addAttribute("products", product);
	            
	        } else {
	            model.addAttribute("message", "Product not found");
	        }
	        model.addAttribute("proId", id);
			model.addAttribute("page", "getProduct");  // Set the page to be 'getProduct' after search
	        return "Index";  // Display within the Index page, not a new page
	    }
    // 4. Delete product by ID
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long id) {
        s.deleteProductById(id);
        return "redirect:/getProduct";
    }
    // 5. Display the Edit Form for a specific product
	/*
	 * @GetMapping("/edit") public String editProduct(@RequestParam("proId") Long
	 * proId, Model model) { ProductModel productModel =
	 * s.gettingProductById(proId); model.addAttribute("productModel",
	 * productModel); model.addAttribute("proId", proId); return "edit"; // Renders
	 * the editProduct.html template }
	 */
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("proId") Long proId, Model model) {
        
        ProductModel productModel = s.gettingProductById(proId);
        model.addAttribute("productModel", productModel);
//        model.addAttribute("categories", Arrays.asList("Mobile", "Camera", "Printer", "Laptop", "Accessories"));

        model.addAttribute("proId", proId);
        
        return "edit";  
    }

  
	
	  @PostMapping("/update")
	  public String updateProduct(@RequestParam Long proId, @ModelAttribute ProductModel productModel) {
	  s.updateProduct(proId, productModel); 
	  return "redirect:/getProduct"; //Ensure this endpoint exists and is accessible
	  }
	    
	  
	  @GetMapping("/about")
	  public String about(Model model) {
		  model.addAttribute("page", "about");
			return "Index";
	  }
	  	
	  	@GetMapping("/contact")
	  	public String contact(Model model) {
	  		model.addAttribute("page", "contact");
			return "Index";
	  	}
	  	
	  	@GetMapping("/home")
	  	public String home(Model model) {
	  		model.addAttribute("page", "home");
			return "Index";}
	  	
	  	
	  	@GetMapping("/")
	  	public String products(Model model) {
	  		return "Index";
	  	}

}
