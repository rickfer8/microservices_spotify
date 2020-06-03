package br.com.spotity.musica.upload.service.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.spotity.musica.upload.service.json.PlayListJson;

@Service
public class UploadMusicaService {

	public static final String UTF_8 = "UTF-8";

	@Value("${kafka.topic.request-topic}")
	private String requestTopic;

	@Value("${kafka.header.uuid-playlist")
	private String headerPlaylist;

	@Value("${kafka.header.uuid-musica")
	private String headerMusica;

	@Autowired
	private UploadS3Service uploadS3Service;

	@Autowired
	private SaveMusicaStatusService saveMusicaStatusService;

	@KafkaListener(topics = "${kafka.topic.request-topic}", groupId = "${kafka.consumer.group-id}")
	public void listen(@Payload ConsumerRecord record, @Headers MessageHeaders messageHeaders)
			throws InterruptedException, IOException {

		byte[] uuidPlayList = (byte[]) messageHeaders.get(headerPlaylist);
		byte[] uuidMusica = (byte[]) messageHeaders.get(headerMusica);

		String uuidPlayListStr = new String(uuidPlayList, UTF_8);
		String uuidMusicaStr = new String(uuidMusica, UTF_8);

		Bytes aByte = (Bytes) record.value();

		PlayListJson playlist = uploadS3Service.execute(uuidPlayListStr, uuidMusicaStr, aByte);

		saveMusicaStatusService.execute(playlist);
	}

}
