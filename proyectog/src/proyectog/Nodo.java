/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;

import java.util.HashMap;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author alber
 */
public class Nodo implements Comparable<Nodo> {
    private int _id;
    private String _nombre;
    public HashMap<String, String> _datos;
    public LinkedList<Nodo> path;
    public float distanciaMinima = Float.POSITIVE_INFINITY;
    public int nodoPrevio;

    public void setId(int _id) {
        this._id = _id;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }    

    public int getId() {
        return _id;
    }

    public String getNombre() {
        return _nombre;
    }
    
    public Nodo(){
        _id = 0;  
        _nombre = "";
        _datos =  new HashMap<String, String>();
    } 

    public Nodo(int _id, String _nombre, String clave, String valor) {
        this._id = _id;
        this._nombre = _nombre;
        _datos = new HashMap<String, String>();
        this._datos.put(clave, valor);
    }
    
    public Nodo(int _id, String _nombre) {
        this._id = _id;
        this._nombre = _nombre; 
        _datos = new HashMap<String, String>();
    }
    public void AgregarDato(String dato, Integer valor){
       
    }
    
    public int compareTo( Nodo otro){
        return Float.compare(distanciaMinima, otro.distanciaMinima);
    }
    
    public int compareTo(Nodo origen, Nodo otro){
        return Float.compare(origen.distanciaMinima, otro.distanciaMinima);
    }
    
}
