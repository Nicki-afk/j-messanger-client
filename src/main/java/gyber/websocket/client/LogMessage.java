package gyber.websocket.client;

import java.util.Arrays;

import gyber.websocket.codecs.MessageDecoder;

// TODO : Сделать перегрузку методов и убрать дублирующийся код
public class LogMessage {



    public static boolean stylePrint = true;  // Переменная отвечает за стиль отрисовки текста если она true то текст будет выводится по буквенно если нет то обычным текстом




    // Для отрисовки сообщений 
    public static void logMessage(String message){

        try{

            if(stylePrint){

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



            }else{

                Message msg = new MessageDecoder().decode(message);
                char[]chars = msg.getContent().toCharArray();

                 System.out.print(msg.getContent().length() > 70 ? "\n[  " + msg.getFrom() + "  ]  : " : "[  " + msg.getFrom() + "  ]  : " );
                
                

                    int x = 0;
                    int charSpace = 0;
                    while(x < chars.length){

                    
                        
                        if(charSpace >= 110){
                                            
                            System.out.println();
                            System.out.print("                " + chars[x]);
                            //Thread.sleep(20);
                            charSpace = 0;
                            x++;
                        

                        }else{

                            System.out.print(chars[x]);

                            
                            x++;
                            charSpace++;
                            Thread.sleep(20);
                        }

                    }





            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    // Для отрисовки клиентских сообщений
    public static void logMessage(String message , boolean haveInput){

        try{

            if(stylePrint){
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

                    if(!haveInput){
                        System.out.println();

                    }



            }else{

                String words[] = message.split(" ");

                // Расставляем переносы стороки 
                int newLineIndex = 0;
                for(int x = 0;  x < words.length; x++){
                    if(newLineIndex == 10){
                        words[x] = words[x].concat(" \n".concat("               "));
                        newLineIndex = 0;

                    }
                    newLineIndex++;


                }

                System.out.print("[  " + "CLIENT" + "  ]  : " );

                // Выводим 
                for(String i : words){
                    System.out.print(i + " ");
                }


                if(!haveInput){
                    System.out.println();

                }


            }

        }catch(Exception e){
            e.printStackTrace();
        }






    }








    // public static void logMessage(String message){

       

    //     try{

    //         Message msg = new MessageDecoder().decode(message);
    //         char[]chars = msg.getContent().toCharArray();

    //         System.out.print(msg.getContent().length() > 70 ? "\n[  " + msg.getFrom() + "  ]  : " : "[  " + msg.getFrom() + "  ]  : " );
        
        

    //         int x = 0;
    //         int charSpace = 0;
    //         while(x < chars.length){

               
                
    //             if(charSpace >= 110){
                                     
    //                 System.out.println();
    //                 System.out.print("                " + chars[x]);
    //                 Thread.sleep(20);
    //                 charSpace = 0;
    //                 x++;
                

    //             }else{

    //                 System.out.print(chars[x]);

                    
    //                 x++;
    //                 charSpace++;
    //                 Thread.sleep(20);
    //             }

    //         }

            

    //         System.out.println();
           
            
    //    }catch(Exception e){
    //         e.printStackTrace();

    //    }

      
    

    // }

    // public static void logClientMessage(String message){

    //     try{

            
    //         char[]chars = message.toCharArray();

    //         System.out.print("[  " + "CLIENT" + "  ]  : " );

        
    //         int x = 0;
    //         int charSpace = 0;
    //         while(x < chars.length){

    //             if(charSpace >= 110){
                                     
    //                 System.out.println();
    //                 System.out.print("                " + chars[x]);
    //                 Thread.sleep(20);
    //                 charSpace = 0;
    //                 x++;
                

    //             }else{

    //                 System.out.print(chars[x]);

                    
    //                 x++;
    //                 charSpace++;
    //                 Thread.sleep(20);
    //             }
                
    //         }

    //         System.out.println();
            
    //    }catch(Exception e){
    //         e.printStackTrace();

    //    }



    // }

    // public static void logPrintClientMessage(String mString){


    //     try{

            
    //         char[]chars = mString.toCharArray();

    //         System.out.print("[  " + "CLIENT" + "  ]  : " );

        
    //         int x = 0;
    //         int charSpace = 0;
    //         while(x < chars.length){

    //             if(charSpace >= 110){
                                     
    //                 System.out.println();
    //                 System.out.print("                " + chars[x]);
    //                 Thread.sleep(20);
    //                 charSpace = 0;
    //                 x++;
                

    //             }else{

    //                 System.out.print(chars[x]);

                    
    //                 x++;
    //                 charSpace++;
    //                 Thread.sleep(20);
    //             }
                
    //         }

    //         // System.out.println();
            
    //    }catch(Exception e){
    //         e.printStackTrace();

    //    }

    // }
    
}
