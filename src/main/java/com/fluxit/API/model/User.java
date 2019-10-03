package com.fluxit.API.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	@NotEmpty(message = "Debe ingresar un nombre de usuario")
	    private String username;
	 	@NotEmpty(message = "Debe ingresar una contrasena")
	    private String password;
		private String token;
	    private Boolean enabled;
	    @Transient
	    private String passwordConfirm;
	    @ManyToMany
	    private Set<Role> roles;

}
