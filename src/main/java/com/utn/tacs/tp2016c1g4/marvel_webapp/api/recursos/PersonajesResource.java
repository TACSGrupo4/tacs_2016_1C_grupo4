package com.utn.tacs.tp2016c1g4.marvel_webapp.api.recursos;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.utn.tacs.tp2016c1g4.marvel_webapp.api.dao.Dao;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.dao.filter.FiltroPersonaje;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.domain.Personaje;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.response.OperationStatus;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.response.personaje.PersonajeGetResponse;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.task.PersonajeImporterTask;
import com.utn.tacs.tp2016c1g4.marvel_webapp.external.domain.PersonajeMarvel;

@Path("/personajes")
public class PersonajesResource {

	private static final Logger logger = LogManager.getLogger(PersonajesResource.class);

	private Dao<Personaje, FiltroPersonaje> personajeDao;
	private PersonajeImporterTask importerTask;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response get() {
		logger.debug("get invocado");

		if (!importerTask.isStarted()) {
			logger.debug("invocando task");
			Thread t = new Thread(importerTask);
			t.start();
		}

		Set<Personaje> personajes = personajeDao.getAll();

		Status status = Status.OK;
		OperationStatus opStatus = new OperationStatus();
		opStatus.setStatusCode(status);

		PersonajeGetResponse.Builder responseBuilder = new PersonajeGetResponse.Builder();
		responseBuilder.setPersonajes(personajes);
		responseBuilder.setOperationstatus(opStatus);

		PersonajeGetResponse response = responseBuilder.build();

		return Response.status(status).entity(response).build();
	}

	@Inject
	public void setPersonajeDao(Dao<Personaje, FiltroPersonaje> personajeDao) {
		this.personajeDao = personajeDao;
	}

	@Inject
	public void setImporterTask(PersonajeImporterTask importerTask) {
		this.importerTask = importerTask;
	}

}
