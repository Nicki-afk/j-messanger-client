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


     public ClientEndpointClass(){}


     public ClientEndpointClass(String username){
        this.username = username;
        System.out.println("Username to set : " + username);

     }




    @OnOpen
    public void onOpen(Session session) {

        this.session = session;

        LogMessage.logClientMessage("Connect to server successful !!");
        logInByUser();
        LogMessage.logClientMessage("Your logined in server by username : " + this.username);
        

        try {

        //     Thread.sleep(2000);
        //    System.out.println("IS Open : " + session.isOpen());

        //     System.out.print(" >> ");

        //     String msg = new Scanner(System.in).nextLine();
        //     System.out.println("MSG = " + msg);

        //     Message message = new Message("gyber", "gyber", msg);



        //     session.getBasicRemote().sendObject(message);
        //     System.out.println("IS Open : " + session.isOpen());

        writeAMessage();
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnMessage
    public void onMessage(String message) {
        try{

        LogMessage.logMessage(message);
        System.out.println();
        writeAMessage();
    


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void logInByUser(){

        LogMessage.logClientMessage("Write your username to connect : ");
        System.out.print(">>> ");
        this.username = new Scanner(System.in).nextLine();
        
        
    }




    public void writeAMessage(){
        try{

            String msg = new Scanner(System.in).nextLine();

            Message message = new Message();
            
            if(msg.equals("exit")){

               this.session.close();
               LogMessage.logClientMessage("Your disconnect from server . Thank for using J-messanger");

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
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    
}
