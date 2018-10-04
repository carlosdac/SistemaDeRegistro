package prova1;

public class Fazenda {
	private int id;
	private String nome;
	private RepositorioDeAnimais animais = new RepositorioDeAnimais();

	public Fazenda(int i, String n) {
		id = i;
		nome = n;
	}

	public boolean venda(int numAnimal) {
		return animais.vender(numAnimal);
	}

	public boolean morte(int numAnimal) {
		return animais.morte(numAnimal);
	}

	public int listarResumoDeAnimais(int tipo, boolean jovem, boolean macho) {
		return animais.listarResumoDeAnimais(tipo, jovem, macho);
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public RepositorioDeAnimais getAnimais() {
		return animais;
	}

	public boolean compra(Animal animal) {
		return animais.inserir(animal, this);
	}

	public double faturamentoAnual(int tipo) {
		return animais.faturamentoAnual(tipo);
	}

	public double perdaAnual(int tipo) {
		return animais.perdaAnual(tipo);
	}

	public boolean vacina(int numeroAnimal) {
		return animais.vacina(numeroAnimal);
	}

	public boolean abate(int numeroAnimal) {
		return animais.abate(numeroAnimal);
	}

	public boolean inserir(Animal a) {
		return animais.inserir(a, this);
	}
}
