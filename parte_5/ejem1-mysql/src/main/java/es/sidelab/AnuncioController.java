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

@RestController
public class AnuncioController {
	
	@Autowired
	private AnuncioRepository repository;
	
	@GetMapping(value = "/anuncios")
	public List<Anuncio> getAnuncios() {
		return repository.findAll();
	}
	
	@PostMapping(value = "/anuncios")
	public ResponseEntity<Boolean> addAnuncio(@RequestBody Anuncio anuncio) {
		repository.save(anuncio);
		return new ResponseEntity<Boolean>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/anuncio/{asunto}")
	public Anuncio getAnuncio(@PathVariable(value = "asunto") String asunto) {
		return repository.findByAsunto(asunto);
	}

}
