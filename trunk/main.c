#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TIPO_VAR "short" /*tipo de variable que van a ser los arreglos*/
/*nombres de las variables y arreglos*/
#define VAR_CANT_FRAMES "CANT_FRAMES"
#define VAR_ARRAY_X "x"
#define VAR_ARRAY_Y "y"
#define VAR_CUADROS "cuadros"
#define TIPO_FUNCION "public void"
#define NOMBRE_PARAMETRO "Frame f"
#define FUNC_LIBERA_MEMORIA "libera_memoria(f)"
#define FUNC_TRANSFORM_COORDS "transform_coords"


#define PACKAGE_NAME "hello" /*nombre del pakete*/


int main(int argc, char *argv[])
{
  	char className[50]; /*vamos a guardar el nombre de la clase*/
  	char outname[50];
	char arrx[5000]; /*guardamos el arreglo de las posiciones x*/
	char arry[5000]; /*guardamos el arreglo de las posiciones y*/
	char arrc[5000]; /*guardamos el arreglo de las posiciones cuadros*/
	char num[10];
	int cantFrames;
	int cantArticulaciones; /*al pedo*/
	int cantCuadros;
	int i,j,x,y;
	FILE * fr;
	FILE * fw;

    
	strcpy(className,argv[1]);
    fr=fopen(className,"r");
    strcpy(outname,className);
    strcat(outname,".fun");
    fw=fopen(outname,"w");
   
    fscanf(fr,"# %d \n",&cantArticulaciones);
	fscanf(fr,"# %d \n",&cantFrames);
  
	/* INICIALIZAMOS LOS STRINGS*/
	/*fprintf(fw,"package " PACKAGE_NAME ";");*/
	if (fw)
{
	fprintf(fw,"public Frame" " %s = new Frame (",className);
	
	
   	printf("********   %d   *****",cantFrames);

	strcat(arrc,"{");
    strcat(arrx,"{");
 	strcat(arry,"{");
 	
	
    
	for (i=0;i<cantFrames;i++)
	{
        strcat(arrx,"{");
        strcat(arry,"{");

		for(j=0;j<cantArticulaciones;j++)
		{
			fscanf(fr,"( %d , %d ) \n",&x,&y);
            strcpy(num,"");
			sprintf(num,"%d",x);
	    strcat(arrx,num);
            strcpy(num,"");
            sprintf(num,"%d",y);
            strcat(arry,num);
            if (j<(cantArticulaciones-1)) /*comprobamos que no sea el ultimo*/
            {
               strcat(arrx,",");
               strcat(arry,",");
            }
        }
        strcat(arrx,"}\n\t");
        strcat(arry,"}\n\t");
        fscanf(fr,"# %d \n",&cantCuadros);
            strcpy(num,"");
        sprintf(num,"%d",cantCuadros);
        strcat(arrc,num);

        if (i<(cantFrames-1)) /*comprobamos que no sea el ultimo*/
            {
               strcat(arrx,",");
               strcat(arry,",");
               strcat(arrc,",");
               
            }

        
    }
    strcat(arrc,"}");
    strcat(arrx,"}");
 	strcat(arry,"}");

 	fprintf(fw,"new short[][]%s,\n",arrx);
	fprintf(fw,"new short[][]%s,\n",arry);
	fprintf(fw,"new short[]%s,\n",arrc);
	fprintf(fw,"(short)%d);\n",cantFrames);
    
	/*fprintf(fw,"\n\t"FUNC_TRANSFORM_COORDS"(f);\n}");*/




	
   fclose(fr);
   fclose(fw);
}

	return 0;


}
