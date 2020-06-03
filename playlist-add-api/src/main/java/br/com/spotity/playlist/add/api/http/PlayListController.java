package br.com.spotity.playlist.add.api.http;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.spotity.playlist.add.api.json.PlayListJson;
import br.com.spotity.playlist.add.api.service.SavePlayListService;


@RestController
@RequestMapping("/v1")
public class PlayListController {
	
    @Autowired
    private SavePlayListService savePlayListService;
	
	@PostMapping("/playlist/{uuid}/musicas")
    public String create(@PathVariable("uuid") String uuid, @RequestBody PlayListJson playListJson) throws ExecutionException, InterruptedException, JsonProcessingException {
		playListJson.setUuidMusica(uuid);
        return savePlayListService.execute(playListJson);
    }

}
