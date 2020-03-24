package com.ricardo.cursomc.resources;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		
		return "REST está funcionando. Parabéns!";
	}

}
