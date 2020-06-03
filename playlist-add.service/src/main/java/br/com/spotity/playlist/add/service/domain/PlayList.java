package br.com.spotity.playlist.add.service.domain;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayList {

	@PrimaryKey
	private UUID id;

	@NotNull
	@NotEmpty
	private String nome;

	private String path;

	@NotNull
	@NotEmpty
	private String status;

	@NotNull
	@NotEmpty
	@Column(value = "uuid_musica")
	private String uuidMusica;

	private long size;

}
