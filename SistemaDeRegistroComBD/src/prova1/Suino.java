package prova1;

public class Suino extends Animal {
	public Suino(int numero, String nome, int diaNascimento, int mesNascimento, int anoNascimento, int genero,
			double vC, double vV) {
		super(numero, nome, diaNascimento, mesNascimento, anoNascimento, genero, vC, vV,2);
	}

	@Override
	public boolean podeSerComercializado() {
		if (isAtivo() && ((isVacinado() && (calcularIdade() > 11)) || (calcularIdade() <= 11)))
			return true;
		return false;
	}
	@Override
	public boolean jovem() {
		if(calcularIdade() < 12)
			return true;
		return false;
	}
	
}
