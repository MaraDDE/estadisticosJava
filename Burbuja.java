package mdde;
/**
     * Crea diagrama de burbuja
     * @author MaraDDE
*/
public class Burbuja extends Grafica{
    public Integer indexX;
    public Integer indexY;
    public Integer indexRadio;

   /**
         *@param indexX eje x
         *@param indexY eje y
         *@param indexRadio valor del radio
         *@param n numero de datos
    */ 
    public Burbuja(Integer indexX, Integer indexY, Integer indexRadio, int n) {
        super(n);
        this.indexX = indexX;
        this.indexY = indexY;
        this.indexRadio = indexRadio;
        this.titulo = "Burbuja";
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

    /**
    *@return indexRadio regresa valor del radio
    */
    public Integer getIndexRadio() {
        return indexRadio;
    }

    /**
     *@param indexRadio asigna el valor del radio
    */  
    public void setIndexRadio(Integer indexRadio) {
        this.indexRadio = indexRadio;
    }
}
