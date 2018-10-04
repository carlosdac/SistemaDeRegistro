package botTelegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Bot extends TelegramLongPollingBot {

	public static ArrayList<TelegramInfo> informacoes = new ArrayList<TelegramInfo>();
	
	@Override
	public String getBotUsername() {
		return "Bot";
	}
	
	public void sair(long idTelegram) throws SQLException {
		DAOAssociado daoAssociado = new DAOAssociado();
		long vazio = 0;
		
		//verifica se o associado já existe
		Associado associado = daoAssociado.buscar(idTelegram);
		if(associado != null) {
			daoAssociado.setIdTelegram(associado.getCodigo(), vazio);
		}
	}
	
	public String acessar(long idTelegram, String texto, TelegramInfo telegramInfo) {
		DAOAssociado daoAssociado = new DAOAssociado();
		String mensagem = "";
		
		try {
			ArrayList<Associado> associados = daoAssociado.buscar(texto);
			Associado associado;
			
			if(associados.size() == 1) {
				//caso o usuario estaja conectado em outro telegram
				sair(idTelegram);
				daoAssociado.setIdTelegram(associados.get(0).getCodigo(), idTelegram);
				
				associado = daoAssociado.buscar(idTelegram);
				if(associado.getNome().indexOf("(") != -1) {
					mensagem += "Bem-vindo, " + associado.getNome().substring(0, associado.getNome().indexOf("(")) + ". Agora você pode consultar os seus pagamentos.";
				}
				else {
					mensagem += "Bem-vindo, " + associado.getNome() + ". Agora você pode consultar os seus pagamentos.";
				}
			}
			else {
				int cont = 1;
				telegramInfo.associados = associados;
				//mensagem += "Há mais de uma pessoa chamada " + texto +". Por favor, digite o número correspondente ao seu nome.\n\n";
				mensagem += "Há mais de uma pessoa chamada " + texto +". Por favor, selecione a opção correspondente ao seu nome.";
				/*for(Associado assoc : associados) {
					mensagem += cont++ + ". " + assoc.getNome() + "\n";
				}*/
				telegramInfo.selecionar = true;
			}
			telegramInfo.login = false;
		} 
		catch(SQLException e) {
			mensagem += "Essa informação não está disponível no momento.";
			e.printStackTrace();
		}
		catch(AssociadoNaoExistente e) {
			mensagem = "O nome digitado não corresponde a nenhum associado cadastrado. Tente novamente.";
			e.printStackTrace();
		}
		
		return mensagem;
	}
	
	public String acessarSelecionar(long idTelegram, String texto, TelegramInfo telegramInfo) {
		DAOAssociado daoAssociado = new DAOAssociado();
		String mensagem = "";
		
		try {
			int index = texto.indexOf(".");
			texto = texto.substring(0, index);
			
			int num = Integer.parseInt(texto);
			
			System.out.println(num + " " + telegramInfo.associados.size());
			
			if(num > 0 && num <= telegramInfo.associados.size()) {
				//caso o usuario estaja conectado em outro telegram
				sair(idTelegram);
				daoAssociado.setIdTelegram(telegramInfo.associados.get(num - 1).getCodigo(), idTelegram);
					
				Associado associado = daoAssociado.buscar(idTelegram);
				//caso o nome do associado tenha (ex-demolay), por exemplo
				if(associado.getNome().indexOf("(") != -1) {
					mensagem += "Bem-vindo, " + associado.getNome().substring(0, associado.getNome().indexOf("(")) + ". Agora você pode consultar os seus pagamentos.";
				}
				else {
					mensagem += "Bem-vindo, " + associado.getNome() + ". Agora você pode consultar os seus pagamentos.";
				}
				telegramInfo.selecionar = false;
			}
			else {
				mensagem += "Número inválido, tente novamente.";
			}
		}
		catch(NumberFormatException e) {
			mensagem += "O código possui apenas números, tente novamente.";
			e.printStackTrace();
		} 
		catch(SQLException e) {
			mensagem += "Essa informação não está disponível no momento.";
			e.printStackTrace();
		}
		
		return mensagem;
	}
	
	public Associado buscarAssociado(long idTelegram) throws SQLException {
		DAOAssociado daoAssociado = new DAOAssociado();
		
		Associado associado = daoAssociado.buscar(idTelegram);
				
		return associado;
	}
	
	public void enviarMensagens(long idTelegram, String mensagem) {
		//Mensagem a ser enviada
		SendMessage send = new SendMessage();
		send.setChatId(idTelegram);
		send.setText(mensagem);
		
		try {
			execute(send);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pagamentos(long idTelegram) {
		DAOTaxa daoTaxa = new DAOTaxa();
		DAOPagamento daoPagamento = new DAOPagamento();
		String mensagem = "";
		int flag, aux = 0;
		double valorPago;
		
		try {
			Associado associado = buscarAssociado(idTelegram);
			
			//String nome = associado.getNome().substring(0, associado.getNome().indexOf(" ("));
			
			ArrayList<Taxa> taxas = daoTaxa.listaDeTaxas();
			ArrayList<Pagamento> pagamentos = daoPagamento.listaDePagamentos(associado.getCodigo());
			
			if(associado.getNome().indexOf("(") != -1) {
				enviarMensagens(idTelegram, associado.getNome().substring(0, associado.getNome().indexOf(" (")) + ", esse é o seu registro de pagamentos:\n\n");
			}
			else {
				enviarMensagens(idTelegram, associado.getNome() + ", esse é o seu registro de pagamentos:\n\n");
			}
			
			//enviarMensagens(idTelegram, nome + ", esse é o seu registro de pagamentos:\n\n");
			
			for(Taxa taxa : taxas) {
				
				mensagem = "Taxa: " + taxa.getNome() + " | Valor: R$" + taxa.getValor() + "\n\n";
				flag = 0;
				valorPago = 0;
				
				for(Pagamento pagamento : pagamentos) {
					if(taxa.getNome().equals(pagamento.getTaxa())) {
						mensagem += "  " + pagamento.getData() + ": R$" + pagamento.getValor() + "\n";
						valorPago += pagamento.getValor();
				        flag = 1;
					}
				}
				if(taxa.getValor() == valorPago) {
					mensagem += "\nTotal pago: R$" + valorPago + "\nTaxa quitada.\n";
				}
				else if(flag != 0) {
					mensagem += "\nTotal pago: R$" + valorPago + "\nFalta pagar: R$" + (taxa.getValor() - valorPago) + "\n";
				}
				else {
					mensagem += "Não houve pagamentos.\nFalta pagar: R$" + taxa.getValor() + "\n";
				}
				aux++;
				enviarMensagens(idTelegram, mensagem);
			}
			if(aux == 0) {
				mensagem += "Não foram encontradas taxas/pagamentos no período solicitado.\n";
			}
		}
		catch(Exception e) {
			mensagem += "Essa informação não está disponível no momento.";
			e.printStackTrace();
		}
		
		//return mensagem;
	}

	@Override
	public void onUpdateReceived(Update u) {
		//Mensagem a ser enviada
		SendMessage send = new SendMessage();
		send.setChatId(u.getMessage().getChatId());
		String resposta = "";
		
		//Dados da mensagem enviada ao bot
		String nome = u.getMessage().getFrom().getFirstName();
		long idTelegram = u.getMessage().getChatId();
		String mensagem = u.getMessage().getText();
		System.out.println(nome + " (" + idTelegram + "): " + mensagem);
		
		//Variavel auxiliar que guarda as informacoes atuais
		TelegramInfo telegramInfo = new TelegramInfo();
		int aux = 0;
		
		for(TelegramInfo informacao : informacoes) {
			if(informacao.idTelegram == idTelegram) {
				telegramInfo = informacao;
				aux++;
			}
		}
		
		if(aux == 0) {
			telegramInfo.idTelegram = idTelegram;
			informacoes.add(telegramInfo);
		}
		
		try {
			if(telegramInfo.login) {
				resposta = acessar(idTelegram, mensagem, telegramInfo);
				if(telegramInfo.selecionar) {
					// Create ReplyKeyboardMarkup object
					ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
					// Create the keyboard (list of keyboard rows)
					List<KeyboardRow> keyboard = new ArrayList<>();
					// Create a keyboard row
					KeyboardRow row;
					
					int cont = 1;
					for(Associado assoc : telegramInfo.associados) {
						row = new KeyboardRow();
						// Set each button, you can also use KeyboardButton objects if you need something else than text
						row.add(cont++ + ". " + assoc.getNome());
						// Add the first row to the keyboard
						keyboard.add(row);
					}
					
					// Set the keyboard to the markup
					keyboardMarkup.setKeyboard(keyboard);
					// Adjust the size of the keyboard so it fits the buttons
					keyboardMarkup.setResizeKeyboard(true);
					// Add it to the message
					send.setReplyMarkup(keyboardMarkup);
				}
			}
			else if(telegramInfo.selecionar) {
				resposta = acessarSelecionar(idTelegram, mensagem, telegramInfo);
				ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
			    send.setReplyMarkup(keyboardMarkup);
			}
			else if(buscarAssociado(idTelegram) == null) {
				resposta = "Oi, primeiro você precisa me dizer o seu nome. Responda João Matos ou Mateus Pereira, por exemplo.";
				telegramInfo.login = true;
			}
			else if(mensagem.equals("/sair")) {
				sair(idTelegram);
				telegramInfo.login = true;
				resposta = "Para acessar seus pagamentos novamente digite o seu nome.";
			}
			/*else if(mensagem.toLowerCase().contains("pag") || mensagem.toLowerCase().contains("taxa")) {
				resposta = pagamentos(idTelegram);
			}*/
			else {
				pagamentos(idTelegram);
				return;
				//resposta = "Eu não entendi, você só pode perguntar sobre os seus pagamentos.";
			}
		} 
		catch (SQLException e) {
			resposta = "Essa informação não está disponível no momento.";
		}
		
		send.setText(resposta);
		
		try {
			execute(send);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getBotToken() {
		// Token gerado na criação do bot
		//Cruzeiro so Sul V token 
		return "640703217:AAHnU8Nu2DbL06ZZNhLh2l7vmfjiaqwoQow";
		
		//Token do Minha Associacao
		//return "596049362:AAEzVTpO-dKQyPqfzZKskmbLGk5SI4sLD3U";
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBot = new TelegramBotsApi();
		Bot bot = new Bot();
		
		try {
			telegramBot.registerBot(bot);
		} catch (TelegramApiRequestException e) {
			System.out.println("Erro no Bot");
			e.printStackTrace();
		}
	}
	
}