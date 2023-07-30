package gyber.websocket.main;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import gyber.websocket.client.ClientEndpointClass;
import gyber.websocket.client.LogMessage;

public class MainClass {

    private URI uriServer;
    private WebSocketContainer container = ContainerProvider.getWebSocketContainer();



    public static void main(String[] args) {

        try {       
          

         MainClass mainClass = new MainClass();
         mainClass.preStart();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }


    public void preStart(){

        Scanner sc = new Scanner(System.in);



        System.out.println();
        LogMessage.logMessage("Welcome to J-messander. Thank you for using my messenger. Please write IP WebSocket Server for connect : " , true);


        String ipServer = sc.nextLine();
        LogMessage.logMessage("Great IP Server is { " + ipServer + " } . Write please a port server to connect :" , true);
        String portServer = sc.nextLine();
        LogMessage.logMessage("IP Server to connect is { " + ipServer + " } port server is { " + portServer + "} . Strart to configure connection..."  , false);
        String fullUriAdress = "ws://"
                                .concat(ipServer)
                                .concat(":")
                                .concat(portServer)
                                .concat("/gyberwebsocket-0.0.2-inside-test/chat/gyber?gyber");

        LogMessage.logMessage("Creating full adress ..." , false);

        this.uriServer = URI.create(fullUriAdress);

        LogMessage.logMessage("Adress create successful ! Init a connection for " + fullUriAdress , false);
        System.out.println();

        try{

        ClientEndpointClass clientEndpointClass = new ClientEndpointClass();
        Session session = container.connectToServer(ClientEndpointClass.class , uriServer);
        session.setMaxIdleTimeout(120000);

        while(session.isOpen()){Thread.sleep(3000);}

        
        }catch(Exception e ){
            e.printStackTrace();
        }



    }


    

    
}
