package br.com.spotity.playlist.add.api.service;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.spotity.playlist.add.api.json.PlayListJson;

@Service
public class SavePlayListService {

	@Autowired
	ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

	@Value("${kafka.topic.request-topic}")
	String requestTopic;

	@Value("${kafka.topic.requestreply-topic}")
	String requestReplyTopic;

	public String execute(PlayListJson playListJson) throws ExecutionException, InterruptedException, JsonProcessingException {

		// convertendo obj para string
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(playListJson);

		// montando o producer que ira ser enviado para o kafka
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(requestTopic, jsonString);
		record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

		// enviando
		RequestReplyFuture<String, String, String> sendAndReceive = kafkaTemplate.sendAndReceive(record);

		// recebendos o retorno
		SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();
		sendResult.getProducerRecord()
				.headers()
				.forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));
		
		ConsumerRecord<String, String> playListRecord = sendAndReceive.get();

		PlayListJson playListJsonReturn = mapper.readValue(playListRecord.value(), PlayListJson.class);

		return playListJsonReturn.getUuid();
	}

}
