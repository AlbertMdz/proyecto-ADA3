/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;
import java.util.HashMap;
import java.util.Random;
/**
 *
 * @author alber
 */
public class Gilbert extends MatrizPrincipal {
    @Override
    public  Grafo ConstruyeGrafo(){
        this.GeneraNodos(this._numNodos);
        this.GeneraAristas();
        
        return new Grafo(this._nodos, this._aristas);
    }
        
    public Gilbert(int n, float p, boolean esDirigido , boolean aceptaCiclos ){
        this._numNodos = n;
        this._probability = p;  
        this.ObtenAristasMaximas(_numNodos);       
        if (p < 0 || p > 1 ) {throw new IllegalArgumentException("la probabilidad debe ser un valor entre cero y uno"); }              
        this._esDirigido = esDirigido;
        this._aceptaCiclos = aceptaCiclos;  
        this._algoritmo = "Gilbert";
    }
    
    public Gilbert(Grafo g){
        this._nodos = g._nodos;
        this._aristas = g._aristas;
        this._numNodos = g._nodos.size();
        this._esDirigido = false;
        this._aceptaCiclos = false;
        this._algoritmo = "Gilbert";
    }
    
       
    private int ObtenNumAristas(){
        
        Random rand = new Random();        
        return rand.nextInt(this._paresDeAristas);        
    }
    
    @Override
    protected HashMap<String,Edge> GeneraAristas(){
        _aristas = new HashMap<String,Edge>();
        int cantidadNodos = _nodos.size();
        int aristasCreadas = 0;

            for(int nodoOrigen = 0; nodoOrigen < cantidadNodos; nodoOrigen++){
                for(int nodoDestino = 0; nodoDestino < cantidadNodos; nodoDestino++){
                    if (aristasCreadas == this._paresDeAristas) break;
                    if( nodoOrigen != nodoDestino ||this._aceptaCiclos){
                        if(super.SeCreaArista() && ! super.ExisteArista(nodoOrigen, nodoDestino)){                             
                            this._aristas.put(getNombreArista(nodoOrigen, nodoDestino) , new Edge(this._nodos.get(nodoOrigen), this._nodos.get(nodoDestino)));
                            aristasCreadas++;
                            
                        }
                    }
                }
            }
         DuplicaAristas();
        return this._aristas;
    }    
}
