package br.com.spotity.musica.upload.service.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.spotity.musica.upload.service.json.PlayListJson;

@Service
public class SaveMusicaStatusService {

	@Autowired
	@Qualifier("templateUpdate")
	private KafkaTemplate template;

	@Value("${kafka.topic.musica-status}")
	private String topicMusicaStatus;

	public void execute(PlayListJson playList) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String playListJson = mapper.writeValueAsString(playList);

		ProducerRecord<String, String> producerRecord = new ProducerRecord(topicMusicaStatus, playListJson,	playListJson);
		template.send(producerRecord);
	}

}
