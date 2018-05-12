package com.example.timeclock;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



public class MainActivity extends Activity {
			
	Button btnPunch;
	Button btnSettings;
	
	ScrollView scrollViewTable;
	
	TextView txtVpunch;
	TextView txtVtitle;
	
	TextView CellSummeryTotalHoursValue;
	TextView CellSummeryOV1Value;
	TextView CellSummeryOV2Value;
	TextView CellSummerySalary;
	
	TableLayout tableLayout;
	TableRow currentTableRow; // global blank (unassigned) Tablerow element
		
	String[] sHour_S = new String[32];
	String[] sHour_E = new String[32];
	
	String[] sHour_S_1_MonthBefore = new String[32];
	String[] sHour_E_1_MonthBefore = new String[32];
	
	String[] sHour_S_2_MonthBefore = new String[32];
	String[] sHour_E_2_MonthBefore = new String[32];
	
	String[] sHour_S_3_MonthBefore = new String[32];
	String[] sHour_E_3_MonthBefore = new String[32];
	
	String[] sHour_S_4_MonthBefore = new String[32];
	String[] sHour_E_4_MonthBefore = new String[32];
	
	String[] sHour_S_5_MonthBefore = new String[32];
	String[] sHour_E_5_MonthBefore = new String[32];
	
	String[] sHour_S_6_MonthBefore = new String[32];
	String[] sHour_E_6_MonthBefore = new String[32];
	
	String[] sHour_S_7_MonthBefore = new String[32];
	String[] sHour_E_7_MonthBefore = new String[32];
	
	String[] sHour_S_8_MonthBefore = new String[32];
	String[] sHour_E_8_MonthBefore = new String[32];
	
	String[] sHour_S_9_MonthBefore = new String[32];
	String[] sHour_E_9_MonthBefore = new String[32];
	
	String[] sHour_S_10_MonthBefore = new String[32];
	String[] sHour_E_10_MonthBefore = new String[32];
	
	String[] sHour_S_11_MonthBefore = new String[32];
	String[] sHour_E_11_MonthBefore = new String[32];
	
	String[] sHour_S_12_MonthBefore = new String[32];
	String[] sHour_E_12_MonthBefore = new String[32];
	
	boolean[] isDayWeekend = new boolean[32];
	private int[] WeekendDayValue= new int [2];
	
	private int thisMonthIndex=0;
	private int NumOfMonthsAllowToUpdate=2;
	
	// User settings
	private String NetoHoursConfiguraion_O="";
	private String OV1HoursConfiguraion_O="";
	private String BreakConfiguration_O="";
	private String NetoHoursConfiguraion_W="";
	private String OV1HoursConfiguraion_W="";
	private String BreakConfiguration_W="";
	private String FareConfiguration="";
	
	private String sPayment="";
	private String SalaryConfiguration="";
	
	private int daysInThisMonth=0;
	//Titles
	TextView Cell00;
	TextView CellD0;
	TextView Cell01;
	TextView Cell02;
	TextView Cell03;
	TextView Cell04;
	TextView Cell05;
	TextView Cell06;
	
	// row No. 1
	TextView Cell10;
	TextView Cell11;
	TextView Cell12;
	// row No. 2
	TextView Cell20;
	TextView Cell21;
	TextView Cell22;
	// row No. 3
	TextView Cell30;
	TextView Cell31;
	TextView Cell32;
	// row No. 4
	TextView Cell40;
	TextView Cell41;
	TextView Cell42;
	// row No. 5
	TextView Cell50;
	TextView Cell51;
	TextView Cell52;
	// row No. 6
	TextView Cell60;
	TextView Cell61;
	TextView Cell62;
	// row No. 7
	TextView Cell70;
	TextView Cell71;
	TextView Cell72;
	// row No. 8
	TextView Cell80;
	TextView Cell81;
	TextView Cell82;
	// row No. 9
	TextView Cell90;
	TextView Cell91;
	TextView Cell92;
	// row No. 10
	TextView Cell100;
	TextView Cell101;
	TextView Cell102;
	// row No. 11
	TextView Cell110;
	TextView Cell111;
	TextView Cell112;
	// row No. 12
	TextView Cell120;
	TextView Cell121;
	TextView Cell122;	
	// row No. 13
	TextView Cell130;
	TextView Cell131;
	TextView Cell132;
	// row No. 14
	TextView Cell140;
	TextView Cell141;	
	TextView Cell142;	
	// row No. 15
	TextView Cell150;
	TextView Cell151;
	TextView Cell152;
	// row No. 16
	TextView Cell160;
	TextView Cell161;
	TextView Cell162;
	// row No. 17
	TextView Cell170;
	TextView Cell171;
	TextView Cell172;	
	// row No. 18
	TextView Cell180;
	TextView Cell181;
	TextView Cell182;
	// row No. 19
	TextView Cell190;
	TextView Cell191;
	TextView Cell192;
	// row No. 20
	TextView Cell200;
	TextView Cell201;
	TextView Cell202;	
	// row No. 21
	TextView Cell210;
	TextView Cell211;
	TextView Cell212;
	// row No. 22
	TextView Cell220;
	TextView Cell221;
	TextView Cell222;
	// row No. 23
	TextView Cell230;
	TextView Cell231;
	TextView Cell232;
	// row No. 24
	TextView Cell240;
	TextView Cell241;
	TextView Cell242;
	// row No. 25
	TextView Cell250;
	TextView Cell251;
	TextView Cell252;	
	// row No. 26
	TextView Cell260;
	TextView Cell261;
	TextView Cell262;
	// row No. 27
	TextView Cell270;
	TextView Cell271;	
	TextView Cell272;	
	// row No. 28
	TextView Cell280;
	TextView Cell281;
	TextView Cell282;
	// row No. 29
	TextView Cell290;
	TextView Cell291;
	TextView Cell292;
	// row No. 30
	TextView Cell300;
	TextView Cell301;
	TextView Cell302;	
	// row No. 31
	TextView Cell310;
	TextView Cell311;
	TextView Cell312;
	
	// addresses
	public static final String  FILENAME = "myHoursRecord.xls";	
	public static final String UserFile = "myFile";
	public String SelectedLanguage ="";
	public String UserLangTitle ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		SetLanguage();			
		GetUserConfigurationSettings();
		SetupObjects();		
		LoadPunchState(getApplicationContext());		
		LoadData();		
		SetScrollViewToChild();
        Log.d("MY_ACTIVITY", "On Create Done" );
	}
	
	//=============================Menu Creation====================================//
	
	//-----------------------------Begin ActionBar----------------------------------//	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		MenuItem itemPrev = menu.findItem(R.id.itemPrev);
		MenuItem itemNext = menu.findItem(R.id.itemNext);		
		MenuItem itemFastPrev = menu.findItem(R.id.itemFastPrev);
		
		MenuItem itemMail = menu.findItem(R.id.itemMail);
		MenuItem itemChangeMail = menu.findItem(R.id.itemChangeMail);
		MenuItem itemChangeLang = menu.findItem(R.id.itemChangeLanguage);
		
		
		itemPrev.setIcon(android.R.drawable.ic_media_rew);
		itemNext.setIcon(android.R.drawable.ic_media_ff);			
		itemFastPrev.setIcon((android.R.drawable.ic_media_next));
		
		if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
		{
			itemMail.setTitle("שלח דוח שעות למייל");
			itemChangeMail.setTitle("שנה כתובת מייל");
			itemChangeLang.setTitle("החלף שפה");
		}
		else
		{
			itemMail.setTitle("export hours record to mail");
			itemChangeMail.setTitle("Change mail address");					
			itemChangeLang.setTitle("Change lanuage");
		}
		
		itemPrev.setEnabled(true);
		itemNext.setEnabled(true);
		
		//btnReset.setVisibility(View.GONE);
		btnPunch.setVisibility(View.GONE);
		
		
		switch (thisMonthIndex)
		{
		case 0:
			itemNext.setEnabled(false); // now			
			btnPunch.setVisibility(View.VISIBLE);
			break;	
		case 12:
			itemPrev.setEnabled(false); // 2 months ago			
			break;
		}
				
		InitializeArray();			
		SetTableData(tableLayout);
		LoadData();		
		return true;
	}
	
	 @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.itemMail:
	    	try 
	    	{
	    		try {
					WriteTable_XlsFile();
				} catch (WriteException e) {					
					e.printStackTrace();
				}	    			    		
	    		
	    		/*
	    		writeToFile(CreateTextTable());	
	    		String sdFile = readFromFile();
	    		*/
	    		
	    		// load this method from Utils class in order to call getSendEmailIntent method
	    	   String EmailAddress = LoadFromInternalMemory(getApplicationContext(),"EmailAddress");	
	    	   if ((SelectedLanguage.trim().equals("heb")))
	    	   {
	    		   startActivity(Utils.getSendEmailIntent(MainActivity.this, EmailAddress, "דוח שעות חודשי", "ראה פירוט בקובץ המצורף", FILENAME));	          	        
	    	   }
	    	   else
	    	   {
	    		   startActivity(Utils.getSendEmailIntent(MainActivity.this, EmailAddress, "time record", "see attachment", FILENAME));	          	        
	    	   }
	           
	        } 
	    	catch (ActivityNotFoundException e) {
	            Toast.makeText(MainActivity.this,
	                "Gmail is not available on this device.",
	                Toast.LENGTH_SHORT).show();
	        }
	    
	      break;
	     
	    case R.id.itemChangeMail:
	    	EmailDiaglog();
	    	break;
	    	
	    case R.id.itemPrev:
	    	Step(1);	    	
	    	invalidateOptionsMenu();
	    	break;
	    	
	    case R.id.itemNext:
	    	Step(-1);	    	
	    	invalidateOptionsMenu();
	    	break;
	    
	    case R.id.itemFastPrev:
	    	thisMonthIndex=0;
	    	invalidateOptionsMenu();
	    	break;
	    
	    case R.id.itemChangeLanguage:
	    	
	        openContextMenu(btnSettings);
	        
	    	break;
	    }
	    return true;
	 }
	 
	 //---------------------------------End ActionBar--------------------//
	
	
	//--------------------------Begin Context menu------------------------------//
	 @Override
		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		      super.onCreateContextMenu(menu, v, menuInfo);
		      	  
	      		  menu.setHeaderTitle("select language");
		          MenuInflater inflater = getMenuInflater();
		          inflater.inflate(R.menu.contextmenu, menu);	
		          
		          SelectedLanguage = LoadFromInternalMemory(getApplicationContext(),"Language");			
				
			       // loop for menu items
		          for (int i = 0; i < menu.size(); ++i) {
		              MenuItem mi = menu.getItem(i);
		              
		              // check the Id as you wish
		              if ((SelectedLanguage.trim().equals("heb")))
		              {
		            	  // check V in connected 
		            	  if (mi.getItemId() == R.id.itemHeb) {
			                  mi.setChecked(true); 
			              }
		              }
		              
		              else
		              {
		            	  // check V in disconnected
		            	  if (mi.getItemId() == R.id.itemEn) {
			                  mi.setChecked(true); 
			              }
		              }
		              
		              
		          }
			          
		      	 
		}

		@Override
		public boolean onContextItemSelected(MenuItem item) {
		      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		      
		      switch(item.getItemId()) {
		      
		         case R.id.itemEn:
		        	 if (item.isChecked())  item.setChecked(true);		        	
		        	 else item.setChecked(false);	
		        	 
		        	 SaveToInternalMemory(getApplicationContext(),"Language","en");
		        	 SetLanguage();
				     invalidateOptionsMenu();	
		             return true;		            
		            
		          case R.id.itemHeb:
		        	  
		        	  if (item.isChecked()) item.setChecked(true);		        	 
		        	  else item.setChecked(false);	
		        	  
		        	  SaveToInternalMemory(getApplicationContext(),"Language","heb");
		        	  SetLanguage();
				      invalidateOptionsMenu();	
		              return true;	         
		                
		          default:
		        	  return super.onContextItemSelected(item);
		      }
		      
		     	      
		    	
		}
	
	
	//--------------------------End Context menu------------------------------//
		
	//------------------------begin Setup objects----------------------//
	private void SetupObjects(){
		
	//declare objects     
    btnPunch = (Button)findViewById(R.id.btnPunch);
    btnSettings = (Button)findViewById(R.id.btnSettings);    
    scrollViewTable = (ScrollView)findViewById(R.id.scrollViewTable);
    
    txtVtitle = (TextView) findViewById(R.id.txtVtitle);
    tableLayout = (TableLayout)findViewById(R.id.tableLayout);
    txtVpunch = (TextView)findViewById(R.id.txtVpunch);
    
	 CellSummeryTotalHoursValue = (TextView)findViewById(R.id.CellSumNeto);
	 CellSummeryOV1Value = (TextView)findViewById(R.id.CellSumOV1);
	 CellSummeryOV2Value = (TextView)findViewById(R.id.CellSumOV2);
	 CellSummerySalary = (TextView)findViewById(R.id.CellSumSalary);
	 
    //Row No. 1
    Cell10 = (TextView)findViewById(R.id.Cell10);
    Cell11 = (TextView)findViewById(R.id.Cell11);
    Cell12 = (TextView)findViewById(R.id.Cell12);
    //Row No. 2
    Cell20 = (TextView)findViewById(R.id.Cell20);
    Cell21 = (TextView)findViewById(R.id.Cell21);
    Cell22 = (TextView)findViewById(R.id.Cell22);
    //Row No. 3
    Cell30 = (TextView)findViewById(R.id.Cell30);
    Cell31 = (TextView)findViewById(R.id.Cell31);
    Cell32 = (TextView)findViewById(R.id.Cell32);
    //Row No. 4
    Cell40 = (TextView)findViewById(R.id.Cell40);
    Cell41 = (TextView)findViewById(R.id.Cell41);
    Cell42 = (TextView)findViewById(R.id.Cell42);
    //Row No. 9
    Cell50 = (TextView)findViewById(R.id.Cell50);
    Cell51 = (TextView)findViewById(R.id.Cell51);
    Cell52 = (TextView)findViewById(R.id.Cell52);
    //Row 8o. 6
    Cell60 = (TextView)findViewById(R.id.Cell60);
    Cell61 = (TextView)findViewById(R.id.Cell61);
    Cell62 = (TextView)findViewById(R.id.Cell62);
    //Row No. 7
    Cell70 = (TextView)findViewById(R.id.Cell70);
    Cell71 = (TextView)findViewById(R.id.Cell71);
    Cell72 = (TextView)findViewById(R.id.Cell72);
    //Row No. 8
    Cell80 = (TextView)findViewById(R.id.Cell80);
    Cell81 = (TextView)findViewById(R.id.Cell81);
    Cell82 = (TextView)findViewById(R.id.Cell82);
    //Row No. 9
    Cell90 = (TextView)findViewById(R.id.Cell90);
    Cell91 = (TextView)findViewById(R.id.Cell91);
    Cell92 = (TextView)findViewById(R.id.Cell92);
    //Row No. 10
    Cell100 = (TextView)findViewById(R.id.Cell100);
    Cell101 = (TextView)findViewById(R.id.Cell101);
    Cell102 = (TextView)findViewById(R.id.Cell102);
    //Row No. 11
    Cell110 = (TextView)findViewById(R.id.Cell110);
    Cell111 = (TextView)findViewById(R.id.Cell111);
    Cell112 = (TextView)findViewById(R.id.Cell112);
    //Row No. 12
    Cell120 = (TextView)findViewById(R.id.Cell120);
    Cell121 = (TextView)findViewById(R.id.Cell121);
    Cell122 = (TextView)findViewById(R.id.Cell122);
    //Row No. 13
    Cell130 = (TextView)findViewById(R.id.Cell130);
    Cell131 = (TextView)findViewById(R.id.Cell131);
    Cell132 = (TextView)findViewById(R.id.Cell132);
    //Row No. 14
    Cell140 = (TextView)findViewById(R.id.Cell140);
    Cell141 = (TextView)findViewById(R.id.Cell141);
    Cell142 = (TextView)findViewById(R.id.Cell142);
    //Row No. 15
    Cell150 = (TextView)findViewById(R.id.Cell150);
    Cell151 = (TextView)findViewById(R.id.Cell151);
    Cell152 = (TextView)findViewById(R.id.Cell152);
    //Row No. 16
    Cell160 = (TextView)findViewById(R.id.Cell160);
    Cell161 = (TextView)findViewById(R.id.Cell161);
    Cell162 = (TextView)findViewById(R.id.Cell162);
    //Row No. 17
    Cell170 = (TextView)findViewById(R.id.Cell170);
    Cell171 = (TextView)findViewById(R.id.Cell171);
    Cell172 = (TextView)findViewById(R.id.Cell172);
    //Row No. 18
    Cell180 = (TextView)findViewById(R.id.Cell180);
    Cell181 = (TextView)findViewById(R.id.Cell181);
    Cell182 = (TextView)findViewById(R.id.Cell182);
    //Row No. 19
    Cell190 = (TextView)findViewById(R.id.Cell190);
    Cell191 = (TextView)findViewById(R.id.Cell191);
    Cell192 = (TextView)findViewById(R.id.Cell192);    
    //Row No. 20
    Cell200 = (TextView)findViewById(R.id.Cell200);
    Cell201 = (TextView)findViewById(R.id.Cell201);
    Cell202 = (TextView)findViewById(R.id.Cell202);
    //Row No. 22
    Cell220 = (TextView)findViewById(R.id.Cell220);
    Cell221 = (TextView)findViewById(R.id.Cell221);
    Cell222 = (TextView)findViewById(R.id.Cell222);
    //Row No. 21
    Cell210 = (TextView)findViewById(R.id.Cell210);
    Cell211 = (TextView)findViewById(R.id.Cell211);
    Cell212 = (TextView)findViewById(R.id.Cell212);
    //Row No. 23
    Cell230 = (TextView)findViewById(R.id.Cell230);
    Cell231 = (TextView)findViewById(R.id.Cell231);
    Cell232 = (TextView)findViewById(R.id.Cell232); 
    //Row No. 24
    Cell240 = (TextView)findViewById(R.id.Cell240);
    Cell241 = (TextView)findViewById(R.id.Cell241);
    Cell242 = (TextView)findViewById(R.id.Cell242);    
    //Row No. 25
    Cell250 = (TextView)findViewById(R.id.Cell250);
    Cell251 = (TextView)findViewById(R.id.Cell251);
    Cell252 = (TextView)findViewById(R.id.Cell252);
    //Row No. 26
    Cell260 = (TextView)findViewById(R.id.Cell260);
    Cell261 = (TextView)findViewById(R.id.Cell261);
    Cell262 = (TextView)findViewById(R.id.Cell262);
    //Row No. 27
    Cell270 = (TextView)findViewById(R.id.Cell270);
    Cell271 = (TextView)findViewById(R.id.Cell271);
    Cell272 = (TextView)findViewById(R.id.Cell272);
    //Row No. 28
    Cell280 = (TextView)findViewById(R.id.Cell280);
    Cell281 = (TextView)findViewById(R.id.Cell281);
    Cell282 = (TextView)findViewById(R.id.Cell282);
    //Row No. 29
    Cell290 = (TextView)findViewById(R.id.Cell290);
    Cell291 = (TextView)findViewById(R.id.Cell291);
    Cell292 = (TextView)findViewById(R.id.Cell292);
    //Row No. 30
    Cell300 = (TextView)findViewById(R.id.Cell300);
    Cell301 = (TextView)findViewById(R.id.Cell301);
    Cell302 = (TextView)findViewById(R.id.Cell302);
    //Row No. 31
    Cell310 = (TextView)findViewById(R.id.Cell310);
    Cell311 = (TextView)findViewById(R.id.Cell311);
    Cell312 = (TextView)findViewById(R.id.Cell312);
  
	//initialize arrays
	InitializeArray();
	
	SetRowTableTags(tableLayout);
	// move data reg 
	MoveData();
	
	// tag tablerows
	SetTableData(tableLayout);
	
	// bind context manu to object
    registerForContextMenu(btnSettings);
	
	// add long click listeners
	
	Cell10.setOnLongClickListener(CellLongClickListener);
	Cell20.setOnLongClickListener(CellLongClickListener);
	Cell30.setOnLongClickListener(CellLongClickListener);
	Cell40.setOnLongClickListener(CellLongClickListener);
	Cell50.setOnLongClickListener(CellLongClickListener);
	Cell60.setOnLongClickListener(CellLongClickListener);
	Cell70.setOnLongClickListener(CellLongClickListener);	
	Cell80.setOnLongClickListener(CellLongClickListener);
	Cell90.setOnLongClickListener(CellLongClickListener);
	Cell100.setOnLongClickListener(CellLongClickListener);	
	Cell110.setOnLongClickListener(CellLongClickListener);	
	Cell120.setOnLongClickListener(CellLongClickListener);			
	Cell130.setOnLongClickListener(CellLongClickListener);
	Cell140.setOnLongClickListener(CellLongClickListener);
	Cell150.setOnLongClickListener(CellLongClickListener);
	Cell160.setOnLongClickListener(CellLongClickListener);
	Cell170.setOnLongClickListener(CellLongClickListener);	
	Cell180.setOnLongClickListener(CellLongClickListener);
	Cell190.setOnLongClickListener(CellLongClickListener);
	Cell200.setOnLongClickListener(CellLongClickListener);	
	Cell210.setOnLongClickListener(CellLongClickListener);
	Cell220.setOnLongClickListener(CellLongClickListener);
	Cell230.setOnLongClickListener(CellLongClickListener);
	Cell240.setOnLongClickListener(CellLongClickListener);
	Cell250.setOnLongClickListener(CellLongClickListener);
	Cell260.setOnLongClickListener(CellLongClickListener);
	Cell270.setOnLongClickListener(CellLongClickListener);	
	Cell280.setOnLongClickListener(CellLongClickListener);
	Cell290.setOnLongClickListener(CellLongClickListener);
	Cell300.setOnLongClickListener(CellLongClickListener);
	Cell310.setOnLongClickListener(CellLongClickListener);
	
	// add on click listeners	
	btnPunch.setOnClickListener(CellOnClickListener);
	btnSettings.setOnClickListener(CellOnClickListener);
	
	Cell11.setOnClickListener(CellOnClickListener);
	Cell12.setOnClickListener(CellOnClickListener);
	Cell21.setOnClickListener(CellOnClickListener);
	Cell22.setOnClickListener(CellOnClickListener);
	Cell31.setOnClickListener(CellOnClickListener);
	Cell32.setOnClickListener(CellOnClickListener);
	Cell41.setOnClickListener(CellOnClickListener);
	Cell42.setOnClickListener(CellOnClickListener);
	Cell51.setOnClickListener(CellOnClickListener);
	Cell52.setOnClickListener(CellOnClickListener);
	Cell61.setOnClickListener(CellOnClickListener);
	Cell62.setOnClickListener(CellOnClickListener);
	Cell71.setOnClickListener(CellOnClickListener);
	Cell72.setOnClickListener(CellOnClickListener);
	Cell81.setOnClickListener(CellOnClickListener);
	Cell82.setOnClickListener(CellOnClickListener);
	Cell91.setOnClickListener(CellOnClickListener);
	Cell92.setOnClickListener(CellOnClickListener);
	Cell101.setOnClickListener(CellOnClickListener);
	Cell102.setOnClickListener(CellOnClickListener);
	Cell111.setOnClickListener(CellOnClickListener);
	Cell112.setOnClickListener(CellOnClickListener);
	Cell121.setOnClickListener(CellOnClickListener);
	Cell122.setOnClickListener(CellOnClickListener);	
	Cell131.setOnClickListener(CellOnClickListener);
	Cell132.setOnClickListener(CellOnClickListener);
	Cell141.setOnClickListener(CellOnClickListener);
	Cell142.setOnClickListener(CellOnClickListener);
	Cell151.setOnClickListener(CellOnClickListener);
	Cell152.setOnClickListener(CellOnClickListener);
	Cell161.setOnClickListener(CellOnClickListener);
	Cell162.setOnClickListener(CellOnClickListener);
	Cell171.setOnClickListener(CellOnClickListener);
	Cell172.setOnClickListener(CellOnClickListener);
	Cell181.setOnClickListener(CellOnClickListener);
	Cell182.setOnClickListener(CellOnClickListener);
	Cell191.setOnClickListener(CellOnClickListener);
	Cell192.setOnClickListener(CellOnClickListener);	
	Cell201.setOnClickListener(CellOnClickListener);
	Cell202.setOnClickListener(CellOnClickListener);
	Cell211.setOnClickListener(CellOnClickListener);
	Cell212.setOnClickListener(CellOnClickListener);
	Cell221.setOnClickListener(CellOnClickListener);
	Cell222.setOnClickListener(CellOnClickListener);
	Cell231.setOnClickListener(CellOnClickListener);
	Cell232.setOnClickListener(CellOnClickListener);
	Cell241.setOnClickListener(CellOnClickListener);
	Cell242.setOnClickListener(CellOnClickListener);
	Cell251.setOnClickListener(CellOnClickListener);
	Cell252.setOnClickListener(CellOnClickListener);
	Cell261.setOnClickListener(CellOnClickListener);
	Cell262.setOnClickListener(CellOnClickListener);
	Cell271.setOnClickListener(CellOnClickListener);
	Cell272.setOnClickListener(CellOnClickListener);
	Cell281.setOnClickListener(CellOnClickListener);
	Cell282.setOnClickListener(CellOnClickListener);
	Cell291.setOnClickListener(CellOnClickListener);
	Cell292.setOnClickListener(CellOnClickListener);
	Cell301.setOnClickListener(CellOnClickListener);
	Cell302.setOnClickListener(CellOnClickListener);
	Cell311.setOnClickListener(CellOnClickListener);
	Cell312.setOnClickListener(CellOnClickListener);
	
	}
	//------------------------End Setup objects----------------------//
	
	
        
   //------------------------Begin CellOnClickListener----------------------------------//	
	   private OnClickListener CellOnClickListener = new OnClickListener() {
	        public void onClick(View v){
	            
	        switch (v.getId()){
	   		 		   	
		   		 case R.id.btnPunch: // set start time or end time		   			 
		   			 		   			
		   			 Punch();
		   			 SetScrollViewToChild();
		   			 SaveData();	
		   			 
		   			 break;
		   			
		   		 case R.id.btnSettings:
		   			 // load settings
	   			 	// Start your app main activity
	                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
	                startActivity(i);
	 
	                // close this activity
	                finish();
		                
		   			 break;
		   		 default: // Set time			
		   			
		   			 if (NumOfMonthsAllowToUpdate>thisMonthIndex)
		   			 {
		   				TextView thisCell = (TextView)findViewById(v.getId());
			   			TableRow thisRow = (TableRow) thisCell.getParent();
			   			CreateTimePickerDialog(thisCell, thisRow);		 
		   			 }		   										   
		   			break;	   			
	   		 }
	        	
	        }
	        
	    };	      
   //------------------------End CellOnClickListener-----------------------------------//
	   
	  //------------------------Begin CellOnClickListener----------------------------------//	
		   private OnLongClickListener CellLongClickListener = new OnLongClickListener() {
		        			 
				@Override
				public boolean onLongClick(View v) {
					 
					switch (v.getId()){
					
						
					default:
						if (NumOfMonthsAllowToUpdate>thisMonthIndex)
						{
							TextView thisCell = (TextView)findViewById(v.getId());
				   			TableRow thisRow = (TableRow) thisCell.getParent();
				   			String sIndex = thisRow.getTag().toString();
				   			int RoIndex = Integer.parseInt(sIndex);
						    ClearRow(RoIndex);
						}
						break;
					}
				
					return true;
				}
		        
		    };	      
	 //------------------------End CellOnClickListener-----------------------------------//
	
    private void SetScrollViewToChild()
    {
    	// get the table row of the current day
		 int[] TodayArr = new int[3];
		 TodayArr=Today();
		 int curDay = TodayArr[0]+1;
		 
		 int childIndex = curDay-2;
		 if (childIndex<0) childIndex=0;
		 
		 int cellSize= 95;
		 // start at the top
		 scrollViewTable.scrollTo(0, 0);		 
		 scrollViewTable.scrollTo(0, childIndex*cellSize);
    }
    //------------------------------------BEGIN Punch-----------------------------------//
	private void Punch()
	{
		 // get the table row of the current day
		 int[] TodayArr = new int[3];
		 TodayArr=Today();
		 int curDay = TodayArr[0]+1;
		 TableRow thisTableRow = (TableRow) tableLayout.getChildAt(curDay-2);
		 // get the punch hour type : starthour or endhour
		 
		 int State = LoadPunchState(getApplicationContext());
		 // in the end of the process make a toggle		  				 
		 TextView thisPunchCell = (TextView) thisTableRow.getChildAt(State);		 
		 Calendar c = Calendar.getInstance();
		 String smin = "";
		 String shour="";
		 
		 int mins = c.get(Calendar.MINUTE);
		 if (mins<10)
		 {
		  smin = "0" + String.valueOf(mins);
		 }
		 else
		 {
			 smin = String.valueOf(mins);
		 }
		 
		 int hours = c.get(Calendar.HOUR_OF_DAY);
		 if (hours<10)
		 {
			 shour = "0" + String.valueOf(hours);
		 }
		 else
		 {
			 shour = String.valueOf(hours);
		 }
		  
	   
	     int[] todayArr = new int[3];
		 todayArr=Today();
		 
		 int curYear = c.get(Calendar.YEAR);  
	     int curMonth = c.get(Calendar.MONTH)+1; //zero based: This month	     
	     int thisDay = c.get(Calendar.DAY_OF_WEEK);	 
	     
	     String sDate = todayArr[0] + "/" +todayArr[1] + "/" +todayArr[2];
		 String dateDayName = String.valueOf(DayTranslateIntValue(thisDay));		 
	     String sTime = shour + ":" + smin;
	     
	     State=ToggleState(getApplicationContext());
		 thisPunchCell.setText(String.valueOf(hours) + ":" + String.valueOf(mins));
		 
		 if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
		 {
			 switch (State)
			 {
			 case 2:
				 btnPunch.setText("כניסה");
				 txtVpunch.setText("החתמת יציאה" + " " + sTime + " " + dateDayName + " " + sDate);
				 break;
			 default:
				 btnPunch.setText("יציאה");
				 txtVpunch.setText("החתמת כניסה" + " " + sTime + " " + dateDayName + " " + sDate);
				 break;
			 }
		 }
		 else
		 {
			 switch (State)
			 {
			 case 2:
				 btnPunch.setText("Entry");
				 txtVpunch.setText("punch out:" + " " + sTime + " " + dateDayName + " " + sDate);
				 break;
			 default:
				 btnPunch.setText("Exit");
				 txtVpunch.setText("punch in:" + " " + sTime + " " + dateDayName + " " + sDate);
				 break;
			 }
		 }
		 
		 State = LoadPunchState(getApplicationContext());
	}
	//------------------------------------End Punch-----------------------------------//
	
	//------------------------------------BEGIN SyncTodayToPunchClock--------------------------------------------//
	
	public void SyncTodayToPunchClock(){
		
		String sYesterday="";
		sYesterday=LoadFromInternalMemory(getApplicationContext(), "Yesterday");
		int[] TodayArr = new int[3];
		TodayArr=Today();		
		SaveToInternalMemory(getApplicationContext(),"Yesterday",String.valueOf(TodayArr[0]));
	}
	//------------------------------------END SyncTodayToPunchClock--------------------------------------------//
	
	//------------------------------------BEGIN SetPunchForNewDay---------------------------------//
	public void SetPunchForNewDay(){
		int State = LoadPunchState(getApplicationContext());
		
		 
		 if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
		 {
			 btnPunch.setText("כניסה");
			 switch (State)
			 {
			 case 2:
				 // Do nothing: already set on entry
				 break;
			 default:
				 // switch State mem to entry				 
				 State=ToggleState(getApplicationContext());					
				 break;
			 }
		 }
		 else
		 {
			 btnPunch.setText("Exit");
			 switch (State)
			 {
			 case 2:				 
				// Do nothing: already set on entry
				 break;
			 default:				
				 // switch State mem to entry
				 State=ToggleState(getApplicationContext());
				 break;
			 }
		 }
		 
		 State = LoadPunchState(getApplicationContext());
	}
	//------------------------------------END SetPunchForNewDay---------------------------------//
	
	//------------------------------------BEGIN SetRowTableTags-----------------------------------//
	private void SetRowTableTags(TableLayout tableLayout)
	{
		int childCount = tableLayout.getChildCount();
		
		for (int i=0; i<childCount;i++)
		{
			
			TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);			
			thisTableRow.setTag(String.valueOf(i));	
		}
	}
	//------------------------------------End SetRowTableTags-----------------------------------//
	
	//------------------------BEGIN SetTableRowTags-----------------------------------//
	private void SetTableData(TableLayout tableLayout)
	{
		
		Date testDate = null;  
	    Calendar cal = Calendar.getInstance();
	    
	    //Current Month data
	    int curYear = cal.get(Calendar.YEAR);  
	    int curMonth = cal.get(Calendar.MONTH)+1 - thisMonthIndex; //zero based: This month
	    
	    if (curMonth>1) 
	    {
	    	// do nothing: same year only previous month. ( - thisMonthIndex)
	    }	   
	    else if (curMonth<1)
	    {
	    	curMonth= 12 + curMonth;
	    	curYear = curYear -1;
	    }
	  
	    cal.set(curYear, curMonth, 1);
	    
	    daysInThisMonth = getSumOfDays(curMonth,curYear);	   
	    txtVtitle.setText(getMonthName(curMonth) + "   " + String.valueOf(curYear));	    
	    
		int childCount = tableLayout.getChildCount();			
		int[] TodayArr = new int[3];
		TodayArr=Today();		
		
		cal.clear();
		
		for (int i=0; i<childCount;i++)
		{
			
			TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);
			thisTableRow.setVisibility(View.VISIBLE);	
			thisTableRow.setTag(String.valueOf(i));			
			TextView thisRowName = (TextView) thisTableRow.getChildAt(0);
			TextView thisRowDate = (TextView) thisTableRow.getChildAt(1);
			
			// last month row tables
			if ((i+1)<=daysInThisMonth)
			{				
				testDate = ConvToDate(String.valueOf(curMonth) +"/"+String.valueOf((i+1)) +"/"+ String.valueOf(curYear));
				String sDayName = DayNameByDate(testDate);				
				thisRowName.setText(sDayName); 				
				thisRowDate.setText( String.valueOf((i+1)) +"/"+String.valueOf(curMonth));
				cal.setTime(testDate);
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				
				// insert the value of the days (middleweek or weekend)
				if (dayOfWeek==WeekendDayValue[0]) 
				{
					isDayWeekend[i+1]=true;
					for (int j=0;j<=7;j++)
					{
						TextView thisCell = (TextView) thisTableRow.getChildAt(j);
						thisCell.setTypeface(null,Typeface.BOLD);
					}
				}
				else if (dayOfWeek==WeekendDayValue[1]) 
				{
					isDayWeekend[i+1]=true;
					for (int j=0;j<=7;j++)
					{
						TextView thisCell = (TextView) thisTableRow.getChildAt(j);
						thisCell.setTypeface(null,Typeface.BOLD);
					}
				}
				else
				{					
					isDayWeekend[i+1]=false;
					for (int j=0;j<=7;j++)
					{
						TextView thisCell = (TextView) thisTableRow.getChildAt(j);
						thisCell.setTypeface(null,Typeface.NORMAL);
						//thisCell.setTextColor(getResources().getColor(0xffffff));
					}
										
				}
				
				
				// mark the current day
				if (TodayArr[0]==(i+1))
				{
					// if this is the current month
					if (thisMonthIndex==0)
					{
						for (int j=0;j<=7;j++)
						{
							TextView thisCell = (TextView) thisTableRow.getChildAt(j);
							thisCell.setBackgroundResource(R.drawable.cell_select); 
							thisCell.setTag(String.valueOf(i)); // tagging all the cell like the row. 
						}
					}
					// if this is the first day but on a different month
					else
					{
						for (int j=0;j<=7;j++)
						{
							TextView thisCell = (TextView) thisTableRow.getChildAt(j);
							thisCell.setBackgroundResource(R.drawable.cell_shape); 
						}
						
					}
					
				}
				
				else
				{
					for (int j=0;j<=7;j++)
					{
						TextView thisCell = (TextView) thisTableRow.getChildAt(j);
						thisCell.setBackgroundResource(R.drawable.cell_shape); 
					}
					
				}
				
				if (sPayment.equals("Hourly")==true)
				{
					// if the payment is set to Gloval: disapear Bruto column	
					TextView thisCell = (TextView) thisTableRow.getChildAt(7);
					thisCell.setVisibility(View.VISIBLE);	
					Cell06.setVisibility(View.VISIBLE);	
					double SalarySum = calcSalarySum(daysInThisMonth);
					CellSummerySalary.setText(String.valueOf(SalarySum));
					CellSummerySalary.setVisibility(View.VISIBLE);
				}
				else 
				{
					// if the payment is set to Gloval: disapear Bruto column	
					TextView thisCell = (TextView) thisTableRow.getChildAt(7);
					thisCell.setVisibility(View.GONE);	
					Cell06.setVisibility(View.GONE);
					CellSummerySalary.setVisibility(View.GONE);
				}
				
			}
			
			else 
			{				
				// no month row table
				// hide tablerows that are out of limit of the two months combined
				thisTableRow.setVisibility(View.GONE);	
				
			}
			
		}
	}
	//------------------------End SetTableRowTags-----------------------------------//
	
    //------------------------BEGIN Save Data using XML---------------------------//
    public void SaveData(){
		
    	// get data from the table
    	
    	switch (thisMonthIndex)
    	{
    	case 0:
    		FillArraysUsingTable(sHour_S,sHour_E);    
    		saveArray(sHour_S, "mStart", getApplicationContext()); 		
    		saveArray(sHour_E, "mEnd", getApplicationContext()); 
    		break;
    	case 1:
    		FillArraysUsingTable(sHour_S_1_MonthBefore,sHour_E_1_MonthBefore);    
    		saveArray(sHour_S_1_MonthBefore, "mStart_1MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_1_MonthBefore, "mEnd_1MonthBefore", getApplicationContext()); 
    		break;
    	case 2:
    		FillArraysUsingTable(sHour_S_2_MonthBefore,sHour_E_2_MonthBefore);    
    		saveArray(sHour_S_2_MonthBefore, "mStart_2MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_2_MonthBefore, "mEnd_2MonthBefore", getApplicationContext()); 
    		break;
    	case 3:
    		FillArraysUsingTable(sHour_S_3_MonthBefore,sHour_E_3_MonthBefore);    
    		saveArray(sHour_S_3_MonthBefore, "mStart_3MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_3_MonthBefore, "mEnd_3MonthBefore", getApplicationContext()); 
    		break;
    	case 4:
    		FillArraysUsingTable(sHour_S_4_MonthBefore,sHour_E_4_MonthBefore);    
    		saveArray(sHour_S_4_MonthBefore, "mStart_4MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_4_MonthBefore, "mEnd_4MonthBefore", getApplicationContext()); 
    		break;
    	case 5:
    		FillArraysUsingTable(sHour_S_5_MonthBefore,sHour_E_5_MonthBefore);    
    		saveArray(sHour_S_5_MonthBefore, "mStart_5MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_5_MonthBefore, "mEnd_5MonthBefore", getApplicationContext()); 
    		break;
    	case 6:
    		FillArraysUsingTable(sHour_S_6_MonthBefore,sHour_E_6_MonthBefore);    
    		saveArray(sHour_S_6_MonthBefore, "mStart_6MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_6_MonthBefore, "mEnd_6MonthBefore", getApplicationContext()); 
    		break;
    	case 7:
    		FillArraysUsingTable(sHour_S_7_MonthBefore,sHour_E_7_MonthBefore);    
    		saveArray(sHour_S_7_MonthBefore, "mStart_7MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_7_MonthBefore, "mEnd_7MonthBefore", getApplicationContext()); 
    		break;
    	case 8:
    		FillArraysUsingTable(sHour_S_8_MonthBefore,sHour_E_8_MonthBefore);    
    		saveArray(sHour_S_8_MonthBefore, "mStart_8MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_8_MonthBefore, "mEnd_8MonthBefore", getApplicationContext()); 
    		break;
    	case 9:
    		FillArraysUsingTable(sHour_S_9_MonthBefore,sHour_E_9_MonthBefore);    
    		saveArray(sHour_S_9_MonthBefore, "mStart_9MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_9_MonthBefore, "mEnd_9MonthBefore", getApplicationContext()); 
    		break;
    	case 10:
    		FillArraysUsingTable(sHour_S_10_MonthBefore,sHour_E_10_MonthBefore);    
    		saveArray(sHour_S_10_MonthBefore, "mStart_10MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_10_MonthBefore, "mEnd_10MonthBefore", getApplicationContext()); 
    		break;
    	case 11:
    		FillArraysUsingTable(sHour_S_11_MonthBefore,sHour_E_11_MonthBefore);    
    		saveArray(sHour_S_11_MonthBefore, "mStart_11MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_11_MonthBefore, "mEnd_11MonthBefore", getApplicationContext()); 
    		break;
    	case 12:
    		FillArraysUsingTable(sHour_S_12_MonthBefore,sHour_E_12_MonthBefore);    
    		saveArray(sHour_S_12_MonthBefore, "mStart_12MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_12_MonthBefore, "mEnd_12MonthBefore", getApplicationContext()); 
    		break;
    		
    	}
    	
    	LoadData();
	 }
   //------------------------END Save Data using XML---------------------------//
    
  //------------------------BEGIN Load Data using XML---------------------------//
    public void LoadData(){
    	    	
    	sHour_S= loadArray("mStart",sHour_S.length,getApplicationContext());
    	sHour_E= loadArray("mEnd",sHour_E.length,getApplicationContext());
    	
    	sHour_S_1_MonthBefore= loadArray("mStart_1MonthBefore",sHour_S_1_MonthBefore.length,getApplicationContext());
    	sHour_E_1_MonthBefore= loadArray("mEnd_1MonthBefore",sHour_E_1_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_2_MonthBefore= loadArray("mStart_2MonthBefore",sHour_E_2_MonthBefore.length,getApplicationContext());
    	sHour_E_2_MonthBefore= loadArray("mEnd_2MonthBefore",sHour_E_2_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_3_MonthBefore= loadArray("mStart_3MonthBefore",sHour_S_3_MonthBefore.length,getApplicationContext());
    	sHour_E_3_MonthBefore= loadArray("mEnd_3MonthBefore",sHour_E_3_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_4_MonthBefore= loadArray("mStart_4MonthBefore",sHour_E_4_MonthBefore.length,getApplicationContext());
    	sHour_E_4_MonthBefore= loadArray("mEnd_4MonthBefore",sHour_E_4_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_5_MonthBefore= loadArray("mStart_5MonthBefore",sHour_S_5_MonthBefore.length,getApplicationContext());
    	sHour_E_5_MonthBefore= loadArray("mEnd_5MonthBefore",sHour_E_5_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_6_MonthBefore= loadArray("mStart_6MonthBefore",sHour_E_6_MonthBefore.length,getApplicationContext());
    	sHour_E_6_MonthBefore= loadArray("mEnd_6MonthBefore",sHour_E_6_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_7_MonthBefore= loadArray("mStart_7MonthBefore",sHour_E_7_MonthBefore.length,getApplicationContext());
    	sHour_E_7_MonthBefore= loadArray("mEnd_7MonthBefore",sHour_E_7_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_8_MonthBefore= loadArray("mStart_8MonthBefore",sHour_E_8_MonthBefore.length,getApplicationContext());
    	sHour_E_8_MonthBefore= loadArray("mEnd_8MonthBefore",sHour_E_8_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_9_MonthBefore= loadArray("mStart_9MonthBefore",sHour_E_9_MonthBefore.length,getApplicationContext());
    	sHour_E_9_MonthBefore= loadArray("mEnd_9MonthBefore",sHour_E_9_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_10_MonthBefore= loadArray("mStart_10MonthBefore",sHour_E_10_MonthBefore.length,getApplicationContext());
    	sHour_E_10_MonthBefore= loadArray("mEnd_10MonthBefore",sHour_E_10_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_11_MonthBefore= loadArray("mStart_11MonthBefore",sHour_E_11_MonthBefore.length,getApplicationContext());
    	sHour_E_11_MonthBefore= loadArray("mEnd_11MonthBefore",sHour_E_11_MonthBefore.length,getApplicationContext());
    	
    	sHour_S_12_MonthBefore= loadArray("mStart_12MonthBefore",sHour_E_12_MonthBefore.length,getApplicationContext());
    	sHour_E_12_MonthBefore= loadArray("mEnd_12MonthBefore",sHour_E_12_MonthBefore.length,getApplicationContext());
    	
    	switch (thisMonthIndex)
    	{
    	case 0:
    		PopulateTableDataUsingArrays(sHour_S,sHour_E);  
    		break;
    	case 1:
    		PopulateTableDataUsingArrays(sHour_S_1_MonthBefore,sHour_E_1_MonthBefore);  
    		break;
    	case 2:
    		PopulateTableDataUsingArrays(sHour_S_2_MonthBefore,sHour_E_2_MonthBefore);  
    		break;
    	case 3:
    		PopulateTableDataUsingArrays(sHour_S_3_MonthBefore,sHour_E_3_MonthBefore);  
    		break;
    	case 4:
    		PopulateTableDataUsingArrays(sHour_S_4_MonthBefore,sHour_E_4_MonthBefore);  
    		break;
    	case 5:
    		PopulateTableDataUsingArrays(sHour_S_5_MonthBefore,sHour_E_5_MonthBefore);  
    		break;
    	case 6:
    		PopulateTableDataUsingArrays(sHour_S_6_MonthBefore,sHour_E_6_MonthBefore);  
    		break;
    	case 7:
    		PopulateTableDataUsingArrays(sHour_S_7_MonthBefore,sHour_E_7_MonthBefore);  
    		break;
    	case 8:
    		PopulateTableDataUsingArrays(sHour_S_8_MonthBefore,sHour_E_8_MonthBefore);  
    		break;
    	case 9:
    		PopulateTableDataUsingArrays(sHour_S_9_MonthBefore,sHour_E_9_MonthBefore);  
    		break;
    	case 10:
    		PopulateTableDataUsingArrays(sHour_S_10_MonthBefore,sHour_E_10_MonthBefore);  
    		break;
    	case 11:
    		PopulateTableDataUsingArrays(sHour_S_11_MonthBefore,sHour_E_11_MonthBefore);  
    		break;
    	case 12:
    		PopulateTableDataUsingArrays(sHour_S_12_MonthBefore,sHour_E_12_MonthBefore);  
    		break;
    	}
    	
    	ShowSumOfTime();
	 }
   //------------------------END Load Data using XML---------------------------//
    
   //------------------------Begin Initialize Arrays---------------------------//
    public void InitializeArray(){
    	
    	for (int i=0;(i<sHour_S.length);i++){
    		sHour_S[i]="";
    		sHour_S_1_MonthBefore[i]="";
    		sHour_S_2_MonthBefore[i]="";
    		sHour_S_3_MonthBefore[i]="";
    		sHour_S_4_MonthBefore[i]="";
    		sHour_S_5_MonthBefore[i]="";
    		sHour_S_6_MonthBefore[i]="";
    		sHour_S_7_MonthBefore[i]="";
    		sHour_S_8_MonthBefore[i]="";
    		sHour_S_9_MonthBefore[i]="";
    		sHour_S_10_MonthBefore[i]="";
    		sHour_S_11_MonthBefore[i]="";
    		sHour_S_12_MonthBefore[i]="";
    		
    		sHour_E[i]= "";
    		sHour_E_1_MonthBefore[i]="";
    		sHour_E_2_MonthBefore[i]="";
    		sHour_E_3_MonthBefore[i]="";
    		sHour_E_4_MonthBefore[i]="";
    		sHour_E_5_MonthBefore[i]="";
    		sHour_E_6_MonthBefore[i]="";
    		sHour_E_7_MonthBefore[i]="";
    		sHour_E_8_MonthBefore[i]="";
    		sHour_E_9_MonthBefore[i]="";
    		sHour_E_10_MonthBefore[i]="";
    		sHour_E_11_MonthBefore[i]="";
    		sHour_E_12_MonthBefore[i]="";
    		
    		isDayWeekend[i]=false;
    		
        }
    	
    }
  //------------------------End Initialize Arrays---------------------------//
    
   
	
	//------------------------Begin ConvertTimeToFloat ---------------------------//
	  public double  ConvertTimeToFloat(String sTime){
	  	
		String timeArr[] = sTime.split(":");
		int hours_element = Integer.parseInt(timeArr[0]);
		int min_element = Integer.parseInt(timeArr[1]);
		int TotalTimeMin = hours_element*60 + min_element;
		double  totalHoursTime =(float) TotalTimeMin;
		totalHoursTime=(totalHoursTime/60);
	  	return totalHoursTime;
	  	
	  }  
	 //------------------------End ConvertTimeToFloat---------------------------//
	  
	 //------------------------Begin CalcWorkHours------------------------------//
	  public double  CalcWorkHours(String sStartTime, String sEndTime){
		  
		  double  floatStartTime = ConvertTimeToFloat(sStartTime);
		  double  floatEndTime = ConvertTimeToFloat(sEndTime);
		  double  WorkingHours = (floatEndTime - floatStartTime);
		  if (WorkingHours<0)
			  WorkingHours=0;
		  
		  return WorkingHours;
		  
	  }
	 //------------------------End CalcWorkHours--------------------------------//
	  
	  private void ShowSumOfTime()
	  {
		
		  double SumOfHours = 0.0;
		  double SumOfOV_1 = 0.0;
		  double SumOfOV_2 = 0.0;
			
		  String thisTextValue="";
		  
	      int count = tableLayout.getChildCount();
	      
	      for (int i=0;(i<count);i++){
	    		TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);
	    		
	    		TextView thisCell = (TextView) thisTableRow.getChildAt(4); // regular hours
	    		thisTextValue = thisCell.getText().toString();
	    		if (thisTextValue.length()==0) 
	    		{
	    			thisTextValue = "0.0";	    		
	    		}
	    		SumOfHours = SumOfHours + Double.parseDouble(thisTextValue);
	    		
	    		thisCell = (TextView) thisTableRow.getChildAt(5); // OV 1 hours
	    		thisTextValue = thisCell.getText().toString();
	    		if (thisTextValue.length()==0) 
	    		{
	    			thisTextValue = "0.0";	    		
	    		}	    		
	    		SumOfOV_1 = SumOfOV_1 + Double.parseDouble(thisTextValue);
	    		
	    		thisCell = (TextView) thisTableRow.getChildAt(6); // OV 2 hours
	    		thisTextValue = thisCell.getText().toString();
	    		if (thisTextValue.length()==0)
	    		{
	    			thisTextValue = "0.0";	    		
	    		}
	    		SumOfOV_2 = SumOfOV_2 + Double.parseDouble(thisTextValue);
	    		
	    	}
	      
	      // display 2d
	      SumOfHours =Math.floor(SumOfHours * 100) / 100;
	      SumOfOV_1 =Math.floor(SumOfOV_1 * 100) / 100;
	      SumOfOV_2 =Math.floor(SumOfOV_2 * 100) / 100;
	      
	      CellSummeryTotalHoursValue.setText(String.valueOf(SumOfHours));
	      CellSummeryOV1Value.setText(String.valueOf(SumOfOV_1));
	      CellSummeryOV2Value.setText(String.valueOf(SumOfOV_2));
	      
	  }
	//-----------------------------Begin setRowTimeData-----------------------//
	  private void setRowTimeData(int RowNo, double TotalHours) //target is a Button
	  {
		  double dblNetoHoursConfiguraion = 0;
		  double dblOV1HoursConfiguraion = 0;
		  double BreakValue = 0;
		  // get the value of the 
		  boolean isWeekendDay = isDayWeekend[RowNo+1];
		  
		  // find out working day neto and o.v. hours by dayvalue
		  if (isWeekendDay==false) 
		  {
			  dblNetoHoursConfiguraion = Double.parseDouble(NetoHoursConfiguraion_O);
			  dblOV1HoursConfiguraion = Double.parseDouble(OV1HoursConfiguraion_O);
			  
			  switch (Integer.parseInt(BreakConfiguration_O))
			  {
			  case 0: // none
				  BreakValue=0;
				  break;
			  case 1: // 15 minutes
				  BreakValue = 0.25;
				  break;
			  case 2: // 20 minutes
				  BreakValue = 0.33;
				  break;
			  case 3: // 30 minutes
				  BreakValue = 0.5;
				  break;
			  case 4: // 45 minutes
				  BreakValue = 0.75;
				  break;
			  case 5: // 60 minutes
				  BreakValue =1;
				  break;			
			  }
		  }
		  else if (isWeekendDay==true)
		  {
			  dblNetoHoursConfiguraion = Double.parseDouble(NetoHoursConfiguraion_W);
			  dblOV1HoursConfiguraion = Double.parseDouble(OV1HoursConfiguraion_W);
			  switch (Integer.parseInt(BreakConfiguration_W))
			  {
			  case 0: // none
				  BreakValue=0;
				  break;
			  case 1: // 15 minutes
				  BreakValue = 0.25;
				  break;
			  case 2: // 20 minutes
				  BreakValue = 0.33;
				  break;
			  case 3: // 30 minutes
				  BreakValue = 0.5;
				  break;
			  case 4: // 45 minutes
				  BreakValue = 0.75;
				  break;
			  case 5: // 60 minutes
				  BreakValue = 1;
				  break;			
			  }
		  }
		  
		  
		  dblNetoHoursConfiguraion = Math.floor(dblNetoHoursConfiguraion * 100) / 100;
		  dblOV1HoursConfiguraion = Math.floor(dblOV1HoursConfiguraion * 100) / 100;		  
		  
		  TotalHours = Math.floor(TotalHours * 100) / 100;		  
		  TotalHours = TotalHours - BreakValue;
		  if (TotalHours<0) TotalHours=0;
		  
		  double [] calc= new double [3];
		  		  
		  calc[0]= TotalHours; //no ovt -> CELL 4
		  calc[0] = Math.floor(calc[0] * 100) / 100;
		  if (calc[0]>=dblNetoHoursConfiguraion)
			  calc[0]=dblNetoHoursConfiguraion; // the next hours are ovt.
		  
		  calc[1]= TotalHours - dblNetoHoursConfiguraion; 
		  calc[1] = Math.floor(calc[1] * 100) / 100;
		  if (calc[1]<0) // no ovt.
			  calc[1]=0;  
		  else if (calc[1]>=dblOV1HoursConfiguraion) // more than 2hr 125 ovt.
			  calc[1]=dblOV1HoursConfiguraion;		  
		  
		  calc[2]= TotalHours - dblOV1HoursConfiguraion - dblNetoHoursConfiguraion; // second ovt (150%) -> CELL 6
		  calc[2] = Math.floor(calc[2] * 100) / 100;
		  if (calc[2]<0)
			  calc[2]=0;
		  
		  //get the TableRow view No. RowNo in TableRowLayOut
		  currentTableRow = (TableRow) tableLayout.getChildAt(RowNo);
		  
		  // set the cells in the chosen TableRow
		  for (int j=0;j<=3;j++)
		  {
			  if (j==3)
			  {
				  if (sPayment.equals("Hourly")==true)
				  {	
					  // salary cell
					  double Salary = Double.parseDouble(SalaryConfiguration);
					  double TodaySalary = calc[0]*Salary+calc[1]*Salary*1.25+calc[2]*Salary*2;
					  TextView CurrentCellSalary = (TextView) currentTableRow.getChildAt(j+4);
					  TodaySalary = Math.floor(TodaySalary * 100) / 100;	
					  CurrentCellSalary.setText(String.valueOf(TodaySalary));
				  }
			  }
			  else
			  {
				  // Time cells
				  TextView CurrentCell = (TextView) currentTableRow.getChildAt(j+4);
				  CurrentCell.setText(String.valueOf(calc[j]));
			  }			  			  
		  }
		  		  
	  }
	 //-----------------------------End setRowTimeData-----------------------//
	  
	//-----------------------------Begin setBlankRow-----------------------//
	  private void setBlankRow(int RowNo) //target is a Button
	  {
		  
		  //get the TableRow view No. RowNo in TableRowLayOut
		  currentTableRow = (TableRow) tableLayout.getChildAt(RowNo);
		  
		  // set the cells in the chosen TableRow
		  for (int j=0;j<=3;j++)
		  {
			  TextView CurrentCell = (TextView) currentTableRow.getChildAt(j+4);
			  CurrentCell.setText("");
		  }
		  
	  }
	 //-----------------------------End setBlankRow-----------------------//
	  
	  private void setWeekendDayValue(String WeekendConfiguraion)
	  {
		  if (WeekendConfiguraion=="") WeekendConfiguraion="0";
		  int intWeekendConfiguraion=Integer.parseInt(WeekendConfiguraion); // initialize		  
		  switch (intWeekendConfiguraion)
		  {
		  case 0:
			  WeekendDayValue[0] = 6; // friday 
			  WeekendDayValue[1] =7; // saturday
			  break;
		  case 1:
			  WeekendDayValue[0] = 7; // saturday 
			  WeekendDayValue[1] = 1; // sunday 
			  break;
		  case 2:
			  WeekendDayValue[0] = 5; // thursday
			  WeekendDayValue[1] = 6; // friday
			  break;			  
		  }
	  }

	//-----------------------------Begin DayTranslateIntValue-----------------------//
	  private String DayTranslateIntValue(int DayValue)
	  {
		  if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
		  {
			  switch (DayValue)
			  {
			  case 1: // sunday
				  return "ראשון";
			  case 2: // monday
				  return "שני";
			  case 3: // tuesday
				  return "שלישי";
			  case 4: // wendsday
				  return "רביעי";
			  case 5: // thursday
				  return "חמישי";
			  case 6: // wendsday
				  return "שישי";
			  case 7: // thursday
				  return "שבת";
			  }
		  }
		  else
		  {
			  switch (DayValue)
			  {
			  case 1: // sunday
				  return "Sun";
			  case 2: // monday
				  return "Mon";
			  case 3: // tuesday
				  return "Tue";
			  case 4: // wendsday
				  return "Wend";
			  case 5: // thursday
				  return "Thu";
			  case 6: // wendsday
				  return "Fri";
			  case 7: // thursday
				  return "Str";
			  }
		  }
		  
		return null;
	  }
	//-----------------------------End DayTranslateIntValue-----------------------//
	  	  
	  //--------------------------Begin Clear---------------------------------//
	  private void Clear()
	  {
		  
		  AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  	
		  	String YesCommand="";
		  	String NoCommand="";
		  	
		  	if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
			{
		    	builder.setTitle("איפוס שעון הנוכחות");
			    builder.setMessage("?האם אתה בטוח");
			    YesCommand= "כן";
			    NoCommand= "לא";
			}
		    else
		    {
		    	builder.setTitle("Reset time record");
			    builder.setMessage("are you sure?");
			    YesCommand = "Yes";
			    NoCommand = "No";
		    }
		    

		    builder.setPositiveButton(YesCommand, new DialogInterface.OnClickListener() {

		        public void onClick(DialogInterface dialog, int which) {
		            
		        
		        	// clear table values
		        	int count = tableLayout.getChildCount();			    	
			    	for (int i=0;(i<count);i++){
			    		TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);
			    		for (int j=2;j<8;j++)
			    		{
			    			TextView thisTextView = (TextView) thisTableRow.getChildAt(j);
				    		thisTextView.setText("");
			    		}			    		
			        }
			    	
		        	// clear Phone memory			        	
		        	InitializeArray();
					SaveData();
		            dialog.dismiss();
		        }

		    });

		    builder.setNegativeButton(NoCommand, new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		            // Do nothing
		            dialog.dismiss();
		        }
		    });

		    AlertDialog alert = builder.create();
		    alert.show();
		
	  }
	  //--------------------------End Clear-----------------------------------//
	  
	  private void ClearRow(int i)
	  {
		
		TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);
  		for (int j=2;j<8;j++)
  		{
  			TextView thisTextView = (TextView) thisTableRow.getChildAt(j);
	    		thisTextView.setText("");	    		
  		}
  		  		
  		switch (thisMonthIndex)
  		{
  		case 0:
  			sHour_S[i]="";
  			sHour_E[i]="";
  			break;
  		case 1:
  			sHour_S_1_MonthBefore[i]="";
  			sHour_E_1_MonthBefore[i]="";
  			break;
  		case 2:
  			sHour_S_2_MonthBefore[i]="";
  			sHour_E_2_MonthBefore[i]="";
  			break;
  		case 3:
  			sHour_S_3_MonthBefore[i]="";
  			sHour_E_3_MonthBefore[i]="";
  			break;
  		case 4:
  			sHour_S_4_MonthBefore[i]="";
  			sHour_E_4_MonthBefore[i]="";
  			break;
  		case 5:
  			sHour_S_5_MonthBefore[i]="";
  			sHour_E_5_MonthBefore[i]="";
  			break;
  		case 6:
  			sHour_S_6_MonthBefore[i]="";
  			sHour_E_6_MonthBefore[i]="";
  			break;
  		case 7:
  			sHour_S_7_MonthBefore[i]="";
  			sHour_E_7_MonthBefore[i]="";
  			break;
  		case 9:
  			sHour_S_9_MonthBefore[i]="";
  			sHour_E_9_MonthBefore[i]="";
  			break;
  		case 10:
  			sHour_S_10_MonthBefore[i]="";
  			sHour_E_10_MonthBefore[i]="";
  			break;
  		case 11:
  			sHour_S_11_MonthBefore[i]="";
  			sHour_E_11_MonthBefore[i]="";
  			break;
  		case 12:
  			sHour_S_12_MonthBefore[i]="";
  			sHour_E_12_MonthBefore[i]="";
  			break;
  		}
  		
		int[] TodayArr = new int[3];
		TodayArr=Today();
		
		if (TodayArr[0]==(i+1))
		{
			ResetPunchValue(getApplicationContext());
		}
		SaveData();
		  	
	  }
	  //===============================Begin TimeDialog=========================================//
	  
	  //--------------------------------Begin CreateTimePickerDialog---------------------//
	  private void CreateTimePickerDialog(final TextView thisCell, final TableRow thisRow){
		  
		  int hour;
		  int minute;
		  
		  Integer.parseInt(thisRow.getTag().toString());		  
		  Calendar mcurrentTime = Calendar.getInstance();
		  String StrTime = thisCell.getText().toString();
		  
		  if (StrTime.length()==0)
		  {
			   // if blank: get the time from inner clock
			   hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
			   minute = mcurrentTime.get(Calendar.MINUTE);
		  }
		  else
		  {	
			  // if not blank: get the time form textview
			  String timeArr[] = StrTime.split(":");
			  int hours_element = Integer.parseInt(timeArr[0]);
			  int min_element = Integer.parseInt(timeArr[1]);
			  hour = hours_element;
			  minute = min_element;
		  }
		  
          TimePickerDialog mTimePicker;
          
          // YES
          mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
              @Override
              public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            	  String selectedTime = ConvertToTimeFormat_DialogResult(selectedHour,selectedMinute);
            	  thisCell.setText(selectedTime);            	  
            	  FillwholeTableRowData(thisRow);
            	  SaveData();
              }
          }, hour, minute, true);//Yes 24 hour time
            
          
          	// CANCEL
          /*
          	mTimePicker.setButton(DialogInterface.BUTTON_NEGATIVE,
            "cancel",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do stuff                     	
                	ClearRow(RowIndex);                 
                	                	
                }
            });
		  */
          if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
		  {
        	  mTimePicker.setTitle("בחר זמן");    
		  }
          else
          {
        	  mTimePicker.setTitle("Select Time");    
          }
          mTimePicker.show();          
	  }
	//--------------------------------End CreateTimePickerDialog---------------------//
	
	//--------------------------------Begin FillwholeTableRowData---------------------//
	  private void FillwholeTableRowData(final TableRow thisRow)
	  {
		// check if StartTime and EndTime are set for this TableRow.
    	  // if true: fill the whole line data.
    	  TextView thisRowStartTime = (TextView) thisRow.getChildAt(2);
    	  TextView thisRowEndTime = (TextView) thisRow.getChildAt(3);
    	  String strStartTime = thisRowStartTime.getText().toString();
    	  String strEndTime = thisRowEndTime.getText().toString();
    	  
    	  int i = Integer.parseInt(thisRow.getTag().toString());
    	  if (strStartTime.length()==0 || strEndTime.length()==0)
    	  {
    		  // if one of the data is missing: set this row to blank
    		  setBlankRow(i);
    	  }
    	  else
    	  {
    		 // both time cells of this RowTable are set. Fill the whole row.    		  
      		  setRowTimeData(i,CalcWorkHours(strStartTime, strEndTime)); // set values                  	
    	  }
	  }
	//--------------------------------End FillwholeTableRowData---------------------//
	  
	//--------------------------------Begin ConvertToTimeFormat_DialogResult---------------------//
	  private String ConvertToTimeFormat_DialogResult(int hrElement, int mnElement){
		  
		  String sTempHrElement="";
		  String sTempMnElement="";
		  
		  if (hrElement<10)
			  sTempHrElement = "0" + String.valueOf(hrElement);
		  else
			  sTempHrElement = String.valueOf(hrElement);
		  
		  if (mnElement<10)
			  sTempMnElement = "0" + String.valueOf(mnElement);
		  else
			  sTempMnElement = String.valueOf(mnElement);
		  
		  
		  
		  return sTempHrElement+":"+sTempMnElement;
		  
	  }
	//--------------------------------End ConvertToTimeFormat_DialogResult---------------------//
	  
	//===============================End TimeDialog=========================================//
	  
		//-------------------------------Begin Get DayNameByDate---------------------------------//
		private String DayNameByDate(Date date) 
		{		
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			return DayTranslateIntValue(dayOfWeek);		
		}
		 
		 //-------------------------------End Get DayNameByDate---------------------------------//
	
	
		//-------------------------------Begin Get Today---------------------------------//
			private int[] Today()
			{
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();			
				
				String tmpStr[] = dateFormat.format(date).split("/");
				int tmpInt[] = new int [3];
				tmpInt[2] = Integer.parseInt(tmpStr[0]); // year
				tmpInt[1] = Integer.parseInt(tmpStr[1]); // month
				tmpInt[0] = Integer.parseInt(tmpStr[2]); // day
				
				return tmpInt;
			}
			 	
		//-------------------------------End Get Today---------------------------------//
		
		//-------------------------------Begin getSumOfDays---------------------------------//	
		private int getSumOfDays(int curMonth, int curYear)
		{
			switch (curMonth)
			{
			case 1:
				return 31;
			case 2:
				if (LeapYear(curYear)==true) return 29;
				else return 28;
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;			
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
				
			}
			return 0;
		}
		//-------------------------------End getSumOfDays---------------------------------//
		
		//-------------------------------Begin LeapYear---------------------------------//
		private boolean LeapYear(int curYear)
		{
			int startYear = 2000;
			 
			for (int runningYear=startYear;runningYear<=curYear;runningYear=runningYear+4)
			{
				if (runningYear==curYear)
				{
					return true;
				}
			}
			return false;
		}
		//-------------------------------End LeapYear---------------------------------//
		
		//-------------------------------Begin ConvToDate---------------------------------//		
		private Date ConvToDate (String sDate)
		{
		
			// example:  sdate = "0/30/2012"; // 0 is january 
			SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");  
	        Date mDate = null;  
	         
	        try{  
	            mDate = df.parse(sDate);  
	        } catch (ParseException e){ System.out.println("invalid format");}  
	        
	        return mDate;
		}
		
		//-------------------------------End ConvToDate---------------------------------//
		
		
		//-------------------------------Begin getMonthName---------------------------------//		
		private String getMonthName(int M)
		{

			if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
			{
				switch (M)
				{
				case 1:
					return "ינואר";
				case 2:
					return "פברואר";
				case 3:
					return "מרץ";
				case 4:
					return "אפריל";
				case 5:
					return "מאי";
				case 6:
					return "יוני";
				case 7:
					return "יולי";
				case 8:
					return "אוגוסט";
				case 9:
					return "ספטמבר";			
				case 10:
					return "אוקטובר";
				case 11:
					return "נובמבר";
				case 12:
					return "דצמבר";
					
				}
			}
			else
			{
				switch (M)
				{
				case 1:
					return "January";
				case 2:
					return "February";
				case 3:
					return "March";
				case 4:
					return "April";
				case 5:
					return "May";
				case 6:
					return "June";
				case 7:
					return "July";
				case 8:
					return "August";
				case 9:
					return "September";
				case 10:
					return "October";
				case 11:
					return "November";
				case 12:
					return "December";
					
				}
			}
			
			
			
			return "";
		}
		//-------------------------------End getMonthName---------------------------------//
		
		//-----------------Begin PopulateTableDataUsingArrays---------------------------//
		private void PopulateTableDataUsingArrays(String[] sHour_S, String[] sHour_E)
		{
			
			int count = tableLayout.getChildCount();	    	
	    	for (int i=0;(i<count);i++){
	    		TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);
	    		TextView thisStartHour = (TextView) thisTableRow.getChildAt(2);
	    		thisStartHour.setText(sHour_S[i].toString());
	    		TextView thisEndHour = (TextView) thisTableRow.getChildAt(3);
	    		thisEndHour.setText(sHour_E[i].toString());
	    		// using the data from both arrays, the row can be filled.
	    		FillwholeTableRowData(thisTableRow);
	    		
	    		
	        }
	    		    	
	    	
	        
	    	
		}		
		//---------------------Begin PopulateTableDataUsingArrays---------------------//
		
		//-----------------Begin PopulateTableDataUsingArrays---------------------------//
		private void FillArraysUsingTable(String[] sHour_S, String[] sHour_E)
		{
			
			int count = tableLayout.getChildCount();
	    	
	    	for (int i=0;(i<count);i++){
	    		TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);	    		
	    		TextView thisStartHour = (TextView) thisTableRow.getChildAt(2);
	    		String sthisStartValue = thisStartHour.getText().toString();
	    		sHour_S[i] = sthisStartValue;	    		
	    		TextView thisEndHour = (TextView) thisTableRow.getChildAt(3);
	    		String sthisEndValue = thisEndHour.getText().toString();
	    		sHour_E[i] = sthisEndValue;	    		
	        }
	    	
		}		
		//---------------------Begin PopulateTableDataUsingArrays---------------------//
				
		//=================================Save and load Data==================================//
		
		//---------------------------------Begin saveArray--------------------------------//
		public boolean saveArray(String[] array, String arrayName, Context mContext) {   
		    SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);  
		    SharedPreferences.Editor editor = prefs.edit();  
		    for(int i=0;i<array.length;i++)  
		        editor.putString(arrayName + "_" + i, array[i]);  
		    return editor.commit();  
		} 
		//---------------------------------End saveArray--------------------------------//
		
		//----------------------------------Begin ToggleState--------------------------//
		private int ToggleState(Context mContext)
		{
			String value = "";
			SharedPreferences prefs;
			
			// Load previous value
			prefs = mContext.getSharedPreferences("preferencename", 0);  
			value = prefs.getString("InOut", "0");
			
			// Toggle value
			int int_value = Integer.parseInt(value);
			switch (int_value)
			{
			
			case 0: // 0 : start from the beginning -> starting hour
				int_value =2;
				break;
			case 2: // 2 : starting hour -> end hour
				int_value =3; 
				break;
			default: // 3 : end hour -> starting hour
				int_value=2;
				break;
			}
			
			value = String.valueOf(int_value);
			
			// Save new value
		    prefs = mContext.getSharedPreferences("preferencename", 0);  
		    SharedPreferences.Editor editor = prefs.edit(); 
		    editor.putString("InOut" , value);  
		    editor.commit();  		   
		    return Integer.parseInt(value);
		}
		//----------------------------------End ToggleState----------------------------//
		
		//----------------------------------Begin ResetPunchValue----------------------//
		private void ResetPunchValue(Context mContext)
		{
			
			// Save new value
			SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);  
		    SharedPreferences.Editor editor = prefs.edit(); 
		    editor.putString("InOut" , "2");  
		    editor.commit();
		    
		    LoadPunchState(mContext);
		    txtVpunch.setText("");
		}
		//----------------------------------End ResetPunchValue------------------------//
		
		//----------------------------------Begin LoadPunchState-----------------------//
		private int LoadPunchState(Context mContext)
		{
			String value = "";
			SharedPreferences prefs;
			
			// Load previous value
			prefs = mContext.getSharedPreferences("preferencename", 0);  
			value = prefs.getString("InOut", "0");
			
			// Toggle value
			int int_value = Integer.parseInt(value);
			
			if ((SelectedLanguage.trim().equals("heb"))) // load the hebrew menu
			{				
				switch (int_value)
				{
				
				case 0: 
					btnPunch.setText("כניסה");
					break;
				case 3: 
					btnPunch.setText("יציאה");
					break;
				default: 
					btnPunch.setText("כניסה");
					break;
				}		
			}
			else
			{
				switch (int_value)
				{
				
				case 0: 
					btnPunch.setText("Entry");
					break;
				case 3: 
					btnPunch.setText("Exit");
					break;
				default: 
					btnPunch.setText("Entry");
					break;
				}		
			}
						
			return int_value;			
		}
		
		//----------------------------------End LoadPunchState-----------------------//
		
		//---------------------------------Begin saveArray--------------------------------//
		public boolean saveArrayElement(String arrayName, String[] array, int ArrayElementIndex, Context mContext) {   
		    SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);  
		    SharedPreferences.Editor editor = prefs.edit(); 
		    editor.putString(arrayName + "_" + ArrayElementIndex, array[ArrayElementIndex]);  
		    return editor.commit();  
		} 
		//---------------------------------End saveArray--------------------------------//
		
		//---------------------------------Begin loadArray--------------------------------//
		public String[] loadArray(String arrayName, int ArrLength ,Context mContext) {  
		    SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);  
		    String array[] = new String[ArrLength];  
		    for(int i=0;i<ArrLength;i++)  
		        array[i] = prefs.getString(arrayName + "_" + i, "");  
		    return array;  
		}  
		//---------------------------------End loadArray--------------------------------//
		
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
		
		//-------------------------Begin GetUserConfigurationSettings----------------------//
		private void GetUserConfigurationSettings()
		{
			// get all the data of the user settings and according to that
			// present the data.
			
			// when the app first created and variables settings are null 
			// I need to enter default data 
			String sSelection="";
			
			sSelection="O"; // Ordinary settings
		    NetoHoursConfiguraion_O = LoadFromInternalMemory(getApplicationContext(),"NetoHoursConfiguration_" + sSelection);
			if (NetoHoursConfiguraion_O.equals("")==true)
			{
				NetoHoursConfiguraion_O="9"; // default: 9 working days ordinary
				SaveToInternalMemory(getApplicationContext(),"NetoHoursConfiguration_" + sSelection, NetoHoursConfiguraion_O);
			}
						
			OV1HoursConfiguraion_O = LoadFromInternalMemory(getApplicationContext(),"OV1HoursConfiguraion_" + sSelection);
			if (OV1HoursConfiguraion_O.equals("")==true)
			{
				OV1HoursConfiguraion_O="2"; // default: extra time hours on Ordinary
				SaveToInternalMemory(getApplicationContext(),"OV1HoursConfiguraion_" + sSelection, OV1HoursConfiguraion_O);
				
			}
			
			BreakConfiguration_O = LoadFromInternalMemory(getApplicationContext(),"Break_" + sSelection);	
			if (BreakConfiguration_O.equals("")==true)
			{
				BreakConfiguration_O="0"; //default: no break on Ordinary
				SaveToInternalMemory(getApplicationContext(),"BreakConfiguration_" + sSelection, BreakConfiguration_O);				
			}
		
			sSelection="W"; // Weekend settings
			NetoHoursConfiguraion_W = LoadFromInternalMemory(getApplicationContext(),"NetoHoursConfiguration_" + sSelection);	
			if (NetoHoursConfiguraion_W.equals("")==true)
			{
				NetoHoursConfiguraion_W="0"; // default: 9 hours working day
				SaveToInternalMemory(getApplicationContext(),"NetoHoursConfiguration_" + sSelection, NetoHoursConfiguraion_O);
			}
			
			OV1HoursConfiguraion_W = LoadFromInternalMemory(getApplicationContext(),"OV1HoursConfiguraion_" + sSelection);			
			if (OV1HoursConfiguraion_W.equals("")==true)
			{
				OV1HoursConfiguraion_W="2"; // default: two extra hours over time
				SaveToInternalMemory(getApplicationContext(),"OV1HoursConfiguraion_" + sSelection, OV1HoursConfiguraion_O);				
			}
			
			BreakConfiguration_W = LoadFromInternalMemory(getApplicationContext(),"Break_" + sSelection);
			if (BreakConfiguration_W.equals("")==true)
			{
				BreakConfiguration_W="0"; //default: no break on weekend
				SaveToInternalMemory(getApplicationContext(),"BreakConfiguration_" + sSelection, BreakConfiguration_W);				
			}
			
			// general settings
			
			FareConfiguration = LoadFromInternalMemory(getApplicationContext(),"FareConfiguration");
			if (FareConfiguration.equals("")==true)
			{
				FareConfiguration="0";
				SaveToInternalMemory(getApplicationContext(),"FareConfiguration", FareConfiguration);					
			}
			
			String WeekendConfiguraion = LoadFromInternalMemory(getApplicationContext(),"WeekendConfiguraion");		
			if (WeekendConfiguraion.equals("")==true)
			{
				WeekendConfiguraion="0";								
				SaveToInternalMemory(getApplicationContext(),"WeekendConfiguraion", WeekendConfiguraion);					
			}
			setWeekendDayValue(WeekendConfiguraion);
			
			sPayment = LoadFromInternalMemory(getApplicationContext(),"PaymentType");		
			if (sPayment.equals("")==true)
			{
				sPayment="Hourly";
				SaveToInternalMemory(getApplicationContext(),"PaymentType", sPayment);					
			}							
			SalaryConfiguration =  LoadFromInternalMemory(getApplicationContext(),"SalaryConfiguration");
			if (SalaryConfiguration.equals("")==true)
			{
				SalaryConfiguration="0";
				SaveToInternalMemory(getApplicationContext(),"SalaryConfiguration", SalaryConfiguration);					
			}			
			
		}
		
		//-------------------------End GetUserConfigurationSettings-----------------------//
		//-----------------Begin MoveData---------------------------//
		private void MoveData()
		{
		
			int[] TodayArr = new int[3];
			TodayArr=Today();	
			
			if (TodayArr[0]==1)
			{
				// if this day is the first of the month: check update status
				String Status = LoadFromInternalMemory(getApplicationContext(),"MoveData_Status");	
				if (Status.length()==0)
				{
					Status="0";
				}
				
				int int_Status = Integer.parseInt(Status);
				switch (int_Status)
				{
				case 0: // update has not been set yet.
					
					// if this is the first of a new month: all the memory must be relocated in this order:			
					// 2 months ago (sHour_S_2MonthBefore[], sHour_E_MonthBefore[]) {gets value of -> } sHour_S_MonthBefore[],sHour_E_MonthBefore[]
					// last month (sHour_S_MonthBefore[],sHour_E_MonthBefore[]) {gets value of -> } sHour_S_2MonthBefore[] , sHour_E_MonthBefore[]
					// now (sHour_S[],sHour_E) {gets value of -> } blank
							
					ShiftMemory();
			    			    
					// after update of the  memory. save the new status
					SaveToInternalMemory(getApplicationContext(),"MoveData_Status","1");
					break;
				case 1:  // Do nothing: update is already has been done today
					break;
				}
				
			}
			else
			{
				// on every other day besides the first of each month : set the status value to 0
				SaveToInternalMemory(getApplicationContext(),"MoveData_Status","0");
			}
							
		}
		//-----------------End MoveData---------------------------//
		
		
		//--------------------------------Begin MoveData---------------------------//
		private void ShiftMemory()
		{
			InitializeArray();
			
			// sHour_S and sHour_E are blank after iniatilization
						
			sHour_S_1_MonthBefore= loadArray("mStart",sHour_S.length,getApplicationContext());
			sHour_E_1_MonthBefore= loadArray("mEnd",sHour_E.length,getApplicationContext());
	    		
			sHour_S_2_MonthBefore= loadArray("mStart_1MonthBefore",sHour_S_1_MonthBefore.length,getApplicationContext());
			sHour_E_2_MonthBefore= loadArray("mEnd_1MonthBefore",sHour_E_1_MonthBefore.length,getApplicationContext());
			
			sHour_S_3_MonthBefore= loadArray("mStart_2MonthBefore",sHour_S_2_MonthBefore.length,getApplicationContext());
			sHour_E_3_MonthBefore= loadArray("mEnd_2MonthBefore",sHour_E_2_MonthBefore.length,getApplicationContext());
			
			sHour_S_4_MonthBefore= loadArray("mStart_3MonthBefore",sHour_S_3_MonthBefore.length,getApplicationContext());
			sHour_E_4_MonthBefore= loadArray("mEnd_3MonthBefore",sHour_E_3_MonthBefore.length,getApplicationContext());
		
			sHour_S_5_MonthBefore= loadArray("mStart_4MonthBefore",sHour_S_4_MonthBefore.length,getApplicationContext());
			sHour_E_5_MonthBefore= loadArray("mEnd_4MonthBefore",sHour_E_4_MonthBefore.length,getApplicationContext());
		
			sHour_S_6_MonthBefore= loadArray("mStart_5MonthBefore",sHour_S_5_MonthBefore.length,getApplicationContext());
			sHour_E_6_MonthBefore= loadArray("mEnd_5MonthBefore",sHour_E_5_MonthBefore.length,getApplicationContext());
			
			sHour_S_7_MonthBefore= loadArray("mStart_6MonthBefore",sHour_S_6_MonthBefore.length,getApplicationContext());
			sHour_E_7_MonthBefore= loadArray("mEnd_6MonthBefore",sHour_E_6_MonthBefore.length,getApplicationContext());
			
			sHour_S_8_MonthBefore= loadArray("mStart_7MonthBefore",sHour_S_7_MonthBefore.length,getApplicationContext());
			sHour_E_8_MonthBefore= loadArray("mEnd_7MonthBefore",sHour_E_7_MonthBefore.length,getApplicationContext());
		
			sHour_S_9_MonthBefore= loadArray("mStart_8MonthBefore",sHour_S_8_MonthBefore.length,getApplicationContext());
			sHour_E_9_MonthBefore= loadArray("mEnd_8MonthBefore",sHour_E_8_MonthBefore.length,getApplicationContext());
			
			sHour_S_10_MonthBefore= loadArray("mStart_9MonthBefore",sHour_S_9_MonthBefore.length,getApplicationContext());
			sHour_E_10_MonthBefore= loadArray("mEnd_9MonthBefore",sHour_E_9_MonthBefore.length,getApplicationContext());
		
			sHour_S_11_MonthBefore= loadArray("mStart_10MonthBefore",sHour_S_10_MonthBefore.length,getApplicationContext());
			sHour_E_11_MonthBefore= loadArray("mEnd_10MonthBefore",sHour_E_10_MonthBefore.length,getApplicationContext());
			
			sHour_S_12_MonthBefore= loadArray("mStart_11MonthBefore",sHour_S_11_MonthBefore.length,getApplicationContext());
			sHour_E_12_MonthBefore= loadArray("mEnd_11MonthBefore",sHour_E_11_MonthBefore.length,getApplicationContext());
		
			// save the new moved data in its new location
			saveArray(sHour_S, "mStart", getApplicationContext()); 		
    		saveArray(sHour_E, "mEnd", getApplicationContext()); 
    				    		
    		saveArray(sHour_S_1_MonthBefore, "mStart_1MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_1_MonthBefore, "mEnd_1MonthBefore", getApplicationContext()); 
    			    		
    		saveArray(sHour_S_2_MonthBefore, "mStart_2MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_2_MonthBefore, "mEnd_2MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_3_MonthBefore, "mStart_3MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_3_MonthBefore, "mEnd_3MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_4_MonthBefore, "mStart_4MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_4_MonthBefore, "mEnd_4MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_5_MonthBefore, "mStart_5MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_5_MonthBefore, "mEnd_5MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_6_MonthBefore, "mStart_6MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_6_MonthBefore, "mEnd_6MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_7_MonthBefore, "mStart_7MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_7_MonthBefore, "mEnd_7MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_8_MonthBefore, "mStart_8MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_8_MonthBefore, "mEnd_8MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_9_MonthBefore, "mStart_9MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_9_MonthBefore, "mEnd_9MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_10_MonthBefore, "mStart_10MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_10_MonthBefore, "mEnd_10MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_11_MonthBefore, "mStart_11MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_11_MonthBefore, "mEnd_11MonthBefore", getApplicationContext()); 
    		
    		saveArray(sHour_S_12_MonthBefore, "mStart_12MonthBefore", getApplicationContext()); 		
    		saveArray(sHour_E_12_MonthBefore, "mEnd_12MonthBefore", getApplicationContext()); 
    		
    		LoadData();
		}
		//--------------------------------Begin MoveData---------------------------//
		
		
		
		//===============================Writing and sending a txt file=======================//
		/*
		//-------------------------------Begin writeToFile----------------------------------//
		private void writeToFile(String data) {
			
			// write on SD card file data in the text box
		try
		{			
			File myFile = new File("/sdcard/" + FILENAME);
			myFile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(myFile);
			OutputStreamWriter myOutWriter = 
									new OutputStreamWriter(fOut);
			myOutWriter.append(data);
			myOutWriter.close();
			fOut.close();			
			
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
			
			
		}
		//-------------------------------End writeToFile----------------------------------//
		
		//-------------------------------Begin readFromFile----------------------------------//
		private String readFromFile() {
			
			String retrieveData="";
			try {
				File myFile = new File("/sdcard/" + FILENAME);
				FileInputStream fIn = new FileInputStream(myFile);
				BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
				String aDataRow = "";
				String aBuffer = "";
				while ((aDataRow = myReader.readLine()) != null) {
					aBuffer += aDataRow + "\n";
				}
				retrieveData = aBuffer;
				myReader.close();				
			} catch (Exception e) {
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
			
			return retrieveData;
		}
		//-------------------------------End readFromFile----------------------------------//
		
		//-------------------------------Begin CreateTextTable----------------------------------//
		private String CreateTextTable()
		{
			String tabletxt="";
			
			TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
			String mPhoneNumber = tMgr.getLine1Number();
			tabletxt = "\t" + "\t" + "\t" + "\t" + "\t" + " משתמש :" + mPhoneNumber + "\r\n";	
			tabletxt= tabletxt + "\r\n";
			tabletxt = tabletxt + "\t" + "\t" + "\t" + txtVtitle.getText().toString() + "\r\n";
			tabletxt = tabletxt + "===========================================================" + "\r\n";
			// get titles and columns
			tabletxt = tabletxt + "יום" + "\t" +  "תאריך" + "\t" +  "התחלה" + "\t" +  "סיום" + "\t" +  "נטו" + "\t" +  "ש.נ. 1" + "\t" +  "ש.נ. 2" + "\r\n";
			tabletxt = tabletxt + "---------------------------------------------------------------------------------------------------------" + "\r\n";
			// scan the whole table, and create a single text combined of columns (\t) and rows (\n)
			int count=tableLayout.getChildCount();
			
			for (int i=0;i<count;i++)
			{
				// create a tmp table row 
				TableRow tmpRow = (TableRow) tableLayout.getChildAt(i);
				tabletxt= tabletxt + "\r\n";
				for (int j=0;j<6;j++)
				{
					// create a tmp textview which will contain all the textview in a single table row
					TextView tmpTxtV = (TextView) tmpRow.getChildAt(j);	
					if (j==0)
					{
						// starting of a new line
					}
					
					tabletxt = tabletxt + "| "  + tmpTxtV.getText().toString() + " |"  + "\t";
				}		
				tabletxt= tabletxt + "\r\n";
				tabletxt = tabletxt +  "---------------------------------------------------------------------------------------------------------" + "\r\n";
			}
			
			// add summery
			tabletxt = tabletxt +"\r\n";
			tabletxt = tabletxt + "סיכום חודשי" + "\r\n";
			tabletxt = tabletxt + "------------" + "\r\n";
			tabletxt = tabletxt + "  :סך כל שעות רגילות " + TextViewSummeryTotalHoursValue.getText().toString() + "\r\n";
			tabletxt = tabletxt + "  :סך כל שעות נוספות סוג 1 " + TextViewSummeryOV1Value.getText().toString() + "\r\n";
			tabletxt = tabletxt + "  :סך כל שעות נוספות סוג 2 " + TextViewSummeryOV2Value.getText().toString() + "\r\n";
						
			return tabletxt;
		}
		//-------------------------------End CreateTextTable----------------------------------//
		*/
		//===============================Writing and sending a txt file=======================//
		
		
		//---------------------------------Begin WriteTable_XlsFile---------------------------//
		private void WriteTable_XlsFile() throws WriteException
		{
			
			  File myFile = new File("/sdcard/" + FILENAME);	
	          WorkbookSettings wbSettings = new WorkbookSettings();	
	          wbSettings.setLocale(new Locale("en", "EN"));	
	          WritableWorkbook workbook;
	          try {	            
	              workbook = Workbook.createWorkbook(myFile, wbSettings);
	              
	              WritableSheet sheet = workbook.createSheet("Hour Record", 0);
	              
	              // cell style configuration: 
	              // 1st argument: color
	              // 2nd argument: bold
	              // 3rd argument: border
	              
	              Label lblTitle_User = new Label(6,1,UserLangTitle,createFormatCellStatus(false,true,false));
	              TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
	  			  String mPhoneNumber = tMgr.getLine1Number();
	              Label lblTitle_Phone = new Label(5,1,mPhoneNumber,createFormatCellStatus(false,true,false));	              
	              Label lblTitle_Month = new Label(3,3,txtVtitle.getText().toString(),createFormatCellStatus(true,true,false));	              
	              Label lblTitle_Day = new Label(0,5, Cell00.getText().toString(),createFormatCellStatus(true,true,true));	              
	              Label lblTitle_Date = new Label(1,5, CellD0.getText().toString(),createFormatCellStatus(true,true,true));
	              Label lblTitle_Start = new Label(2,5, Cell01.getText().toString(),createFormatCellStatus(true,true,true));
	              Label lblTitle_End = new Label(3,5, Cell02.getText().toString(),createFormatCellStatus(true,true,true));
	              Label lblTitle_Neto = new Label(4,5, Cell03.getText().toString(),createFormatCellStatus(true,true,true));
	              Label lblTitle_OV1 = new Label(5,5, Cell04.getText().toString(),createFormatCellStatus(true,true,true));
	              Label lblTitle_OV2 = new Label(6,5, Cell05.getText().toString(),createFormatCellStatus(true,true,true));	              
	              Label lblTitle_Bruto = new Label(7,5, Cell06.getText().toString(),createFormatCellStatus(true,true,true));
	             	              
	              Label lblSummeryNetoCaption = new Label(10,6, Cell03.getText().toString() ,createFormatCellStatus(true,true,false));
	              Label lblSummeryOV1Caption = new Label(10,7,Cell04.getText().toString(),createFormatCellStatus(true,true,false));
	              Label lblSummeryOV2Caption = new Label(10,8,Cell05.getText().toString(),createFormatCellStatus(true,true,false));
	              Label lblIncomeCaption = new Label(10,10,Cell06.getText().toString(),createFormatCellStatus(true,true,false));
	             
	              
	              String sTransport;
	              if (SelectedLanguage.trim().equals("heb")) sTransport="נסיעות";
	              else sTransport="Fare";	            	 
	              Label lblTransport = new Label(10,11, sTransport ,createFormatCellStatus(true,true,false));
	              
	              String sTransportAndIncomeCaption;
	              if (SelectedLanguage.trim().equals("heb")) sTransportAndIncomeCaption="כללי";
	              else sTransportAndIncomeCaption="Total";	            	 
	              Label lblTransportAndIncomeCaption = new Label(10,12, sTransportAndIncomeCaption ,createFormatCellStatus(true,true,false));
	              
	              Label lblSummeryIncome = null;
	              Label lblSummeryTransportAndIncome = null;
	              Label lblSummeryNeto = new Label(9,6, CellSummeryTotalHoursValue.getText().toString(),createFormatCellStatus(false,true,false));
	              Label lblSummeryOV1 = new Label(9,7, CellSummeryOV1Value.getText().toString(),createFormatCellStatus(false,true,false));
	              Label lblSummeryOV2 = new Label(9,8, CellSummeryOV2Value.getText().toString(),createFormatCellStatus(false,true,false));
	              lblSummeryIncome = new Label(9,10, CellSummerySalary.getText().toString() ,createFormatCellStatus(false,true,false));	  				
	              Label lblSummeryTransport = new Label(9,11,FareConfiguration ,createFormatCellStatus(false,true,false));
	              
	              // file data 
	              int count=tableLayout.getChildCount();
	              
	  		   	  for (int i=0;i<daysInThisMonth;i++)
	  			  {
	  				// create a tmp table row 
	  				TableRow tmpRow = (TableRow) tableLayout.getChildAt(i);	
	  				
	  				int limit=0;
	  				if (sPayment.equals("Hourly")==true) limit =8;
	  				else limit = 7;
	  				
	  				for (int j=0;j<limit;j++)
	  				{
	  					// create a tmp textview which will contain all the textview in a single table row
	  					TextView tmpTxtV = (TextView) tmpRow.getChildAt(j);		  					
  						Label lblTmp =  new Label(j,i+6,tmpTxtV.getText().toString(),createFormatCellStatus(false,false,true));  						  					
	  					try {
							sheet.addCell(lblTmp);
						} catch (RowsExceededException e) {							
							e.printStackTrace();
						} catch (WriteException e) {							
							e.printStackTrace();
						}	  					
	  				}
	  				
	  				double FareIncome =0;
	  				double Income=0;
	  				if (FareConfiguration.length()>0) FareIncome = Double.parseDouble(FareConfiguration);	  				
	  				if (CellSummerySalary.length()>0) Income = Double.parseDouble(CellSummerySalary.getText().toString());
	  				double TotalIncome = Income + FareIncome;
	  				TotalIncome = Math.floor(TotalIncome *100) / 100;
	  				lblSummeryTransportAndIncome = new Label(9,12, String.valueOf(TotalIncome) ,createFormatCellStatus(false,true,false));
		               
	  			  }	  			
	  		   	  
	              try {
	            	  
		            	  sheet.addCell(lblTitle_User);
	                      sheet.addCell(lblTitle_Phone);
	                      sheet.addCell(lblTitle_Month);
              
                          sheet.addCell(lblTitle_Day);
                          sheet.addCell(lblTitle_Date);
                          sheet.addCell(lblTitle_Start);
                          sheet.addCell(lblTitle_End);
                          sheet.addCell(lblTitle_Neto);
                          sheet.addCell(lblTitle_OV1);
                          sheet.addCell(lblTitle_OV2);
                          
                          if (sPayment.equals("Hourly")==true)
                    	  {
                    	  	sheet.addCell(lblTitle_Bruto);
                    	  	sheet.addCell(lblSummeryIncome);
                    	  	sheet.addCell(lblIncomeCaption);
                    	  	sheet.addCell(lblSummeryTransport);
                    	  	sheet.addCell(lblTransport);
  	                        sheet.addCell(lblTransportAndIncomeCaption);
  	                        sheet.addCell(lblSummeryTransportAndIncome);  	                      
                    	  }
                      
                          sheet.addCell(lblSummeryNeto);
	                      sheet.addCell(lblSummeryOV1);
	                      sheet.addCell(lblSummeryOV2);
	                      
	                      sheet.addCell(lblSummeryNetoCaption);
	                      sheet.addCell(lblSummeryOV1Caption);
	                      sheet.addCell(lblSummeryOV2Caption);
	                      
	                      
                    } catch (RowsExceededException e) {                           
                           e.printStackTrace();
                    } catch (WriteException e) {                           
                           e.printStackTrace();
                    }
      
	
	              workbook.write();
	              try {
	                               workbook.close();
	                        } catch (WriteException e) {	                              
	                               e.printStackTrace();
	                        }
	              //createExcel(excelSheet);
	          } catch (IOException e) {	              
	              e.printStackTrace();
	          }
		}
		//---------------------------------End WriteTable_XlsFile---------------------------//
		
		//-----------------------------Begin SetCellStyle---------------------------------------//
		public WritableCellFormat createFormatCellStatus(boolean color, boolean bold, boolean borderLine) throws WriteException{
			
			WritableFont wfontStatus=null;
			 
			if(color == false & bold==false)
			{
				wfontStatus = new WritableFont(WritableFont.createFont("Arial"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			}
			else if(color == false & bold==true)
			{
				wfontStatus = new WritableFont(WritableFont.createFont("Arial"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			}
			else if(color == true & bold==false)
			{
				wfontStatus = new WritableFont(WritableFont.createFont("Arial"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE_GREY);
			}
			else if(color == true & bold==true)
			{
				wfontStatus = new WritableFont(WritableFont.createFont("Arial"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE_GREY);
			}
		    
			
		    WritableCellFormat fCellstatus = new WritableCellFormat(wfontStatus);
		    fCellstatus.setWrap(false);
		    fCellstatus.setAlignment(jxl.format.Alignment.CENTRE);
		    fCellstatus.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		    
		    if(borderLine == true)
		    {
		    	fCellstatus.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
		    }	
		    
		    return fCellstatus;
		}
		//-----------------------------End SetCellStyle---------------------------------------//
		
		
		//-------------------------------Begin EmailDiaglog----------------------------------//
		private void EmailDiaglog()
		{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		if (SelectedLanguage.trim().equals("heb")) // load the hebrew menu
		{
			alert.setTitle("הגדרות כתובת");
			alert.setMessage("הכנס את כתובת המייל ");
		}
		else
		{
			alert.setTitle("mail configuration");
			alert.setMessage("insert mail address");
		}
		

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);
		String value = LoadFromInternalMemory(getApplicationContext(),"EmailAddress");	
		input.setText(value);
		  
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  
		 String value = input.getText().toString();
		 SaveToInternalMemory(getApplicationContext(),"EmailAddress",value);
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		}
		//-------------------------------End EmailDiaglog----------------------------------//
		
		//---------------------------Begin Step-------------------------//
		private void Step(int value)
		{
			// value is -/+ 1
			thisMonthIndex = thisMonthIndex + value;
			
			if (thisMonthIndex<0) thisMonthIndex=0;
			else if (thisMonthIndex>12) thisMonthIndex=12;
			
		}
		//---------------------------End Step---------------------------//
		
		private double calcSalarySum(int dayInMonth)
		{
			double thisSalary=0;
			double totalSalary=0;
			for (int i=0; i<dayInMonth;i++)
			{
				
				TableRow thisTableRow = (TableRow) tableLayout.getChildAt(i);
				TextView thisSalaryRow = (TextView)thisTableRow.getChildAt(7);
				if (thisSalaryRow.length()>0)  thisSalary = Double.parseDouble(thisSalaryRow.getText().toString());			
				else thisSalary=0;				
				
				totalSalary = totalSalary + thisSalary;				
			}
			
			totalSalary = Math.floor(totalSalary* 100) / 100;
			return totalSalary;
		}
		
		//----------------------Begin SetLanguage -----------------------------//
		private void SetLanguage()
		{
			
			SelectedLanguage = LoadFromInternalMemory(getApplicationContext(),"Language");	
									
			Cell00 = (TextView)findViewById(R.id.Cell00);
			CellD0 = (TextView)findViewById(R.id.CellD0);
			Cell01 = (TextView)findViewById(R.id.Cell01);
			Cell02 = (TextView)findViewById(R.id.Cell02);
			Cell03 = (TextView)findViewById(R.id.Cell03);
			Cell04 = (TextView)findViewById(R.id.Cell04);
			Cell05 = (TextView)findViewById(R.id.Cell05);
			Cell06 = (TextView)findViewById(R.id.Cell06);
						
		    btnPunch = (Button)findViewById(R.id.btnPunch);		
		    btnSettings = (Button)findViewById(R.id.btnSettings);
		    
		    CellSummeryTotalHoursValue = (TextView)findViewById(R.id.CellSumNeto);
			CellSummeryOV1Value = (TextView)findViewById(R.id.CellSumOV1);
			CellSummeryOV2Value = (TextView)findViewById(R.id.CellSumOV2);
			
		    txtVpunch = (TextView)findViewById(R.id.txtVpunch);
		    txtVpunch.setText("");
		    
		   
			if (SelectedLanguage.trim().equals("heb")) // load the hebrew menu
			{
				Cell00.setText("יום");
				CellD0.setText("תאריך");
				Cell01.setText("התחלה");
				Cell02.setText("סיום");
				Cell03.setText("נטו");
				Cell04.setText("125%");
				Cell05.setText("150%");
				Cell06.setText("ברוטו");			
				btnSettings.setText("הגדרות");
				UserLangTitle = "משתמש";
				
				int State = LoadPunchState(getApplicationContext());
				 switch (State)
				 {
				 case 2:
					 btnPunch.setText("כניסה");
					 break;
				 default:
					 btnPunch.setText("יציאה");		
					 break;
				 }
				 
			}
			
			else // load the english menu
			{
				Cell00.setText("Day");	
				CellD0.setText("Date");	
				Cell01.setText("Start");
				Cell02.setText("End");	
				Cell03.setText("Neto");	
				Cell04.setText("125%");	
				Cell05.setText("150%");	
				Cell06.setText("Bruto");
								
				btnSettings.setText("Settings");
				UserLangTitle = "User";
				
				 int State = LoadPunchState(getApplicationContext());
				 switch (State)
				 {
				 case 2:
					 btnPunch.setText("Entry");
					 break;
				 default:
					 btnPunch.setText("Exit");		
					 break;
				 }
			}
		
		}
		//--------------------------------End SetLanguge---------------------//
}		


