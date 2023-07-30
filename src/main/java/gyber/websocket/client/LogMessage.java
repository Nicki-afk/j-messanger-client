package gyber.websocket.client;


import gyber.websocket.codecs.MessageDecoder;

public class LogMessage {
    public static boolean STYLE_PRINT = true;       // Переменная отвечает за стиль отрисовки текста если она true то текст будет выводится по буквенно если нет то обычным текстом
    public static long CHAR_PER_MILLISECOND = 20;   // Изменяет скорость печати символа в милисекнду  




    // Для отрисовки сообщений 
    public static void logMessage(String message){

        try{
            Message msg = new MessageDecoder().decode(message);
            String contentMessage = msg.getContent();
         
             

            if(STYLE_PRINT){
                char[]chars = arrangeLineBreaksAndSpaces(contentMessage).toCharArray();
                System.out.print(msg.getContent().length() > 70 ? "\n[  " + msg.getFrom() + "  ]  : " : "[  " + msg.getFrom() + "  ]  : " );
                
                
                int x = 0;
                while(x < chars.length){

                    System.out.print(chars[x]);
                    Thread.sleep(CHAR_PER_MILLISECOND);
                    x++;

                }

            }else{

                System.out.print(arrangeLineBreaksAndSpaces(message));
            
            }

            System.out.println();

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    // Для отрисовки клиентских сообщений
    public static void logMessage(String message , boolean haveInput){

        try{

            if(STYLE_PRINT){
                char[]chars = arrangeLineBreaksAndSpaces(message).toCharArray();
                System.out.print("[  " + "CLIENT" + "  ]  : " );
                
            
                int x = 0;
                while(x < chars.length){

                    System.out.print(chars[x]);
                    Thread.sleep(CHAR_PER_MILLISECOND);
                    x++;

                }


            }else{
                System.out.print("[  " + "CLIENT" + "  ]  : ".concat(arrangeLineBreaksAndSpaces(message)));

            }

            if(!haveInput){
                System.out.println();

            }

        }catch(Exception e){
            e.printStackTrace();
        }






    }


    private static String arrangeLineBreaksAndSpaces(String msg){
        String words[] = msg.split(" ");
        String returnVar = "";

        

        // Расставляем переносы стороки и пробелы 
        int newLineIndex = 0;
        for(int x = 0;  x < words.length; x++){
            if(newLineIndex == 10){
                words[x] = words[x].concat(" \n".concat("               "));
                newLineIndex = 0;

            }

            newLineIndex++;
        }



        // Трансформируем в строку
        for(String i : words){
            returnVar += i.concat(" ");
        }

            
        return returnVar;

    }

    
}
