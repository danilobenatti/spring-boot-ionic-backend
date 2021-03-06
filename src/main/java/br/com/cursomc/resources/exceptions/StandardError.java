package br.com.cursomc.resources.exceptions;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;

	private String message;

	private LocalDate timeStamp;

}
