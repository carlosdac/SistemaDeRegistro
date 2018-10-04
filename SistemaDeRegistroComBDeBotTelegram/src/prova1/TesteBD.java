package prova1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteBD {

	@Test
	void testInserirFazenda() {
		Rastreavel r = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1,"Zeca");
		Fazenda f2 = new Fazenda(2,"Real");
		r.cadastrarFazenda(f1);
		r.cadastrarFazenda(f2);
		r.limparBanco();
	}
	
	@Test
	void testInserirAnimal() {
		Rastreavel r = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1,"Zeca");
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		r.cadastrarFazenda(f1);
		assertEquals(0,r.listarResumoDeAnimais(1, 0, true, true));
		assertEquals(true,r.cadastrarAnimal(bovino1, f1));
		r.limparBanco();
	}
	
	@Test
	void testListar() {
		Rastreavel r = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1,"Zeca");
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		r.cadastrarFazenda(f1);
		r.cadastrarAnimal(bovino1, f1);
		assertEquals(1,r.listarResumoDeAnimais(1, 0, true, true));
		r.limparBanco();
	}
	
	@Test
	void testVacinar() {
		Rastreavel r = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1,"Zeca");
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		r.cadastrarFazenda(f1);
		r.cadastrarAnimal(bovino1, f1);
		assertEquals(true,r.vacina(1, 1));
		r.limparBanco();
	}
	
	@Test
	
	void testVender() {
		Rastreavel r = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1,"Zeca");
		Fazenda f2 = new Fazenda(2,"Real");
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		r.cadastrarFazenda(f1);
		r.cadastrarFazenda(f2);
		r.cadastrarAnimal(bovino1, f1);
		assertEquals(true,r.venda(1, 1, 2));
		assertEquals(0,r.listarResumoDeAnimais(1, 0, true, true));
		assertEquals(1,r.listarResumoDeAnimais(2, 0, true, true));
		r.limparBanco();
	}

}
