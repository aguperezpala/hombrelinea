
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Administrador
 */
public class HelloMIDlet extends MIDlet implements CommandListener {

  	 private Command exitCommand;
         private Command startGame;
	 private Display display;
	 private SSCanvas screen;
    
	 
	 
	 
	 public HelloMIDlet () {
		/*Ficheros_J2ME agufile=new Ficheros_J2ME();
		
		try{
			System.out.print("Estamos leyendo: \n");
			agufile.getViaStreamConnection("file:///Readme");
		}catch(Exception e){
			System.out.print("NO SE PUEDE");
		}
	 */
            
             
	 display=Display.getDisplay(this);
         
	 exitCommand = new Command("Salir",Command.SCREEN,2);
         startGame=new Command ("Empezar",Command.SCREEN,3);

	 //Connection aguc;//=Connector.open("file:/root1/Readme.txt", Connector.READ);
	 screen=new SSCanvas();
	 screen.addCommand(exitCommand);
         screen.addCommand(startGame);
	 screen.setCommandListener(this);
         
         }
	 
	 public void startApp() throws MIDletStateChangeException {
	 display.setCurrent(screen);
	 }


	 
	 public void pauseApp() {}

	 public void destroyApp(boolean unconditional) {}

	 public void commandAction(Command c, Displayable s) {

	 if (c == exitCommand) {
	  destroyApp(false);
	  notifyDestroyed();
	 }
         if (c == startGame) {
             if (!screen.activo){
                new Thread(screen).start();
                screen.removeCommand(startGame);
             }

	 }
	 
	}//GEN-LINE:|methods|0|

}
