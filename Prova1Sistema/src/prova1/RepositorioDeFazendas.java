package prova1;

import java.util.ArrayList;

public class RepositorioDeFazendas {
	private ArrayList<Fazenda> fazendas = new ArrayList<Fazenda>();

	public Fazenda buscarFazenda(int numFazenda) {
		for (Fazenda f : fazendas) {
			if (f.getId() == numFazenda)
				return f;
		}
		return null;
	}

	public boolean cadastrarFazenda(Fazenda f) {
		boolean validador = validar(f);
		if (validador == true) {
			Fazenda buscada = buscarFazenda(f.getId());
			if (buscada == null) {
				fazendas.add(f);
				return true;
			}
			return false;
		}
		return false;
	}

	private boolean validar(Fazenda f) {
		if (f == null || f.getId() < 1 || f.getNome() == null || f.getNome().equals(""))
			return false;
		return true;
	}

	private boolean validar(Animal a) {
		if (a == null || a.getNumero() < 1 || a.getNome() == null || a.getNome().equals("")
				|| (a.getGenero() != 0 && a.getGenero() != 1) || (a.getDataNascimento().verficarData() == false)
				|| ((a.getDataVacinacao() != null) && (a.getDataVacinacao().verficarData() == false))
				|| a.getValorCompra() < 1 || a.getValorVenda() < 1)
			return false;
		return true;
	}

	public boolean cadastrarAnimal(Animal a, Fazenda f) {
		boolean valida = validar(a);
		if (valida == true) {
			Fazenda buscada = buscarFazenda(f.getId());
			if (buscada != null) {
				return buscada.inserir(a);
			} else {
				return false;
			}
		}
		return false;
	}

	public ArrayList<Fazenda> getFazendas() {
		return fazendas;
	}

	public boolean comprar(int comprado, int fazendaCompra, int fazendaVenda) {
		Fazenda compradora = buscarFazenda(fazendaCompra);
		Fazenda vendedora = buscarFazenda(fazendaVenda);
		if (vendedora != null && compradora != null && (compradora.getId() != vendedora.getId())) {
			Animal animalNaFazendaVendedora = vendedora.getAnimais().buscar(comprado);
			Animal animalNaFazendaCompradora = compradora.getAnimais().buscar(comprado);
			if (animalNaFazendaVendedora != null && animalNaFazendaVendedora.podeSerComercializado() == true
					&& animalNaFazendaCompradora == null) {
				try {
					Animal aux = (Animal) animalNaFazendaVendedora.clone();
					compradora.compra(aux);
					return vendedora.venda(comprado);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
					return false;
				}

			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean vender(int comprado, int fazendaVenda, int fazendaCompra) {
		Fazenda compradora = buscarFazenda(fazendaCompra);
		Fazenda vendedora = buscarFazenda(fazendaVenda);
		if (vendedora != null && compradora != null && (compradora.getId() != vendedora.getId())) {
			Animal animalNaFazendaVendedora = vendedora.getAnimais().buscar(comprado);
			Animal animalNaFazendaCompradora = compradora.getAnimais().buscar(comprado);
			if (animalNaFazendaVendedora != null && animalNaFazendaVendedora.podeSerComercializado() == true
					&& animalNaFazendaCompradora == null) {
				try {
					Animal aux = (Animal) animalNaFazendaVendedora.clone();
					compradora.compra(aux);
					return vendedora.venda(comprado);
				} catch (CloneNotSupportedException e) {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean abate(int numeroAnimal, int identificadorFazenda) {
		Fazenda buscada = buscarFazenda(identificadorFazenda);
		if (buscada != null) {
			return buscada.abate(numeroAnimal);
		} else {
			return false;
		}
	}

	public boolean morte(int numeroAnimal, int identificadorFazenda) {
		Fazenda buscada = buscarFazenda(identificadorFazenda);
		if (buscada != null) {
			return buscada.morte(numeroAnimal);
		} else {
			return false;
		}
	}

	public boolean vacina(int numeroAnimal, int identificadorFazenda) {
		Fazenda buscada = buscarFazenda(identificadorFazenda);
		if (buscada != null) {
			return buscada.vacina(numeroAnimal);
		} else {
			return false;
		}
	}

	public int listarResumoDeAnimais(int fazenda, int tipo, boolean jovem, boolean macho) {
		Fazenda buscada = buscarFazenda(fazenda);
		if (buscada != null) {
			return buscada.listarResumoDeAnimais(tipo, jovem, macho);
		} else {
			return 0;
		}
	}

	public double faturamentoAnual(int fazenda, int tipo) {
		Fazenda buscada = buscarFazenda(fazenda);
		if (buscada != null) {
			return buscada.faturamentoAnual(tipo);
		} else {
			return 0;
		}
	}

	public double perdaAnual(int fazenda, int tipo) {
		Fazenda buscada = buscarFazenda(fazenda);
		if (buscada != null) {
			return buscada.perdaAnual(tipo);
		}
		return 0;
	}
}
