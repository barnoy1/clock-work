package com.example.timeclock;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Utils {

	
	//-----------------------------Begin sendEmail----------------------------------//
		public static Intent getSendEmailIntent(Context context, String email,
	            String subject, String body, String Filename) {
		
		File f=new File(context.getFilesDir(), Filename);
	    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);		 
	    //Explicitly only use Gmail to send
	    emailIntent.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");		 
	    emailIntent.setType("plain/text");		 
	    //Add the recipients
	    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { email });		 
	    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);		 
	    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
	 
	    //Add the attachment by specifying a reference to our custom ContentProvider
	    //and the specific file of interest	   
	    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/" + Filename));
	 
	    return emailIntent;
		  
	   }
	//-----------------------------End sendEmail----------------------------------//
}
