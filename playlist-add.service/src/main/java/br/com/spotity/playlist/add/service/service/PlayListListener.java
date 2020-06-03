package br.com.spotity.playlist.add.service.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.spotity.playlist.add.service.domain.PlayList;
import br.com.spotity.playlist.add.service.enums.PlayListStatusEnum;
import br.com.spotity.playlist.add.service.json.PlayListJson;

@Service
public class PlayListListener {

	@Autowired
	private PlayListService playListService;

	@KafkaListener(topics = "${kafka.topic.request-topic}")
	@SendTo
	public String listen(String json) throws InterruptedException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		PlayListJson playListJson = mapper.readValue(json, PlayListJson.class);

		UUID uuid = playListService.execute(
				PlayList.builder()
				.nome(playListJson.getNome())
				.status(PlayListStatusEnum.AGUARDANDO_PATH.toString())
				.uuidMusica(playListJson.getUuidMusica())
				.build());

		playListJson.setUuid(uuid.toString());

		return mapper.writeValueAsString(playListJson);

	}

}
