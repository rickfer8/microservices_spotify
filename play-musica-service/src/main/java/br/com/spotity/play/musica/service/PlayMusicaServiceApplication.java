package br.com.spotity.play.musica.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

import br.com.spotity.play.musica.service.domain.PlayList;

@SpringBootApplication
@EntityScan(basePackageClasses = PlayList.class)
@EnableAsync
@EnableKafka
public class PlayMusicaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayMusicaServiceApplication.class, args);
    }

}
