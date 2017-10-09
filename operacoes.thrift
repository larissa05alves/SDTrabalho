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
	1:i32 v1,
	2:i32 v2,
	3:double peso,
	4:i32 flag,
	5:string descricao
}

struct Grafo {
    1:list<Vertice> V,
    2:list<Aresta> A
}

service Operations {
    void criaGrafo(1:string caminho),
    void salvaGrafo(1:string caminho),
    bool criaVertice(1:i32 nome,2:i32 cor,3:string descricao,4:double peso),
    bool criaAresta(1:i32 v1,2:i32 v2,3:double peso,4:i32 flag,5:string descricao),
    bool excluiVertice(1:i32 nome),
    bool excluiAresta(1:i32 v1,2:i32 v2),
    bool updateVertice(1:i32 nomeUp,2:Vertice V),
    bool updateAresta(1:i32 nomeV1, 2:i32 nomeV2, 3:Aresta A),
    bool updateGrafo(1:list<Vertice> V,2:list<Aresta> A),
    Vertice getVertice(1:i32 nome),
    Aresta getAresta(1:i32 v1,2:i32 v2),
    string mostraGrafo(),
    string mostraVertice(),
    string mostraAresta(),
    string mostraVerticedeAresta(1:i32 v1,2:i32 v2),
    string mostraArestadeVertice(1:i32 nomeV),
    string mostraAdjacentes(1:i32 nomeV),
    string smallerPath(1:i32 v1,2:i32 v2)
}