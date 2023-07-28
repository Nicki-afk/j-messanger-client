package gyber.websocket.client;

import gyber.websocket.codecs.MessageDecoder;

public class LogMessage {




    public static void logMessage(String message){

       

        try{

            Message msg = new MessageDecoder().decode(message);
            char[]chars = msg.getContent().toCharArray();

            System.out.print(msg.getContent().length() > 70 ? "\n[  " + msg.getFrom() + "  ]  : " : "[  " + msg.getFrom() + "  ]  : " );
        
        

            int x = 0;
            int charSpace = 0;
            while(x < chars.length){

               
                
                if(charSpace >= 110){
                                     
                    System.out.println();
                    System.out.print("                " + chars[x]);
                    Thread.sleep(20);
                    charSpace = 0;
                    x++;
                

                }else{

                    System.out.print(chars[x]);

                    
                    x++;
                    charSpace++;
                    Thread.sleep(20);
                }

            }

            

            System.out.println();
           
            
       }catch(Exception e){
            e.printStackTrace();

       }

      
    

    }

    public static void logClientMessage(String message){

        try{

            
            char[]chars = message.toCharArray();

            System.out.print("[  " + "CLIENT" + "  ]  : " );

        
            int x = 0;
            int charSpace = 0;
            while(x < chars.length){

                if(charSpace >= 110){
                                     
                    System.out.println();
                    System.out.print("                " + chars[x]);
                    Thread.sleep(20);
                    charSpace = 0;
                    x++;
                

                }else{

                    System.out.print(chars[x]);

                    
                    x++;
                    charSpace++;
                    Thread.sleep(20);
                }
                
            }

            System.out.println();
            
       }catch(Exception e){
            e.printStackTrace();

       }



    }
    
}
