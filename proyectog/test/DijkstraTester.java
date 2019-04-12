
import proyectog.ErdosRenyi;
import proyectog.Gilbert;
import proyectog.ModeloGeografico;
import proyectog.BarabasiAlbert;
import proyectog.BFS;
import proyectog.DFS;
import proyectog.Edge;
import proyectog.Grafo;
import proyectog.Nodo;
import proyectog.Gilbert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rike
 */
public class DijkstraTester {
    static Gilbert gil;
    static    Nodo cero = new Nodo(0, "N_0");
    static    Nodo uno = new Nodo(1, "N_1");
    static    Nodo dos = new Nodo(2, "N_2");
    static   Nodo tres = new Nodo(3, "N_3");
    static    Nodo cuatro = new Nodo(4, "N_4");
    static    Nodo cinco = new Nodo(5, "N_5");
    static    Nodo seis = new Nodo(6, "N_6");
    static    Nodo siete = new Nodo(7, "N_7");
    static    Nodo ocho = new Nodo(8, "N_8");
    public static void main(String[] args) {
        Grafo grafo = new Grafo();        
        
        HashMap<Integer,Nodo> nodos = new HashMap<Integer,Nodo>();
        HashMap<String,Edge> aristas = new HashMap<String,Edge>();
        
        // this._aristas.put(getNombreArista(nodoOrigen, nodoDestino) , new Arista(this._nodos.get(nodoOrigen), 
        // this._nodos.get(nodoDestino)));

        //Arista ceroUno = new Arista(cero.getNombre() +"--" + uno.getNombre(), new Arista( cero, uno));
        Edge ceroSiete= new Edge(cero, siete, 8);
        Edge sieteCero= new Edge(siete, cero, 8);
        
        Edge ceroUno = new Edge(cero, uno, 4);
        Edge unoCero = new Edge(uno, cero, 4);
        
        Edge unoDos =  new Edge(uno,dos, 8);
        Edge dosUno =  new Edge(dos, uno, 8);
        
        Edge unoSiete = new Edge(uno, siete, 11);
        Edge sieteUno = new Edge(siete, uno, 11);
        
        Edge dosTres = new Edge(dos, tres, 7);
        Edge tresDos = new Edge(tres, dos, 7);
        
        
        Edge dosOcho= new Edge(dos, ocho, 2);
        Edge ochoDos= new Edge(ocho, dos, 2);
        
        Edge dosCinco= new Edge(dos, cinco, 4);
        Edge cincoDos= new Edge(cinco, dos, 4);
        
        Edge tresCuatro = new Edge(tres, cuatro, 9);
        Edge cuatroTres = new Edge(cuatro, tres, 9);
        
        Edge tresCinco= new Edge(tres, cinco, 14);
        Edge cincoTres= new Edge(cinco, tres, 14);
        
        Edge cuatroCinco= new Edge(cuatro, cinco, 10);
        Edge cincoCuatro= new Edge(cinco, cuatro, 10);
        
        Edge cincoSeis= new Edge(cinco, seis, 2);
        Edge seisCinco= new Edge(seis, cinco, 2);
        
        Edge seisSiete= new Edge(seis, siete, 1);
        Edge sieteSeis= new Edge(siete, seis, 1);
        
        Edge seisOcho= new Edge(seis, ocho, 6);  
        Edge ochoSeis = new Edge(ocho, seis, 6);  
        
        Edge sieteOcho= new Edge(siete, ocho, 7);
        Edge ochoSiete= new Edge(ocho, siete, 7);
        
        nodos.put(0, cero);
        nodos.put(1, uno);
        nodos.put(2, dos);
        nodos.put(3, tres);
        nodos.put(4, cuatro);
        nodos.put(5, cinco);
        nodos.put(6, seis);
        nodos.put(7, siete);
        nodos.put(8, ocho);

        aristas.put(cero.getNombre() +"--"+ siete.getNombre(), ceroSiete);
        aristas.put(siete.getNombre() +"--"+ cero.getNombre(), sieteCero);
        
        aristas.put(cero.getNombre() +"--"+ uno.getNombre(), ceroUno);
        aristas.put(uno.getNombre() +"--"+ cero.getNombre(), unoCero);
        
        aristas.put(uno.getNombre() +"--"+ dos.getNombre(), unoDos);
        aristas.put(dos.getNombre() +"--"+ uno.getNombre(), dosUno);
        
        aristas.put(uno.getNombre() +"--"+ siete.getNombre(), unoSiete);
        aristas.put(siete.getNombre() +"--"+ uno.getNombre(), sieteUno);
        
        aristas.put(dos.getNombre() +"--"+ tres.getNombre(), dosTres);
        aristas.put(tres.getNombre() +"--"+ dos.getNombre(), tresDos);
        
        aristas.put(dos.getNombre() +"--"+ ocho.getNombre(), dosOcho);
        aristas.put(ocho.getNombre() +"--"+ dos.getNombre(), ochoDos);
        
        aristas.put(dos.getNombre() +"--"+ cinco.getNombre(), dosCinco);
        aristas.put(cinco.getNombre() +"--"+ dos.getNombre(), cincoDos);
        
        aristas.put(tres.getNombre() +"--"+ cuatro.getNombre(), tresCuatro);
        aristas.put(cuatro.getNombre() +"--"+ tres.getNombre(), cuatroTres);
        
        aristas.put(tres.getNombre() +"--"+ cinco.getNombre(), tresCinco);
        aristas.put(cinco.getNombre() +"--"+ tres.getNombre(), cincoTres);
        
        
        aristas.put(cuatro.getNombre() +"--"+ cinco.getNombre(), cuatroCinco);
        aristas.put(cinco.getNombre() +"--"+ cuatro.getNombre(), cincoCuatro);
        
        aristas.put(cinco.getNombre() +"--"+ seis.getNombre(), cincoSeis);
        aristas.put(seis.getNombre() +"--"+ cinco.getNombre(), seisCinco);
        
        aristas.put(seis.getNombre() +"--"+ siete.getNombre(), seisSiete);
        aristas.put(siete.getNombre() +"--"+ seis.getNombre(), sieteSeis);
        
        aristas.put(seis.getNombre() +"--"+ ocho.getNombre(), seisOcho);
        aristas.put(ocho.getNombre() +"--"+ seis.getNombre(), ochoSeis);
        
        aristas.put(siete.getNombre() +"--"+ ocho.getNombre(), sieteOcho);
        aristas.put(ocho.getNombre() +"--"+ siete.getNombre(), ochoSiete);
        
        
        grafo.setNodos(nodos);
        grafo.setAristas(aristas);
        
        
        gil = new Gilbert(grafo);
        CreaListaAdj();
        
        gil.EscribeArchivo();        
        gil.Dijkstra( 0);
        
        
    }
    
    static void CreaListaAdj(){
        gil._listaAdjacencia = new ArrayList<List<Integer>>();  
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        
        //gil._listaAdjacencia.get(cero.getId()).add(cero.getId());
        gil._listaAdjacencia.get(cero.getId()).add(uno.getId());
        gil._listaAdjacencia.get(cero.getId()).add(siete.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        gil._listaAdjacencia.get(uno.getId()).add(cero.getId());
        gil._listaAdjacencia.get(uno.getId()).add(dos.getId());
        gil._listaAdjacencia.get(uno.getId()).add(siete.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        gil._listaAdjacencia.get(dos.getId()).add(uno.getId());
        gil._listaAdjacencia.get(dos.getId()).add(tres.getId());
        gil._listaAdjacencia.get(dos.getId()).add(cinco.getId());
        gil._listaAdjacencia.get(dos.getId()).add(ocho.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        gil._listaAdjacencia.get(tres.getId()).add(dos.getId());
        gil._listaAdjacencia.get(tres.getId()).add(cuatro.getId());
        gil._listaAdjacencia.get(tres.getId()).add(cinco.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        gil._listaAdjacencia.get(cuatro.getId()).add(tres.getId());
        gil._listaAdjacencia.get(cuatro.getId()).add(cinco.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());        
        gil._listaAdjacencia.get(cinco.getId()).add(dos.getId());
        gil._listaAdjacencia.get(cinco.getId()).add(tres.getId());
        gil._listaAdjacencia.get(cinco.getId()).add(cuatro.getId());
        gil._listaAdjacencia.get(cinco.getId()).add(seis.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        gil._listaAdjacencia.get(seis.getId()).add(cinco.getId());
        gil._listaAdjacencia.get(seis.getId()).add(siete.getId());
        gil._listaAdjacencia.get(seis.getId()).add(ocho.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        gil._listaAdjacencia.get(siete.getId()).add(cero.getId());
        gil._listaAdjacencia.get(siete.getId()).add(uno.getId());
        gil._listaAdjacencia.get(siete.getId()).add(seis.getId());
        gil._listaAdjacencia.get(siete.getId()).add(ocho.getId());
        
        gil._listaAdjacencia.add(new ArrayList<Integer>());
        gil._listaAdjacencia.get(ocho.getId()).add(dos.getId());
        gil._listaAdjacencia.get(ocho.getId()).add(seis.getId());
        gil._listaAdjacencia.get(ocho.getId()).add(siete.getId());
    }
}
