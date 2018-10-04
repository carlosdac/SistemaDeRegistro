package prova1;

public class Bovino extends Animal {
	public Bovino(int numero, String nome, int diaNascimento, int mesNascimento, int anoNascimento, int genero,
			double vC, double vV) {
		super(numero, nome, diaNascimento, mesNascimento, anoNascimento, genero, vC, vV,1);
	}

	@Override
	public boolean podeSerComercializado() {
		if (isAtivo() && ((isVacinado() && (calcularIdade() > 22)) || (calcularIdade() <= 22)))
			return true;
		return false;
	}

	@Override
	public boolean jovem() {
		if(calcularIdade() < 23)
			return true;
		return false;
	}
	
	

}
