package br.com.curso.save.music.api.service;

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

import br.com.curso.save.music.api.gateway.json.MusicaJson;

@Service
public class SaveMusicaService {
	
	 @Autowired
	    ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

	    @Value("${kafka.topic.request-topic}")
	    String requestTopic;

	    @Value("${kafka.topic.requestreply-topic}")
	    String requestReplyTopic;
	    
	    public String execute(MusicaJson musicaJson) throws ExecutionException, InterruptedException, JsonProcessingException {
	    	
	    	 // convertendo objeto para string
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonString = mapper.writeValueAsString(musicaJson);
	        
	        // montando o producer que ira ser enviado para o kafka
	        ProducerRecord<String, String> record = new ProducerRecord<String, String>(requestTopic, jsonString);
	        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));
	        
	        // enviando
	        RequestReplyFuture<String, String, String> sendAndReceive = kafkaTemplate.sendAndReceive(record);
	        
	        // recebendo o retorno
	        SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();
	        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

	        ConsumerRecord<String, String> consumerRecord = sendAndReceive.get();
	        
	        MusicaJson musicaJsonRetorno = mapper.readValue(consumerRecord.value(), MusicaJson.class);

	        return musicaJsonRetorno.getUuid();
	    }

}
