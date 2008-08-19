/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Graphics;
/**
 *
 * @author a
 */
public class ImagenFondo {
    public Image imagen;



     public static Image resizeImage(Image src,int width,int height) {
      int srcWidth = src.getWidth();
      int srcHeight = src.getHeight();
      Image tmp = Image.createImage(width, srcHeight);
      Graphics g = tmp.getGraphics();
      int ratio = (srcWidth << 16) / width;
      int pos = ratio/2;

      //Horizontal Resize

      for (int x = 0; x < width; x++) {
          g.setClip(x, 0, 1, srcHeight);
          g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
          pos += ratio;
      }

      Image resizedImage = Image.createImage(width, height);
      g = resizedImage.getGraphics();
      ratio = (srcHeight << 16) / height;
      pos = ratio/2;

      //Vertical resize

      for (int y = 0; y < height; y++) {
          g.setClip(0, y, width, 1);
          g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
          pos += ratio;
      }
      return resizedImage;

  }//resize image    
    public ImagenFondo(String nomfile,int width, int height)
    {
        try{
            this.imagen=Image.createImage(getClass().getResourceAsStream(nomfile));
            this.imagen=this.resizeImage(this.imagen,width,height);
	 
            }catch (Exception e){System.out.print(e.toString());}
        

    }


}
