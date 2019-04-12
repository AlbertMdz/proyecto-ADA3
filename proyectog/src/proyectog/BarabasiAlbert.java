/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;
import java.util.HashMap;
/**
 *
 * @author alber
 */
public class BarabasiAlbert extends MatrizPrincipal {
    private int _conexionesPorNodo;
    @Override
    public Grafo ConstruyeGrafo(){
        
        for (int nodo = 0; nodo < this._numNodos; nodo++){
            ConstruyeNodo(nodo);
        }
        DuplicaAristas();
        return new Grafo(this._nodos, this._aristas);
    }
    
    public BarabasiAlbert(int n, int d, boolean esDirigido, boolean aceptaCiclos){
        this._numNodos = n;
        this._paresDeAristas = n * d;
        if (d == 0) throw new IllegalArgumentException(" d debe ser mayor a cero");
        this._conexionesPorNodo = d;
        this._esDirigido = esDirigido;
        this._aceptaCiclos = aceptaCiclos;  
        this._algoritmo = "BarabasiAlberert";
        _nodos = new HashMap<Integer,Nodo>();
        _aristas = new HashMap<String,Edge>();
    }
    
    public void ConstruyeNodo(int indiceNodo){
        Nodo n = new Nodo(indiceNodo, "N_" + indiceNodo);            
        _nodos.put(indiceNodo, n);
        ConstruyeAristas(indiceNodo);
    }
    
    public void ConstruyeAristas(int nodoOrigen){
        int cantNodo = this._nodos.size();
        if (nodoOrigen == 0) return;
        int grado = 0;
        for(int nodoDestino = nodoOrigen - 1; grado < this._conexionesPorNodo  && nodoDestino >= 0; nodoDestino--){
            // Aqui va se crea arista
            if(DebeConectarse(grado)){
            this._aristas.put(getNombreArista(nodoOrigen, nodoDestino), 
                    new Edge(this._nodos.get(nodoOrigen), this._nodos.get(nodoDestino)));
            grado++;
            }
            
        }        
    }
    
    private boolean DebeConectarse(int gradoNodo){
        this._probability = CalculaProbablidad(gradoNodo);        
        return SeCreaArista();
    }
    
    private float CalculaProbablidad(int gradoNodo){        
        return 1 -(gradoNodo/this._conexionesPorNodo );
    }    
}
