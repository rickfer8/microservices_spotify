package br.com.spotity.play.musica.service.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.spotity.play.musica.service.domain.PlayList;

public interface PlayListRepository extends CrudRepository<PlayList, UUID> {

}
