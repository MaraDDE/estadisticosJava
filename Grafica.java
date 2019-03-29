package mdde;

/**     
     * Base de las graficas usadas
     * @author MaraDDE
*/
public class Grafica {
    public Estadisticos valores [];
    public String ejes [];
    public String titulo;

    public Grafica() {
    }
    
    /**
     *@param n numero de datos
    */      
    public Grafica(int n) {
        this.valores = new Estadisticos [n];
        this.ejes = new String [n];
    }
    
    /**
     *@return valores de la columna
     *@param x columna que quieres
    */   
    public Estadisticos recuperaColumna(int x){
        return valores[x];
    }
    
     /**
     *@return titulo del eje
     *@param x columna que quieres
    */ 
    public String recuperaEje(int x){
        return ejes[x];
    }
    
     /**
     *@param nombre mete nombre de la columna
     *@param estad mete valores de estadisticos
     *@param x columna que quieres
    */    
    public void setColumna(String nombre, Estadisticos estad, int x){
        valores[x] = estad;
        ejes[x] = nombre;
    }

    /**
     *@return titulo
    */        
    public String getTitulo() {
        return titulo;
    }
    
    /**
     *@param titulo asigna titulo
    */    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
  
}
