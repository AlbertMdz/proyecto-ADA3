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
public class ErdosRenyi extends MatrizPrincipal {
    @Override
    public  Grafo ConstruyeGrafo(){
        
        this.GeneraNodos(this._numNodos);
        this.GeneraAristas();        
        return new Grafo(this._nodos, this._aristas);
    }
    
    public ErdosRenyi(int n, int m, boolean esDirigido , boolean aceptaCiclos ){
        this._numNodos = n;  
        this.ObtenAristasMaximas(_numNodos);
        if (m > this._paresDeAristas) {
            throw new IllegalArgumentException("El número de aristas debe ser menor que: " + this._paresDeAristas);
        }        
        this._paresDeAristas = m;  
        this._probability = 0.5f;
        this._esDirigido = esDirigido;
        this._aceptaCiclos = aceptaCiclos;
        this._algoritmo = "ErdosRenyi";
    }
    
    private int CrearVertices(int n){
        return n;
    }
    
    private int CrearNVerticesAleatorios(int valor){
        return (int)(Math.random() * valor);
    }    
}
