/*
 *  Esta clase la vamos a usar para setear las animaciones
 */

package hello;

/**
 *
 * @author Administrador
 */
public class AnimacionSet {

    /*secuencia de animacion hasta 4 animaciones
     *vamos a guardar la primera animacion en el valor
     *mas alto del entero, osea 0x11223344
     * ese va a ser el orden de las animaciones
     * tenemos 255 posibles animaciones por posicion
     */ 
    private int animSeq;


    /*******************ANIMACIONES*********************/
    
    public static final Frame travolta_general = new Frame (new short[][]{{278,295,177,202,298,264,213,190,248,249,247}
	,{295,295,203,202,298,299,213,216,248,249,254}
	,{315,295,224,202,298,316,213,242,248,249,263}
	,{295,295,202,202,298,298,213,213,248,249,259}
	},
new short[][]{{146,200,145,203,385,286,382,288,142,219,116}
	,{148,200,147,203,385,284,382,290,142,219,116}
	,{152,200,149,203,385,291,382,294,142,219,120}
	,{151,200,150,203,385,293,382,293,142,219,120}
	},
new short[]{5,5,5,1},
(short)4);
    
    public static void transform_coords(Frame f)
    {
        for (int i = 0;i<f.CANT_FRAMES;i++)
        {
            for (int j=0;j<Jugador.CANT_ARTICULACIONES;j++)
            {
                f.x[i][j]=(short)(f.x[i][j]*Jugador.SCREEN_SIZE_X/Jugador.TAM_PANTALLA_X);
                f.y[i][j]=(short)(f.y[i][j]*Jugador.SCREEN_SIZE_Y/Jugador.TAM_PANTALLA_Y);
            }
        }
    }

    public Frame get_anim_frame(int animn) //devuelve la animacion segun animNumber
    {
        switch(animn)
        {
                      

            default:
                return travolta_general;
        }
    }
    public void anim_set(int anim, int place) //posibilidades de place son 0 1 2 3
    {
        int pos=(3-place)*8;//invertimos el lugar donde vamos a guardar la anim
                            //y le decimos cuantos bits corremos
        
        anim=anim & 0x000000FF;
        this.animSeq=this.animSeq & (0x000000FF)<<pos; //borramos la actual anim
        this.animSeq=this.animSeq | (anim<<pos); //seteamos la anim

    }

    

}
