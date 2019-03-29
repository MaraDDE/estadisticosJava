package mdde;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**     
     * Calcula los estadisticos descriptivos de un conjunto de datos
     * @author MaraDDE
*/
public class Estadisticos {

    private int[] datos;
    private int numDatos;
   
    /**
     * @param arr mete los datos en un arreglo
     * @param dim establece el numero de datos
    */      
    public Estadisticos(int[] arr, int dim){
        datos = arr;
        numDatos = dim;
        ordenar();
    }
    
    /**
     * @param arr mete los datos en un arreglo
     * @param dim establece el numero de datos
     * @param ord establece si los datos van ordenados
    */
    public Estadisticos(int[] arr, int dim, boolean ord){
        datos = arr;
        numDatos = dim;
        if(ord)
            ordenar();
    }
    
    private void ordenar (){
       Arrays.sort(datos);
    }
    
    /**
     * @return suma regresa la suma de todos los datos
    */
    private int sumar(){
        int suma = 0;
        for (int i=0; i<numDatos; i++)
            suma = suma + datos[i];
        return suma;
    }
    
    /**
    *@return numDatos regresa total de datos
    */
    public int getNumDatos(){
        return numDatos;
    }
    
    /**
    *@return datos regresa los datos en el arreglo
    */
    public int[] getDatos(){
        return datos;
    }
    
    /**
    *@return el valor más chico del arreglo
    */
    public int minimo(){
        int[] datosTemp = datos;
        Arrays.sort(datosTemp);
        return datosTemp[0];
    }
    
        
    /**
    *@return regresa el valor más grande del arreglo
    */
    public int maximo(){
        int[] datosTemp = datos;
        Arrays.sort(datosTemp);
        return datosTemp[numDatos-1];
    }
          
    /**
    *@return regresa el valor mas grande menos el valor mas chico
    */
    public int rango(){
        int[] datosTemp = datos;
        Arrays.sort(datosTemp);
        return datosTemp[numDatos-1] - datosTemp[0];
    }
    
    /**
    *@return regresa el valor que se encuentra en el cuartil 1
    */
    public double cuartil_1(){
        int i = 25*numDatos/100;
        int[] datosTemp = datos;
        Arrays.sort(datosTemp);
        if(i%2==0)
            return (datosTemp[i]+datosTemp[i+1])/2;
        else
            return datosTemp[i];
    }
    
    /**
    *@return regresa el valor que se encuentra en el cuartil 2
    */
    public double cuartil_2(){
        int i = 50*numDatos/100;
        int[] datosTemp = datos;
        Arrays.sort(datosTemp);
        if(i%2==0)
            return (datosTemp[i]+datosTemp[i+1])/2;
        else
            return datosTemp[i];
    }
    
     /**
    *@return regresa el valor que se encuentra en el cuartil 3
    */
    public double cuartil_3(){
        int i = 75*numDatos/100;
        int[] datosTemp = datos;
        Arrays.sort(datosTemp);
        if(i%2==0)
            return (datosTemp[i]+datosTemp[i+1])/2;
        else
            return datosTemp[i];
    }
    
    /**
    *@return regresa el valor del rango intercuartilico
    */
    public double ric(){
        return cuartil_3() - cuartil_1();
    }   
        
    /**
    *@return regresa el valor de la media
    */
    public double media(){
        return ((double) sumar() / (double) numDatos);
    }
        
    /**
    *@return regresa el valor de la varianza
    */
    public double varianza(){
        double var=0;
        for (int i = 0; i < numDatos; i++){
            var = var + Math.pow((datos[i]-media()), 2);
        }
        return var /(numDatos-1);
    }    
        
    /**
    *@return regresa el valor de la desviacion estandar
    */
    public double desvStd(){
        return Math.sqrt(varianza());
    }  
        
    /**
    *@return regresa el valor del coeficiente de variacion
    */
    public double coefVar(){
        return desvStd() / media ();
    }
    
    /**
    *@return regresa la lista de los valoressin repetir
    */
    public int[] valorSinRepertir(){
        int[] datosTemp = datos;
        Arrays.sort(datosTemp);
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < datosTemp.length; i++){
            if(!lista.contains(datosTemp[i]))
                lista.add(datosTemp[i]);
        }
        return lista.stream().mapToInt(i->i).toArray();
    }
    
    /**
     * @param datVerif recibe un numero para contar sus repeticiones
     * @return devuelve el numero de repeticiones encontradas del parametro
    */ 
    public int repeticiones(int datVerif){
        int[] datosTemp = datos;
        int cont = 0;
        for (int i = 0; i < datosTemp.length; i++){
            if(datosTemp[i]==datVerif)
                cont++;
        }
        return cont;
    }    
    
    @Override
    public String toString(){
        String cad = "";
        for(int i = 0; i < numDatos; i++){
            cad = cad + datos[i] + " ";
        }
        return cad;
    }
}
