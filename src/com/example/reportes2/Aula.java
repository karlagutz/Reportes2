package com.example.reportes2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;


@SuppressLint("NewApi") public class Aula extends Activity implements android.view.View.OnClickListener {
	ExpandableListAdapter listAdapter;
	Button enviar;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	Context con = this;
	String mensaje;
    ArrayList<String> selecteds;
    EditText aula,desc;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_aula);
		
		selecteds = new ArrayList<String>();
		
		 expListView = (ExpandableListView) findViewById(R.id.lvAula);
		 enviar = (Button) findViewById(R.id.btEnviar);
		 aula = (EditText)findViewById(R.id.etAula);
		 desc = (EditText)findViewById(R.id.etDescripcion);
		 
		 
		 enviar.setOnClickListener(this);
		 // preparing list data
	        prepareListData();
	 
	        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
	 
	        // setting list adapter
	        expListView.setAdapter(listAdapter);
	     
	        expListView.setOnGroupClickListener(new OnGroupClickListener() {
	        	 
	            @Override
	            public boolean onGroupClick(ExpandableListView parent, View v,
	                    int groupPosition, long id) {
	                // Toast.makeText(getApplicationContext(),
	                // "Group Clicked " + listDataHeader.get(groupPosition),
	                // Toast.LENGTH_SHORT).show();
	                return false;
	            }
	        });
	        
	        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
	        	 
	            @Override
	            public void onGroupExpand(int groupPosition) {
	                
	            }
	        });
	        
	     // Listview Group collasped listener
	        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
	 
	            @Override
	            public void onGroupCollapse(int groupPosition) {
	                
	 
	            }
	        });
	        
	        expListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					
					CheckBox cb = (CheckBox)v.findViewById(R.id.lblListItem);
				  
					boolean selection = cb.isChecked();
					String childString;
					childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
					 
					if (selection) {
						cb.setChecked(false);
					    childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
						selecteds.remove(childString);
						
						int [][] ma = listAdapter.getMatriz();
						ma[groupPosition][childPosition] = 0;
						listAdapter.setMatriz(ma);
						
					} else {
						cb.setChecked(true);
					    childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
						Toast.makeText(
		                        getApplicationContext(),
		                        listDataHeader.get(groupPosition)
		                                + " : "
		                                +  childString
		                                + " añadido a la lista"
		                                , Toast.LENGTH_SHORT)
		                        .show();
						selecteds.add(childString);
						int [][] ma = listAdapter.getMatriz();
						ma[groupPosition][childPosition] = 1;
						listAdapter.setMatriz(ma);
					}
					
					
					return false;
				}
			});
	        
	        

	}
	
	  /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Infraestructura");
        listDataHeader.add("Limpieza");
        listDataHeader.add("Equipo de Cómputo");
 
        // Adding child data
        List<String> Infraestructura = new ArrayList<String>();
        Infraestructura.add("Iluminación");
        Infraestructura.add("Electricidad");
        Infraestructura.add("Aire Acondicionado");
        Infraestructura.add("Mesabancos/Sillas");
        Infraestructura.add("Mesa/Escritorio");
        Infraestructura.add("Ventanas");
        Infraestructura.add("Contacto Eléctrico");
        Infraestructura.add("Llave de Aula");
 
        List<String> Limpieza = new ArrayList<String>();
        Limpieza.add("Aseo de aula");
        Limpieza.add("Contenedor de basura");
        Limpieza.add("Pintarrón / Pizarrón");

 
        List<String> computo = new ArrayList<String>();
        computo.add("Configuración de Red");
        computo.add("Internet");
        computo.add("Formateo");
        computo.add("Instalación de Software");
        computo.add("Instalación Equipo de Computo");
        computo.add("Mantenimiento preventivo");
        computo.add("Mantenimiento correctivo");
        computo.add("Mantenimiento de Impresora");
        computo.add("Mantenimiento de Proyector");
        computo.add("Reparación de cables");
        
 
        listDataChild.put(listDataHeader.get(0), Infraestructura); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Limpieza);
        listDataChild.put(listDataHeader.get(2), computo);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aula, menu);
		return true;
	}

	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btEnviar:
			String aul = aula.getText().toString();
			String des = desc.getText().toString();
			if (aul.length() > 0) {
				
				mensaje = "El sistema de SuperReportesUni le manda un comunicado sobre " +
							"problemas que se an detectado en el aula " + aul + ".\n" +
							"A Continuacion un listado de los problemas: \n";
				Object [] items  = selecteds.toArray();
				for (int i = 0; i < items.length; i++) {
					mensaje = mensaje + "\n" +  String.valueOf(items[i]) + ".";
				}
				mensaje = mensaje + "\n\n";
				mensaje = mensaje + "Descripcion: \n\n" + des;
				Log.d("mesaje",mensaje);
				
				Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
				 
		        //vamos a enviar texto plano a menos que el checkbox esté marcado 
		        itSend.setType("plain/text");
		 
		        //colocamos los datos para el envío
		        itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"reportes.unison@gmail.com"});
		        itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sistema De Fallas");
		        itSend.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
		        startActivity(itSend);
		        cleanScreen();
			} else {
				
				Toast t = Toast.makeText(this, "Introdusca Un Salon de clases", Toast.LENGTH_SHORT);
				t.show();
			}
			
			
			break;
		}
		
	}
	
	public void cleanScreen(){
		
		aula.setText("");
		desc.setText("");
		int ma [][] = listAdapter.getMatriz();
		listAdapter.fillMatrix(ma);
		listAdapter.setMatriz(ma);
		
		
		
	}

	

}
