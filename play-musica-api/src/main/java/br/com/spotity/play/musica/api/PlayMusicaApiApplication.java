package br.com.spotity.play.musica.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableKafka
@EnableAsync
public class PlayMusicaApiApplication {
	
	 public static void main(String[] args) {
	        SpringApplication.run(PlayMusicaApiApplication.class, args);
	    }


}
