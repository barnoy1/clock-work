package com.example.timeclock;


import java.io.IOException;
import java.util.TimeZone;

import android.R.drawable;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;
 
public class SplashScreen extends Activity {
	
	
	 // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_splash);
        
        FirstLoadLanguage();        
        LaunchSpashScreen();
        
        new Handler().postDelayed(new Runnable() {
        	
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    
    //===============================METHODS AND FUNCTIONS===================================//
    //---------------------------------------------------------------------------------------//

    
    //----------------------------------Begin SaveToInternalMemory----------------------//
	private void SaveToInternalMemory(Context mContext, String variableName, String variableValue)
	{			
		// Save new value
		SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);  
	    SharedPreferences.Editor editor = prefs.edit(); 
	    editor.putString(variableName , variableValue);  
	    editor.commit();		      
	}
	//----------------------------------End SaveToInternalMemory------------------------//
	
	//-----------------Begin LoadFromInternalMemory---------------------------//
	private String LoadFromInternalMemory(Context mContext, String variableName)
	{
		String value = "";
		SharedPreferences prefs;
		
		// Load previous value
		prefs = mContext.getSharedPreferences("preferencename", 0);  
		value = prefs.getString(variableName, "");
		return value;
	}	
	//-----------------End LoadFromInternalMemory---------------------------//	
	
	
    private void FirstLoadLanguage()
    {
    	String SelectedLanguage = LoadFromInternalMemory(getApplicationContext(),"Language");	
    	if (SelectedLanguage.length()==0)
    	{    		
    		// set the language for the first time
    		TimeZone tz = TimeZone.getDefault();
        	String[] Location = tz.getID().split("/");
        	if (Location[1].equals("Jerusalem")) SaveToInternalMemory(getApplicationContext(),"Language","heb");
        	else SaveToInternalMemory(getApplicationContext(),"Language","en");
        	
    	}
    	
    }
    
    private void LaunchSpashScreen()
    {
    	ImageView imgSplash = (ImageView)findViewById(R.id.imgSplash);    	
    	String SelectedLanguage = LoadFromInternalMemory(getApplicationContext(),"Language");
    	
    	if (SelectedLanguage.equals("heb"))
    	{
    		// load a heb splash version     		
    		imgSplash.setImageDrawable(getResources().getDrawable(R.drawable.splash_clock));    		 
    	}
    	else
    	{
    		// load an english splash version        	
    		imgSplash.setImageDrawable(getResources().getDrawable(R.drawable.splash_clock_en));    		
    	}
    }
  
}
