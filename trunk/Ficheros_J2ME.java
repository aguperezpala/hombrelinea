package hello;
import java.io.*;
import javax.microedition.io.*;

/**
 * An example MIDlet to fetch a page 
 * using a StreamConnection.
 */
public class Ficheros_J2ME {
  
  String url = "file:///root1/Readme";
  public Ficheros_J2ME() {
  
  }
  /**
   * This method is invoked when the MIDlet is activated
   */
  
  void getViaStreamConnection(String url) 
		throws IOException {
    StreamConnection c = null;
    InputStream s = null;
    StringBuffer b = new StringBuffer();
    
    try {
      c = (StreamConnection)Connector.open(url,Connector.READ);
      s = c.openInputStream();
      int ch;
      while((ch = s.read()) != -1) {
        b.append((char) ch);
        
      }
      System.out.println(b.toString());
     
    } finally {
      if(s != null) {
        s.close();
      }
      if(c != null) {
        c.close();
      }
    }
    // display the contents of the file in a text box.
    
  }
}

