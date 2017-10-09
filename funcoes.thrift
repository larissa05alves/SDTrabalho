namespace java models

exception KeyNotFound
{
}

struct Vertice {
	1:i32 nome,
	2:i32 cor,
	3:string descricao,
	4:double peso
}

struct Aresta {
	1:i32 nome_vertice1,
	2:i32 nome_vertice2,
	3:double peso,
	4:bool bidirecional,
	5:string descricao
}

struct Grafo {
    1:list<Vertice> vertices,
    2:list<Aresta> arestas
}

service funcoes {
	bool recuperaGrafo(),
    bool persisteGrafo(),
	bool Adiciona_Vertice(1:Vertice vertice),
	Vertice getVertice(1:i32 nome),
	Aresta getAresta(1:string descricao),
	Aresta existeAresta(1:i32 nomeVertice1, 2:i32 nomeVertice2),
	bool Adiciona_Aresta(1:Aresta aresta),
	bool existeVertice(1:i32 nomeVertice),
	bool updateVertice(1:i32 nome, 2:i32 cor, 3:string descricao, 4:double peso),
	bool updateAresta(1:string descricao, 2:i32 nome_vertice1,3:i32 nome_vertice2,4:double peso,5:bool bidirecional)
	bool deleteVertice(1:i32 nomeVertice),
	bool deleteAresta(1:i32 nomeVertice1, 2:i32 nomeVertice2),
	Vertice readVertice(1:i32 nomeVertice),
	Aresta readArestas(1:i32 nomeVertice1, 2:i32 nomeVertice2),
	list<Vertice> listaVerticedaAresta(1:Aresta aresta),
	list<Aresta> listaArestasdoVertice(1:Vertice vertice),
	list<Vertice> listaVerticesVizinhos(1:Vertice vertice),
	list<Vertice> getVertices(),
	void setVertices(1:list<Vertice> vertices),
	list<Aresta> getArestas(),
	void setArestas(1:list<Aresta> arestas)	
}
