package mdde;
/**
     * Crea diagrama de dispersion
     * @author MaraDDE
*/
public class Dispersion extends Grafica{
    public Integer indexX;
    public Integer indexY;

   /**
         *@param indexX eje x
         *@param indexY eje y
         *@param n numero de datos
    */ 
    public Dispersion(Integer indexX, Integer indexY, int n) {
        super(n);
        this.indexX = indexX;
        this.indexY = indexY;
        this.titulo = "Dispersion";
    }

    /**
    *@return indexX regresa valor del eje x
    */
    public Integer getIndexX() {
        return indexX;
    }

    /**
     *@param indexX asigna el valor del eje x
    */  
    public void setIndexX(Integer indexX) {
        this.indexX = indexX;
    }

    /**
    *@return indexY regresa valor del eje y
    */
    public Integer getIndexY() {
        return indexY;
    }

    /**
     *@param indexY asigna el valor del eje y
    */  
    public void setIndexY(Integer indexY) {
        this.indexY = indexY;
    }
    
}
