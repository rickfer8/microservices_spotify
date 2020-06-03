package br.com.spotity.musica.upload.api.http;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.spotity.musica.upload.api.json.MusicaUploadJson;
import br.com.spotity.musica.upload.api.service.UploadMusicaService;

@RestController
@RequestMapping("/v1")
public class MusicaController {

	@Autowired
	private UploadMusicaService uploadMusicaService;

	@PostMapping("/playlist/{uuid}/musica/{uuidMusica}")
	public ResponseEntity<?> create(@PathVariable("uuid") String uuid, @PathVariable("uuidMusica") String uuidMusica, @RequestParam("file") MultipartFile file) throws IOException {
		uploadMusicaService.execute(MusicaUploadJson
				.builder()
				.uuid(uuid)
				.uuidMusica(uuidMusica)
				.file(file)
			.build());

		return ResponseEntity.ok().build();
	}

}
