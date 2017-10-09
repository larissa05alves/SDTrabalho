package models;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

public class Handler implements funcoes.Iface {
	  //private ArrayList<Graph> Graphs = new ArrayList<Graph>();
    private Grafo G = new Grafo(new ArrayList<Vertice>(),new ArrayList<Aresta>());
    
    public boolean Adiciona_Vertice(Vertice vertice){
    	synchronized(G.getVertices()) {
    		if (existeVertice(vertice.nome))
    			return false;
    		else
    		{
    			this.G.vertices.add(vertice);	
    		}	
    	}
    	return true;
	}
    
	
    public Vertice getVertice(int nome){
    	
    	synchronized(G.getVertices()) {
    		for (int i = 0; i < G.vertices.size(); i++){
    			Vertice obj = (Vertice) G.vertices.get(i);
    			if (obj.nome == nome)
    				return obj;
    		}
    	}
		
		return null;	
	}
	
    public  Aresta getAresta(String descricao){	
    	synchronized(G.getArestas()) {
    		for (int i = 0; i < G.arestas.size(); i++){
    			Aresta obj = (Aresta) G.arestas.get(i);
    			if (obj.descricao.equals(descricao))
    				return obj;
    		}	
    	}

		return null;	
	}
	
    public  Aresta existeAresta(int nomeVertice1, int nomeVertice2){
    	synchronized(G.getArestas()) {
	    	for (int i = 0; i < G.arestas.size(); i++){
				Aresta obj = (Aresta) G.arestas.get(i);
				
					if(nomeVertice1 == obj.nome_vertice1 && nomeVertice2 == obj.nome_vertice2)
						return obj;
				
			}
    	}
		return null;
		
	}

    public boolean Adiciona_Aresta(Aresta aresta){
    	synchronized(G.getVertices())
    	{
			if (existeVertice(aresta.nome_vertice1) & existeVertice(aresta.nome_vertice2)){
				Aresta verifica = existeAresta(aresta.nome_vertice1,aresta.nome_vertice2);
				
				if(verifica == null)
				{
					synchronized(G.getArestas()) {
						G.arestas.add(aresta);
						return true;
					}
				}		
			}
    	}
		return false;		
	}
	
    public boolean existeVertice(int nomeVertice){
    	synchronized(G.getVertices()) {
    		for (int i = 0; i < G.vertices.size(); i++){
    			Vertice obj = (Vertice) G.vertices.get(i);
    			if (obj.nome == nomeVertice)
    				return true;
    		}
    	}

		return false;
	}
    
    public  boolean updateVertice(int nome, int cor, String descricao, double peso){
    	
    	for (int i = 0; i < G.vertices.size(); i++){
			Vertice obj = (Vertice) G.vertices.get(i);
			synchronized(obj) {
				if (obj.nome == nome)
				{
					
					obj.cor = cor;
					obj.descricao = descricao;
					obj.peso = peso;
					G.vertices.set(i, obj);
					return true;
				}
			}
		}
		
		return false;
	}
    
    public boolean updateAresta(String descricao, int nome_vertice1,int nome_vertice2,double peso,boolean bidirecional){
		for (int i = 0; i < G.arestas.size(); i++){
			Aresta obj = (Aresta) G.arestas.get(i);
			synchronized(obj)
			{
				if (obj.nome_vertice1 == nome_vertice1 & obj.nome_vertice1 == nome_vertice2)
				{
					obj.descricao = descricao;
					obj.peso = peso;
					obj.bidirecional = bidirecional;
					G.arestas.set(i, obj);
					return true;
				}
			}	
		}
		return false;
	}

    public boolean deleteVertice(int nomeVertice){
		for (int i = 0; i < G.vertices.size(); i++){
			
			Vertice obj = (Vertice) G.vertices.get(i);
			synchronized(obj)
			{
				if (obj.nome == nomeVertice)
				{
					G.vertices.remove(i);
					synchronized(G.getArestas())
					{
						for(int j = 0; j < G.arestas.size(); j++){
							Aresta obj2 = (Aresta) G.arestas.get(j);
							if (obj2.nome_vertice1 == nomeVertice || obj2.nome_vertice2 == nomeVertice)
								G.arestas.remove(j);		
						}
						return true;
					}
				}	
			}
		}
		
		return false;
	}
	
    public  boolean deleteAresta(int nomeVertice1, int nomeVertice2){
		for (int i = 0; i < G.arestas.size(); i++){
			Aresta obj = (Aresta) G.arestas.get(i);
			synchronized(obj)
			{
				if(obj.nome_vertice1 == nomeVertice1 && obj.nome_vertice2 == nomeVertice2)
				{
					G.arestas.remove(i);
					return true;
				}
			}
		}
		return false;	
	}
	
    public Vertice readVertice(int nomeVertice)
	{
		for (int i = 0; i < G.vertices.size(); i++){
			Vertice obj = (Vertice) G.vertices.get(i);
			synchronized(obj)
			{
				if(obj.nome == nomeVertice)
					return obj;
			}
		}
		return null;
	}

    public Aresta readArestas(int nomeVertice1, int nomeVertice2)
	{
		for (int i = 0; i < G.arestas.size(); i++){
			Aresta obj = (Aresta) G.arestas.get(i);
			synchronized(obj)
			{
				if(obj.nome_vertice1 == nomeVertice1 && obj.nome_vertice2 == nomeVertice2)
					return obj;
			}
		}
		return null;
	}
	
    public  ArrayList<Vertice> listaVerticedaAresta(Aresta aresta){
		ArrayList<Vertice> listaVertice = new ArrayList<Vertice>();
		
		for (int i = 0; i < G.vertices.size(); i++){
			Vertice obj = (Vertice) G.vertices.get(i);
			if(aresta.nome_vertice1 == obj.nome || aresta.nome_vertice2 == obj.nome)
				listaVertice.add(obj);
		}
		return listaVertice;
	}
	
    public  ArrayList<Aresta> listaArestasdoVertice(Vertice vertice){
		ArrayList<Aresta> listaArestas = new ArrayList<Aresta>();
		synchronized(G.getArestas())
		{
			for (int i = 0; i < G.arestas.size(); i++){
				Aresta obj = (Aresta) G.arestas.get(i);
				if(vertice.nome == obj.nome_vertice1 || vertice.nome == obj.nome_vertice2)
					listaArestas.add(obj);
			}
		}
		
		return listaArestas;
	}

    public  ArrayList<Vertice> listaVerticesVizinhos(Vertice vertice){
		
		ArrayList<Vertice> listaVertice = new ArrayList<Vertice>();
		synchronized(G.getArestas())
		{
			for (int i = 0; i < G.arestas.size(); i++){
				Aresta obj = (Aresta) G.arestas.get(i);
				if(vertice.nome == obj.nome_vertice1)
				{
					listaVertice.add(readVertice(obj.nome_vertice2));
				}
				else if(vertice.nome == obj.nome_vertice2){
					listaVertice.add(readVertice(obj.nome_vertice1));
				}

			}
		}
		
		return listaVertice;
	}

    public synchronized  ArrayList<Vertice> getVertices() {
		return (ArrayList<Vertice>) G.vertices;
	}
  
    public synchronized  ArrayList<Aresta> getArestas() {
		return (ArrayList<Aresta>) G.arestas;
	}


	@Override
	public synchronized void setVertices(List<Vertice> vertices) throws TException {
		G.vertices = vertices;
		
	}

	@Override
	public synchronized void setArestas(List<Aresta> arestas) throws TException {
		G.arestas = arestas;
	}

	@Override
	public synchronized boolean recuperaGrafo() throws TException {
		Object aux = null;
		String arquivo = "arquivo.txt";
        
		try{
            FileInputStream restFile = new FileInputStream(arquivo);
            ObjectInputStream stream = new ObjectInputStream(restFile);

            aux = stream.readObject();
            if(G!=null)
            	G.clear();
            if(aux != null){
                G = (Grafo) aux;
            }
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
	}

	@Override
	public synchronized boolean persisteGrafo() throws TException {
		String arquivo = "arquivo.txt";
		try{
            FileOutputStream saveFile = new FileOutputStream(arquivo);
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);

            stream.writeObject(G);
            stream.close();
            return true;
        } catch (IOException exc){
            exc.printStackTrace();
            return false;
        }
		
	}
}
