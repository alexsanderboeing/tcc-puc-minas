package com.juntamedica.anexo;

import com.juntamedica.anexo.converter.AnexoMimeTypeConverter;
import com.juntamedica.anexo.factory.AnexoMimeTypeEnum;
import com.juntamedica.classificacaoanexo.ClassificacaoAnexo;
import com.juntamedica.utils.DateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Anexo extends DateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anexo")
	@SequenceGenerator(name = "anexo", sequenceName = "s_anexo", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private ClassificacaoAnexo classificacaoAnexo;

	@Convert(converter = AnexoMimeTypeConverter.class)
	private AnexoMimeTypeEnum mimeType;

	private String uuid;

	@Size(max = 100, message = "O nome do arquivo pode conter no máximo 100 caracteres")
	private String nome;
	private Long usuarioId;
	private String extensao;
}
