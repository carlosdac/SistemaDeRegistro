package prova1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteSpearador {

	@Test
	void testVacinar() {
		Rastreavel r = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Zeca");
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		r.cadastrarFazenda(f1);
		r.cadastrarAnimal(bovino1, f1);
		assertEquals(true, r.vacina(1, 1));
		r.limparBanco();
	}

}
