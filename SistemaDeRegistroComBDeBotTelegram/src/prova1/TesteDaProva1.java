package prova1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteDaProva1 {

	@Test
	void testeCadastrarFazenda() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");

		// Cadastrando
		assertEquals(true, sistema.cadastrarFazenda(f1));
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
	void testeCadastrarFazendaComNomeInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "");
		Fazenda f2 = new Fazenda(2, null);

		// Tenta cadastrar fazenda com nome vazio
		assertEquals(false, sistema.cadastrarFazenda(f1));
		// Tenta cadastrar fazenda com nome nulo
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
		assertEquals(3, sistema.listarResumoDeAnimais(1, 0, true, true));
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
	void testeCadastrarAnimalEmFazendaNaoExistente() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);

		// Testando cadastrar animal em fazenda nao cadastrada
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimalComNumeroInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(0, "Zeze", 10, 5, 2018, 0, 500, 250);
		Animal bovino2 = new Bovino(-1, "Tata", 10, 5, 2018, 1, 500, 250);

		// Testando cadastro de animal com numero igual a zero
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1));
		// Testando cadastro de animal com numero negativo
		assertEquals(false, sistema.cadastrarAnimal(bovino2, f1));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimalComNomeInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "", 10, 5, 2018, 0, 500, 250);
		Animal bovino2 = new Bovino(2, null, 10, 5, 2018, 1, 500, 250);

		// Testando cadastro de animal com nome vazio
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1));
		// Testando cadastro de animal com nome nulo
		assertEquals(false, sistema.cadastrarAnimal(bovino2, f1));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimalComDataDeNascimentoInvalida() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", -1, 1, 2018, 0, 500, 250);
		Animal bovino2 = new Bovino(2, "Tata", 12, -1, 2018, 1, 500, 250);
		Animal caprino1 = new Caprino(3, "Barnabe", 12, 5, -1, 0, 400, 200);
		Animal caprino2 = new Caprino(4, "Sofia", 12, 13, 2017, 1, 400, 200);
		Animal suino1 = new Suino(5, "Quasimodo", 12, 5, 2019, 0, 800, 300);

		// Testa cadastro de animal com dia de nascimento invalido
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1));
		// Testa cadastro de animal com mes de nascimento invalido
		assertEquals(false, sistema.cadastrarAnimal(bovino2, f1));
		// Testa cadastro de animal com ano de nascimento invalido
		assertEquals(false, sistema.cadastrarAnimal(caprino1, f1));
		// Testa cadastro de animal com mes de nascimento invalido
		assertEquals(false, sistema.cadastrarAnimal(caprino2, f1));
		// Testa cadastro de animal com data de nascimento maior que a atual
		assertEquals(false, sistema.cadastrarAnimal(suino1, f1));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimalComGeneroInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, -1, 500, 250);
		Animal bovino2 = new Bovino(2, "Tata", 15, 5, 2018, 2, 500, 250);

		// Testa cadastro de animais com valores de genero fora do intervalo
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1)); // Negativo
		assertEquals(false, sistema.cadastrarAnimal(bovino2, f1)); // Acima de 1
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimalComValorDeCompraInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 0, 250);
		Animal bovino2 = new Bovino(2, "Tata", 15, 5, 2018, 1, -1, 250);

		// Testa cadastro de animal com valor de compra zero
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1));
		// Testa cadastro de animal com valor de compra negativo
		assertEquals(false, sistema.cadastrarAnimal(bovino2, f1));
		sistema.limparBanco();
	}

	@Test
	void testeCadastrarAnimalComValorDeVendaInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 0);
		Animal bovino2 = new Bovino(2, "Tata", 15, 5, 2018, 1, 500, -1);

		// Testa cadastro de animal com valor de venda zero
		assertEquals(false, sistema.cadastrarAnimal(bovino1, f1));
		// Testa cadastro de animal com valor de venda negativo
		assertEquals(false, sistema.cadastrarAnimal(bovino2, f1));
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

		// Verificando a compra com listagem de animais
		assertEquals(1, sistema.listarResumoDeAnimais(1, 0, true, true)); // Fazenda 1 vendeu dois e comprou um
		assertEquals(2, sistema.listarResumoDeAnimais(2, 0, true, true)); // Fazenda 2 comprou dois e vendeu um
		sistema.limparBanco();
	}

	@Test
	void testeCompraComFazendasNaoCadastradas() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarAnimal(bovino1, f1);

		// Teste fazenda de compra nao cadastrada
		assertEquals(false, sistema.compra(1, 2, 1));
		// Teste fazenda de venda nao cadastrada
		assertEquals(false, sistema.compra(1, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeCompraComAnimalNaoCadastrado() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);

		// Teste com animal nao cadastrado
		assertEquals(false, sistema.compra(1, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeCompraComAnimalJaVendido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Realizando a compra
		sistema.compra(1, 2, 1);

		// Testando comprar animal ja vendido
		assertEquals(false, sistema.compra(1, 2, 1));
		sistema.limparBanco();
	}

	@Test
	void testeCompraComAnimalAbatido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Abate do animal
		sistema.abate(1, 1);

		// Testando a compra com animal abatido
		assertEquals(false, sistema.compra(1, 2, 1));
		sistema.limparBanco();
	}

	@Test
	void testeCompraComAnimalMorto() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Registra morte do animal
		sistema.morte(1, 1);

		// Testando a compra com animal morto
		assertEquals(false, sistema.compra(1, 2, 1));
		sistema.limparBanco();
	}

	@Test
	void testeCompraDeAnimaisAdultosNaoVacinados() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 12, 9, 2017, 0, 800, 300);
		Animal caprino1 = new Caprino(3, "Sofia", 12, 3, 2017, 1, 400, 200);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(caprino1, f1);

		// Testando compra dos animais adultos nao vacinados
		assertEquals(false, sistema.compra(1, 2, 1));
		assertEquals(false, sistema.compra(2, 2, 1));
		assertEquals(false, sistema.compra(3, 2, 1));
		sistema.limparBanco();
	}

	@Test
	void testeCompraDeAnimaisAdultosVacinados() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 12, 9, 2017, 0, 800, 300);
		Animal caprino1 = new Caprino(3, "Sofia", 12, 3, 2017, 1, 400, 200);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(caprino1, f1);

		// Registro de vacina dos animais
		sistema.vacina(1, 1);
		sistema.vacina(2, 1);
		sistema.vacina(3, 1);

		// Testando compra dos animais adultos nao vacinados
		assertEquals(true, sistema.compra(1, 2, 1));
		assertEquals(true, sistema.compra(2, 2, 1));
		assertEquals(true, sistema.compra(3, 2, 1));
		sistema.limparBanco();
	}

	@Test
	void testeCompraDeAnimalComNumeroJaExistenteNaCompradora() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal suino1 = new Suino(1, "Quasimodo", 12, 9, 2017, 0, 800, 300);

		// Cadastrando os animais
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f2);

		// Testando comprar o animal com numero ja existente
		assertEquals(false, sistema.compra(1, 1, 2));
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
	void testeVendaComFazendasNaoCadastradas() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);

		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Testando vendas com fazenda de compra nao cadastrada
		assertEquals(false, sistema.venda(1, 1, 2));
		// Testando vendas com fazenda de venda nao cadastrada
		assertEquals(false, sistema.venda(2, 2, 1));
		sistema.limparBanco();
	}

	@Test
	void testeVendaComAnimalNaoCadastrado() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);

		// Testando venda com animal nao cadastrado
		assertEquals(false, sistema.venda(1, 1, 2));
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
	void testeVendaComAnimalAbatido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Abate do animal
		sistema.abate(1, 1);

		// Testando vender animal abatido
		assertEquals(false, sistema.venda(1, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeVendaComAnimalMorto() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Registra morte do animal
		sistema.morte(1, 1);

		// Testando vender animal morto
		assertEquals(false, sistema.venda(1, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeVendaDeAnimaisAdultosNaoVacinados() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 12, 9, 2017, 0, 800, 300);
		Animal caprino1 = new Caprino(3, "Sofia", 12, 3, 2017, 1, 400, 200);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(caprino1, f1);

		// Testando venda dos animais adultos nao vacinados
		assertEquals(false, sistema.venda(1, 1, 2));
		assertEquals(false, sistema.venda(2, 1, 2));
		assertEquals(false, sistema.venda(3, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeVendaDeAnimaisAdultosVacinados() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal suino1 = new Suino(2, "Quasimodo", 12, 9, 2017, 0, 800, 300);
		Animal caprino1 = new Caprino(3, "Sofia", 12, 3, 2017, 1, 400, 200);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(caprino1, f1);

		// Registro de vacina dos animais
		sistema.vacina(1, 1);
		sistema.vacina(2, 1);
		sistema.vacina(3, 1);

		// Testando venda dos animais adultos vacinados
		assertEquals(true, sistema.venda(1, 1, 2));
		assertEquals(true, sistema.venda(2, 1, 2));
		assertEquals(true, sistema.venda(3, 1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeVendaDeAnimalComNumeroJaExistenteNaCompradora() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal suino1 = new Suino(1, "Quasimodo", 12, 9, 2017, 0, 800, 300);

		// Cadastrando os animais
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f2);

		// Testando vender animal com numero ja existente
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
	void testeAbateEmFazendaNaoCadastrada() {
		Rastreavel sistema = new SistemaDeRegistro();

		// Teste de abate em fazenda nao cadstrada
		assertEquals(false, sistema.abate(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeAbateDeAnimalNaoCadastrado() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);

		// Teste abate de animal nao cadastrado
		assertEquals(false, sistema.abate(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeAbateDeAnimalVendido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Realizando a venda
		sistema.venda(1, 1, 2);

		// Testando abate com animal vendido
		assertEquals(false, sistema.abate(1, 1));
		assertEquals(true, sistema.abate(1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeAbateDeAnimalJaAbatido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Abate do animal
		sistema.abate(1, 1);

		// Testar abate do animal ja abatido
		assertEquals(false, sistema.abate(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeAbateDeAnimalMorto() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Morte do animal
		sistema.morte(1, 1);

		// Testar abate do animal morto
		assertEquals(false, sistema.abate(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeMorteDeAnimais() {
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

		// Testando morte dos animais
		assertEquals(true, sistema.morte(1, 1));
		assertEquals(true, sistema.morte(2, 1));
		assertEquals(true, sistema.morte(3, 1));

		// Verificando com listar animais
		assertEquals(1, sistema.listarResumoDeAnimais(1, 0, true, true));
		sistema.limparBanco();
	}

	@Test
	void testeMorteEmFazendaNaoCadastrada() {
		Rastreavel sistema = new SistemaDeRegistro();

		// Testando morte em fazenda nao cadastrada
		assertEquals(false, sistema.morte(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeMorteDeAnimalNaoCadastrado() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);

		// Teste morte de animal nao cadastrado
		assertEquals(false, sistema.morte(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeMorteDeAnimalVendido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Realizando a venda
		sistema.venda(1, 1, 2);

		// Testando morte com animal vendido
		assertEquals(false, sistema.morte(1, 1));
		assertEquals(true, sistema.morte(1, 2));
		sistema.limparBanco();
	}

	@Test
	void testeMorteDeAnimalAbatido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Abate do animal
		sistema.abate(1, 1);

		// Testar morte de animal abatido
		assertEquals(false, sistema.morte(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeMorteDeAnimalJaMorto() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		sistema.cadastrarAnimal(bovino1, f1);

		// Morte do animal
		sistema.morte(1, 1);

		// Testar morte do animal ja morto
		assertEquals(false, sistema.morte(1, 1));
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
	void testeVacinacaoEmFazendaNaoCadastrada() {
		Rastreavel sistema = new SistemaDeRegistro();

		// Testando vacinacao em fazenda nao cadastrada
		assertEquals(false, sistema.vacina(1, 1));
		sistema.limparBanco();
	}

	@Test
	void testeVacinacaoDeAnimalNaoCadastrado() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);

		// Testando vacinacao de animal nao cadastrado
		assertEquals(false, sistema.vacina(1, 1));
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
	void testeListarAnimaisEmFazendaNaoCadastrada() {
		Rastreavel sistema = new SistemaDeRegistro();

		// Testando listar animais em fazenda nao cadastrada
		assertEquals(0, sistema.listarResumoDeAnimais(1, 0, true, true));
		sistema.limparBanco();
	}

	@Test
	void testeListarAnimaisComTipoInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);
		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal bovino2 = new Bovino(2, "Tata", 15, 9, 2016, 1, 500, 250);
		Animal bovino3 = new Bovino(3, "Zeze", 15, 3, 2018, 0, 500, 250);
		Animal suino1 = new Suino(4, "Quasimodo", 12, 9, 2017, 0, 800, 300);
		Animal suino2 = new Suino(5, "Che", 12, 5, 2018, 1, 800, 300);
		Animal suino3 = new Suino(6, "Santana", 12, 9, 2017, 1, 800, 300);
		Animal caprino1 = new Caprino(7, "Sofia", 12, 3, 2017, 1, 400, 200);
		Animal caprino2 = new Caprino(8, "Barnabe", 12, 3, 2018, 0, 400, 200);
		Animal caprino3 = new Caprino(9, "Tobias", 12, 3, 2018, 0, 400, 200);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(bovino2, f1);
		sistema.cadastrarAnimal(bovino3, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(suino2, f1);
		sistema.cadastrarAnimal(suino3, f1);
		sistema.cadastrarAnimal(caprino1, f1);
		sistema.cadastrarAnimal(caprino2, f1);
		sistema.cadastrarAnimal(caprino3, f1);

		// Testando listar animais com tipo invalido
		assertEquals(0, sistema.listarResumoDeAnimais(1, -1, true, true));
		assertEquals(0, sistema.listarResumoDeAnimais(1, 4, true, true));
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
	void testeFaturamentoEmFazendaNaoCadastrada() {
		Rastreavel sistema = new SistemaDeRegistro();

		// Testando faturamento de fazenda nao cadastrada
		assertEquals(0, sistema.faturamentoAnual(1, 0));
		sistema.limparBanco();
	}

	@Test
	void testeFaturamentoComTipoInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Fazenda f2 = new Fazenda(2, "Estrela do Sul");
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarFazenda(f2);
		Animal bovino1 = new Bovino(1, "Zeze", 15, 3, 2018, 0, 600, 350);
		Animal suino1 = new Suino(2, "Quasimodo", 12, 9, 2018, 0, 800, 430);
		Animal caprino1 = new Caprino(3, "Barnabe", 12, 3, 2018, 0, 400, 210);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(caprino1, f1);

		sistema.compra(1, 2, 1);
		sistema.compra(2, 2, 1);
		sistema.compra(3, 2, 1);

		// Testando faturamento com tipo invalido
		assertEquals(0, sistema.faturamentoAnual(1, -1));
		assertEquals(0, sistema.faturamentoAnual(1, 4));
		sistema.limparBanco();
	}

	@Test
	void testePerdaAnual() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		sistema.cadastrarFazenda(f1);

		Animal bovino1 = new Bovino(1, "Zezao", 15, 9, 2016, 0, 500, 250);
		Animal bovino2 = new Bovino(2, "Tata", 15, 9, 2016, 1, 500, 290);
		Animal bovino3 = new Bovino(3, "Zeze", 15, 3, 2018, 0, 500, 220);
		Animal suino1 = new Suino(4, "Quasimodo", 12, 9, 2017, 0, 800, 400);
		Animal suino2 = new Suino(5, "Che", 12, 5, 2018, 1, 800, 350);
		Animal suino3 = new Suino(6, "Santana", 12, 9, 2017, 1, 800, 300);
		Animal caprino1 = new Caprino(7, "Sofia", 12, 3, 2017, 1, 400, 210);
		Animal caprino2 = new Caprino(8, "Barnabe", 12, 3, 2018, 0, 400, 180);
		Animal caprino3 = new Caprino(9, "Tobias", 12, 3, 2018, 0, 400, 200);
		sistema.cadastrarAnimal(bovino1, f1);
		sistema.cadastrarAnimal(bovino2, f1);
		sistema.cadastrarAnimal(bovino3, f1);
		sistema.cadastrarAnimal(suino1, f1);
		sistema.cadastrarAnimal(suino2, f1);
		sistema.cadastrarAnimal(suino3, f1);
		sistema.cadastrarAnimal(caprino1, f1);
		sistema.cadastrarAnimal(caprino2, f1);
		sistema.cadastrarAnimal(caprino3, f1);

		// Registrando as mortes
		sistema.morte(1, 1);
		sistema.morte(2, 1);
		sistema.morte(3, 1);
		sistema.morte(4, 1);
		sistema.morte(5, 1);
		sistema.morte(6, 1);
		sistema.morte(7, 1);
		sistema.morte(8, 1);
		sistema.morte(9, 1);

		// Testando perdas
		assertEquals(2400, sistema.perdaAnual(1, 0), 0.01); // Todos
		assertEquals(760, sistema.perdaAnual(1, 1), 0.01); // Bovinos
		assertEquals(1050, sistema.perdaAnual(1, 2), 0.01); // Suinos
		assertEquals(590, sistema.perdaAnual(1, 3), 0.01); // Caprinos
		sistema.limparBanco();
	}

	@Test
	void testePerdaAnualEmFazendaNaoCadastrada() {
		Rastreavel sistema = new SistemaDeRegistro();

		// Testando perda anual de fazenda nao cadastrada
		assertEquals(0, sistema.perdaAnual(1, 0));
		sistema.limparBanco();
	}

	@Test
	void testePerdaAnualComTipoInvalido() {
		Rastreavel sistema = new SistemaDeRegistro();
		Fazenda f1 = new Fazenda(1, "Estrela do Norte");
		Animal bovino1 = new Bovino(1, "Zeze", 12, 1, 2017, 0, 1000, 500);
		sistema.cadastrarFazenda(f1);
		sistema.cadastrarAnimal(bovino1, f1);

		sistema.morte(1, 1);

		// Testando perda anual com tipo invalido
		assertEquals(0, sistema.perdaAnual(1, -1));
		assertEquals(0, sistema.perdaAnual(1, 4));
		sistema.limparBanco();
	}
}