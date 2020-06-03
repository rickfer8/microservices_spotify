package br.com.spotity.musica.upload.api.service;

import java.io.IOException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.spotity.musica.upload.api.json.MusicaUploadJson;

@Service
public class UploadMusicaService {

	@Autowired
	private KafkaTemplate kafkaTemplate;

	@Value("${kafka.topic.request-topic}")
	String requestTopic;

	@Value("${kafka.header.uuid-playlist")
	String headerPlaylist;

	@Value("${kafka.header.uuid-musica")
	String headerMusica;

	public void execute(MusicaUploadJson musicaUploadJson) throws IOException {

		Bytes bytes = new org.apache.kafka.common.utils.Bytes(musicaUploadJson.getFile().getBytes());

		ProducerRecord<Bytes, Bytes> producerRecord = new ProducerRecord(requestTopic, bytes, bytes);
		producerRecord.headers().add(new RecordHeader(headerPlaylist, musicaUploadJson.getUuid().getBytes()));
		producerRecord.headers().add(new RecordHeader(headerMusica, musicaUploadJson.getUuidMusica().getBytes()));

		kafkaTemplate.send(producerRecord);
	}

}
