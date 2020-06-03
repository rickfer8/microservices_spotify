package br.com.curso.save.music.api.gateway.http;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.curso.save.music.api.gateway.json.MusicaJson;
import br.com.curso.save.music.api.service.SaveMusicaService;

@RestController
@RequestMapping("/v1")
public class MusicaController {
	
    @Autowired
    private SaveMusicaService saveMusicaService;
	
	 @PostMapping("/")
	    public String create(@RequestBody MusicaJson musicaJson) throws ExecutionException, InterruptedException, JsonProcessingException {
	        return saveMusicaService.execute(musicaJson);
	    }

}
