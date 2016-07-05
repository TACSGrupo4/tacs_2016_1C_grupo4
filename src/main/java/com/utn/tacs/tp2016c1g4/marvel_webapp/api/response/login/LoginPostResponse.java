package com.utn.tacs.tp2016c1g4.marvel_webapp.api.response.login;

import com.utn.tacs.tp2016c1g4.marvel_webapp.api.response.OperationStatus;

public class LoginPostResponse {

	private OperationStatus status;
	private Long idUsuario;
	private String token;
	private boolean admin;

	public OperationStatus getStatus() {
		return status;
	}

	public void setStatus(OperationStatus status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean status) {
		this.admin = status;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
