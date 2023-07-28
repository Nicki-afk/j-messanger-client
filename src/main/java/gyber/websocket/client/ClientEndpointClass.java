package gyber.websocket.client;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

import javax.websocket.*;

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


        }catch(Exception e){
            e.printStackTrace();
        }
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
                message.setTo(to);
                message.setContent(msg);

                session.getBasicRemote().sendObject(msg);

            }else{

                message.setFrom(username);
                message.setTo("");
                message.setContent(msg);

                session.getBasicRemote().sendObject(msg);


            }




        }catch(Exception e){
            e.printStackTrace();

        }
    }

    
}
