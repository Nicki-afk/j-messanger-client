package gyber.websocket.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.glassfish.grizzly.nio.transport.DefaultStreamReader.Input;

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
        Properties serverProperties = new Properties();
        Scanner sc = new Scanner(System.in);


            try{
                // TODO : Изменить путь к файлу и его место расположениe
                String ipServer = "" , portServer = "";
                String filePath = "gyberwebsocket/src/main/resources/server.properties";
                File theServerDataFile = new File(filePath);
                

                if(!theServerDataFile.exists()){
                    theServerDataFile.createNewFile();


                    LogMessage.logMessage("Welcome to J-messander. Thank you for using my messenger. Please write IP WebSocket Server for connect : " , true);
                    // Проверка IP на null и на пустое значение 
                    while(true){

                        ipServer = sc.nextLine();

                        if(ipServer == null || ipServer.isEmpty()){
                            LogMessage.logMessage("IP server is null or Empty . Please write a valid server IP : ", true);

                        }else{

                            break;
                        }

                    }
                    
                    LogMessage.logMessage("Great IP Server is { " + ipServer + " } . Write please a port server to connect :" , true);


                    // Проверка порта 
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
                    configAdnConnectToServer(ipServer, portServer);



                }else{

                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    InputStream inputStream = classLoader.getResourceAsStream("server.properties");
                    serverProperties.load(inputStream);


                    LogMessage.logMessage("Your list contains saved servers. Write the name of the server to connect. If you want to create a new connection instead of the server name, enter the command /new", false);
                    System.out.println();

                    Set<String>serverNamesList = serverProperties.stringPropertyNames();

                    for(String i : serverNamesList){
                        System.out.println("  * " + i);

                    }

                    System.out.println();
                    LogMessage.logMessage("Write a name server to connect : ", true);
                    String serverName = sc.nextLine();


                    if(serverName.equals("/new")){


                        LogMessage.logMessage("Write please a server IP : ", true);

                        // Проверка IP на null и на пустое значение 
                        while(true){

                            ipServer = sc.nextLine();

                            if(ipServer == null || ipServer.isEmpty()){
                                LogMessage.logMessage("IP server is null or Empty . Please write a valid server IP : ", true);

                            }else{

                                break;
                            }

                        }

                        LogMessage.logMessage("Write please a port server : ", true);
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

                        configAdnConnectToServer(ipServer, portServer);

                    }else{


                        configAdnConnectToServer(serverProperties.getProperty(serverName));
                    }




                
                    


                }


            }catch(Exception e){
                e.printStackTrace();

            }



    }


    public void configAdnConnectToServer(String serverIp , String serverPort){
        try{


            String fullUriAdress = "ws://"
                                .concat(serverIp)
                                .concat(":")
                                .concat(serverPort)
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



     public void configAdnConnectToServer(String adress ){
        try{



            this.uriServer = URI.create(adress);
            LogMessage.logMessage("Init a connection for " + adress , false);
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
