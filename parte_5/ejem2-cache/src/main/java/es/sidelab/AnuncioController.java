package es.sidelab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.sidelab.model.Anuncio;
import jakarta.annotation.PostConstruct;

@RestController
public class AnuncioController {
	
	private AnuncioRepository repository;
	
	public AnuncioController(AnuncioRepository repository) {
		super();
		this.repository = repository;
	}

	@PostConstruct
	public void init() {
		Anuncio anuncio = new Anuncio("Mew", "Vendo moto", "Pocos kilómetros");
		repository.save(anuncio);
	}
	
	@GetMapping(value = "/anuncios")
	public List<Anuncio> getAnuncios() {
		return repository.findAll();
	}
	
	@PostMapping(value = "/anuncios")
	public ResponseEntity<Boolean> addAnuncio(@RequestBody Anuncio anuncio) {
		repository.save(anuncio);
		return new ResponseEntity<Boolean>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/anuncio/{name}")
	public Anuncio getAnuncio(@PathVariable String name) {
		return repository.findByNombre(name);
	}

}
