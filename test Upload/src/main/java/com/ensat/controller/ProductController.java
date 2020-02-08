package com.ensat.controller;

import com.ensat.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensat.entities.Product;


/**
 * Product controller
 * **/
@Controller
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public void SetProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * List all products
	 * @param model
	 * @return
	 *  **/
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println("왔다왔어!!!!");
		model.addAttribute("products", productService.listAllProducts());
		System.out.println("Returning rproducts:");
		return "products";
	}
	
	
	
	/**
	 * id로 상세보기 
	 * @param model
	 * @return
	 *  **/
	@RequestMapping("product/{id}")
	public String showProduct(@PathVariable Integer id, Model model) {
		System.out.println("아이디왔군?? 그담?");
		model.addAttribute("product", productService.getProductById(id));
		System.out.println("탔나???? 탔나?????");
		return "productshow";
	}
	
	/**
	 * id로 수정
	 * @param model
	 * @return
	 *  **/
	@RequestMapping("product/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productform";
	}
	
	/**
	 * New product.
	 * @param model
	 * @return
	 *  **/
	@RequestMapping("product/new")
	public String newProduct(Model model) {
		System.out.println("생성!!!!");
		model.addAttribute("product", new Product());
		return "productform";
	}
	
	/**
	 * db insert
	 * @param model
	 * @return
	 *  **/
	@RequestMapping(value="product", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		System.out.println("왔지??: " + product.getProductId());
		productService.saveProduct(product);
		System.out.println("저장됐나확인: " + product.getName());
		return "redirect:/products" ;
	}
	
	/**
	 * Delete product by ist id
	 * @param model
	 * @return
	 *  **/
	@RequestMapping("product/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
	
}
