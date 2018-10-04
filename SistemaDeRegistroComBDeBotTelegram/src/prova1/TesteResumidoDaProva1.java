package prova1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteResumidoDaProva1 {

	@Test
	void testeCadastrarFazenda() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, null);
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");

		// Cadastrando
		assertEquals(false, sistema.cadastrarFazenda(f1));
		assertEquals(true, sistema.cadastrarFazenda(f2));

		// Verificando com listar animais
		assertEquals(0, sistema.listarResumoDeAnimais(1, 0, true, true));
		assertEquals(0, sistema.listarResumoDeAnimais(2, 0, true, true));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarFazendaComNumeroJaCadastrado() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);

		// Testando cadastro de fazenda com numero ja cadastrado
		assertEquals(false, sistema.cadastrarFazenda(f1));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarFazendaComNumeroInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(0, "Estrela do Norte");
		Fazenda f2 = new Fazenda(-1, "Estrela do Sul");

		// Tenta cadastrar fazenda com numero zero:
		assertEquals(false, sistema.cadastrarFazenda(f1));
		// Tenta cadastrar fazenda com numero negativo:
		assertEquals(false, sistema.cadastrarFazenda(f2));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimal() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		// Contrutor: (numero, nome, diaNascimento, mesNascimento, anoNascimento,
		// genero,
		// valorDeCompra, valorDeVenda)
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 10, 5, 2018, 0, 800, 300);
		Animal caprino1 = new Caprino(3, "Barnabe", 10, 5, 2018, 0, 400, 200);

		// Testando cadastro
		assertEquals(true, sistema.cadastrarAnimal(bovino1, f1));
		assertEquals(true, sistema.cadastrarAnimal(suino1, f1));
		assertEquals(true, sistema.cadastrarAnimal(caprino1, f1));

		// Verificando que foi cadastrado com listar animais
		assertEquals(0, sistema.listarResumoDeAnimais(1, 5, true, true));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimalComNumeroJaCadastrado() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		assertEquals(true, sistema.cadastrarAnimal(bovino1, f1));

		// Testando cadastro de animal com mesmo numero
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1));
		sistema.limparBanco();
	}

	@Test
	void testeCompra() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 10, 5, 2018, 0, 800, 300);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);

		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f2);
		Animal caprino1 = new Caprino(3, "Barnabe", 10, 5, 2018, 0, 400, 200);
		sistema.cadastrarAnimal(caprino1, f2);

		// Testando as compras
		assertEquals(true, sistema.compra(1, 2, 1));
		assertEquals(true, sistema.compra(2, 2, 1));
		assertEquals(true, sistema.compra(3, 1, 2));
		assertEquals(true, sistema.vacina(1, 2));
		assertEquals(false, sistema.vacina(1, 1));

		// Verificando a compra com listagem de animais
		assertEquals(1, sistema.listarResumoDeAnimais(1, 0, true, true)); // Fazenda 1 vendeu dois e comprou um
		assertEquals(2, sistema.listarResumoDeAnimais(2, 0, true, true)); // Fazenda 2 comprou dois e vendeu um
		sistema.limparBanco();
	}

	@Test
	void testeFilipe() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 300);
		Animal bovino2 = new Bovino(2, "Zeze", 15, 3, 2018, 0, 600, 350);
		Animal suino1 = new Suino(3, "Quasimodo", 12, 9, 2017, 0, 800, 430);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(bovino2, f1);
		sistema.cadastrarAnimal(suino1, f1);

		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f2);
		Animal suino2 = new Suino(4, "Che", 12, 5, 2018, 1, 700, 320);
		Animal caprino1 = new Caprino(5, "Barnabe", 12, 3, 2018, 0, 400, 210);
		Animal caprino2 = new Caprino(6, "Sofia", 12, 3, 2017, 1, 300, 150);
		sistema.cadastrarAnimal(suino2, f2);
		sistema.cadastrarAnimal(caprino1, f2);
		sistema.cadastrarAnimal(caprino2, f2);

		// Vacinando animais adultos
		sistema.vacina(1, 1);
		sistema.vacina(3, 1);
		sistema.vacina(6, 2);

		sistema.morte(1, 1);
		// Realizando as transacoes
		sistema.compra(1, 2, 1);
		sistema.compra(2, 2, 1);
		sistema.compra(3, 2, 1);
		sistema.compra(4, 1, 2);
		sistema.compra(5, 1, 2);
		sistema.compra(6, 1, 2);
		sistema.limparBanco();
	}

	@Test
	void testeVenda() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);

		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 10, 5, 2018, 0, 800, 300);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);

		Animal caprino1 = new Caprino(3, "Barnabe", 10, 5, 2018, 0, 400, 200);
		sistema.cadastrarAnimal(caprino1, f2);

		// Testando as vendas
		sistema.venda(1, 1, 2);
		sistema.venda(2, 1, 2);
		sistema.venda(3, 2, 1);

		// Verificando a compra com listagem de animais e faturamento
		assertEquals(1, sistema.listarResumoDeAnimais(1, 0, true, true)); // Fazenda 1 vendeu dois e comprou um
		assertEquals(2, sistema.listarResumoDeAnimais(2, 0, true, true)); // Fazenda 2 comprou dois e vendeu um
		sistema.limparBanco();
	}

	@Test
	void testeVendaComAnimalJaVendido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Realizando a venda
		sistema.venda(1, 1, 2);

		// Testando vender animal ja vendido
		assertEquals(false, sistema.venda(1, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeAbateDeAnimais() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 10, 5, 2018, 0, 800, 300);
		Animal caprino1 = new Caprino(3, "Barnabe", 10, 5, 2018, 0, 400, 200);
		Animal caprino2 = new Caprino(4, "Tobias", 10, 5, 2018, 0, 400, 200);
		// Cadastro dos animais
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(caprino1, f1);
		sistema.cadastrarAnimal(caprino2, f1);

		// Testando o abate
		assertEquals(true, sistema.abate(1, 1));
		assertEquals(true, sistema.abate(2, 1));
		assertEquals(true, sistema.abate(3, 1));

		// Verificando com listar animais
		assertEquals(1, sistema.listarResumoDeAnimais(1, 0, true, true));
		sistema.limparBanco();
	}

	@Test
	void testeVacina() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 12, 9, 2017, 0, 800, 300);
		Animal caprino1 = new Caprino(3, "Sofia", 12, 3, 2017, 1, 400, 200);
		Animal caprino2 = new Caprino(4, "Barnabe", 15, 5, 2018, 1, 400, 200);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(caprino1, f1);
		sistema.cadastrarAnimal(caprino2, f1);

		// Testando vacinacao de animais
		assertEquals(true, sistema.vacina(1, 1));
		assertEquals(true, sistema.vacina(2, 1));
		assertEquals(true, sistema.vacina(3, 1));
		assertEquals(true, sistema.vacina(4, 1));

		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f2);
		// Verificando com venda de animais adultos
		assertEquals(true, sistema.venda(1, 1, 2));
		assertEquals(true, sistema.venda(2, 1, 2));
		assertEquals(true, sistema.venda(3, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeListarResumoDeAnimais() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250); // Adulto/Macho
		Animal bovino2 = new Bovino(2, "Tata", 15, 9, 2016, 1, 500, 250); // Adulto/Femea
		Animal bovino3 = new Bovino(3, "Zeze", 15, 3, 2018, 0, 500, 250); // Jovem/Macho
		Animal suino1 = new Suino(4, "Quasimodo", 12, 9, 2017, 0, 800, 300); // Adulto/Macho
		Animal suino2 = new Suino(5, "Che", 12, 5, 2018, 1, 800, 300); // Jovem/Femea
		Animal suino3 = new Suino(6, "Santana", 12, 9, 2017, 1, 800, 300); // Adulto/Femea
		Animal caprino1 = new Caprino(7, "Sofia", 12, 3, 2017, 1, 400, 200); // Adulto/Femea
		Animal caprino2 = new Caprino(8, "Barnabe", 12, 3, 2018, 0, 400, 200); // Jovem/Macho
		Animal caprino3 = new Caprino(9, "Tobias", 12, 3, 2018, 0, 400, 200); // Jovem/Macho
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(bovino2, f1);
		sistema.cadastrarAnimal(bovino3, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(suino2, f1);
		sistema.cadastrarAnimal(suino3, f1);
		sistema.cadastrarAnimal(caprino1, f1);
		sistema.cadastrarAnimal(caprino2, f1);
		sistema.cadastrarAnimal(caprino3, f1);

		// Testando listagem de animais
		assertEquals(3, sistema.listarResumoDeAnimais(1, 0, false, false)); // Todos/Adultos/Femeas
		assertEquals(2, sistema.listarResumoDeAnimais(1, 0, false, true)); // Todos/Adultos/Machos
		assertEquals(1, sistema.listarResumoDeAnimais(1, 0, true, false)); // Todos/Jovens/Femeas
		assertEquals(3, sistema.listarResumoDeAnimais(1, 0, true, true)); // Todos/Jovens/Machos
		assertEquals(1, sistema.listarResumoDeAnimais(1, 1, false, false)); // Bovinos/Adultos/Femeas
		assertEquals(1, sistema.listarResumoDeAnimais(1, 1, false, true)); // Bovinos/Adultos/Machos
		assertEquals(0, sistema.listarResumoDeAnimais(1, 1, true, false)); // Bovinos/Jovens/Femeas
		assertEquals(1, sistema.listarResumoDeAnimais(1, 1, true, true)); // Bovinos/Jovens/Machos
		assertEquals(1, sistema.listarResumoDeAnimais(1, 2, false, false)); // Suinos/Adultos/Femeas
		assertEquals(1, sistema.listarResumoDeAnimais(1, 2, false, true)); // Suinos/Adultos/Machos
		assertEquals(1, sistema.listarResumoDeAnimais(1, 2, true, false)); // Suinos/Jovens/Femeas
		assertEquals(0, sistema.listarResumoDeAnimais(1, 2, true, true)); // Suinos/Jovens/Machos
		assertEquals(1, sistema.listarResumoDeAnimais(1, 3, false, false)); // Caprinos/Adultos/Femeas
		assertEquals(0, sistema.listarResumoDeAnimais(1, 3, false, true)); // Caprinos/Adultos/Machos
		assertEquals(0, sistema.listarResumoDeAnimais(1, 3, true, false)); // Caprinos/Jovens/Femeas
		assertEquals(2, sistema.listarResumoDeAnimais(1, 3, true, true)); // Caprinos/Jovens/Machos
		sistema.limparBanco();
	}

	@Test
	void testeFaturamentoAnual() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 300);
		Animal bovino2 = new Bovino(2, "Zeze", 15, 3, 2018, 0, 600, 350);
		Animal suino1 = new Suino(3, "Quasimodo", 12, 9, 2017, 0, 800, 430);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(bovino2, f1);
		sistema.cadastrarAnimal(suino1, f1);

		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f2);
		Animal suino2 = new Suino(4, "Che", 12, 5, 2018, 1, 700, 320);
		Animal caprino1 = new Caprino(5, "Barnabe", 12, 3, 2018, 0, 400, 210);
		Animal caprino2 = new Caprino(6, "Sofia", 12, 3, 2017, 1, 300, 150);
		sistema.cadastrarAnimal(suino2, f2);
		sistema.cadastrarAnimal(caprino1, f2);
		sistema.cadastrarAnimal(caprino2, f2);

		// Vacinando animais adultos
		sistema.vacina(1, 1);
		sistema.vacina(3, 1);
		sistema.vacina(6, 2);

		// Realizando as transacoes
		sistema.compra(1, 2, 1);
		sistema.compra(2, 2, 1);
		sistema.compra(3, 2, 1);
		sistema.compra(4, 1, 2);
		sistema.compra(5, 1, 2);
		sistema.compra(6, 1, 2);

		// Verificando o faturamento
		assertEquals(1080, sistema.faturamentoAnual(1, 0), 0.01); // Fazenda1/Todos
		assertEquals(650, sistema.faturamentoAnual(1, 1), 0.01); // Fazenda1/Bovinos
		assertEquals(430, sistema.faturamentoAnual(1, 2), 0.01); // Fazenda1/Suinos
		assertEquals(0, sistema.faturamentoAnual(1, 3), 0.01); // Fazenda1/Caprinos
		assertEquals(680, sistema.faturamentoAnual(2, 0), 0.01); // Fazenda2/Todos
		assertEquals(0, sistema.faturamentoAnual(2, 1), 0.01); // Fazenda2/Bovinos
		assertEquals(320, sistema.faturamentoAnual(2, 2), 0.01); // Fazenda2/Suinos
		assertEquals(360, sistema.faturamentoAnual(2, 3), 0.01); // Fazenda2/Caprinos
		sistema.limparBanco();
	}

	@Test
	void perdaAnualTest() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 300);
		Animal bovino2 = new Bovino(2, "Zeze", 15, 3, 2018, 0, 600, 350);
		Animal suino1 = new Suino(3, "Quasimodo", 12, 9, 2017, 0, 800, 430);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(bovino2, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.morte(1, 1);
		sistema.morte(2, 1);
		sistema.morte(3, 1);
		assertEquals(1080, sistema.perdaAnual(1, 0), 0.01); // Fazenda1/Todos
		assertEquals(650, sistema.perdaAnual(1, 1), 0.01); // Fazenda1/Bovinos
		assertEquals(430, sistema.perdaAnual(1, 2), 0.01); // Fazenda1/Suinos
		assertEquals(0, sistema.perdaAnual(1, 3), 0.01); // Fazenda1/Caprinos
		sistema.limparBanco();
	}
}
