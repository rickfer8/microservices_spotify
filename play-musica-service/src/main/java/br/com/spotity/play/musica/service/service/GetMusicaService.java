package br.com.spotity.play.musica.service.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.spotity.play.musica.service.domain.PlayList;
import br.com.spotity.play.musica.service.json.PlayListJson;
import br.com.spotity.play.musica.service.repository.PlayListRepository;

@Service
public class GetMusicaService {

	@Autowired
	private PlayListRepository musicaRepository;

	@KafkaListener(topics = "${kafka.topic.request-topic}")
	@SendTo
	public String execute(String json) throws JsonProcessingException {

		json = json.replaceAll("\"\\{", "\\{");
		json = json.replaceAll("\\}\"", "\\}");
		json = json.replace("\\", "");

		ObjectMapper mapper = new ObjectMapper();
		PlayListJson musicaJson = mapper.readValue(json, PlayListJson.class);

		Optional<PlayList> music = musicaRepository.findById(UUID.fromString(musicaJson.getUuid()));
		PlayListJson musicaReturn = PlayListJson.builder()
				.path(music.get()
				.getPath())
				.size(music.get().getSize()).build();

		return mapper.writeValueAsString(musicaReturn);
	}

}
