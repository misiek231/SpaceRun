package com.mygdx.game;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	//	WarpClient.initialize("21f7179644c0942a92b45382f0607de7abfc8a86704878edfb82dfba7e1363ca","34ca1352cb49b74f9c709b4482c997822156cd5e04f295e0559774de64bf837a"); 

		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Berek(), config);
	}
}
