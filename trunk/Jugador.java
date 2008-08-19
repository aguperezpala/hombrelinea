
package hello;

import javax.microedition.lcdui.*;




// ************************************************************************
// ************************		INFO GENERAL	***************************
//Vamos
//**************************************************************************


public class Jugador {
	//estos son los tamaños de la pantalla donde hicimos los movimientos del
	//del personaje
	public static final short TAM_PANTALLA_X=500;
	public static final short TAM_PANTALLA_Y=500;
	
	public static final short CANT_ARTICULACIONES=11;
	//las constantes para saber en que posicion del arreglo esta cada parte
	//del cuerpo
	public static final short MANO_DER_INI = 0;
	public static final short CODO_DER_INI = 1;
	public static final short MANO_IZQ_INI = 2;
	public static final short CODO_IZQ_INI = 3;
	public static final short PIE_DER_INI = 4;
	public static final short RODILLA_DER_INI = 5;
	public static final short PIE_IZQ_INI = 6;
	public static final short RODILLA_IZQ_INI = 7;
	public static final short CUELLO_INI = 8;
	public static final short CADERA_INI = 9;
	public static final short CABEZA_INI = 10;
	//public boolean enMovimiento = false;
	//determinamos el grosor de la linea y de las cabezas
	private short GROSOR;
        //private Image cabeza=null;
        
	//TAMAÑO DE LA PANTALLA
	public static short SCREEN_SIZE_X;
	public static short SCREEN_SIZE_Y;
	
        //aca vamos a guardar las cordenadas destino
        public Frame frameDest;

	//cantidad de cuadros entre la posicion inicial y la final de 1 frame
	public short cantCuadros;

        public short frameActual;
        
        //vamos a determinar las posiciones de las articulaciones
	private Pos[] cuerpo=new Pos[this.CANT_ARTICULACIONES];//partes del cuerpo
	
	
	private void inicializa_articulaciones(){
				for (int i=0;i<cuerpo.length;i++)
					cuerpo[i]=new Pos();
	}
		
	//el constructor va a tomar solamente el tamaño de la pantalla y con eso
	//vamos a definir las 2 variables que van hacer generico el juego..
	//grosor(de la linea) y escala del jugador 
	public Jugador (int alt, int lar){
		this.cantCuadros=0;
                
		//determinemos el grosor de antemano?
		this.GROSOR = 10;
		/* try{
                    this.cabeza=Image.createImage(getClass().getResourceAsStream("cabeza.PNG"));
                    this.cabeza=ImagenFondo.resizeImage(this.cabeza,20,25);
	             }catch (Exception e){System.out.print(e.toString());}
                 * */
               //vamos a tomar la escala con el minimo de las resoluciones
		//y tenemos como base un promedio (calcularlo)
		this.SCREEN_SIZE_X =(short) alt;
		this.SCREEN_SIZE_Y=(short) lar;
                this.frameActual=0;
		inicializa_articulaciones();
		
		this.set_articulacion(Jugador.CABEZA_INI,(short)150,(short)  100);
		this.set_articulacion(Jugador.CADERA_INI, (short)150,(short)  150);
		this.set_articulacion(Jugador.CODO_DER_INI ,(short)200, (short) 100);
		this.set_articulacion(Jugador.CODO_IZQ_INI ,(short)100,(short)  100);
		this.set_articulacion(Jugador.CUELLO_INI ,(short)150,(short)  120);
		this.set_articulacion(Jugador.MANO_DER_INI ,(short)220,(short)  130);
		this.set_articulacion(Jugador.MANO_IZQ_INI ,(short)80, (short) 130);
		this.set_articulacion(Jugador.PIE_DER_INI ,(short)180,(short)  250);
		this.set_articulacion(Jugador.PIE_IZQ_INI ,(short)120,(short)  250);
		this.set_articulacion(Jugador.RODILLA_DER_INI ,(short)160,(short)  200);
		this.set_articulacion(Jugador.RODILLA_IZQ_INI ,(short)130, (short) 200);
		//seteamos los finales en el mismo lugar
		// CAMBIAR!!!!!
		
	}
	
	
//*************************************************************/
//********* Funciones set de Pos de las articulaciones*********/
	//vamos a tomar una Pos p que viene del editor, y la transformamos para luego
        //posicionar la articulacion en el sistema de coordenadas de la pantalla

        public void set_articulacion (short art, short posx, short posy)
        {
		cuerpo[art].X=posx;
		cuerpo[art].Y=posy;
	}
        /*public void set_articulacion_converting (short art, short x, short y)
        {
            
            cuerpo[art].X=this.convert_x_system(x);
            cuerpo[art].Y=this.convert_y_system(y);
            
	}*/
          //convertimos la posicion del editor en la posicion de la pantalla
        private short convert_x_system(short x)
        {
            return (short)(x*this.SCREEN_SIZE_X/TAM_PANTALLA_X);
	    
        }
        private short convert_y_system(short y)
        {
            return (short)(y*this.SCREEN_SIZE_Y/TAM_PANTALLA_Y);
        }
        //frames
		
	public void set_Frame(Frame f,short num_frame)
        {
            if (f!=null)
            {
                this.frameDest=f;
                this.frameActual=num_frame;
                this.cantCuadros=this.frameDest.cuadros[this.frameActual];
            }
        }
        
//*****************************************************//

	//vamos a dibujar los miembros segun 2 posiciones
	/*private void miembro_draw(Graphics g, short origen, short destino){
		//vamos a hacerlo primero con lineas
		g.drawLine(this.cuerpo[origen].X, this.cuerpo[origen].Y, this.cuerpo[destino].X, this.cuerpo[destino].Y);
	}*/
	
//	ahora vamos a dibujar las articulaciones con un circulo
	//teniendo en cuenta la altura

        private void draw_middle(short org, short dest, Graphics g)
        {
            int auxx=this.cuerpo[org].X+(this.cuerpo[dest].X-this.cuerpo[org].X)/2;
            int auxy=this.cuerpo[org].Y+(this.cuerpo[dest].Y-this.cuerpo[org].Y)/2;
            g.fillArc(auxx-this.GROSOR/2,auxy-this.GROSOR/2, this.GROSOR, this.GROSOR, 0, 360);
            
        }
	
	public void player_draw(Graphics g){
		//mano izquierda
		//miembro_draw (g,Jugador.CODO_IZQ_INI,Jugador.MANO_IZQ_INI);
                g.drawLine(this.cuerpo[Jugador.CODO_IZQ_INI].X, this.cuerpo[Jugador.CODO_IZQ_INI].Y, this.cuerpo[Jugador.MANO_IZQ_INI].X, this.cuerpo[Jugador.MANO_IZQ_INI].Y);
                draw_middle(this.CODO_IZQ_INI,this.MANO_IZQ_INI,g);
		//brazo izquierdo
		//miembro_draw(g,Jugador.CUELLO_INI,Jugador.CODO_IZQ_INI);
                g.drawLine(this.cuerpo[Jugador.CUELLO_INI].X, this.cuerpo[Jugador.CUELLO_INI].Y, this.cuerpo[Jugador.CODO_IZQ_INI].X, this.cuerpo[Jugador.CODO_IZQ_INI].Y);
                draw_middle(this.CUELLO_INI,this.CODO_IZQ_INI,g);
		//brazo derecho
		//miembro_draw(g,Jugador.CUELLO_INI,Jugador.CODO_DER_INI);
                g.drawLine(this.cuerpo[Jugador.CUELLO_INI].X, this.cuerpo[Jugador.CUELLO_INI].Y, this.cuerpo[CODO_DER_INI].X, this.cuerpo[CODO_DER_INI].Y);
                draw_middle(this.CUELLO_INI,this.CODO_DER_INI,g);
		//mano derecha
		//miembro_draw(g,Jugador.CODO_DER_INI,Jugador.MANO_DER_INI);
                g.drawLine(this.cuerpo[Jugador.CODO_DER_INI].X, this.cuerpo[Jugador.CODO_DER_INI].Y, this.cuerpo[Jugador.MANO_DER_INI].X, this.cuerpo[Jugador.MANO_DER_INI].Y);
                draw_middle(this.CODO_DER_INI,this.MANO_DER_INI,g);
		//cuello
		//miembro_draw(g,Jugador.CUELLO_INI,Jugador.CABEZA_INI);
                g.drawLine(this.cuerpo[Jugador.CUELLO_INI].X, this.cuerpo[Jugador.CUELLO_INI].Y, this.cuerpo[Jugador.CABEZA_INI].X, this.cuerpo[Jugador.CABEZA_INI].Y);
               // draw_middle(this.CUELLO_INI,this.CABEZA_INI,g);
		//columna
		//miembro_draw(g,Jugador.CUELLO_INI,Jugador.CADERA_INI);
                g.drawLine(this.cuerpo[Jugador.CUELLO_INI].X, this.cuerpo[Jugador.CUELLO_INI].Y, this.cuerpo[Jugador.CADERA_INI].X, this.cuerpo[Jugador.CADERA_INI].Y);
                draw_middle(this.CUELLO_INI,this.CADERA_INI,g);
		//muslo derecho
		//miembro_draw(g,Jugador.CADERA_INI,Jugador.RODILLA_DER_INI);
                g.drawLine(this.cuerpo[Jugador.CADERA_INI].X, this.cuerpo[Jugador.CADERA_INI].Y, this.cuerpo[Jugador.RODILLA_DER_INI].X, this.cuerpo[Jugador.RODILLA_DER_INI].Y);
                draw_middle(this.CADERA_INI,this.RODILLA_DER_INI,g);
		//pie derecho
		//miembro_draw(g,Jugador.RODILLA_DER_INI,Jugador.PIE_DER_INI);
                g.drawLine(this.cuerpo[Jugador.RODILLA_DER_INI].X, this.cuerpo[Jugador.RODILLA_DER_INI].Y, this.cuerpo[Jugador.PIE_DER_INI].X, this.cuerpo[Jugador.PIE_DER_INI].Y);
                draw_middle(this.RODILLA_DER_INI,this.PIE_DER_INI,g);
		//muslo izquierdo
		//miembro_draw(g,Jugador.CADERA_INI,Jugador.RODILLA_IZQ_INI);
                g.drawLine(this.cuerpo[Jugador.CADERA_INI].X, this.cuerpo[Jugador.CADERA_INI].Y, this.cuerpo[Jugador.RODILLA_IZQ_INI].X, this.cuerpo[Jugador.RODILLA_IZQ_INI].Y);
                draw_middle(this.CADERA_INI,this.RODILLA_IZQ_INI,g);
		//pie izquierdo
		//miembro_draw(g,Jugador.RODILLA_IZQ_INI,Jugador.PIE_IZQ_INI);
                g.drawLine(this.cuerpo[Jugador.RODILLA_IZQ_INI].X, this.cuerpo[Jugador.RODILLA_IZQ_INI].Y, this.cuerpo[Jugador.PIE_IZQ_INI].X, this.cuerpo[Jugador.PIE_IZQ_INI].Y);
                draw_middle(this.RODILLA_IZQ_INI,this.PIE_IZQ_INI,g);
		
		//ahora vamos a dibujar las articulaciones con un circulo
		//teniendo en cuenta la altura y la largura del jugador
		
		//sabemos que las pares son las articulaciones origen, osea las importantes
		for (int i=0;i<cuerpo.length-1;i++)
                {                    
                    g.fillArc(this.cuerpo[i].X-this.GROSOR/2, this.cuerpo[i].Y-this.GROSOR/2, this.GROSOR, this.GROSOR, 0, 360);
		}
                g.fillArc(this.cuerpo[10].X-this.GROSOR, this.cuerpo[10].Y-this.GROSOR, this.GROSOR*2, this.GROSOR*2, 0, 360);
                //if (cabeza!=null)
                //    g.drawImage(cabeza, cuerpo[cuerpo.length-1].X-cabeza.getWidth()/2, cuerpo[cuerpo.length-1].Y-cabeza.getHeight()/2, Graphics.LEFT | Graphics.TOP);
                 
		
	}
	
	//******************** 	Movimiento Suave **********************//
	public void mueve_suave(){
		short auxx;
		short auxy;
              
                if (this.frameDest!=null)
                {
                    for (short i=0;i<cuerpo.length;i++)
                    {
                       auxx=this.frameDest.x[this.frameActual][i];
                       auxy=this.frameDest.y[this.frameActual][i];
                       auxx=(short)(((auxx-this.cuerpo[i].X)/this.cantCuadros)+this.cuerpo[i].X); //tenemos (pos_final_x-pos_inicial_x)/cant_frames
                       auxy=(short)(((auxy-this.cuerpo[i].Y)/this.cantCuadros)+this.cuerpo[i].Y); //tenemos (pos_final_y-pos_inicial_y)/cant_frames
                        
                                                 
                       //ahora lo que hacemos es actualizar las pos_ini de cada una de las articulaciones
                       //de forma que vayan moviendose de a poco
                       this.set_articulacion(i, auxx, auxy);
                            


                    }
                    //decrementamos un frame
                    this.cantCuadros--;
                }
	}
	
}
