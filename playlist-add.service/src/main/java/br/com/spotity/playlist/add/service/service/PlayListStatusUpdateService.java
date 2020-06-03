package br.com.spotity.playlist.add.service.service;

import java.util.Optional;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.spotity.playlist.add.service.domain.PlayList;
import br.com.spotity.playlist.add.service.enums.PlayListStatusEnum;
import br.com.spotity.playlist.add.service.json.PlayListJson;
import br.com.spotity.playlist.add.service.repository.PlayListRepository;

@Service
public class PlayListStatusUpdateService {

	@Autowired
	private PlayListRepository playListRepository;

	@KafkaListener(topics = "${kafka.topic.music-status}", groupId = "${kafka.consumergroup}")
	public void execute(Object playListUpdate) throws JsonProcessingException {

		ConsumerRecord playListConsumer = (ConsumerRecord) playListUpdate;

		String json = (String) playListConsumer.value();

		ObjectMapper mapper = new ObjectMapper();

		PlayListJson playListUpdateStatusJson = mapper.readValue(json, PlayListJson.class);

		Optional<PlayList> playList = playListRepository.findById(UUID.fromString(playListUpdateStatusJson.getUuid()));

		playList.get().setStatus(PlayListStatusEnum.PATH_SALVO.toString());
		playList.get().setPath(playListUpdateStatusJson.getPath());
		playList.get().setSize(playListUpdateStatusJson.getSize());

		playListRepository.save(playList.get());

	}

}
