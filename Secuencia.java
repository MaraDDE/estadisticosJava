package mdde;

/**
     * Crea una grafica de secuencia temporal
     * @author MaraDDE
*/
public class Secuencia extends Grafica{
    public Integer indexX;

    /**
         *@param indexX eje x
         *@param n numero de datos
    */     
    public Secuencia(Integer indexX, int n) {
        super(n);
        this.indexX = indexX;
        this.titulo = "Secuencia";
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
}
