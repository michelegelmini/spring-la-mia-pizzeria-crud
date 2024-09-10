package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/menu")
public class PizzaController {

	@Autowired
	// repository field con autowired per dipendency injection
	private PizzaRepository repo;

	@GetMapping
	public String index(Model model, @RequestParam(name = "name", required = false) String name) {

		model.addAttribute("pizzaName", name);
		List<Pizza> pizzaList;

		if (name!=null && !name.isEmpty()) {
			model.addAttribute("pizzaName", name);
			pizzaList = repo.findByNameContainingIgnoreCaseOrderByNameAsc(name);
			
		} else {
			// prendo i dati da consegnare a pizzas
			pizzaList = repo.findAll(Sort.by("name"));
		}

		//pizzaList = repo.findAll();
		// li inserisco nel modello
		model.addAttribute("menu", pizzaList);
		
		return "/pizzas/menu";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer pizzaId, Model model) {
		model.addAttribute("pizza", repo.findById(pizzaId).get());
		return "/pizzas/show";
	}
	
//	@GetMapping("search/{name}")
//	public String findByNameContainingIgnoreCaseOrderByNameAsc(@PathVariable String name, Model model) {
//		model.addAttribute("pizzas", repo.findByNameContainingIgnoreCaseOrderByNameAsc(name));
//		return "/pizzas/menu";
//	}


}
 