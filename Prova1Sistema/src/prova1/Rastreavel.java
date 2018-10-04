package prova1;

public interface Rastreavel {
	// Grupo 1
	public boolean cadastrarFazenda(Fazenda f);

	public boolean cadastrarAnimal(Animal a, Fazenda f);

	// Grupo 2
	public boolean compra(int comprado, int fazendaCompra, int fazendaVenda);

	public boolean venda(int vendido, int fazendaVenda, int fazendaCompra);

	public boolean abate(int numeroAnimal, int identificadorFazenda);

	public boolean morte(int numeroAnimal, int identificadorFazenda);

	public boolean vacina(int numeroAnimal, int identificadorFazenda);

	// Grupo 3
	public int listarResumoDeAnimais(int fazenda, int tipo, boolean jovem, boolean macho);

	public double faturamentoAnual(int fazenda, int tipo); // só vendas de animais

	public double perdaAnual(int fazenda, int tipo); // só mortes de animais (não inclui abate!)
}
