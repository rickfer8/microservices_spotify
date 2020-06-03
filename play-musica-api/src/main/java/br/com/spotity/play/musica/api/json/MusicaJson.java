package br.com.spotity.play.musica.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicaJson {

	private String uuid;
	private String path;
	private long size;

}
