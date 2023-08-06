package gyber.websocket.main;

import gyber.websocket.client.ClientConfigClass;

public class MainClass {



    public static void main(String[] args) {

        try {       
          
        

         ClientConfigClass clientConfigClass = new ClientConfigClass();
         clientConfigClass.preStart();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    
}
