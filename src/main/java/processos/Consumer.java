package processos;

import semaforo.PC;

public class Consumer implements Runnable{
		
	private PC handler;

	public Consumer(PC handler) {
		this.handler = handler;
	}
	
	@Override
	public void run() {		
		this.handler.decreaseMutex();
                this.handler.decreaseItens();
                this.handler.decreaseCount();
                String msg = "";
                
                try{
                    msg = this.handler.getMessageFromBuffer(0);
                    this.handler.removeMessageFromBuffer(0);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
                
                this.handler.increaseMutex();
                
		System.out.println("Foi consumida a mensagem: " + msg.substring(msg.lastIndexOf(":")));
	}
}