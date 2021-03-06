package com.example.reportes2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends Activity implements OnClickListener {
	EditText nombre, contra;
	Button confirmar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_registro);
		
		nombre = (EditText) findViewById (R.id.etUsuario);
		contra = (EditText) findViewById (R.id.etContra);
		confirmar = (Button) findViewById (R.id.btConfirmar);
		
		confirmar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registro, menu);
		return true;
	}


	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btConfirmar:
			Intent i = new Intent (this,MainActivity.class);
			startActivity(i);
			break;

		}
		
	}

}
