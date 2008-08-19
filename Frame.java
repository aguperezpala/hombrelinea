package hello;

public class Frame {
public short CANT_FRAMES;
public short cuadros[];
public short x[][];
public short y[][];


public Frame(short[][] x1,short[][] y1, short[] cu, short cf)
{
    this.x=x1;
    this.y=y1;
    this.cuadros=cu;
    this.CANT_FRAMES=cf;
    AnimacionSet.transform_coords(this);
   
}
public Frame(){};
}