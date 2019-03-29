package mdde;
/**
     * Crea Boxplots de las tres columnas
     * @author MaraDDE
*/
public class Boxplot extends Grafica{
    public Integer graf1;
    public Integer graf2;
    public Integer graf3;

     /**
         *@param graf1 asigna la grafica de la columna 1
         *@param graf2 asigna la grafica de la columna 2
         *@param graf3 asigna la grafica de la columna 3
         *@param n numero de datos
    */       
    public Boxplot(Integer graf1, Integer graf2, Integer graf3, int n) {
        super(n);
        this.graf1 = graf1;
        this.graf2 = graf2;
        this.graf3 = graf3;
        this.titulo = "BoxPlot";
    }

    /**
    *@return graf1 regresa la grafica de la columna 1
    */
    public Integer getGraf1() {
        return graf1;
    }

    /**
     *@param graf1 asigna la grafica 1
    */    
    public void setGraf1(Integer graf1) {
        this.graf1 = graf1;
    }

    /**
    *@return graf2 regresa la grafica de la columna 2
    */
    public Integer getGraf2() {
        return graf2;
    }

    /**
     *@param graf2 asigna la grafica 2
    */ 
    public void setGraf2(Integer graf2) {
        this.graf2 = graf2;
    }

    /**
    *@return graf3 regresa la grafica de la columna 3
    */
    public Integer getGraf3() {
        return graf3;
    }

    /**
     *@param graf3 asigna la grafica 3
    */ 
    public void setGraf3(Integer graf3) {
        this.graf3 = graf3;
    }
}
