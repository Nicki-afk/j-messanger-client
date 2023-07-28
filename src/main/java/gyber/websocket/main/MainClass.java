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

    public static void main(String[] args) {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        
        try {       
            System.out.println();
            LogMessage.logClientMessage("Welcome to J-messander. Thank you for using my messenger. ");
            URI uri = URI.create("ws://localhost:8080/gyberwebsocket-0.0.2-inside-test/chat/gyber?gyber");


        
            ClientEndpointClass endpoint = new ClientEndpointClass();
    
            Session session = container.connectToServer(ClientEndpointClass.class, uri);
            session.setMaxIdleTimeout(120000);

            while(session.isOpen()){ Thread.sleep(3000);}
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }


    

    
}
