package com.mygdx.connection;

import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;
import com.mygdx.random.objects.RandomObject;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;

public class NotificationListener implements NotifyListener {

	Berek game;
	TextButton ok;
	TextButton no;
	
	public NotificationListener(Berek game) {
		this.game = game;
	}

	@Override
	public void onChatReceived(ChatEvent arg0) {
		System.out.println(arg0.getMessage());
	}

	@Override
	public void onGameStarted(String arg0, String arg1, String arg2) {
		System.out.println("onGameStarted");

	}

	@Override
	public void onGameStopped(String arg0, String arg1) {
		System.out.println("onGameStopped");

	}

	@Override
	public void onMoveCompleted(MoveEvent arg0) {
		
		//System.out.println("onMoveCompleted " + arg0.getMoveData());
		
		if(!game.server){
			
			try {  
				JSONObject data = new JSONObject(new String( arg0.getMoveData() ) );  
				float x1 = (float)data.getDouble("x1");  
				float y1 = (float)data.getDouble("y1");  
		    
				float x2 = (float)data.getDouble("x2");  
				float y2 = (float)data.getDouble("y2");  
		    
				//System.out.println(x1);
				//System.out.println(y1);
				//System.out.println(x2);
				//System.out.println(y2);
		    
				game.x1 = x1;
				game.y1 = y1;
		    
				game.x2 = x2;
				game.y2 = y2;
		    
		
			} catch (Exception e) {  
				System.out.println("B³AD@@@@@@@@ ODCZYTU@@@@@@@@CLIENT");
			}  
		}
		else{
			
			try {  
				
				JSONObject data = new JSONObject(new String( arg0.getMoveData() ) );  
				
				float knobX = (float)data.getDouble("knobX");  
				float knobY = (float)data.getDouble("knobY");  
		    
				System.out.println(knobX);
				System.out.println(knobY);
			
				game.Knobx2 = knobX;
				game.Knoby2 = knobY;
		    
		
			} catch (Exception e) {  
				System.out.println("B³AD@@@@@@@@ ODCZYTU@@@@@@@@SERVER");
			}  			
		}
	}

	@Override
	public void onNextTurnRequest(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrivateChatReceived(String arg0, final String arg1) {
		
		System.out.println("onPrivateChatReceived " + arg1);
	
		ok = new TextButton("Zagraj",game.msgskin);
		
		ok.scaleBy(3);
		
		ok.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				zagraj(arg1);
				super.clicked(event, x, y);
			}
		});
		
		no = new TextButton("Anuluj",game.msgskin);
		
		no.scaleBy(3);
		
		no.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Zagraj");
				super.clicked(event, x, y);
			}
		});
		
		Dialog msgbox = new Dialog("Zaproszenie", game.msgskin);
		
		msgbox.text(new Label("Gracz " + arg0 + " zaprosi³ ciê do gry. Czy chcesz z nim zagraæ? ", game.msgskin));
		
		msgbox.scaleBy(0.5f);

		msgbox.button(ok);
		
		msgbox.button(no);
		
		msgbox.show(game.stage);
		
		if(arg1.equals("wyzwanie")){
			


		}
	
	}
	
	private void zagraj(String arg1) {		
		
		String[] data = arg1.split(",");
		
		System.out.println(data[0] + " " + data[1]);
		
		game.roomId = data[1];
		
		try {
			
			WarpClient.getInstance().joinRoom(game.roomId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onPrivateUpdateReceived(String arg0, byte[] arg1, boolean arg2) {
		
		System.out.println("onPrivateUpdateReceived " + arg1.toString());
		
		if(!game.server){
			
			try { 
				
				JSONObject data = new JSONObject(new String( arg1 ) );  
				float x1 = (float)data.getDouble("x1");  
				float y1 = (float)data.getDouble("y1");  
		    
				float x2 = (float)data.getDouble("x2");  
				float y2 = (float)data.getDouble("y2");  
		    
				System.out.println(x1);
				System.out.println(y1);
				System.out.println(x2);
				System.out.println(y2);
		    
				game.x1 = x1;
				game.y1 = y1;
		    
				game.x2 = x2;
				game.y2 = y2;
	
			} catch (Exception e) {  
				System.out.println("B³AD@@@@@@@@ ODCZYTU@@@@@@@@CLIENT");
			}  
		}
		else{
			
			try {  
				
				JSONObject data = new JSONObject(new String( arg1 ) );  
				
				float knobX = (float)data.getDouble("knobX");  
				float knobY = (float)data.getDouble("knobY");  
		    
				System.out.println(knobX);
				System.out.println(knobY);
			
				game.Knobx2 = knobX;
				game.Knoby2 = knobY;
		    		
			} catch (Exception e) {  
				System.out.println("B³AD@@@@@@@@ ODCZYTU@@@@@@@@SERVER");
			} 			
		}
	}

	@Override
	public void onRoomCreated(RoomData arg0) {
		
	}

	@Override
	public void onRoomDestroyed(RoomData arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onUpdatePeersReceived(UpdateEvent arg0) {
		
		System.out.println("onUpdatePeersReceived");
		
		if(!game.server){
			
	/*		try {  
			
			JSONObject data = new JSONObject(new String( arg0.getUpdate() ) ); 
			
			//game.randomObjectsControler.AddObject( (float)data.getDouble("objx") , (float)data.getDouble("objy") , (int)data.getInt("objtyp")); 
			
			System.out.println("odbiur");
			
			System.out.println((float)data.getDouble("objx"));
			
			System.out.println((float)data.getDouble("objy") );
			
			System.out.println((int)data.getInt("objtyp"));
			
			}catch (Exception e) {
				System.out.println("błąd odbioru");
			}*/
			
	            try {  
	                JSONObject data = new JSONObject(new String( arg0.getUpdate() ) );  
	                float x1 = (float)data.getDouble("x1");  
	                float y1 = (float)data.getDouble("y1");  
	           
	                float x2 = (float)data.getDouble("x2");  
	                float y2 = (float)data.getDouble("y2");
	               
	                boolean b1 = (boolean)data.getBoolean("b1");
	           
	            //  System.out.println(x1);
	            //  System.out.println(y1);
	            //  System.out.println(x2);
	            //   System.out.println(y2);
	            
	                game.x1 = x1;
	                game.y1 = y1;
	           
	                game.x2 = x2;
	                game.y2 = y2;
	               
	                game.player1.isBerek = b1;
	                game.player2.isBerek = !game.player1.isBerek;
	                
	                game.curentRoundTime = (String)data.get("time");
	           
	       
	            } catch (Exception e) {  
	                //System.out.println("BłAD@@@@@@@@ ODCZYTU@@@@@@@@CLIENT");
	            }  
	        }
	        else{
	           
	            try {  
	               
	                JSONObject data = new JSONObject(new String( arg0.getUpdate() ) );  
	               
	                float knobX = (float)data.getDouble("knobX");  
	                float knobY = (float)data.getDouble("knobY");  
	           
	                //System.out.println(knobX);
	                //System.out.println(knobY);
	           
	                game.Knobx2 = knobX;
	                game.Knoby2 = knobY;
	           
	       
	            } catch (Exception e) {  
	                //System.out.println("BłAD@@@@@@@@ ODCZYTU@@@@@@@@SERVER");
	            }
	        }

	}

	@Override
	public void onUserChangeRoomProperty(RoomData arg0, String arg1, HashMap<String, Object> arg2,
			HashMap<String, String> arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserJoinedLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserJoinedRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserLeftRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserPaused(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserResumed(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub

	}

}
