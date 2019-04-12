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
public class Edge {
    private Nodo _origen;
    private Nodo _destino;
    private HashMap<String, Integer> _datos;
    private float peso;
    
    public float GetPeso(){
        return peso;
    }
    
    public void SetPeso(float peso){
        this.peso = peso;
    }
    
    public Edge() {
        this._origen = new Nodo();
        this._destino = new Nodo();
        this._datos = new HashMap<String, Integer>();
    }

    public Edge(Nodo origen, Nodo destino, HashMap datos) {
        this._origen = origen;
        this._destino = destino;
        this._datos = datos;
    }
    
    public Edge(Nodo origen, Nodo destino) {
        this._origen = origen;
        this._destino = destino;
    }
    public Edge(Nodo origen, Nodo destino, float peso) {
        this._origen = origen;
        this._destino = destino;
        this.peso = peso;
    }
    

    public Nodo getOrigen() {
        return _origen;
    }

    public Nodo getDestino() {
        return _destino;
    }

    public HashMap getDatos() {
        return _datos;
    }

    public void setOrigen(Nodo _origen) {
        this._origen = _origen;
    }

    public void setDestino(Nodo _destino) {
        this._destino = _destino;
    }

    public void setDatos(HashMap _datos) {
        this._datos = _datos;
    }
    
    public void AgregarDato(String dato, Integer valor){
        this._datos.put(dato, valor);
    }    
    
}
