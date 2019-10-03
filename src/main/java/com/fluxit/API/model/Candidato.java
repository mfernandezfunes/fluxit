package com.fluxit.API.model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data 
@Entity
@Table(name = "candidatos")

public class Candidato {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="firstname")
	@Length (min=5,max=24)
	@NotEmpty(message = "Debe ingresar un nombre")
	private String firstName;
	
	@Column(name="lastname")
	@Length (min=5,max=24)
	@NotEmpty(message = "Debe ingresar un apellido")
	private String lastName;
	
	@Column(name="dni")
	@NotEmpty(message = "Debe ingresar un dni")
	private String dni;
	
	@Column(name="email")
	@NotEmpty(message = "Debe ingresar un email")
	private String email;
	
	@Column(name="phone")
	@NotEmpty(message = "Debe ingresar un telefono")
	private String phone;

}