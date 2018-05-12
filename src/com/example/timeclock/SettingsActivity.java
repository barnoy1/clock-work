package com.example.timeclock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class SettingsActivity extends Activity implements OnItemSelectedListener {
	
	Spinner SpinnerWeekendDays;
	ArrayAdapter<CharSequence> adapterWeekEnd;
	
	Spinner SpinnerBreak;
	ArrayAdapter<CharSequence> adapterBreak;
	
	RadioButton RadioButtonOrdinary;
	RadioButton RadioButtonWeekEnd;
	RadioButton RadioButtonGlobal;
	RadioButton RadioButtonHours;
	EditText editTextNetoHours;
	EditText editTextOV1;
	EditText editTextTransportation;
	EditText editTexSalary;
	
	private String sSelection ="";
	private String SelectedLanguage="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	  //get language
	  	SelectedLanguage = LoadFromInternalMemory(getApplicationContext(),"Language");
	  	if (SelectedLanguage.equals("heb")==true) setContentView(R.layout.activity_settings_heb);
	  	else setContentView(R.layout.activity_settings_en);
	    SetupSettings();
	}
	
	// ---------------------Begin onBackPressed-------------------// 
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	
	    	// save current data
	    	SaveConfigData("Transport");
			SaveConfigData("Salary");
			SaveConfigData ("Neto");
			SaveConfigData ("OV1");
			
	    	Intent i = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(i);             
            // close this activity
            if (SelectedLanguage.equals("heb")==true)
            {
            	Toast.makeText(getApplicationContext(), "הנתונים נשמרו בהצלחה", Toast.LENGTH_LONG).show();
            }
            else
            {
            	Toast.makeText(getApplicationContext(), "data is saved", Toast.LENGTH_LONG).show();            	
            }
            
            finish();
	        return true;
	    }	   
	    return super.onKeyDown(keyCode, event);
	}
	// ---------------------End onBackPressed-------------------//
	
	// -------------------Begin OrdinaryOrWeekenListener----------------------//
	private OnClickListener OrdinaryOrWeekendListener = new OnClickListener() {
	        public void onClick(View v){
	        
	        switch (v.getId()){
	        
	        case R.id.radioButtonOrdinary:	
	        	
	        	RadioButtonOrdinary.setChecked(true);
	        	RadioButtonWeekEnd.setChecked(false);
	        	sSelection="O";
	        	break;
	        
	        case R.id.radioButtonWeekEnd:
	        	RadioButtonOrdinary.setChecked(false);
	        	RadioButtonWeekEnd.setChecked(true);
	        	sSelection="W";
	        	break;
	        }
	        
	        LoadOrdinaryWeekendData(sSelection);	        
	    }
	 };
	// -------------------End OrdinaryOrWeekenListener----------------------//
	 
	// -------------------Begin OrdinaryOrWeekenListener----------------------//
		private OnClickListener paymentListener = new OnClickListener() {
		        public void onClick(View v){
		        
		        	String sPaymentType = "";
			        switch (v.getId()){
			        
			        case R.id.radioButtonHours:	
			        	
			        	RadioButtonHours.setChecked(true);
			        	RadioButtonGlobal.setChecked(false);
			        	sPaymentType= "Hourly";
			        	String SalaryConfiguration =  LoadFromInternalMemory(getApplicationContext(),"SalaryConfiguration");		
						editTexSalary.setText(SalaryConfiguration);
						editTexSalary.setEnabled(true);
			        	break;
			        
			        case R.id.radioButtonGlobal:
			        	RadioButtonHours.setChecked(false);
			        	RadioButtonGlobal.setChecked(true);		
			        	editTexSalary.setText("");
			        	editTexSalary.setEnabled(false);
			        	sPaymentType= "Global";
			        	break;
			        }
		        // save payment type
			    SaveToInternalMemory(getApplicationContext(),"PaymentType",sPaymentType);  
			    LoadPaymentData();
		    }
		 };
		// -------------------End OrdinaryOrWeekenListener----------------------//
		 
		 
	//------------------------------------Methods and functions------------------------------//
	//=======================================================================================//
	
	//----------------------------------Begin Initialization of Objects----------------------//
	private void SetupSettings()
	{
		// initiate sSelection
		sSelection="O";
		
		// populate spinners
		SpinnerWeekendDays = (Spinner)findViewById(R.id.SpinnerWeekendDays);
		SpinnerWeekendDays.setTag("WeekEndDays");
		PopulateSpinners("SpinnerWeekendDays");
		
		SpinnerBreak = (Spinner)findViewById(R.id.spinnerBreak);
		SpinnerBreak.setTag("Break");
		PopulateSpinners("SpinnerBreak");
		
		// set options: ordinary/weekend and load data accordingly 
		RadioButtonOrdinary = (RadioButton)findViewById(R.id.radioButtonOrdinary);
		RadioButtonWeekEnd = (RadioButton)findViewById(R.id.radioButtonWeekEnd);
		RadioButtonOrdinary.setOnClickListener(OrdinaryOrWeekendListener);
		RadioButtonWeekEnd.setOnClickListener(OrdinaryOrWeekendListener);
		RadioButtonOrdinary.setChecked(true);
		RadioButtonWeekEnd.setChecked(false);
		
		RadioButtonGlobal = (RadioButton)findViewById(R.id.radioButtonGlobal);
		RadioButtonHours = (RadioButton)findViewById(R.id.radioButtonHours);
		RadioButtonGlobal.setOnClickListener(paymentListener);
		RadioButtonHours.setOnClickListener(paymentListener);
		
	    editTextNetoHours = (EditText)findViewById(R.id.editTextNetoHours);
	    editTextOV1 = (EditText)findViewById(R.id.editTextOV1);	   	
	    editTextTransportation = (EditText)findViewById(R.id.editTextTransportation);
	    editTexSalary = (EditText)findViewById(R.id.editTexSalary);
	    
	    LoadOrdinaryWeekendData(sSelection);
	    LoadPaymentData();
	    
	  //Saving editTextNetoHours
	  editTextNetoHours.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if (actionId==EditorInfo.IME_ACTION_DONE)
				{	
					SaveConfigData ("Neto");
				}
				return false;
			}
  	   });	  
	  
	  //Saving editTextOV1
	  editTextOV1.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if (actionId==EditorInfo.IME_ACTION_DONE)
				{							
					SaveConfigData ("OV1");
				}
				return false;
			}
  	   });
	  	
	  //Saving editTextTransportation
	  editTextTransportation.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if (actionId==EditorInfo.IME_ACTION_DONE)
				{				
					SaveConfigData("Transport");					
				}
				return false;
			}
  	   });
	  
	  //Saving editTexSalary
	  editTexSalary.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				if (actionId==EditorInfo.IME_ACTION_DONE)
				{				
					SaveConfigData("Salary");					
				}
				return false;
			}
  	   });
	  
	  
	  SpinnerBreak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	        
	         public void onItemSelected(AdapterView<?> parent, View view, 
	                    int pos, long id) {
	                
	        	 	if (pos<0) pos=0;
	        	 	
	        	 	if (sSelection=="O")
	        	 	{	        	 		
	        	 		// on other case: save the selected item of spinner to memory
	        	 		SaveToInternalMemory(getApplicationContext(),"Break_" + sSelection, String.valueOf(pos));			     
	        	 	}
	        	 	
	        	 	else if  (sSelection=="W")
	        	 		
	        	 	{	        	 		
	        	 		// on other case: save the selected item of spinner to memory
	        	 		SaveToInternalMemory(getApplicationContext(),"Break_" + sSelection, String.valueOf(pos));			                	        	 	
	        	 	}	        	 	
	            }

	            public void onNothingSelected(AdapterView<?> parent) {
	                // Do nothing, just another required interface callback
	            }

	    }); 
	  
	  SpinnerWeekendDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {	        
	         public void onItemSelected(AdapterView<?> parent, View view, 
	                    int pos, long id) {
	        	 		
	        	 		if (pos<0) pos=0;
	        	 		// on other case: save the selected item of spinner to memory
	        	 		SaveToInternalMemory(getApplicationContext(),"WeekendConfiguraion", String.valueOf(pos));		        	 		
	         }
	           
	            public void onNothingSelected(AdapterView<?> parent) {
	                // Do nothing, just another required interface callback
	            }

	    }); 
	  
	}
	//----------------------------------End Initialization of Objects----------------------//
	
	//----------------------------------Begin PopulateSpinners-----------------------------//
	private void PopulateSpinners(String SpinnerName)
	{
		if (SpinnerName=="SpinnerWeekendDays")
		{
			String[] StringWeekendArray_heb = { "שישי-שבת", "שבת-ראשון", "חמישי-שישי" };
			String[] StringWeekendArray_en = { "friday-saturday", "saturday-sunday", "thursday-friday" };
			String[] StringWeekendArray = null;
			
			// Create an ArrayAdapter using the fixed aray array 
	         //and set a default spinner layout
			if (SelectedLanguage.equals("heb")) StringWeekendArray = StringWeekendArray_heb;
			else  StringWeekendArray = StringWeekendArray_en;
			
			 
			 Spinner selectedSpinner = (Spinner)findViewById(R.id.SpinnerWeekendDays);
			 adapterWeekEnd = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,StringWeekendArray);
			 adapterWeekEnd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	         // Apply the adapter to the spinner
	         selectedSpinner.setAdapter(adapterWeekEnd);
		}
		
		else if (SpinnerName=="SpinnerBreak")
		{
			
			 // Create an ArrayAdapter using the fixed aray array 
	         //and set a default spinner layout
			 String[] StringBreakArray_heb = { "ללא", "15 דקות", "20 דקות" , "30 דקות", "45 דקות", "60 דקות" };
			 String[] StringBreakArray_en = { "none", "15 minutes", "20 minutes" , "30 minutes", "45 minutes", "60 minutes" };
			 String[] StringBreakArray = null;
			 
			 if (SelectedLanguage.equals("heb")) StringBreakArray = StringBreakArray_heb;
			 else  StringBreakArray = StringBreakArray_en;
			 
			 Spinner selectedSpinner = (Spinner)findViewById(R.id.spinnerBreak);
			 adapterBreak = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,StringBreakArray);
			 adapterBreak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	         // Apply the adapter to the spinner
	         selectedSpinner.setAdapter(adapterBreak);
		}
		
	}
	//----------------------------------End PopulateSpinners-------------------------------//
	
	private void LoadPaymentData()
	{
		
		String sPayment = LoadFromInternalMemory(getApplicationContext(),"PaymentType");		
		
		if (sPayment.equals("Hourly")==true)
		{
			String SalaryConfiguration =  LoadFromInternalMemory(getApplicationContext(),"SalaryConfiguration");		
			editTexSalary.setText(SalaryConfiguration);
			RadioButtonHours.setChecked(true);
			editTexSalary.setEnabled(true);
			RadioButtonGlobal.setChecked(false);			
		}
		else
		{
			editTexSalary.setText("");
			editTexSalary.setEnabled(false);
			RadioButtonHours.setChecked(false);
			RadioButtonGlobal.setChecked(true);
		}		
		
	}
	//--------------------------------- Begin LoadOrdinaryWeekendData----------------------//
	private void LoadOrdinaryWeekendData(String sSelection)
	{
		// sSelection=="O": Ordinary
		// sSelection=="W": Weekend
		
		String NetoHoursConfiguraion = LoadFromInternalMemory(getApplicationContext(),"NetoHoursConfiguration_" + sSelection);		
		editTextNetoHours.setText(NetoHoursConfiguraion.trim().toString());	
		String OV1HoursConfiguraion = LoadFromInternalMemory(getApplicationContext(),"OV1HoursConfiguraion_" + sSelection);
		editTextOV1.setText(OV1HoursConfiguraion);				
		String FareConfiguration = LoadFromInternalMemory(getApplicationContext(),"FareConfiguration");
		editTextTransportation.setText(FareConfiguration); 
		
		
		//set the default according to value
		String BreakConfiguration = LoadFromInternalMemory(getApplicationContext(),"Break_" + sSelection);			
		if (BreakConfiguration.length()==0) 
		{
			SaveToInternalMemory(getApplicationContext(),"Break_" + sSelection,"0");
			SpinnerBreak.setSelection(0,true);		
							
		}
		else
		{			
			if (BreakConfiguration.contains("1")==true) SpinnerBreak.setSelection(1,true);					
			else if (BreakConfiguration.contains("2")==true) SpinnerBreak.setSelection(2,true);
			else if (BreakConfiguration.contains("3")==true) SpinnerBreak.setSelection(3,true);
			else if (BreakConfiguration.contains("4")==true) SpinnerBreak.setSelection(4,true);
			else if (BreakConfiguration.contains("5")==true) SpinnerBreak.setSelection(5,true);
			else  SpinnerBreak.setSelection(0,true);
			
		}
		
		
		String WeekendConfiguraion = LoadFromInternalMemory(getApplicationContext(),"WeekendConfiguraion");		
		if (WeekendConfiguraion.length()==0) 
		{
			SaveToInternalMemory(getApplicationContext(),"WeekendConfiguraion","0");
			SpinnerWeekendDays.setSelection(0,true);								
		}
		else
		{			
			if (WeekendConfiguraion.contains("1")==true) SpinnerWeekendDays.setSelection(1,true);			
			else if (WeekendConfiguraion.contains("2")==true) SpinnerWeekendDays.setSelection(2,true);			
			else  SpinnerWeekendDays.setSelection(0,true);
			
		}
		
		
		// if ordinary: disable the weekendspinner
		// else: enable it and load the current weekend days
		if (sSelection=="O") SpinnerWeekendDays.setEnabled(false);		
		else SpinnerWeekendDays.setEnabled(true);

	}
	//----------------------------------End LoadOrdinaryWeekendData----------------------//
	
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
	
	private void SaveConfigData(String sField)
	{		
		if (sField.length()>0)
		{			
			if (sField.equals("Transport")==true)
			{
				SaveToInternalMemory(getApplicationContext(),"FareConfiguration", editTextTransportation.getText().toString());					
			}
			else if (sField.equals("Salary")==true)
			{
				SaveToInternalMemory(getApplicationContext(),"SalaryConfiguration", editTexSalary.getText().toString());					
			}
			else if (sField.equals("Neto")==true)
			{
				String editTextValue = editTextNetoHours.getText().toString();
				double doubleValue = Double.parseDouble(editTextValue);
				doubleValue = Math.floor(doubleValue * 100) / 100;
				if (doubleValue>24) 
				{
					doubleValue=0;	
					editTextNetoHours.setText("0");
					if (SelectedLanguage.equals("heb")==true)
					{
						Toast.makeText(getApplicationContext(), "לא יתכן שהגדרת יום תהיה יותר מ-24 שעות. שעות נטו אותחלו ל-0", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Day setup is contain more than 24 hours: Neto hours set to 0", Toast.LENGTH_LONG).show();
					}				
				}
				SaveToInternalMemory(getApplicationContext(),"NetoHoursConfiguration_" + sSelection, String.valueOf(doubleValue));				
			}
			else if (sField.equals("OV1")==true)
			{
				String editTextValue = editTextOV1.getText().toString();
				String editTextNeto = editTextNetoHours.getText().toString();					
				double doubleNetoValue = Double.parseDouble(editTextNeto);
				doubleNetoValue = Math.floor(doubleNetoValue * 100) / 100;					
				double doubleValue = Double.parseDouble(editTextValue);
				doubleValue = Math.floor(doubleValue * 100) / 100;
				
				if (doubleValue+doubleNetoValue>24)
				{
					doubleValue=0;
					editTextOV1.setText("0");
					if (SelectedLanguage.equals("heb")==true)
					{
						Toast.makeText(getApplicationContext(), "לא יתכן שהגדרת יום תהיה יותר מ-24 שעות. שעות נוספות אותחלו ל-0", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Day setup is contain more than 24 hours: Overtime set to 0", Toast.LENGTH_LONG).show();
					}
					
				}					
				SaveToInternalMemory(getApplicationContext(),"OV1HoursConfiguraion_" + sSelection, editTextOV1.getText().toString());					
			}
		}
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
		
}
