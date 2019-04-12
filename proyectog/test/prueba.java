/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import proyectog.ErdosRenyi;
import proyectog.Gilbert;
import proyectog.ModeloGeografico;
import proyectog.BarabasiAlbert;
import proyectog.BFS;
import proyectog.DFS;
/**
 *
 * @author alber
 */
public class prueba {
    public static void main(String[] args) {
        // TODO code application logic here
        try { 
            int[] nodos = new int[2];   
            nodos[0] = 5;
            nodos[1] = 20;

            for(int numNodos : nodos){
                CrearGrafos(numNodos);
            } 
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }        
    }
    
    public static void CrearGrafos(int numNodos){
       ModeloGeografico geo = new ModeloGeografico (numNodos, 0.65f, false, false);
        geo.ConstruyeGrafo();
        geo.AsignarCostos(1, 10);
        geo.EscribeArchivo();
        geo.Dijkstra( 0);
        
        int aristas;
        if(numNodos <= 10){
            aristas = (int)numNodos*(numNodos-1)/2;
        }else{
            aristas = (int)numNodos*(numNodos-1)/4;
        }
        
         
        ErdosRenyi erdos = new ErdosRenyi (numNodos, aristas, false, false);
        erdos.ConstruyeGrafo();
        erdos.AsignarCostos(1, 10);
        erdos.EscribeArchivo();
        erdos.Dijkstra( 0);
        
          
        
        Gilbert gil = new  Gilbert(numNodos, 0.6f, false, false);
        gil.ConstruyeGrafo();
        gil.AsignarCostos(1, 10);
        gil.EscribeArchivo();
        gil.Dijkstra( 0);
        
        
        BarabasiAlbert barabasi = new BarabasiAlbert(numNodos,(int)(numNodos/2),false, false);
        barabasi.ConstruyeGrafo();
        barabasi.AsignarCostos(1, 10);
        barabasi.EscribeArchivo();
        barabasi.Dijkstra(0);      
          
        
    }
    
}
        
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
     
        