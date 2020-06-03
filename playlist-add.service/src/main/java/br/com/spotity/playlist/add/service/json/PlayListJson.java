package br.com.spotity.playlist.add.service.json;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String uuidMusica;

	private String path;

	private long size;

}
