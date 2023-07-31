package gyber.websocket.client;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import gyber.websocket.codecs.MessageDecoder;
import gyber.websocket.codecs.MessageEncoder;


@ClientEndpoint(encoders = MessageEncoder.class , decoders = MessageDecoder.class)
public class ClientEndpointClass {


     private Session session;
     private String username;
     private BlockingQueue<String> incomingMessages = new LinkedBlockingQueue<>();
     private ExecutorService executorService = Executors.newFixedThreadPool(2); 




    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        LogMessage.logMessage("Connect to server successful !!" , false );
        logInByUser();
        LogMessage.logMessage("Your logined in server by username : " + this.username + " Now you can chat with other members !!!" , false);



        try{
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // Слушаем ввод 
                    while (true) {
                        System.out.println();
                        writeAMessage();
                    }

                }
            });


            executorService.execute(() -> {
                try {
                    while (true) {
                        // Берем сообщение из очереди и выводим его
                        String message = incomingMessages.take();
                        LogMessage.logMessage(message);
                    
                    }


                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    
    }

    @OnMessage
    public void onMessage(String message) {
        incomingMessages.add(message);
    }


    public void logInByUser(){
        LogMessage.logMessage("Write your username to connect : " , true);
        String username = new Scanner(System.in).nextLine(); 
        

        while(username.length() != 6){
            LogMessage.logMessage("Impossible to login in this nickname . Nickname has been a 4 chars. Repeat to write your username : " , true);
          
            username = new Scanner(System.in).nextLine();

        }

        this.username = username;
        
        
    }




    // TODO : Разобратся с потоками . System.exit(0) == Bad practice
    public void writeAMessage(){
        try{

           // System.out.print(": ");
            String msg = new Scanner(System.in).nextLine();
        

            Message message = new Message();
            
            if(msg.equals("exit")){

               this.session.close();
               LogMessage.logMessage("Your disconnect from server . Thank for using J-messanger" , false);
               System.out.println();
               System.exit(0);          // Убиваем все потоки :(
        

            }else if (msg.contains("/")){
                String command = msg.substring(msg.indexOf('/'),  msg.indexOf(' '));
                String args = msg.substring(msg.indexOf(' ')+1);


                if(command.equals("/styleprint") && args.equals("--off")){
                    LogMessage.STYLE_PRINT = false;
                    LogMessage.logMessage("Text output changed successfully. Now the characters will not be printed character by character", false);
                          
                }else if(command.equals("/styleprint") && args.equals("--on")){

                    LogMessage.logMessage("Text output changed successfully. Now the characters will be printed character by character. You can change the print speed using the /charpm command", false);
                    LogMessage.STYLE_PRINT = true;

                }else if(command.equals("/charpm")){
                    LogMessage.CHAR_PER_MILLISECOND = Long.parseLong(args);
                    LogMessage.logMessage("The command has been successfully applied. Characters will be printed once per " + args, false);

                }

                writeAMessage();

            }else{

                message.setFrom(this.username);
                message.setPrefixTo("");
                message.setContent(msg);
            

                session.getBasicRemote().sendObject(new MessageEncoder().encode(message));


            }




        }catch(Exception e){
            e.printStackTrace();

 
        }

        return;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    

    

    

    
}
