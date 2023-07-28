package gyber.websocket.client;

import gyber.websocket.codecs.MessageDecoder;

public class LogMessage {




    public static void logMessage(String message){

       

        try{

            Message msg = new MessageDecoder().decode(message);
            char[]chars = msg.getContent().toCharArray();

            System.out.print("[  " + msg.getFrom() + "  ]  : " );

        
            int x = 0;
            while(x < chars.length){
                
                System.out.print(chars[x]);  // Печатает один символ в секунду

                x++;
                Thread.sleep(1000);
            }

            System.out.println();
            
       }catch(Exception e){
            e.printStackTrace();

       }
    

    }
    
}
