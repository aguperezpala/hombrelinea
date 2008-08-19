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

    public void anim_set(int anim, int place) //posibilidades de place son 0 1 2 3
    {

    }

    

}
