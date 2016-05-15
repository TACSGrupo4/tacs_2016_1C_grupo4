package com.utn.tacs.tp2016c1g4.marvel_webapp.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.utn.tacs.tp2016c1g4.marvel_webapp.api.dao.filter.FiltroGrupo;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.dao.inmemory.GrupoInMemoryDao;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.domain.Grupo;

public class GrupoInMemoryDaoTest {

	private GrupoInMemoryDao dao;
	private FiltroGrupo.Builder filterBuilder;

	public GrupoInMemoryDaoTest() {
		dao = new GrupoInMemoryDao();
		filterBuilder = new FiltroGrupo.Builder();
	}

	@Before
	public void before() {
		dao.clear();
	}

	@Test
	public void basicTest() {
		Set<Grupo> grupos = dao.getAll();

		assertEquals(0, grupos.size());

		Grupo g1 = new Grupo();
		g1.setNombre("pepito1");

		Grupo g2 = new Grupo();
		g2.setNombre("pepito2");

		dao.save(g1);
		dao.save(g2);

		dao.save(g1);
		dao.save(g1);

		grupos = dao.getAll();
		assertEquals(2, grupos.size());
	}

	@Test
	public void checkFilters() {
		Grupo g1 = new Grupo();
		g1.setNombre("pepito1");

		Grupo g2 = new Grupo();
		g2.setNombre("pepito2");

		dao.save(g1);
		dao.save(g2);

		List<FiltroGrupo> filtros = new ArrayList<>();
		filtros.add(new FiltroGrupo(FiltroGrupo.Tipo.ID, 1L));

		Set<Grupo> grupos;

		grupos = dao.find(filtros);

		assertEquals("cantidad resultados en filtrado por id", 1, grupos.size());
		assertEquals("objeto esperado en filtrado por id", Long.valueOf(1), grupos.iterator().next().getId());

		filtros.clear();
		filtros.add(new FiltroGrupo(FiltroGrupo.Tipo.ID, 1L));
		filtros.add(new FiltroGrupo(FiltroGrupo.Tipo.NAME, "pepito2"));

		grupos = dao.find(filtros);

		assertEquals("cantidad resultados en combinacion imposible de filtros", 0, grupos.size());

	}

	@Test
	public void checkFilterBuilder() {

		popularBasico();

		filterBuilder.clear();
		filterBuilder.setId(1);

		Set<FiltroGrupo> filtros;
		Set<Grupo> grupos;

		filtros = filterBuilder.build();
		grupos = dao.find(filtros);

		assertEquals("filtro construido con builder para filtrado por id", 1, grupos.size());

		filterBuilder.clear();
		filterBuilder.setName("pepito2");

		filtros = filterBuilder.build();
		grupos = dao.find(filtros);

		assertEquals("filtro construido con builder para filtrado por id - 2", 1, grupos.size());
		assertEquals("filtro construido con builder para filtrado por nombre", "pepito2",
				grupos.iterator().next().getNombre());
	}

	private void popularBasico() {

		Grupo g1 = new Grupo();
		g1.setNombre("pepito1");

		Grupo g2 = new Grupo();
		g2.setNombre("pepito2");

		dao.save(g1);
		dao.save(g2);

	}
}