package br.com.spotity.musica.upload.service.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayListJson {

	private String uuid;

	private String nome;

	private String uuidMusica;

	private String path;

	private long size;

}
