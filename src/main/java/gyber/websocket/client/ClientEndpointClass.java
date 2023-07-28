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



    @OnOpen
    public void onOpen(Session session) {

        this.session = session;
        System.out.println("Connected! ");
        

        try {

            Thread.sleep(2000);
           System.out.println("IS Open : " + session.isOpen());

            System.out.print(" >> ");

            String msg = new Scanner(System.in).nextLine();
            System.out.println("MSG = " + msg);

            Message message = new Message("gyber", "gyber", msg);



            session.getBasicRemote().sendObject(message);
            System.out.println("IS Open : " + session.isOpen());
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnMessage
    public void onMessage(String message) {
        try{
        System.out.println("Received: " + new MessageDecoder().decode(message).getContent());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
}
