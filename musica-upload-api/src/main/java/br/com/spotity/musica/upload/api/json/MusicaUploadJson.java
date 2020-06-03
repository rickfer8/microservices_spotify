package br.com.spotity.musica.upload.api.json;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicaUploadJson {

	private String uuid;
	private String uuidMusica;
	private MultipartFile file;

}
