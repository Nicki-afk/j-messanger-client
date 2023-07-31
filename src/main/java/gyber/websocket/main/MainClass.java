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


        // Проверка IP на null и на пустое значение 
        String ipServer = "";
        while(true){

            ipServer = sc.nextLine();

            if(ipServer == null || ipServer.isEmpty()){
                LogMessage.logMessage("IP server is null or Empty . Please write a valid server IP : ", true);

            }else{

                break;
            }

        }


        LogMessage.logMessage("Great IP Server is { " + ipServer + " } . Write please a port server to connect :" , true);

        // Проверка порта на корректность на null и на пустое значение 
        String portServer = "";
        while(true){

             portServer = sc.nextLine();

            try{
                Long i = Long.parseLong(portServer);  // Проверка. Является ли порт валидным значением , если нет то просят ввести повторно порт 

                if(portServer == null || portServer.isEmpty()){
                    LogMessage.logMessage("Server port is null or empty . Please write a valid port server : ", true);

                }else{
                    break;
                }


            }catch(NumberFormatException e){

                LogMessage.logMessage("Port server invalid , please write a port server again : ", true);
            }
        }




        LogMessage.logMessage("IP Server to connect is { " + ipServer + " } port server is { " + portServer + "} . Strart to configure connection..."  , false);




        

        try{

            String fullUriAdress = "ws://"
                                .concat(ipServer)
                                .concat(":")
                                .concat(portServer)
                                .concat("/gyberwebsocket-0.0.2-inside-test/chat/gyber?gyber");

            LogMessage.logMessage("Creating full adress ..." , false);

            this.uriServer = URI.create(fullUriAdress);
            LogMessage.logMessage("Adress create successful ! Init a connection for " + fullUriAdress , false);
            System.out.println();

            ClientEndpointClass clientEndpointClass = new ClientEndpointClass();
            Session session = container.connectToServer(ClientEndpointClass.class , uriServer);
            session.setMaxIdleTimeout(120000);

            while(session.isOpen()){Thread.sleep(3000);}

            
        }catch(DeploymentException deploymentException){
            LogMessage.logMessage("Error to connect on server , Impossible to create new session. The server may not be available, please try again later. Perhaps the problem is also related to your server IP or port. Make sure your connection details are correct", false);
            

        }catch(IOException ioException){
            LogMessage.logMessage("Error to connect on server . Error on your network or Protocol. Check your network and try again ", false);

        }catch(IllegalArgumentException illegalArgumentException){
            LogMessage.logMessage("Error to connect on server. Impossible to set session time-out", false);

        }catch(IllegalStateException illegalStateException){
            LogMessage.logMessage("Error to set session time-out . The session may not be close", false);

        }catch(InterruptedException interruptedException){
            LogMessage.logMessage("Error thread. The stream was interrupted by an unknown", false);

        }



    }


    

    
}
