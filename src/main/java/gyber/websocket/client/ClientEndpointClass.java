package gyber.websocket.client;

import java.util.Scanner;

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




    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        LogMessage.logClientMessage("Connect to server successful !!");
        logInByUser();
        LogMessage.logClientMessage("Your logined in server by username : " + this.username + " Now you can chat with other members !!!");
        writeAMessage();

    
    }

    @OnMessage
    public void onMessage(String message) {
        LogMessage.logMessage(message);
        writeAMessage();


    }


    public void logInByUser(){
        LogMessage.logPrintClientMessage("Write your username to connect : ");
        String username = new Scanner(System.in).nextLine(); 
        

        while(username.length() != 6){
            LogMessage.logPrintClientMessage("Impossible to login in this nickname . Nickname has been a 4 chars. Repeat to write your username : ");
          
            username = new Scanner(System.in).nextLine();

        }

        this.username = username;
        
        
    }




    public void writeAMessage(){
        try{

            System.out.print(": ");
            String msg = new Scanner(System.in).nextLine();
        

            Message message = new Message();
            
            if(msg.equals("exit")){

               this.session.close();
               LogMessage.logClientMessage("Your disconnect from server . Thank for using J-messanger");
               System.out.println();

            }else if (msg.contains("/")){
                String to = msg.substring(msg.indexOf('/'),  msg.indexOf(' ')-1);


                message.setFrom(username);
                message.setPrefixTo(to);
                message.setContent(msg);

                session.getBasicRemote().sendObject(new MessageEncoder().encode(message));

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
