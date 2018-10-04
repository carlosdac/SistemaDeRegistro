package prova1;

import jdbcTest.AnimalDAO;
import jdbcTest.FazendaDAO;

public class SistemaDeRegistro implements Rastreavel {
	private RepositorioDeFazendas fazendas = new RepositorioDeFazendas();

	public RepositorioDeFazendas getFazendas() {
		return fazendas;
	}

	@Override
	public boolean cadastrarFazenda(Fazenda f) {
		return fazendas.cadastrarFazenda(f);
	}

	@Override
	public boolean cadastrarAnimal(Animal a, Fazenda f) {
		return fazendas.cadastrarAnimal(a, f);
	}

	@Override
	public boolean compra(int comprado, int fazendaCompra, int fazendaVenda) {
		return fazendas.comprar(comprado, fazendaCompra, fazendaVenda);
	}

	@Override
	public boolean venda(int vendido, int fazendaVenda, int fazendaCompra) {
		return fazendas.vender(vendido, fazendaVenda, fazendaCompra);
	}

	@Override
	public boolean abate(int numeroAnimal, int identificadorFazenda) {
		return fazendas.abate(numeroAnimal, identificadorFazenda);
	}

	@Override
	public boolean morte(int numeroAnimal, int identificadorFazenda) {
		return fazendas.morte(numeroAnimal, identificadorFazenda);
	}

	@Override
	public boolean vacina(int numeroAnimal, int identificadorFazenda) {
		return fazendas.vacina(numeroAnimal, identificadorFazenda);
	}

	@Override
	public int listarResumoDeAnimais(int fazenda, int tipo, boolean jovem, boolean macho) {
		return fazendas.listarResumoDeAnimais(fazenda, tipo, jovem, macho);
	}

	@Override
	public double faturamentoAnual(int fazenda, int tipo) {
		return fazendas.faturamentoAnual(fazenda, tipo);
	}

	@Override
	public double perdaAnual(int fazenda, int tipo) {
		return fazendas.perdaAnual(fazenda, tipo);
	}

	@Override
	public void limparBanco() {
		FazendaDAO f = new FazendaDAO();
		AnimalDAO a = new AnimalDAO();
		f.limpar();
		a.limpar();
	}

}
