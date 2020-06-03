package br.com.spotity.playlist.add.service.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.spotity.playlist.add.service.domain.PlayList;

public interface PlayListRepository extends CrudRepository<PlayList, UUID> {

}
