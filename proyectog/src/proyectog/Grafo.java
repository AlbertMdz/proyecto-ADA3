/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
/**
 *
 * @author alber
 */
public class Grafo {
    protected HashMap<Integer,Nodo> _nodos;
    protected HashMap<String,Edge> _aristas;
    
    private Integer _indiceNodo;
    private Integer _indiceArista;
    public String _algoritmo;

    public Grafo(HashMap<Integer, Nodo> _nodos, HashMap<String, Edge> _aristas) {
        this._nodos = _nodos;
        this._aristas = _aristas;
        _indiceNodo = 0;
        _indiceArista = 0;
        _indiceArista = 0;
    }   
        
    public Grafo() {
        this._nodos = new HashMap<Integer,Nodo>();
        this._aristas = new HashMap<String,Edge>();
        _indiceNodo = 0;
        _indiceArista = 0;
    }

    public HashMap<Integer,Nodo> getNodos() {
        return _nodos;
    }

    public HashMap<String,Edge> getAristas() {
        return _aristas;
    }

    public void setNodos(HashMap<Integer,Nodo> _nodos) {
        this._nodos = _nodos;
    }

    public void setAristas(HashMap<String,Edge> _aristas) {
        this._aristas = _aristas;
    }
    
    public void CrearNodo(Nodo nodo){
        _nodos.put(_indiceNodo, nodo);
        _indiceNodo++;
    }
    
    public void CrearArista(Edge arista){
        _aristas.put(arista.getOrigen().toString() + "_" +arista.getDestino().toString() , arista);        
    }
    
    public void setAlgoritmo(String _algoritmo) {
        this._algoritmo = _algoritmo;
    }

    public String getAlgoritmo() {
        return _algoritmo;
    }
    
    public void Dijkstra(Grafo g, int nodoFuente){
           this._nodos.get(nodoFuente).distanciaMinima = 0;
           PriorityQueue<Nodo> queue = new PriorityQueue<Nodo>();
           queue.add(this._nodos.get(nodoFuente));
           
           while(!queue.isEmpty()){
               Nodo u = queue.poll();
               
               this._aristas.forEach((key, value) -> {
                   
                   float nuevaDistancia = u.distanciaMinima + value.GetPeso();
                   // busca el valor de la distancia minima en el peso del nodo
                   if(value.getDestino().distanciaMinima > nuevaDistancia){
                       //queue.remove(value.getDestino());
                       value.getDestino().distanciaMinima = nuevaDistancia;
                       value.getDestino().nodoPrevio = u.getId();
                       
                       /*
                       value.getDestino().path = new LinkedList<Nodo>();
                       value.getDestino().path.add(u);
                       */
                       queue.add(value.getDestino());
                   }                   
               });
           }
           
           this._nodos.forEach((key, value) -> {
               System.out.println("Origen -> " +   value.getNombre()  + 
                       " destino: " + value.distanciaMinima + 
                       " padre: " + value.nodoPrevio);
            });
           
    }
    
    public String toString(){// N_0 [label="N_0(0.0)"];
        StringBuilder grafoString = new StringBuilder();
        String tipoGrafo = "graph";
        grafoString.append(tipoGrafo);
        grafoString.append(" grafo {\n");
        this._nodos.forEach((key,value) -> {
            grafoString.append(value.getNombre());
            grafoString.append(" [label=");//
            grafoString.append(value.getNombre());
            grafoString.append("(");
            grafoString.append(value.distanciaMinima);
            grafoString.append(")];\n");
        });
        this._aristas.forEach((key, value) -> {
            
            grafoString.append(key);
            /*grafoString.append(" [label=(");
            grafoString.append(value.getDestino().distanciaMinima);
            grafoString.append(") ]");*/
            grafoString.append(";\n");                
        });
        grafoString.append("}");
        System.out.println(grafoString.toString());        
        return grafoString.toString();
    }
}
