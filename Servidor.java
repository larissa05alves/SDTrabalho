package models;

import java.util.Scanner;

import org.apache.thrift.server.*;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Servidor {
    private static Scanner s;

	public static void main(String [] args){
    	s = new Scanner( System.in );
    	System.out.println("Digite a porta:");
    	int porta = s.nextInt();
    	
    	try{

            Handler handler = new Handler();
            funcoes.Processor<Handler> processor = new funcoes.Processor<Handler>(handler);
            TServerTransport serverTransport = new TServerSocket(porta);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

           System.out.println("Olá, sou o servidor e estou aqui!");
           System.out.println(porta);
            server.serve();
        } catch (Exception x){
            x.printStackTrace();
        }
    }
}