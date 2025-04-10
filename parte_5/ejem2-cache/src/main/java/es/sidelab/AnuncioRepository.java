package es.sidelab;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.sidelab.model.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	Anuncio save(Anuncio anuncio);
	
//	select nombre, asunto, comentario
//	from anuncios
//	where nombre = nombreAnuncio
	Anuncio findByNombre(String nombre);
	
	List<Anuncio> findAll();
	
}
