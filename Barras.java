package mdde;
/**
     * Crea una grafica de barras
     * @author MaraDDE
*/
public class Barras extends Grafica{
    public Integer indexX;
   
    /**
         *@param indexX eje x
         *@param n numero de datos
    */    
    public Barras(Integer indexX, int n) {
        super(n);
        this.indexX = indexX;
        this.titulo = "Barras";
    }
    
    /**
    *@return indexX regresa eje x
    */
    public Integer getIndexX() {
        return indexX;
    }
    
    /**
     *@param indexX asigna eje x
    */    
    public void setIndexX(Integer indexX) {
        this.indexX = indexX;
    }
}
