package models;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TBinaryProtocol;
import java.util.Scanner;

import java.util.ArrayList;
public class Client {
	

	private static Scanner dados;
	private static Scanner s = new Scanner( System.in );;

	public static void main(String [] args) {
		int porta;
		String host;
		System.out.println("Digite a porta:");
		porta = s.nextInt();
		System.out.println("Digite o Host:");
		host = s.nextLine();
		try{
    	  
          TTransport transport = new TSocket(host,porta);
          transport.open();

          TProtocol protocol = new TBinaryProtocol(transport);
          funcoes.Client client = new funcoes.Client(protocol);
          Menu(client);
          transport.close();
	  }catch (TException x){
        x.printStackTrace();
	  }
	}
 
	public static void Menu(funcoes.Client client) throws TException{
	
		dados = new Scanner(System.in);
		int opcao = 0;
		int v1, v2, cor;
        String descricao;
        double peso;
        boolean bid;
		Vertice v = new Vertice();
		Aresta a = new Aresta();
		ArrayList<Aresta> listaAresta = null;
		ArrayList<Vertice> listaVertice = null;
		
		while(opcao!=12) {
			System.out.println("\n-----------------------------------");
			System.out.println("              MENU                  ");
			System.out.println("        Digite uma op��o          \n");
			System.out.println("1  - Inserir um v�rtice");
			System.out.println("2  - Inserir uma aresta");
			System.out.println("3  - Consultar um v�rtice");
			System.out.println("4  - Consultar uma aresta");
			System.out.println("5  - Atualizar um v�rtice");
			System.out.println("6  - Atualizar uma aresta");
			System.out.println("7  - Excluir um v�rtice");
			System.out.println("8  - Excluir uma aresta");
			System.out.println("9  - Listar v�rtices de uma aresta");
			System.out.println("10 - Listar aresta de um v�rtice");
			System.out.println("11 - Listar v�rtices vizinhos de um v�rtice");
			System.out.println("12 - Sair do programa!");
			
			opcao = dados.nextInt();
			 
			switch (opcao){
			 
			 	case 1:
			 		client.recuperaGrafo();
			 		System.out.println("--------- 1  - Inserir um v�rtice ---------");
					System.out.println("        Digite os dados do v�rtice         ");
					System.out.println("Nome: ");
                    v.nome = dados.nextInt();
                    System.out.println("Cor: ");
                    v.cor = dados.nextInt();
                    System.out.println("Descri��o: ");
                    dados.nextLine();
                    v.descricao = dados.nextLine();
                    System.out.println("Peso: ");
                    v.peso = dados.nextDouble();
                    
					if(client.Adiciona_Vertice(v) == true)
						System.out.println("\nVertice adicionado com sucesso!");
					else
						System.out.println("\nOcorreu um erro na inser��o do v�rtice");
					client.persisteGrafo();
					break;
			 	 
			 	case 2:
			 		client.recuperaGrafo();
			 		System.out.println("---------- 2 - Inserir uma aresta ---------\n");
					System.out.println("          Digite os dados da Aresta         \n");
					

					System.out.println("Descricao:");
					dados.nextLine();
					a.descricao = dados.nextLine();
					
					System.out.println("Vertice 1:");
					a.nome_vertice1 = dados.nextInt();
					
					System.out.println("Vertice 2:");
					a.nome_vertice2 = dados.nextInt();
					
					System.out.println("Peso:");
					a.peso = dados.nextDouble();
					
					System.out.println("Bidirecional? 1 - Sim / 2 - N�o");
					if(dados.nextInt() == 1)
						a.bidirecional = true;
					else 
						a.bidirecional = false;
					
                     if(client.Adiciona_Aresta(a))
                         System.out.println("Aresta inserida com sucesso!");
                     else
                    	 System.out.println("Ocorreu um erro na inser��o da aresta.");
                     client.persisteGrafo();
                     break;
			 	 
			 	case 3:
			 		client.recuperaGrafo();
			 		System.out.println("---------- 3 - Consultar um v�rtice ---------\n");
					System.out.println("Nome do v�rtice:\n");
					opcao = dados.nextInt();
					v = client.readVertice(opcao);
					if (v != null)
						System.out.println(v);
					else 
						System.out.println("O v�rtice n�o existe!\n");
					
					dados.nextLine();
					client.persisteGrafo();
					break;
			 	
			 	case 4:
			 		client.recuperaGrafo();
					System.out.println("---------- 4 - Consultar uma aresta ---------\n");
					System.out.println("Nome do v�rtice 1:\n");
					v1 = dados.nextInt();
					System.out.println("Nome do v�rtice 2:\n");
					v2 = dados.nextInt();
					a = client.readArestas(v1, v2);
					if(a != null)
						System.out.println(a);
					else 
						System.out.println("A aresta n�o existe");
					dados.nextLine();
					client.persisteGrafo();
					break;   
			 	
			 	case 5:
			 		client.recuperaGrafo();
			 		System.out.println("---------- 5  - Atualizar um v�rtice ---------\n");
			 		System.out.println("Digite o nome do v�rtice a ser atualizado:\n");
			 		v1 = dados.nextInt();
			 		if(client.existeVertice(v1))
			 		{
			 			System.out.println("Digite a nova cor:");
			 			cor = dados.nextInt();
			 			System.out.println("Digite a nova descricao:");
			 			dados.nextLine();
			 			descricao = dados.nextLine();
			 			System.out.println("Digite o novo peso:");
			 			peso = dados.nextDouble();
			 			client.updateVertice(v1, cor, descricao, peso);
			 			System.out.println("Vertice atualizado com sucesso!\n");
			 		}
			 		else 
			 			System.out.println("V�rtice n�o identificado no grafo!\n");
			 		client.persisteGrafo();
			 		break;
			 		
			 	case 6:
			 		client.recuperaGrafo();
			 		System.out.println("---------- 6  - Atualizar uma aresta ---------\n");
			 		System.out.println("Digite o nome do v�rtice 1 da aresta a ser atualizada:");
			 		v1 = dados.nextInt();
			 		System.out.println("Digite o nome do v�rtice 2 da aresta a ser atualizada:");
			 		v2 = dados.nextInt();
			 		if(client.existeVertice(v1) && client.existeVertice(v2)){   //deveria existir um verifica se existe Aresta aqui
			 			System.out.println("Digite a nova descricao:");
			 			dados.nextLine();
			 			descricao = dados.nextLine();
			 			System.out.println("Digite o novo peso:");
			 			peso = dados.nextDouble();
			 			System.out.println("Digite se � bidirecional ou n�o: 1 - Sim / 2 - N�o");
			 			if(dados.nextInt() == 1)
							bid = true;
						else 
							bid = false;
			 			client.updateAresta(descricao, v1, v2, peso, bid);
			 			System.out.println("Aresta atualizada!");
			 			
			 		}
			 		else 
			 			System.out.println("Erro na atualiza��o! Provavelmente v�rtice ou aresta n�o existem.");
			 		client.persisteGrafo();
			 		break;
			 		
			 	case 7:
			 		client.recuperaGrafo();
			 		System.out.println("---------- 7  - Excluir um v�rtice ---------\n");
			 		System.out.println("Digite o nome do v�rtice a ser exclu�do:");
			 		v1 = dados.nextInt();
			 		if(client.deleteVertice(v1))
			 			System.out.println("V�rtice exclu�do com sucesso!");
			 		else
			 			System.out.println("Erro ao excluir o v�rtice");
			 		client.persisteGrafo();
			 		break;
			 	
			 	case 8:
			 		client.recuperaGrafo();
			 		System.out.println("---------- 8  - Excluir uma aresta ---------\n");
			 		System.out.println("Digite o nome do v�rtice 1 da aresta a ser exclu�da:");
			 		v1 = dados.nextInt();
			 		System.out.println("Digite o nome do v�rtice 2 da aresta a ser exclu�da:");
			 		v2 = dados.nextInt();
			 		if(client.deleteAresta(v1, v2))
			 			System.out.println("Aresta exclu�da com sucesso!");
			 		else
			 			System.out.println("Erro ao excluir a aresta");
			 		client.persisteGrafo();
			 		break;
			 	
			 	case 9:
			 		client.recuperaGrafo();
			 		if (listaVertice != null)
			 			listaVertice.clear();
			 		System.out.println("---------- 9  - Listar v�rtices de uma aresta ---------\n");
			 		System.out.println("Digite a descricao da aresta:");
			 		dados.nextLine();
			 		descricao = dados.nextLine();
			 		a =  client.getAresta(descricao);
			 		System.out.println(a);
			 		listaVertice = (ArrayList<Vertice>) client.listaVerticedaAresta(a);
			 		if(listaVertice != null)
			 			System.out.println(listaVertice);
			 		else
			 			System.out.println("Ocorreu erro!");
			 		client.persisteGrafo();
			 		break;	
			 		
			 	case 10:
			 		client.recuperaGrafo();
			 		if (listaAresta != null)
			 			listaAresta.clear();
			 		System.out.println("---------- 10 - Listar aresta de um v�rtice ---------\n");
			 		System.out.println("Digite o nome do v�rtice:");
			 		v1 = dados.nextInt();
			 		v =  client.getVertice(v1);
			 		listaAresta = (ArrayList<Aresta>) client.listaArestasdoVertice(v);
			 		if (listaAresta != null)
			 			System.out.println(client.listaArestasdoVertice(v));
			 		else
			 			System.out.println("Ocorreu erro!");
			 		client.persisteGrafo();
			 		break;
			 	
			 	case 11:
			 		client.recuperaGrafo();
			 		System.out.println("---------- 11 - Listar v�rtices vizinhos de um v�rtice ---------\n");
			 		System.out.println("Digite o nome do v�rtice:");
			 		v1 = dados.nextInt();
			 		v =  client.getVertice(v1);
			 		if (v!= null)
			 			System.out.println(client.listaVerticesVizinhos(v));
			 		else
			 			System.out.println("Ocorreu erro!");
			 		client.persisteGrafo();
			 		break;
			 		
			 	case 12:
			 		System.out.println("---------- 12 - Sair do programa! ---------\n");
			 		System.out.println("Finalizando!\n");
			 		break;
			 		
			 	default:
                    System.out.println("Op��o Inv�lida!");
			 }
			 

		}

	}
}

