package com.mygdx.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

import com.mygdx.game.Berek;

public class ConnectionController {
	
	
	 PrintWriter out;
	
	 BufferedReader in;
	
	 Socket socket;

	 public String nickName;

	 public int connectionResult = 100;
	 
	 String recivedData;
	 
	 NotificationListener notificationListener;
	
	
	public ConnectionController(Berek game){
		
		notificationListener = new NotificationListener(game);	
	}
	
	public void connectWithNickName(final String nick) {

		Thread connection = new Thread(new Runnable() {
			
			@Override
			public void run() {

				try{

					socket = new Socket("192.168.56.1", 8084);

					out = new PrintWriter(socket.getOutputStream(), true);
					
					out.println(nick);
					
					System.out.println("nick wys³ay");
					
					in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
				 
					
					String result = "";

					if(( result = in.readLine() ) != null){
						
						if( result.equals("1") ){
							
							System.out.println("Po³¹czono");
							
							connectionResult = 1;
							
							dataRefresch();
							
						}
						else if (result.equals("2")) {
							
							connectionResult = 2;
							
							System.out.println("Nick zajêty. Spróbuj ponownie");
							
							socket.close();
							
						}							
					}			
				} catch (UnknownHostException e) {
				     
					System.out.println("Unknown host: 192.168.56.1");
					
					connectionResult = 3;

					     
				} catch  (IOException e) {
							   
					System.out.println("No I/O");
					
					connectionResult = 4;
				}					
			}
		});
	
		connection.start();
	
	}

	public void joinLobby() {
		
		out.println("lobby");
	}
	
	private void dataRefresch() {

		Thread dataRefresch = new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					while((recivedData = in.readLine() ) != null){
					
						notificationListener.OnDataRecived(recivedData);	
						
						
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}			
		});
	
		dataRefresch.start();
	}
	
	
	public void sendDataToHost(float knobX, float knobY){
		
		try { 
			
			
			
			JSONObject data = new JSONObject();  
			
			/*data.put("toHost", true);
			
			data.put("nickName", nickName);*/
			
			data.put( "knobX", knobX );  
			
			data.put( "knobY", knobY ); 
			
			out.println(data.toString());
			
			//WarpClient.getInstance().sendUDPUpdatePeers(data.toString().getBytes());			
						
		} catch (Exception e) {  
			System.out.println("B£AD@@@@@@@@ WYSY£U");
		}  		
	}	
}