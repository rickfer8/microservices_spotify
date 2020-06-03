package br.com.curso.save.musica.service.gateway.json;

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
public class MusicaJson {
	
	 private String uuid;

	    @NotNull
	    @NotEmpty
	    private String nome;

	    @NotNull
	    @NotEmpty
	    private String origem;

	    @NotNull
	    @NotEmpty
	    private String estilo;

}
