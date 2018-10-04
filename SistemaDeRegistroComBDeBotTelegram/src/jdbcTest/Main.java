package jdbcTest;

import prova1.Animal;
import prova1.Bovino;
import prova1.Fazenda;

public class Main {

	public static void main(String[] args) {
		
		Animal bovino1 = new Bovino(1, "Zeze", 10, 5, 2018, 0, 500, 250);
		AnimalDAO animal = new AnimalDAO();
		animal.limpar();
		//System.out.println(animal.buscar(1,1));
		//animal.inserir(bovino1);
		//System.out.println("Deu bom no animal");
	}

}
