package com.example.reportes2;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	EditText usuario, contraseña;
	Button ingresar;
	TextView registrar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		usuario = (EditText) findViewById (R.id.etUsuario);
		contraseña = (EditText) findViewById (R.id.etContra);
		ingresar = (Button) findViewById (R.id.btEntrar);
		registrar  = (TextView) findViewById (R.id.tvRegistrar);
		
		ingresar.setOnClickListener(this);
		registrar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		Intent i = null;
		switch (arg0.getId()) {
		case R.id.btEntrar:
			
		    i = new Intent (this,Aula.class);
			startActivity(i);
			break;
		case R.id.tvRegistrar:
			i = new Intent (this,Registro.class);
			startActivity(i);		
			break;

		default:
			break;
		}
		
	}

}
