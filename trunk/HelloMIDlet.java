
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javax.microedition.media.control.VolumeControl;
/**
 * @author Administrador
 */
public class HelloMIDlet extends MIDlet implements CommandListener {

  	 private Command exitCommand;
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
	 //Connection aguc;//=Connector.open("file:/root1/Readme.txt", Connector.READ);
	 
	Player p;
	VolumeControl vc;

	 try {
             
             
             p=Manager.createPlayer(this.getClass().getResourceAsStream("saturdaynight.mid"), "audio/midi");
	     //p = Manager.createPlayer("file://pattern.mid");
	     p.realize();
            vc=(VolumeControl) p.getControl("VolumeControl");
            vc.setLevel(100); 
	     // Grab the tempo control.
	    
	     p.start();

	 } catch (Exception ioe) {System.out.print("ERROR********************************\n");
         System.out.print(ioe.toString());
System.out.print("ERROR********************************\n");
         }
	

	 
	 
	 screen=new SSCanvas();
	 screen.addCommand(exitCommand);
	 screen.setCommandListener(this);
         
	 new Thread(screen).start();
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
	 
	}//GEN-LINE:|methods|0|

}
