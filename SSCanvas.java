package hello;

import java.util.Random;
import javax.microedition.lcdui.*;


class SSCanvas extends Canvas implements Runnable{

	 //private Sprite miSprite=new Sprite(1);
	 //private int pos_agu=80;
	 //private int delta_x=0;
         public final int SCREEN_SIZE_Y=this.getHeight();
         public final int SCREEN_SIZE_X=this.getWidth();
	 private Jugador jugador=new Jugador (this.getHeight(),this.getWidth());;
         private Frame frame=new Frame();
         private AnimacionSet animset=new AnimacionSet();
         private ImagenFondo fondo=new ImagenFondo("fondo1.PNG",this.getWidth(),this.getHeight());
         private Numeros num=new Numeros(0,420,this.getWidth(),this.getHeight());
         private Random random=new Random();
         private int puntaje;
        private Frame fa=new Frame(new short[][]{{217,202,80,100,322,282,170,189,167,169,150}
	,{303,291,142,159,322,282,170,189,226,228,222}
	,{303,320,188,217,322,282,170,189,273,278,250}
	,{303,287,117,154,322,282,170,189,232,232,232}
	},new short[][]{{129,184,130,100,394,306,402,306,165,228,100}
	,{129,181,217,171,394,306,402,306,164,230,120}
	,{129,182,230,171,394,306,402,306,168,229,127}
	,{129,184,239,183,394,306,402,306,172,235,130}
	},new short[]{10,10,10,10},(short)4);

         public SSCanvas()
         {
           //random.setSeed(System.currentTimeMillis());
           num.set_nums(0x00040406);
           
           
	   
       
	 }
	 
	 public void movimiento()
         {
             if (jugador.frameActual<frame.CANT_FRAMES)
             {
                 if(jugador.cantCuadros>0 )
                 {
                     jugador.mueve_suave();
                 }
                 else
                 {
                     jugador.cantCuadros=frame.cuadros[jugador.frameActual];
                     jugador.frameActual++;
                 }
             }
             else
             {
                 jugador.set_Frame(fa, (short) 0);
                 num.set_nums(num.transform_random_int(Math.abs(random.nextInt())));
                 

             }
                 
	 }

         
	 public void run() {
	        
	        while (true) {
                 
                repaint();
                serviceRepaints();
            	movimiento();
                
                
	            try {
	                Thread.sleep(5);
	            } catch (InterruptedException e) {
	                System.out.println(e.toString());
	            }
	        }
	    }  

	    public void keyPressed(int keyCode) {
	        int action=getGameAction(keyCode);

	        switch (action) {
                    case FIRE:
                        puntaje+=num.get_puntaje(5);
                        break;
                    case KEY_NUM5:
                        puntaje+=num.get_puntaje(5);
                        break;
	            case LEFT:
                        puntaje+=num.get_puntaje(4);
	            	break;
	            case KEY_NUM4:
                        puntaje+=num.get_puntaje(4);
	            	break;
                    case RIGHT:
	            	puntaje+=num.get_puntaje(6);
	                break;
                    case KEY_NUM6:
	            	puntaje+=num.get_puntaje(6);
	                break;    
	            case UP:
                        puntaje+=num.get_puntaje(2);
	                break;
	            case KEY_NUM2:
                        puntaje+=num.get_puntaje(2);
	                break;
                    case DOWN:
                        puntaje+=num.get_puntaje(8);
	                break;
                    case KEY_NUM8:
                        puntaje+=num.get_puntaje(8);
	                break;
	        }
	    }
public void keyReleased (int keyCode) {
    int action=getGameAction(keyCode);

    switch (action) {

        case LEFT:
            //miSprite.setX(miSprite.getX()+5);
        	break;
        case RIGHT:
        	//miSprite.setX(miSprite.getX()-5);
    //    	delta_x=0;
            break;
        case UP:
      //  	miSprite.setY(miSprite.getY()+1);
            break;
        case DOWN:       
        //	miSprite.setY(miSprite.getY()-5);
            break;
        
    }
}
       	 public void paint(Graphics g) {

	 //  Borrar pantalla
	 g.setColor(255,255,255);
	 g.fillRect(0,0,getWidth(),getHeight());
	 g.setColor(0,255,0);
         
         //g.drawString("EW", 50, 50, 0);
         //g.drawString("millisecs :"+(System.currentTimeMillis()-this.delta_time), 50, 50, 0);
        
          if (fondo.imagen!=null) //CAMBIAR
          {   
             g.drawImage(fondo.imagen, 0, 0,  Graphics.LEFT | Graphics.TOP);
          }
         g.drawString("PUNTAJE: "+this.puntaje, 50, 10, 0); 
              
	 //g.drawLine(0, 0, 90, 90);
	 // situar y dibujar sprite
	
	 //miSprite.draw(g);
	 jugador.player_draw(g);
         num.num_draw(g);
	
	 }
 }
