package net.codejava.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.codejava.entity.Product;
import net.codejava.service.ProductService;

@Controller
@RequestMapping("/views/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String ListProducts(Model model) {
		
		List<Product> listProducts = productService.ListAll();
		
		model.addAttribute("titule", "List of Products");
		model.addAttribute("lProduct", listProducts);
		
		return "/views/products/list";
	}
	
	@GetMapping("/new")
	public String Create(Model model) {
		
		Product  product = new Product();
		
		model.addAttribute("titule", "New Product");
		model.addAttribute("product", product);
		
		return "/views/products/new_product";
	}
	
	@PostMapping("/save")
	public String Save(@Valid @ModelAttribute Product product, BindingResult result, 
			Model model, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("titule", "New Product");
			model.addAttribute("product", product);
			System.out.println("Errores!");
			return "/views/products/new_product";
		}
		
		productService.Save(product);
		
		System.out.println("Cliente guardado con exito!");
		attribute.addFlashAttribute("success", "Producto Guardado");
		
		return "redirect:/views/products/";
	}
	
	@GetMapping("/edit/{id}")
	public String Edit(@PathVariable("id") Long idProduct, Model model, RedirectAttributes attribute) {
		
		Product product = null;
		
		if (idProduct > 0) {
			product = productService.BforId(idProduct);
			
			if(product==null) {
				System.out.println("No existe el ID del producto!");
				attribute.addFlashAttribute("error", "No existe el ID del producto!");
				return "redirect:/views/products/";
			}
		}else {
			System.out.println("Error con el ID!");
			attribute.addFlashAttribute("error", "Error con el ID!");
			return "redirect:/views/products/";
		}
		 
		model.addAttribute("titule", "Edit Product");
		model.addAttribute("product", product);
		
		return "/views/products/new_product";
	}
	
	@GetMapping("/delete/{id}")
	public String Delete(@PathVariable("id") Long idProduct, RedirectAttributes attribute) {
		
		Product product = null;
		
		if (idProduct > 0) {
			product = productService.BforId(idProduct);
			
			if(product==null) {
				System.out.println("No existe el ID del producto!");
				attribute.addFlashAttribute("error", "No existe el ID del producto!");
				return "redirect:/views/products/";
			}
		}else {
			System.out.println("Error con el ID");	
			attribute.addFlashAttribute("error", "Error con el ID!");
			return "redirect:/views/products/";
		}
		
		productService.Delete(idProduct);
		attribute.addFlashAttribute("warning", "Registro Eliminado!");
		
		return "redirect:/views/products/";
	}
	
	
}
