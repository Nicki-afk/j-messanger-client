package gyber.websocket.client;

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

public class ClientConfigClass {

    private URI uriServer;
    private WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    private  Properties serverProperties = new Properties();



     public void preStart(){
        // Properties serverProperties = new Properties();
        Scanner sc = new Scanner(System.in);


            try{
                String ipServer = "" , portServer = "";
                String filePath = "props/server.properties";
                File theServerDataFile = new File(filePath);
                

                if(!theServerDataFile.exists()){
                    new File("props").mkdir();
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

                    saveAServerData(ipServer, portServer);
                    configAdnConnectToServer(ipServer, portServer);



                }else{

                    FileInputStream fileInputStream = new FileInputStream("props/server.properties");
                    serverProperties.load(fileInputStream);
                    fileInputStream.close();


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

                    


                        saveAServerData(ipServer, portServer);
                        configAdnConnectToServer(ipServer, portServer);

                    }else{

                        while(!serverNamesList.contains(serverName)){
                            LogMessage.logMessage("Error . Name save server not found . Write server name again : ", true);
                            serverName = sc.nextLine();

                        }


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


    // TODO : Сделать проверку на имя сервера. Сделать так что бы нельзя было сохранять имя сервера с тем же названием которое уже есть в файле
    public void saveAServerData(String serverIp , String serverPort){
        
        try{
            Scanner sc = new Scanner(System.in);

            LogMessage.logMessage("IP Server to connect is { " + serverIp + " } port server is { " + serverPort + "} . Strart to configure connection..."  , false);
            LogMessage.logMessage("Save a this connection data ? [Y/n]", true);

            String answer = sc.nextLine();
            
            if(answer.trim().equalsIgnoreCase("y")){

                LogMessage.logMessage("Write a name server to save : ", true);  
                String serverSaveName = "";

                // Проверка имени сервера
                while(true){
                    serverSaveName = sc.nextLine();
                    
                    if(serverSaveName == null || serverSaveName.isEmpty()){

                        LogMessage.logMessage("Error. Server name is null or empty , write a name server in format 'name.server' : ", true);
                    }else{

                        if(Pattern.compile("[^a-zA-Z0-9\u0430-\u044F\u0410-\u042F\\\\.\\\\s]").matcher(serverSaveName).find()){
                            LogMessage.logMessage("Error . Name server is invalid , write please name server in format 'name.server' : ", true);

                        }else{
                            break;
                        }
                    }
                    

                }

                File fileToWrite = new File("props/server.properties");
                List<String>serverNames = new ArrayList<>();

                try(BufferedReader reader = new BufferedReader(new FileReader(fileToWrite))){
                    String i = "";
                    while((i = reader.readLine()) != null){
                        serverNames.add(i);
                    }

                }

                

                serverNames.add(
                    serverSaveName
                    .concat("=")
                    .concat("ws://")
                    .concat(serverIp)
                    .concat(":")
                    .concat(serverPort)
                    .concat("/gyberwebsocket-0.0.2-inside-test/chat/gyber?gyber")
                    .concat("\n")
                    
                );

                try(BufferedWriter writer  = new BufferedWriter(new FileWriter(fileToWrite))){
                    int x = 0;
                    while(x < serverNames.size()){
                        writer.write(serverNames.get(x));
                        writer.newLine();
                        x++;
                    }

                }
           

                    


            }else{
                
                return;
            }


           


        }catch(Exception e){
            e.printStackTrace();
        }


    }




















    
}
