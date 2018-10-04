package prova1;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

public abstract class Animal implements Cloneable {
	private int numero;
	private String nome;
	private Data dataNascimento;
	private int genero;
	private Data dataVacinacao;
	private int fazendaAssociada;
	private boolean vacinado;
	private boolean morto;
	private boolean abatido;
	private boolean vendido;
	private boolean ativo;
	private double valorCompra;
	private double valorVenda;
	private int tipo;

	public Animal(int numero, String nome, int diaNascimento, int mesNascimento, int anoNascimento, int genero,
			double vC, double vV,int tipo) {
		this.numero = numero;
		this.nome = nome;
		this.genero = genero;
		dataNascimento = new Data(diaNascimento, mesNascimento, anoNascimento);
		valorCompra = vC;
		valorVenda = vV;
		this.setTipo(tipo);
	}

	

	public boolean vacina() {
		if (ativo == true) {
			GregorianCalendar gc = new GregorianCalendar();
			int dia = gc.get(gc.DAY_OF_MONTH);
			int mes = gc.get(gc.MONTH) + 1;
			int ano = gc.get(gc.YEAR);
			dataVacinacao = new Data(dia,mes,ano);
			vacinado = true;
			return true;
		}
		return false;
	}
	
	public long calcularIdade() {
		LocalDateTime dataCadastro = LocalDateTime.of(dataNascimento.getAno(), dataNascimento.getMes(),
				dataNascimento.getDia(), 0, 0, 0);
		LocalDateTime hoje = LocalDateTime.now();
		return dataCadastro.until(hoje, ChronoUnit.MONTHS);
	}

	public abstract boolean jovem();
	
	public boolean abate() {
		if (ativo == true) {
			abatido = true;
			ativo = false;
			return true;
		}
		return false;
	}

	public boolean morte() {
		if (ativo == true) {
			morto = true;
			ativo = false;
			return true;
		}
		return false;
	}

	public boolean vender() {
		if (ativo == true) {
			vendido = true;
			ativo = false;
			return true;
		}
		return false;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract boolean podeSerComercializado();
	
	public void setFazendaAssociada(int fazendaAssociada) {
		this.fazendaAssociada = fazendaAssociada;
		ativo = true;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNascimento(int diaNascimento,int mesNascimento,int anoNascimento) {
		this.dataNascimento = new Data(diaNascimento,mesNascimento,anoNascimento);
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public void setDataVacinacao(int dia, int mes,int ano) {
		this.dataVacinacao = new Data(dia,mes,ano);
	}

	public void setVacinado(boolean vacinado) {
		this.vacinado = vacinado;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}

	public void setAbatido(boolean abatido) {
		this.abatido = abatido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public Data getDataNascimento() {
		return dataNascimento;
	}

	public int getGenero() {
		return genero;
	}

	public Data getDataVacinacao() {
		return dataVacinacao;
	}

	public int getFazendaAssociada() {
		return fazendaAssociada;
	}

	public boolean isVacinado() {
		return vacinado;
	}

	public boolean isMorto() {
		return morto;
	}

	public boolean isAbatido() {
		return abatido;
	}

	public boolean isVendido() {
		return vendido;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public double getValorVenda() {
		return valorVenda;
	}



	public int getTipo() {
		return tipo;
	}



	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
