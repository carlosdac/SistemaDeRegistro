package prova1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Data {
	private int dia;
	private int mes;
	private int ano;

	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public boolean verficarData() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			String date = dia + "/" + mes + "/" + ano;
			sdf.parse(date);
		} catch (ParseException e) {
			return false;
		}
		GregorianCalendar gc = new GregorianCalendar();
		GregorianCalendar data = new GregorianCalendar(ano, mes - 1, dia);
		data.setLenient(false);
		if (gc.before(data)) {
			return false;
		}
		return true;
	}
}
