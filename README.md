#  J-messanger client 

### What is J-messanger client ?

This is a small program with which you can connect to a WebSocket message server and chat with friends. This version, which is presented in this repository in the jar folder **( gyberwebsocket-client-0.0.3-alpha.zip )** is quite raw, but it is enough to correspond with friends. Let's get started!

<br>

---
## Version note 0.0.4-alpha

**In the new version 0.0.4-alpha, the following bugs have been fixed, and the following features have been added:**

 - The output of messages has been fixed, now the line break occurs by words and not by letters as it was before
 - Added the ability to disable message output character by character using the `/styleprint --off`  command. Also added the ability to change the speed of printing characters using the command `/charpm "time in milliseconds "`

---

<br>

### Start Application 

Recommended system settings to run the application:

- openjdk 11.0.19 2023-04-18

<br>


And so, first of all, you should download the repository or clone it to your computer, after that you will get into the jar folder, unpack the archive where it is convenient for you, and run its accompanying command: 

```
java -jar gyberwebsocket-client-0.0.4-alpha.jar

```

If everything worked out for you, then when you start the program, you should see the following message

```
[  CLIENT  ]  : Welcome to J-messander. Thank you for using my messenger. Please write IP WebSocket Server for connect :

```

After that, you must enter the address of the WebSocket server you want to connect to, after that, as you enter the server address, follow the further instructions in the terminal

```
[  CLIENT  ]  : Welcome to J-messander. Thank you for using my messenger. Please write IP WebSocket Server for connect : localhost
[  CLIENT  ]  : Great IP Server is { localhost } . Write please a port server to connect :8080
[  CLIENT  ]  : IP Server to connect is { localhost } port server is { 8080} . Strart to configure connection...
[  CLIENT  ]  : Creating full adress ...
[  CLIENT  ]  : Adress create successful ! Init a connection for ws://localhost:8080/gyberwebsocket-0.0.2-inside-test/chat/gyb
                er?gyber

[  CLIENT  ]  : Connect to server successful !!
[  CLIENT  ]  : Write your username to connect : nic_ko
[  CLIENT  ]  : Your logined in server by username : nic_ko Now you can chat with other members !!!
: Hi All !
[  nic_ko  ]  : Hi All !

```

Great! Now you can chat with your friends. Remember session timeout 2 minutes
