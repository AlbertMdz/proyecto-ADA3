/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;

/**
 *
 * @author alber
 */
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class DFS extends MatrizPrincipal {
    private MatrizPrincipal _grafoExplorado;
    private Queue<Integer> _capas = new LinkedList<>();
    boolean[] descubiertos;
    private int _raiz;
    private Grafo _arbolDFS = new Grafo();
    
    public DFS(MatrizPrincipal grafo, int s){
        _raiz = s;
        _grafoExplorado = grafo;        
        descubiertos = new boolean[grafo._numNodos];
        descubiertos[_raiz] = true;
        _arbolDFS.setNodos(_grafoExplorado._nodos);
        this._esDirigido = grafo._esDirigido;
        this._algoritmo=grafo._algoritmo;
    }
    
    
    @Override
    public Grafo ConstruyeGrafo(){        
        this._grafoExplorado._algoritmo="DFSRecursivo";
        this._arbolDFS._aristas.clear();
        return this._arbolDFS;
    }
    
    public void DFSRecursivo(int origen){
        descubiertos[origen] = true;
        for (int destino = 0; destino < this._grafoExplorado.getListaAdjacencia(origen).size(); destino++ ){
            if(descubiertos[destino] == false ){
                this._arbolDFS._aristas.put(getNombreArista(origen, destino),
                   new Edge(_arbolDFS._nodos.get(origen), _arbolDFS._nodos.get(destino)));
                DFSRecursivo(destino);
            }
        }
    }

    public void DFSIterativo(int origen){
        this._arbolDFS._aristas.clear();
        this._grafoExplorado._algoritmo = "DFSIterativo";
        Vector<Boolean> visitados = new Vector<Boolean>(this._grafoExplorado._numNodos);
        for (int i = 0; i < this._grafoExplorado._numNodos; i++) 
                visitados.add(false); 
        Stack<Integer> pila = new Stack<>();
        
        pila.push(origen);
        
        while(!pila.empty()) 
        {            
            origen = pila.peek(); 
            pila.pop(); 
                  
            
            
            if(visitados.get(origen) == false) 
            { 
                //System.out.print(origen + " "); 
                visitados.set(origen, true); 
            } 
                  
             
            Iterator<Integer> itr = this._grafoExplorado.getListaAdjacencia(origen).iterator(); 
                  
            while (itr.hasNext())  
            { 
                int destino = itr.next(); 
                if(!visitados.get(destino)) {
                    //System.out.print(origen + " " + destino + "\n");
                    this._arbolDFS._aristas.put(getNombreArista(origen, destino),
                            new Edge(_arbolDFS._nodos.get(origen), 
                                    _arbolDFS._nodos.get(destino)));
                    
                    origen = destino;
                    pila.push(destino); 
                }
            }
            
                            
        }         
    }         
        
    
    public boolean NodoTieneHijosNoExplorados(int nodo){
        List<Integer> nodoDestino = _grafoExplorado.getListaAdjacencia(nodo);
        return false;
    }
    
    @Override
    public String toString(){
        StringBuilder grafoString = new StringBuilder();
        String tipoGrafo = _esDirigido ? "digraph" : "graph";
        grafoString.append(tipoGrafo);
        grafoString.append(" arbolDFS_");
        grafoString.append(this._grafoExplorado._algoritmo);
        grafoString.append("{\n");
        this._arbolDFS._aristas.forEach((key, value) -> {
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
                new FileOutputStream( 
                    this._algoritmo+ this._grafoExplorado._algoritmo 
                        + "_" + this._arbolDFS._nodos.size() + ".gv"), "utf-8"));
            writer.write(this.toString());
            writer.close();
        }catch(Exception ex){
            System.out.println("Error al imprimir: " + ex.getMessage());
        }   
    }

    private class Color{
    
    }    
}