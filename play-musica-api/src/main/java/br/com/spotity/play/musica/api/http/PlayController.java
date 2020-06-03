package br.com.spotity.play.musica.api.http;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import br.com.spotity.play.musica.api.json.MusicaJson;
import br.com.spotity.play.musica.api.service.PlayMusicaService;
import br.com.spotity.play.musica.api.service.S3Service;

@RestController
@RequestMapping("/v1")
public class PlayController {

	@Autowired
	private PlayMusicaService playService;

	@Autowired
	private S3Service s3Service;

	@GetMapping("/musica/{uuid}")
	public ResponseEntity<InputStreamResource> create(@PathVariable("uuid") String uuid)
			throws ExecutionException, InterruptedException, IOException {

		MusicaJson music = playService.execute(MusicaJson.builder().uuid(uuid).build());

		S3ObjectInputStream s3ObjectInputStream = s3Service.execute(music.getPath());

		InputStreamResource resource = new InputStreamResource(s3ObjectInputStream);

		MediaType mediaType = MediaType.parseMediaType("application/octet-stream");
		long len = music.getSize();

		return ResponseEntity.ok().contentType(mediaType)
				.contentLength(len)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"music.mp3").body(resource);
	}

}
