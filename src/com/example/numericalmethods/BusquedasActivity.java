package com.example.numericalmethods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BusquedasActivity extends Activity implements OnClickListener{
	
	private Button btnGraficar;
	private Button btnSolucionar;
	private EditText etFB;
	private EditText etXB;
	private EditText etDB;
	private EditText etIB;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }
	
	@Override
	 public boolean onOptionsItemSelected(MenuItem action_help) {
	     // Handle item selection
	     switch (action_help.getItemId()) {
	         case R.id.action_help:
	        	 setContentView(R.layout.activity_helpbusquedas);
	             ;
	             return true;
	         default:
	             return super.onOptionsItemSelected(action_help);
	     }
	 }
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquedas);
        
        btnGraficar = (Button)findViewById(R.id.btnGraficar);
        btnSolucionar = (Button)findViewById(R.id.btnSolucionar);
        etFB = (EditText)findViewById(R.id.etFB);
        etXB = (EditText)findViewById(R.id.etXB);
        etDB = (EditText)findViewById(R.id.etDB);
        etIB = (EditText)findViewById(R.id.etIB);
        
        btnGraficar.setOnClickListener(this);
        btnSolucionar.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGraficar:			
			break;	
		case R.id.btnSolucionar:
			double xi = Double.parseDouble(etXB.getText().toString());
			double delta = Double.parseDouble(etDB.getText().toString());
			int iter = Integer.parseInt(etIB.getText().toString());
			String Xi = etXB.getText().toString();
			String Delta = etDB.getText().toString();
			String Iter = etIB.getText().toString();
			if( Xi == null || Delta == null || Iter == null){
				Mensaje("Por favor llene correctamente todos los campos");
			}else{
				BusquedasIncrementales(xi,delta,iter);
			}
			break;
		}
	}
	
	public double funcion(double x){
		return (3*x)-(Math.exp(x));
	}

	public void BusquedasIncrementales(double xi, double delta, int iter){
		double yi = funcion(xi);
		if(yi == 0){
			Mensaje(xi + " es raiz");
		}else{
			double xs = xi + delta;
			double ys = funcion(xs);
			int cont = 1;
			while( yi*ys > 0 && ys != 0 && cont < iter){
				xi = xs;
				yi = ys;
				xs = xi + delta;
				ys = funcion(xs);
				cont ++;			
			}
			if( ys == 0){
				Mensaje(xs + " es raiz");
			}else
				if(yi*ys < 0){
					Mensaje("Intervalo: [" + xi + ";" + xs + "]");
				}else{
					Mensaje("fracaso en esas iteraciones");
				}		
		}		
	}
	
	public void Mensaje(String s){
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setMessage(s);
		dialog.setCancelable(false);
		dialog.setPositiveButton("Salir",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						BusquedasActivity.this.finish();
					}
				});
		dialog.setNegativeButton("Calcular otro intervalo",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		dialog.show();
	}
	
}
