package rmi;

import java.rmi.registry.*;
import java.util.Scanner;

public class RmiClient {
	static public void main(String args[]) {
		ReceiveMessageInterface rmiServer;
		Registry registry;
		String serverAddress = "127.0.0.1";
		String serverPort = "5555";
		String input;
		Scanner inputClient = new Scanner(System.in);
		String process = "";

		try {
			while (!process.equals("1") 
					&& !process.equals("2")) {
				System.out.println("Selecione o numero da opcao do processo desejado:");
				System.out.println("Digite 1 para Produzir");
				System.out.println("Digite 2 para Consumir");
				process = inputClient.nextLine();
			}

			String output = process.substring(0, 1).toUpperCase() + process.substring(1);
			System.out.println(output + " selecionado.");
			
			if (process.equals("1"))
				System.out.println("\nDigite o que deseja produzir");
			else
				System.out.println("\nAperte ENTER para consumir");
			System.out.println("Digite exit para terminar o processo");
			registry = LocateRegistry.getRegistry(serverAddress, 
					(Integer.valueOf(serverPort)).intValue());
			rmiServer = (ReceiveMessageInterface) (registry.lookup("rmiServer"));
			
			while (inputClient.hasNextLine()) {
				input = inputClient.nextLine();
				if (input.equals("exit")) {
					System.out.println(output + " abortado.");
					break;
				}
				rmiServer.receiveMessage(process + ": " +input);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		inputClient.close();
	}
}
