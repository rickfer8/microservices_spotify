package br.com.curso.save.musica.service.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.curso.save.musica.service.domain.Musica;

public interface MusicaRepository extends CrudRepository<Musica, UUID> {

}
