package com.mygdx.connection;

import com.shephertz.app42.gaming.multiplayer.client.listener.ChatRequestListener;

public class ChatListener implements ChatRequestListener {

	@Override
	public void onSendChatDone(byte arg0) {
		System.out.println(arg0);

	}

	@Override
	public void onSendPrivateChatDone(byte arg0) {
		System.out.println("onSendPrivateChatDone: " + arg0);

	}

}
