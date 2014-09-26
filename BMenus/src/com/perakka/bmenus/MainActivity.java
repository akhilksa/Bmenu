package com.perakka.bmenus;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int MENU_ADD=1, MENU_SEND=2, MENU_DEL=3; 
	private final int GROUP_DEFAULT=0, GROUP_DEL=1; 
	private final int ID_DEFAULT=0; 
	private final int ID_TEXT1=1, ID_TEXT2=2, ID_TEXT3=3; 
	private String[] choices = {"Press Me", "Try Again", "Change Me"};
	private static int itemNum=0; 
	private TextView bv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bv=(TextView)findViewById(R.id.textView1);
		registerForContextMenu((View) findViewById(R.id.textView1));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		
		menu.add(GROUP_DEFAULT, MENU_ADD, 0	, "Add").setIcon(R.drawable.ic_launcher);
		menu.add(GROUP_DEFAULT, MENU_SEND, 0, "Send");
		menu.add(GROUP_DEL, MENU_DEL, 0, "Del");
		return super.onCreateOptionsMenu(menu);
	}

	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		if(itemNum>0){
			menu.setGroupVisible(GROUP_DEL, true);
		}
		else {
			menu.setGroupVisible(GROUP_DEL, false);
		}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()) { 
		case MENU_ADD:
		create_note(); 
		return true;
		case MENU_SEND: 
		send_note(); 
		return true;
		case MENU_DEL: 
		delete_note(); 
		return true;
		} 
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		if(v.getId() == R.id.textView1) {
			SubMenu textMenu = menu.addSubMenu("Change Text"); 
			textMenu.add(0, ID_TEXT1, 0, choices[0]); 
			textMenu.add(0, ID_TEXT2, 0, choices[1]); 
			textMenu.add(0, ID_TEXT3, 0, choices[2]); 
			menu.add(0, ID_DEFAULT, 0, "Original Text");
			} 
		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) { 
		case ID_DEFAULT:
		bv.setText(R.string.hello); 
		return true;
		case ID_TEXT1: 
		case ID_TEXT2: 
		case ID_TEXT3:
			bv.setText(choices[item.getItemId()-1]); 
			return true;
			} 
		return super.onContextItemSelected(item);
	}

	void create_note() { // mock code to create note 
		itemNum++;
		} 
		void send_note() { // mock code to send note
		Toast.makeText(this, "Item: "+itemNum, 
		Toast.LENGTH_SHORT).show();
		} 
		void delete_note() { // mock code to delete note
		itemNum--; 
		}
}
