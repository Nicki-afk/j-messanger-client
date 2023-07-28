package gyber.websocket.client;

public class LogMessage {




    public static void logMessage(Message message){

        System.out.print("[  " + message.getFrom() + "  ]  : " );


        char[]chars = message.getContent().toCharArray();

        try{
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
