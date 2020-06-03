package br.com.spotity.playlist.add.service.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spotity.playlist.add.service.domain.PlayList;
import br.com.spotity.playlist.add.service.repository.PlayListRepository;

@Service
public class PlayListService {

	@Autowired
	private PlayListRepository playListRepository;

	public UUID execute(PlayList playList) {
		playList.setId(UUID.randomUUID());
		playListRepository.save(playList);
		return playList.getId();
	}

}
