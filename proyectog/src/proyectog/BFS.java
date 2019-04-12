/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;



import java.util.LinkedList; 
import java.util.Queue; 
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;



import java.util.LinkedList; 
import java.util.Queue; 
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class BFS extends MatrizPrincipal {
    private MatrizPrincipal _grafoExplorado;
    private Queue<Integer> _capas = new LinkedList<>();
    boolean[] descubiertos;
    private int _raiz;
    private Grafo _arbolBFS = new Grafo();
    
    int[] array = new int[1];
    
    
    public BFS(MatrizPrincipal grafo, int s){
        _raiz = s;
        _grafoExplorado = grafo;        
        descubiertos = new boolean[grafo._numNodos];
        descubiertos[_raiz] = true;
        _arbolBFS.setNodos(_grafoExplorado._nodos);
        this._esDirigido = grafo._esDirigido;
        this._algoritmo=grafo._algoritmo;
    }
    
    @Override
    public Grafo ConstruyeGrafo(){
        this._arbolBFS._algoritmo = "BFS";
        Explorar();
        return _arbolBFS;
    }
    
    public void Explorar(){
        int contadorCapa = 0;
        _capas.add(_raiz);
        int origen = _raiz;
        while(!_capas.isEmpty()){
            origen = _capas.remove();            
            for(int destino = 0; destino < _grafoExplorado.getListaAdjacencia(origen).size(); destino++){
                int nodoDestino = _grafoExplorado.getListaAdjacencia(origen).get(destino);
                if(descubiertos[nodoDestino] == false ){
                    descubiertos[nodoDestino] = true;  
                    if(!_capas.contains(nodoDestino));
                        _capas.add(nodoDestino);
                    _arbolBFS._aristas.put(getNombreArista(origen, nodoDestino) , new Edge(_arbolBFS._nodos.get(origen), _arbolBFS._nodos.get(nodoDestino)));
                }                
            }
        }
    }
    
    public void ExploraBFS(){
        
    }
        
    
    
    @Override
    public String toString(){
        StringBuilder grafoString = new StringBuilder();
        String tipoGrafo = _esDirigido ? "digraph" : "graph";
        grafoString.append(tipoGrafo);
        grafoString.append(" arbolBFS_");
        grafoString.append(this._grafoExplorado._algoritmo);
        grafoString.append(" {\n");
        this._arbolBFS._aristas.forEach((key, value) -> {
            grafoString.append(key);
            grafoString.append(";\n");                
        });
        grafoString.append("}");
        System.out.println(grafoString.toString());        
        return grafoString.toString();
    }
    
    @Override
    public void EscribeArchivo(){        
        try{          
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream( this._algoritmo+ this._arbolBFS._algoritmo 
                        + "_" + this._arbolBFS._nodos.size() + ".gv"), "utf-8"));
            writer.write(this.toString());
            writer.close();
        }catch(Exception ex){
            System.out.println("Error al imprimir: " + ex.getMessage());
        }   
    }   
}