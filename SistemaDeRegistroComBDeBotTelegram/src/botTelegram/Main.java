package botTelegram;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;


public class Main {

	public static void main(String[] args) {
		// Cria��o do objeto bot com as informa��es de acesso
		TelegramBot bot = new TelegramBot("414189934:AAH_ckxJSi1pMdH1iHViwtZfDZwekUgNELk");

		// objeto respons�vel por receber as mensagens
		GetUpdatesResponse updatesResponse;
		// objeto respons�vel por gerenciar o envio de respostas
		SendResponse sendResponse;
		// objeto respons�vel por gerenciar o envio de a��es do chat
		BaseResponse baseResponse;

		// controle de off-set, isto �, a partir deste ID ser� lido as mensagens
		// pendentes na fila
		int m = 0;

		// loop infinito pode ser alterado por algum timer de intervalo curto
		while (true) {

			// executa comando no Telegram para obter as mensagens pendentes a partir de um
			// off-set (limite inicial)
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();

			// an�lise de cada a��o da mensagem
//			for (Update update : updates) {
//				// atualiza��o do off-set
//				m = update.updateId() + 1;
//
//				System.out.println("Recebendo mensagem:" + update.message().text());
//
//				// envio de "Escrevendo" antes de enviar a resposta
//				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
//				// verifica��o de a��o de chat foi enviada com sucesso
//				System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());
//
//				// envio da mensagem de resposta
//				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "N�o entendi..."));
//				// verifica��o de mensagem enviada com sucesso
//				System.out.println("Mensagem Enviada?" + sendResponse.isOk());
//
//			}

		}
	}

}
