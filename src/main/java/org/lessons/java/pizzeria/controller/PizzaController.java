package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class PizzaController {
	
	@Autowired
	//repository field con autowired per dipendency injection
	private PizzaRepository repo;
	
	@GetMapping()
	public String index(Model model) {
		
		//prendo i dati da consegnare a pizzas
		List <Pizza> menu = repo.findAll();
		//li inserisco nel modello
		model.addAttribute("menu", menu);
		
		
		return "/pizzas/menu";
	}

}
