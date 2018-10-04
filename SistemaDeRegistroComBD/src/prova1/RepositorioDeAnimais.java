package prova1;

import java.util.ArrayList;

import jdbcTest.AnimalDAO;

public class RepositorioDeAnimais {
	// private ArrayList<Animal> animais = new ArrayList<Animal>();
	private AnimalDAO animais = new AnimalDAO();
	private int idFazenda;

	public RepositorioDeAnimais(int id) {
		idFazenda = id;
	}

	public Animal buscar(int id) {
		return animais.buscar(id, idFazenda);
	}

	public boolean inserir(Animal a) {
		Animal buscado = buscar(a.getNumero());
		if (buscado == null) {
			a.setFazendaAssociada(idFazenda);
			animais.inserir(a);
			return true;
		}
		return false;
	}

	public boolean vender(int numAnimal) {
		Animal buscado = buscar(numAnimal);
		if (buscado != null) {
			if (buscado.podeSerComercializado() == true) {
				animais.atualizarBooleans("vendido", true, numAnimal, idFazenda);
				animais.atualizarBooleans("ativo", false, numAnimal, idFazenda);
				return true;
			}
		}
		return false;

	}

	public boolean morte(int numAnimal) {
		Animal buscado = buscar(numAnimal);
		if (buscado != null) {
			if (buscado.isAtivo()) {
				animais.atualizarBooleans("morto", true, numAnimal, idFazenda);
				animais.atualizarBooleans("ativo", false, numAnimal, idFazenda);
				return true;
			}
		}
		return false;
	}

	public boolean abate(int numAnimal) {
		Animal buscado = buscar(numAnimal);
		if (buscado != null) {
			if (buscado.isAtivo()) {
				animais.atualizarBooleans("abatido", true, numAnimal, idFazenda);
				animais.atualizarBooleans("ativo", false, numAnimal, idFazenda);
				return true;
			}
		}
		return false;
	}

	public int listarResumoDeAnimais(int tipo, boolean jovem, boolean macho) {
		int contador = 0;
		ArrayList<Animal> procurados = animais.listaDeAnimais(idFazenda);
		for (Animal a : procurados) {
			if (tipo == 1 && a instanceof Bovino && a.isAtivo()) {
				if (jovem == a.jovem()) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				}
			} else if (tipo == 2 && a instanceof Suino && a.isAtivo()) {
				if (jovem == a.jovem()) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				}
			} else if (tipo == 3 && a instanceof Caprino && a.isAtivo()) {
				if (jovem == a.jovem()) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				}
			} else if (tipo == 0 && a.isAtivo()) {
				if (jovem == a.jovem()) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				}
			}
		}
		return contador;
	}

	public double faturamentoAnual(int tipo) {
		double faturado = 0;
		ArrayList<Animal> procurados = animais.listaDeAnimais(idFazenda);
		for (Animal a : procurados) {
			if (tipo == 1 && a instanceof Bovino && a.isVendido()) {
				faturado += a.getValorVenda();
			} else if (tipo == 2 && a instanceof Suino && a.isVendido()) {
				faturado += a.getValorVenda();
			} else if (tipo == 3 && a instanceof Caprino && a.isVendido()) {
				faturado += a.getValorVenda();
			} else if (tipo == 0 && a.isVendido()) {
				faturado += a.getValorVenda();
			}
		}
		return faturado;
	}

	public double perdaAnual(int tipo) {
		double perdido = 0;
		ArrayList<Animal> procurados = animais.listaDeAnimais(idFazenda);
		for (Animal a : procurados) {
			if (tipo == 1 && a instanceof Bovino && a.isMorto()) {
				perdido += a.getValorVenda();
			} else if (tipo == 2 && a instanceof Suino && a.isMorto()) {
				perdido += a.getValorVenda();
			} else if (tipo == 3 && a instanceof Caprino && a.isMorto()) {
				perdido += a.getValorVenda();
			} else if (tipo == 0 && a.isMorto()) {
				perdido += a.getValorVenda();
			}
		}
		return perdido;
	}

	public boolean vacina(int numeroAnimal) {
		Animal buscado = buscar(numeroAnimal);
		if (buscado != null && buscado.isAtivo()) {
			animais.atualizarBooleans("vacinado", true, numeroAnimal, idFazenda);
			return buscado.vacina();
		}
		return false;
	}
}
