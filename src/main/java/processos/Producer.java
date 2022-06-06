package processos;

import semaforo.PC;

public class Producer implements Runnable{
	
	private String texto;
	private PC handler;
	
	public Producer(String msg, PC handler) {
		this.texto = msg;
		this.handler = handler;
	}
	
	public void setTexto(String msg) {
		this.texto = msg;
	}
	
	@Override
	public void run(){
		this.handler.decreaseMutex();
		
		this.handler.increaseItens();		
		this.handler.increaseCount();
		this.handler.increaseMutex();
		
		this.handler.addMessageToBuffer(this.texto);
		System.out.println("Foi produzida a mensagem: " + this.texto.substring(this.texto.lastIndexOf(":")));
	}
}