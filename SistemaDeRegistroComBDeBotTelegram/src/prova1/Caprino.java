package prova1;

public class Caprino extends Animal {
	public Caprino(int numero, String nome, int diaNascimento, int mesNascimento, int anoNascimento, int genero,
			double vC, double vV) {
		super(numero, nome, diaNascimento, mesNascimento, anoNascimento, genero, vC, vV,3);
	}

	@Override
	public boolean podeSerComercializado() {
		if (isAtivo() && ((isVacinado() && (calcularIdade() > 17)) || (calcularIdade() <= 17)))
			return true;
		return false;
	}

	@Override
	public boolean jovem() {
		if(calcularIdade() < 18)
			return true;
		return false;
	}
	
}
