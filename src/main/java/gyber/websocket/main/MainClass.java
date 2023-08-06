package gyber.websocket.main;

import gyber.websocket.client.ClientConfigClass;

public class MainClass {



    public static void main(String[] args) {

        try {       
          
            System.out.println();

            ClientConfigClass clientConfigClass = new ClientConfigClass();
            clientConfigClass.preStart();

        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    
}
