package gyber.websocket.main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import gyber.websocket.client.ClientConfigClass;
import gyber.websocket.client.ClientEndpointClass;
import gyber.websocket.client.LogMessage;

public class MainClass {

    private URI uriServer;
    private WebSocketContainer container = ContainerProvider.getWebSocketContainer();



    public static void main(String[] args) {

        try {       
          
        

         ClientConfigClass clientConfigClass = new ClientConfigClass();
         clientConfigClass.preStart();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    
}
