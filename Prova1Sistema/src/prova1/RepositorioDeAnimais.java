package prova1;

import java.util.ArrayList;

public class RepositorioDeAnimais {
	private ArrayList<Animal> animais = new ArrayList<Animal>();

	public ArrayList<Animal> getAnimais() {
		return animais;
	}

	public Animal buscar(int numero) {
		for (Animal a : animais) {
			if (a.getNumero() == numero) {
				return a;
			}
		}
		return null;
	}

	public boolean inserir(Animal a, Fazenda f) {
		Animal buscado = buscar(a.getNumero());
		if (buscado == null) {
			a.setFazendaAssociada(f);
			animais.add(a);
			return true;
		}
		return false;
	}

	public boolean vender(int numAnimal) {
		Animal buscado = buscar(numAnimal);
		if (buscado != null) {
			if (buscado.podeSerComercializado() == true) {
				return buscado.vender();
			}
		}
		return false;

	}

	public boolean morte(int numAnimal) {
		Animal buscado = buscar(numAnimal);
		if (buscado != null) {
			return buscado.morte();
		}
		return false;
	}

	public boolean abate(int numAnimal) {
		Animal buscado = buscar(numAnimal);
		if (buscado != null) {
			return buscado.abate();
		}
		return false;
	}

	public int listarResumoDeAnimais(int tipo, boolean jovem, boolean macho) {
		int contador = 0;
		for (Animal a : animais) {
			if (tipo == 1 && a instanceof Bovino && a.isAtivo()) {
				if (jovem == true && a.calcularIdade() < 23) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				} else if (jovem == false && a.calcularIdade() > 22) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				}
			} else if (tipo == 2 && a instanceof Suino && a.isAtivo()) {
				if (jovem == true && a.calcularIdade() < 12) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				} else if (jovem == false && a.calcularIdade() > 11) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				}
			} else if (tipo == 3 && a instanceof Caprino && a.isAtivo()) {
				if (jovem == true && a.calcularIdade() < 18) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				} else if (jovem == false && a.calcularIdade() > 17) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				}
			} else if (tipo == 0 && a.isAtivo()) {
				if (jovem == true && ((a.calcularIdade() < 23 && a instanceof Bovino)
						|| (a.calcularIdade() < 18 && a instanceof Caprino)
						|| (a.calcularIdade() < 12 && a instanceof Suino))) {
					if (macho == true && a.getGenero() == 0) {
						contador++;
					} else if (macho == false && a.getGenero() == 1) {
						contador++;
					}
				} else if (jovem == false && ((a.calcularIdade() >= 23 && a instanceof Bovino)
						|| (a.calcularIdade() >= 18 && a instanceof Caprino)
						|| (a.calcularIdade() >= 12 && a instanceof Suino))) {
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
		for (Animal a : animais) {
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
		for (Animal a : animais) {
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
		if (buscado != null) {
			return buscado.vacina();
		}
		return false;
	}
}
