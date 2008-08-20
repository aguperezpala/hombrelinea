/*
 *  Vamos a suponer que cada numero ocupa 10 espacios, por lo que tenemos que
 * dividir el tamaño de la pantalla en 3 (3 numeros)
 * Hay que tener en cuenta que vamos a empezar siempre desde la posicion (0,0)
 * del rectangulo donde se van a dibujar los numneros
 */

package hello;
import javax.microedition.lcdui.Graphics;
/**
 *
 * @author a
 */
public class Numeros implements Runnable{
    //vamos a pedir en donde empieza el  rectangulo donde van a ir los numeros
    //(el rect va a ser formado desde ese punto hasta abajo a la derecha de la
    //pantalla)
    private final static short GROSOR_NUMEROS = 3;
    private final static short DIST_ENTRE_NUM=8;
    
    private short POS_RECT_X; //rectangulo inicial ya transformada las coordenadas
    private short POS_RECT_Y; //al tamaño de la pantalla
    private short SCREEN_SIZE_X;
    private short SCREEN_SIZE_Y;
    private int secuencia; //aca vamos a guardar 0xCAN1N2N3 C=cantidad de aciertos
                           // A=numero actual apretado (contador) y los 3 numeros
    public boolean activo=false;
    public short tiempoEspera;
    private int color; //0x00RRGGBB hexa
   //el arreglo es [3 numeros][RECT][pos_ini][size_rect]
    private short num[][][][]=new short[3][][][];
    Thread thnum;
    //aca vamos a guardar los colores de los bloques de atras de los numeros
    private int backColors[]=new int[3];
        private short convert_x_system(short x)
        {
            return (short)((x*this.SCREEN_SIZE_X)/Jugador.TAM_PANTALLA_X);
	    
        }
        private short convert_y_system(short y)
        {
            return (short)((y*this.SCREEN_SIZE_Y)/Jugador.TAM_PANTALLA_Y);
        }
    
    public Numeros (int posx, int posy, int screenx,int screeny) 
    {
        this.SCREEN_SIZE_X=(short)screenx;
        this.SCREEN_SIZE_Y=(short)screeny;
        this.POS_RECT_X=convert_x_system((short)posx);
        this.POS_RECT_Y=convert_y_system((short)posy);
        this.color=0x000000FF;
        this.tiempoEspera=100;
        /*comenzamos el thread*/
        this.thnum=new Thread(this);
        
        thnum.start();
        
        
        
    }

    private int get_alt_rect()
    {
        return (this.SCREEN_SIZE_Y-this.POS_RECT_Y);
    }
    private int get_larg_rect()
    {
        return (this.SCREEN_SIZE_X-this.POS_RECT_X);
    }
    private int get_pos_num (int start) //devuelve el tamaño de un numero
    {
        return (this.SCREEN_SIZE_X*start/this.num.length);
    }
    
    private short [][] get_rect_7seg (int seg,int start, int piso, int techo)
    {
        short tmp[][]=new short[2][2];
        switch(seg)
        {
            case 1:
                tmp[0][0]=(short)(this.get_pos_num(start)+this.DIST_ENTRE_NUM);
                tmp[0][1]=(short)techo;
                tmp[1][0]=(short)(this.get_pos_num(start+1)-this.DIST_ENTRE_NUM-tmp[0][0]);
                tmp[1][1]=(short)(techo+this.GROSOR_NUMEROS-tmp[0][1]);
                break;
            case 2:
                tmp[0][0]=(short)(this.get_pos_num(start+1)-this.DIST_ENTRE_NUM-this.GROSOR_NUMEROS);
                tmp[0][1]=(short)techo;
                tmp[1][0]=(short)(this.get_pos_num(start+1)-this.DIST_ENTRE_NUM-tmp[0][0]);
                tmp[1][1]=(short)(techo+this.get_alt_rect()/2+this.GROSOR_NUMEROS/2-tmp[0][1]);
                break;
            case 3:
                tmp[0][0]=(short)(this.get_pos_num(start+1)-this.DIST_ENTRE_NUM-this.GROSOR_NUMEROS);
                tmp[0][1]=(short)(techo+this.get_alt_rect()/2-this.GROSOR_NUMEROS/2);
                tmp[1][0]=(short)(this.get_pos_num(start+1)-this.DIST_ENTRE_NUM-tmp[0][0]);
                tmp[1][1]=(short)(piso-tmp[0][1]);
                break;
            case 4:
                tmp[0][0]=(short)(this.get_pos_num(start)+this.DIST_ENTRE_NUM);
                tmp[0][1]=(short)(piso-this.GROSOR_NUMEROS);
                tmp[1][0]=(short)(this.get_pos_num(start+1)-this.DIST_ENTRE_NUM-tmp[0][0]);
                tmp[1][1]=(short)(piso-tmp[0][1]);
                break;
             case 5:
                tmp[0][0]=(short)(this.get_pos_num(start)+this.DIST_ENTRE_NUM);
                tmp[0][1]=(short)(techo+this.get_alt_rect()/2-this.GROSOR_NUMEROS/2);
                tmp[1][0]=(short)(this.get_pos_num(start)+this.DIST_ENTRE_NUM+this.GROSOR_NUMEROS-tmp[0][0]);
                tmp[1][1]=(short)(piso-tmp[0][1]);
                break;
            case 6:
                tmp[0][0]=(short)(this.get_pos_num(start)+this.DIST_ENTRE_NUM);
                tmp[0][1]=(short)techo;
                tmp[1][0]=(short)(this.get_pos_num(start)+this.DIST_ENTRE_NUM+this.GROSOR_NUMEROS-tmp[0][0]);
                tmp[1][1]=(short)(techo+this.get_alt_rect()/2+this.GROSOR_NUMEROS/2-tmp[0][1]);
                break;
            case 7:
                tmp[0][0]=(short)(this.get_pos_num(start)+this.DIST_ENTRE_NUM);
                tmp[0][1]=(short)(techo+this.get_alt_rect()/2-this.GROSOR_NUMEROS/2);
                tmp[1][0]=(short)(this.get_pos_num(start+1)-this.DIST_ENTRE_NUM-tmp[0][0]);
                tmp[1][1]=(short)(techo+this.get_alt_rect()/2+this.GROSOR_NUMEROS/2-tmp[0][1]);
                break;
        }
        return tmp;
    }
    
  
    //le pasamos como argumento si es el 1 2 o 3 numero
    public short[][][] num_2(int start)
    {        
        short tmp[][][]=new short[5][][]; // tiene 5 rectangulos
        tmp[0]=this.get_rect_7seg(1, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[1]=this.get_rect_7seg(2, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[2]=this.get_rect_7seg(7, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[3]=this.get_rect_7seg(5, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[4]=this.get_rect_7seg(4, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        
        return tmp;
    }
    
     public short[][][] num_4(int start)
    {
        short tmp[][][]=new short[4][][]; // tiene 5 rectangulos
        tmp[0]=this.get_rect_7seg(6, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[1]=this.get_rect_7seg(7, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[2]=this.get_rect_7seg(2, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[3]=this.get_rect_7seg(3, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        
        return tmp;
    }

      public short[][][] num_5(int start)
    {
       short tmp[][][]=new short[5][][]; // tiene 5 rectangulos
        tmp[0]=this.get_rect_7seg(1, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[1]=this.get_rect_7seg(6, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[2]=this.get_rect_7seg(7, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[3]=this.get_rect_7seg(3, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[4]=this.get_rect_7seg(4, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        
        return tmp;
    }
      
    public short[][][] num_6(int start)
    {
       short tmp[][][]=new short[6][][]; // tiene 5 rectangulos
        tmp[0]=this.get_rect_7seg(1, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[1]=this.get_rect_7seg(6, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[2]=this.get_rect_7seg(5, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[3]=this.get_rect_7seg(4, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[4]=this.get_rect_7seg(3, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[5]=this.get_rect_7seg(7, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        
      
        return tmp;
    }
        
    public short[][][] num_8(int start)
    {
         short tmp[][][]=new short[7][][]; // tiene 5 rectangulos
        tmp[0]=this.get_rect_7seg(1, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[1]=this.get_rect_7seg(2, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[2]=this.get_rect_7seg(3, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[3]=this.get_rect_7seg(4, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[4]=this.get_rect_7seg(5, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[5]=this.get_rect_7seg(6, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        tmp[6]=this.get_rect_7seg(7, start, this.SCREEN_SIZE_Y, this.POS_RECT_Y);
        
        
        return tmp;
    }

    private void add_num(int num, int pos)
    {
        switch (num)
        {
            case 2:
                this.num[pos]=this.num_2(pos);
                break;
            case 4:
                this.num[pos]=this.num_4(pos);
                break;
            case 5:
                this.num[pos]=this.num_5(pos);
                break;
            case 6:
                this.num[pos]=this.num_6(pos);
                break;
            case 8:
                this.num[pos]=this.num_8(pos);
                break;
        }
    }
     public void set_nums(int n)
     {
         if(!this.activo)
         {
             this.backColors[0]=0x00A0A0A0;//seteamos los colores en una
             this.backColors[1]=0x00A0A0A0;//especie de blanco
             this.backColors[2]=0x00A0A0A0;
             this.secuencia=(n & 0x00FFFFFF); //inicializamos el contador
            for (short i=0;i<num.length;i++) /*inicializamos*/
                num[i]=null;
            this.set_color(0x000000FF);
              /* if ((n & 0xFF000000) !=0)
                   this.add_num(((n & 0xFF000000)>>24), 3);*/
             if ((n & 0x00FF0000) !=0)
                 this.add_num(((n & 0x00FF0000)>>16), 0);
             if ((n & 0x0000FF00) !=0)
                 this.add_num(((n & 0x0000FF00)>>8), 1);
             if ((n & 0x000000FF) !=0)
                 this.add_num((n & 0x000000FF), 2);

           thnum=null;
           thnum= new Thread(this);
           
           thnum.start();
            this.activo=true;

             
         }
        
     }
 
     public void num_draw(Graphics g)
     {
         //primero vamos a dibujar los fondos de los numeros
        int dx=this.get_pos_num(1)-this.get_pos_num(0)-this.GROSOR_NUMEROS;
        int dy=this.SCREEN_SIZE_Y-this.POS_RECT_Y;
        
         for(int i=0;i<backColors.length;i++)
             {
                 g.setColor(backColors[i]);
                 g.drawRect(this.get_pos_num(i), this.POS_RECT_Y,dx, dy);
                 
             }
         if (this.activo)
         {
             g.setColor(color);
             for(int i = 0;i<num.length;i++)//cantidad de numeros
             {
                 
                 for(int j=0;j<num[i].length;j++)//cantidad de triangulos
                 {
                     g.fillRect(num[i][j][0][0],num[i][j][0][1] ,num[i][j][1][0],num[i][j][1][1]);
                 }
             }
         }
     }
     public int get_color()
     {
         return this.color;
     }
     public int get_red_color ()
     {
         int r;
         r=(this.color & 0x00FF0000)>>16;
         return r;
     }
     public int get_green_color ()
     {
         int r;
         r=(this.color & 0x0000FF00)>> 8;
         return r;
     }
     public int get_blue_color ()
     {
         int r;
         r=(this.color & 0x000000FF);
         return r;
     }
     public void set_color(int RGB)
     {
         this.color=RGB;
     }

     public void run()
     {
         try{Thread.sleep(this.tiempoEspera*2);}catch (Exception e){};
         
        while(get_blue_color()>0) //primera fase
        {
            this.color=this.color-51; //disminuimos el azul
            this.color+=(51<<8);//aumentamos el verde
            try{Thread.sleep(this.tiempoEspera);}catch (Exception e){};
        }
        
        this.color=0x0000FF00;//lo seteamos en verde
        while (get_red_color()<254) //segunda fase
        {
            this.color+=(51<<16); //aumentamos el rojo
            try{Thread.sleep(this.tiempoEspera);}catch (Exception e){};            
        }

        this.color=0x00FFFF00; //amarillo
        while (get_green_color()>0) //tercera fase
        {
            this.color-=(51<<8);//disminuimos verde
            try{Thread.sleep(this.tiempoEspera);}catch (Exception e){};
        }
       this.activo=false;
       
     }
     private int get_corresp_num(int x)
     {
         if (x<3)
             return 2;
         if (x<6)
             return 4;
         if (x<9)
             return 5;
         if (x<12)
             return 6;

         return 8;
     }
     public int transform_random_int(int x)
     {
         int r=0x00000000;
         r+=get_corresp_num((x & 0x0000000F));
         r+=((get_corresp_num((x & 0x000000F0)%15))<<8);
         r+=(get_corresp_num(((x & 0x00000F00)%15))<<16);

         return r;
         
     }
     private int get_cantidad_aciertos()
     {
         return (int)(this.secuencia & (0xF0000000)>>28);
     }
    private boolean es_correcto(int numApretado)
     {
         if (!this.activo)//si no esta activo retornamos falso
             return false;

         boolean r=false;
         int cont=(this.secuencia & 0x0F000000)>>24;
         switch (cont) //obtenemos el contador de la secuencia
         {
             case 0:
                 r=((this.secuencia & 0x00FF0000)>>16==numApretado);
                 break;
             case 1:
                 r=((this.secuencia & 0x0000FF00)>>8==numApretado);
                 break;
             case 2:
                 r=((this.secuencia & 0x000000FF)==numApretado);
                 break;
         }            
         if (r)
         {
             if(cont<3)
                 this.backColors[cont]=this.color;
             
             this.secuencia=this.secuencia+(0x10000000); //aumentamos un acierto
             this.secuencia=this.secuencia+(0x01000000); //aumentamos pose de secuencia secuencia
         }
             
         
         
         return r;

     }

     public int get_puntaje(int numApretado)
     {
         int r=0;
         if (this.es_correcto(numApretado))
         {
             if(this.get_blue_color()>0) //puntaje perfecto azul
                 r=3;
             else if (this.get_red_color()>0)
             {
                 if (this.get_green_color()>0) //puntaje medio amarillo
                     r=2;
                 else                           //pubtaje malo estamos en rojo
                     r=1;
             }

            //r=r*this.get_cantidad_aciertos();

         }
         return r;

     }
}
