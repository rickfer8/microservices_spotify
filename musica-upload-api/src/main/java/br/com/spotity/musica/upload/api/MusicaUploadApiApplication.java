package br.com.spotity.musica.upload.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableKafka
@EnableAsync
public class MusicaUploadApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicaUploadApiApplication.class, args);
	}

}
