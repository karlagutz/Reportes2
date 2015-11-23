package com.example.reportes2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;


public class Aula extends Activity implements android.view.View.OnClickListener {
	ExpandableListAdapter listAdapter;
	Button enviar;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	Context con = this;
	String mensaje;
    ArrayList<String> selecteds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aula);
		
		selecteds = new ArrayList<String>();
		
		 expListView = (ExpandableListView) findViewById(R.id.lvAula);
		 enviar = (Button) findViewById(R.id.btEnviar);
		 Log.d("simon", "hay baa");
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
	                Toast.makeText(getApplicationContext(),
	                        listDataHeader.get(groupPosition) + " Expanded",
	                        Toast.LENGTH_SHORT).show();
	            }
	        });
	        
	     // Listview Group collasped listener
	        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
	 
	            @Override
	            public void onGroupCollapse(int groupPosition) {
	                Toast.makeText(getApplicationContext(),
	                        listDataHeader.get(groupPosition) + " Collapsed",
	                        Toast.LENGTH_SHORT).show();
	 
	            }
	        });
	        
	        expListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					
					CheckBox cb = (CheckBox)v.findViewById(R.id.lblListItem);
					boolean selection = cb.isChecked();
					String childString;
					
					if (selection) {
						cb.setChecked(false);
					    childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
						selecteds.remove(childString);
						
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
			mensaje = "Se le manda un comunicado ";
			Object [] items  = selecteds.toArray();
			for (int i = 0; i < items.length; i++) {
				
			}
			
			break;
			
		}
		
	}

	

}
