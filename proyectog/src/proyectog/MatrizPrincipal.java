/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;
import java.util.HashMap;
import java.util.Random;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
/**
 *
 * @author alber
 */
public abstract class MatrizPrincipal {
    public HashMap<Integer,Nodo> _nodos;
    public HashMap<String,Edge> _aristas;
    protected int _paresDeAristas;
    protected int _numNodos;
    protected boolean _esDirigido;
    protected boolean _aceptaCiclos;
    protected float _probability;
    protected String _algoritmo;
    private int[][] _matrizAdyacencia;
    public List<List<Integer>> _listaAdjacencia;
    private PriorityQueue<Nodo> pq;
    private Set<Integer> encontrados;
    private float distancias[];
    
    public abstract Grafo ConstruyeGrafo();
    
    protected HashMap<Integer,Nodo> GeneraNodos(int numNodos){
        _nodos = new HashMap<Integer,Nodo>();
        Nodo n = null;
        try{
            for(int i = 0; i < numNodos; i++){            
                n = new Nodo(i, "N_" + i);            
                _nodos.put(i, n);
            }
        }
        catch(Exception ex){
            System.out.println("GeneraNodosError: " + ex.getMessage());
        }
        return _nodos;
    }
    
    protected HashMap<String,Edge> GeneraAristas(){
        _aristas = new HashMap<String,Edge>();
        int cantidadNodos = _nodos.size();
        int aristasCreadas = 0;
        while(aristasCreadas < this._paresDeAristas){
            for(int nodoOrigen = 0; nodoOrigen < cantidadNodos; nodoOrigen++){
                _listaAdjacencia.add(new ArrayList<Integer>());
                int nodoDestino = 0;
                while(aristasCreadas < this._paresDeAristas && nodoDestino < cantidadNodos){
                    if( nodoOrigen != nodoDestino ||this._aceptaCiclos){
                        if(SeCreaArista() && !ExisteArista(nodoOrigen, nodoDestino)){                             
                            this._aristas.put(getNombreArista(nodoOrigen, nodoDestino) , new Edge(this._nodos.get(nodoOrigen), this._nodos.get(nodoDestino)));
                            _matrizAdyacencia[nodoOrigen][nodoDestino] = 1;
                            _listaAdjacencia.get(nodoOrigen).add(nodoDestino);
                            aristasCreadas++;
                            if (aristasCreadas == this._paresDeAristas){
                                break;
                            }
                            }
                        }
                        }
                nodoDestino++;
                    }
                }
    
        
        DuplicaAristas();
        return this._aristas;
    }
    
    public void DuplicaAristas( ){
        HashMap<String,Edge> duplicador = new HashMap<String, Edge>();
        
        Iterator duplicada = this._aristas.entrySet().iterator();
        while(duplicada.hasNext()){
            
            Map.Entry par = (Map.Entry<String,Edge>)duplicada.next();
            Edge arista = (Edge)par.getValue();
            int origen = arista.getOrigen().getId();
            int destino = arista.getDestino().getId();
            //Nodo nOrigen = this._nodos.get(arista.getDestino().getId());
            //Nodo nDestino = this._nodos.get(arista.getOrigen().getId());
             duplicador.put(getNombreArista(destino, origen) , 
                     new Edge(this._nodos.get(arista.getDestino().getId()), this._nodos.get(arista.getOrigen().getId())));
                            _matrizAdyacencia[destino][origen] = 1;
                            _listaAdjacencia.get(destino).add(origen);
        }
        this._aristas.putAll(duplicador);
    }
    
    public boolean ExisteArista(int origen, int destino){        
         return this._aristas.containsKey(getNombreArista(origen,destino)) 
                 || this._aristas.containsKey(getNombreArista(destino,origen));
    }
    
    protected String getNombreArista(int origen, int destino){
        String union = this._esDirigido ? "->" : "--";
        return "N_"+origen +" " + union +" N_"+destino;
    }
    
    protected boolean SeCreaArista(){
        boolean seCrea = false;
        Random rand = new Random();        
        float randVal = rand.nextFloat();
        if(randVal <= this._probability){
            seCrea = true;
        }
        return seCrea;       
    }  
    
    protected void ObtenAristasMaximas(int vertices){
        _paresDeAristas = (vertices)*(vertices - 1) / 2;
    }
    
    public void Dijkstra( int nodoFuente){
           this._nodos.get(nodoFuente).distanciaMinima = 0;
           this._nodos.get(nodoFuente).nodoPrevio = 0;
           
           PriorityQueue<Nodo> queue = new PriorityQueue<Nodo>();
           queue.add(this._nodos.get(nodoFuente));         
           List<Integer> explorados = new ArrayList<Integer>();
           
               
           while(!queue.isEmpty()){
               Nodo u = queue.poll();
               explorados.add(u.getId());
               Iterator it = this._listaAdjacencia.get(u.getId()).iterator();
               while(it.hasNext()){
                   int end = (int)it.next();
                   String arista = u.getNombre() + " -- " +  this._nodos.get(end).getNombre();
                   float nuevaDistancia = this._nodos.get(u.getId()).distanciaMinima 
                           + this._aristas.get(arista).GetPeso();
                   if(this._nodos.get(end).distanciaMinima>nuevaDistancia){
                       queue.remove(this._nodos.get(end));
                       this._nodos.get(end).nodoPrevio = u.getId();
                       this._nodos.get(end).distanciaMinima = nuevaDistancia;
                       if(!explorados.contains(end)){
                            queue.add(this._nodos.get(end));
                       }
                   }                   
               }
        }
           this._nodos.forEach((key, value) -> {
               System.out.println("Origen -> " +   value.getNombre()  + 
                       ": " + value.distanciaMinima + 
                       " padre: " + value.nodoPrevio);
                }); 
           
           Grafo dijkstra = new Grafo();
           dijkstra._algoritmo = this._algoritmo + "_Dijkstra";
           dijkstra._nodos = this._nodos;
           //System.out.println("Nodos en Djks: " + dijkstra._nodos.size());

           dijkstra._aristas = GeneraAristasDijkstra(dijkstra);
           //System.out.println("Nodos en Djks: " + dijkstra._nodos.size());

           EscribeArchivoDijkstra(dijkstra);
           
           
           
    }
    
    private HashMap<String,Edge> GeneraAristasDijkstra(Grafo g){
        //System.out.println("Nodos en Djks_: " + g._nodos.size());
        Iterator nodos = g._nodos.entrySet().iterator();
        while(nodos.hasNext()){
            Map.Entry pair = (Map.Entry<Integer,Nodo>)nodos.next();
            Nodo destino = (Nodo)pair.getValue();
            if(destino.nodoPrevio == destino.getId()) continue;
            g._aristas.put(getNombreArista(destino.nodoPrevio, destino.getId()) , 
                    new Edge(this._nodos.get(destino.nodoPrevio), this._nodos.get(destino.getId())));
            //System.out.println("Nodos en Djks=: " + g._nodos.size());
            //nodos.remove();
            //System.out.println("Nodos en Djks: " + g._nodos.size());
        }
        
        return g._aristas;
    }  
    
    
    // Asigna el peso de forma aleatoria a cada arista en el rango min-max
    public void AsignarCostos(float min, float max){
        Random rand = new Random();
        float rango = max -min;        
        
        Iterator it = this._aristas.entrySet().iterator();
        
        while(it.hasNext()){
            try{
            Map.Entry par = (Map.Entry<String, Edge>)it.next();
            Edge arista = (Edge)par.getValue();
            
            String aristaDup = arista.getDestino().getNombre() +" -- " +arista.getOrigen().getNombre();
            if (this._aristas.get(aristaDup).GetPeso() > 0){
                arista.SetPeso(this._aristas.get(aristaDup).GetPeso());
            }else{
                float peso = min + rand.nextFloat()* rango;
                arista.SetPeso(peso);
            } 
            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }            
        }   
    }
    
    public List<Integer> getListaAdjacencia(int origen) {
        return _listaAdjacencia.get(origen);
    }

    public int getMatrizAdyacencia(int origen, int destino) {
        return _matrizAdyacencia[origen][destino];
    }
    
    @Override
    public String toString(){
        StringBuilder grafoString = new StringBuilder();
        String tipoGrafo = _esDirigido ? "digraph" : "graph";
        grafoString.append(tipoGrafo);
        grafoString.append(" grafo {\n");
        this._aristas.forEach((key, value) -> {
            grafoString.append(key);
            grafoString.append(" [label=");
            grafoString.append(value.GetPeso());
            grafoString.append(" ]");
            grafoString.append(";\n");                
        });
        grafoString.append("}");
        System.out.println(grafoString.toString());        
        return grafoString.toString();
    }
    
    public void EscribeArchivo(){        
        try{          
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream( "ArchivosDijkstra/"+ this._algoritmo + "_" + this._numNodos + ".gv"), "utf-8"));
            writer.write(this.toString());
            writer.close();
        }catch(Exception ex){
            System.out.println("Error al imprimir: " + ex.getMessage());
        }
    }  
        public void EscribeArchivoDijkstra(Grafo g){  
        System.out.println("Nodos en Djks: " + g._nodos.size());
        try{          
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream("ArchivosDijkstra/"+ g._algoritmo + "_" + g._nodos.size() + ".gv"), "utf-8"));
            writer.write(g.toString());
            writer.close();
        }catch(Exception ex){
            System.out.println("Error al imprimir: " + ex.getMessage());
        }   
    }
    
 }