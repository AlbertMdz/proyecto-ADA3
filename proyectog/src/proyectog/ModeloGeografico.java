/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alber
 */
public class ModeloGeografico extends MatrizPrincipal{
    private float _radio;
    private int _aristasMaximas;
    
    @Override 
    public Grafo ConstruyeGrafo(){
        this.ObtenAristasMaximas(_numNodos);
        this.GeneraNodos(this._numNodos);
        this.GeneraAristas();  
        return new Grafo(this._nodos, this._aristas);
    }

    @Override
    protected HashMap<Integer,Nodo> GeneraNodos(int numNodos){
        _nodos = new HashMap<Integer,Nodo>();
        Nodo n = null;
        try{
            for(int i = 0; i < numNodos; i++){            
                n = new Nodo(i, "N_" + i);    
                n._datos.put("pos", Float.toString(GetCoordenada()) +","+ Float.toString(GetCoordenada()));
                _nodos.put(i, n);
            }
        }
        catch(Exception ex){
            System.out.println("GeneraNodosError: " + ex.getMessage());
        }
        _matrizAdyacencia = new int[this._numNodos][this._numNodos];
        _listaAdjacencia = new ArrayList<List<Integer>>();       
        return _nodos;
    }
 
    
    @Override
    protected HashMap<String,Edge> GeneraAristas(){
        _aristas = new HashMap<String,Edge>();
        int cantidadNodos = _nodos.size();
        int aristasCreadas = 0;
        
        for(int nodoOrigen = 0; nodoOrigen < cantidadNodos; nodoOrigen++){
            _listaAdjacencia.add(new ArrayList<Integer>());
            for(int nodoDestino = 0; nodoDestino < cantidadNodos; nodoDestino++){
                if (aristasCreadas == this._paresDeAristas)     break;
                if( nodoOrigen != nodoDestino ||this._aceptaCiclos){
                   if(SeCreaArista(nodoOrigen, nodoDestino) && !ExisteArista(nodoOrigen, nodoDestino)){                             
                        this._aristas.put(super.getNombreArista(nodoOrigen, nodoDestino), 
                                new Edge(this._nodos.get(nodoOrigen), this._nodos.get(nodoDestino)));
                        _matrizAdyacencia[nodoOrigen][nodoDestino] = 1;
                        _listaAdjacencia.get(nodoOrigen).add(nodoDestino);
                        aristasCreadas++;
                        
                    }
                }
            }
        }
        DuplicaAristas();
        return this._aristas;
    }
    
    
    private boolean SeCreaArista(int indiceOrigen, int indiceDestino){
        Nodo org = this._nodos.get(indiceOrigen);
        Nodo dst = this._nodos.get(indiceDestino);
        float distancia = CalculaDistancia(org, dst);
        return distancia <= this._radio  ? true: false;
    }

    private float CalculaDistancia(Nodo origen, Nodo destino){
        String coordsOrigen  = (String)origen._datos.get("pos");
        String coordsDestino = (String)destino._datos.get("pos");
        Coords org = new Coords(coordsOrigen);
        Coords dst = new Coords(coordsDestino);        
        float distancia =(float) Math.sqrt(Math.pow(dst.getX() - org.getX(), 2) 
        + Math.pow(dst.getY() - org.getY(), 2));
        return distancia;
    }   
    
    public ModeloGeografico(int n, float r, boolean d, boolean a){
        this._radio = r;
        this._numNodos = n;
        this._esDirigido = d;
        this._aceptaCiclos = a;
        this._algoritmo = "Geografico";
        GeneraNodos(n);
        GeneraAristas();
    }
    
    public float GetCoordenada(){
        Random rand = new Random(); 
        return rand.nextFloat();
    }

    @Override
    public String toString(){
        StringBuilder grafoString = new StringBuilder();
        String tipoGrafo = _esDirigido ? "digraph" : "graph";
        grafoString.append(tipoGrafo);
        grafoString.append(" grafo {\n");
        
        this._nodos.forEach((key, value) ->{
            grafoString.append(value.getNombre());
            grafoString.append(" [");
            grafoString.append("pos=");
            grafoString.append('"' +value._datos.get("pos") +'"');
            grafoString.append("];\n");
            
        });

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

    private class Coords{

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }
        
        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
        private float x;
        private float y;    
        
        public Coords(String coords){
            String[] coordArray = coords.split(",");
            this.x = Float.parseFloat(coordArray[0]);
            this.y = Float.parseFloat(coordArray[1]);
        }
    }
}
