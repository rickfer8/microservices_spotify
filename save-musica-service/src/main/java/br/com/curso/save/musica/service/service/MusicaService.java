package br.com.curso.save.musica.service.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.save.musica.service.domain.Musica;
import br.com.curso.save.musica.service.repository.MusicaRepository;

@Service
public class MusicaService {

	@Autowired
	private MusicaRepository musicaRepository;

	public UUID execute(Musica musica) {
		musica.setId(UUID.randomUUID());
		musicaRepository.save(musica);
		return musica.getId();
	}

}
