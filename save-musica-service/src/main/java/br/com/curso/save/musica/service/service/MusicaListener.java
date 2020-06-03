package br.com.curso.save.musica.service.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.curso.save.musica.service.domain.Musica;
import br.com.curso.save.musica.service.gateway.json.MusicaJson;

@Service
public class MusicaListener {

	@Autowired
	private MusicaService musicaService;

	@KafkaListener(topics = "${kafka.topic.request-topic}")
	@SendTo
	public String listen(String json) throws InterruptedException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		MusicaJson musicaJson = mapper.readValue(json, MusicaJson.class);

		UUID uuid = musicaService.execute(Musica.builder().origem(musicaJson.getOrigem()).estilo(musicaJson.getEstilo())
				.nome(musicaJson.getNome()).build());

		musicaJson.setUuid(uuid.toString());

		return mapper.writeValueAsString(musicaJson);
	}

}
