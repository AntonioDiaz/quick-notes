package com.quicknotes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityMain extends Activity {

	protected static final String FILE_NAME = "temp.txt";
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FileInputStream inputStream;
		InputStreamReader inputStreamReader;
		BufferedReader bufferedReader;
		StringBuilder txt = new StringBuilder();
		try {
			inputStream = openFileInput(FILE_NAME);
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = bufferedReader.readLine())!=null) {
				txt.append(line);
			}

			inputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EditText editText = (EditText)findViewById(R.id.edit_text);
		editText.setText(txt);
		
		Button saveButton = (Button)this.findViewById(R.id.save_button);
		context = this;
		saveButton.setOnClickListener(this.createButtonListener());
	}

	private OnClickListener createButtonListener() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editText = (EditText)findViewById(R.id.edit_text);
				String myText = editText.getText().toString();
				Toast.makeText(ActivityMain.this, "vamossss:" + myText, Toast.LENGTH_SHORT).show();
				
				/** save text content. */
				FileOutputStream outputStream;
				try {
					outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
					outputStream.write(myText.getBytes());
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
