package com.example.numericalmethods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecanteActivity extends Activity implements OnClickListener{
	
	private Button btnGraficarS;
	private Button btnSolucionarS;
	private EditText etFS;
	private EditText etX0S;
	private EditText etX1S;
	private EditText etTS;
	private EditText etIS;
	
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
	        	 setContentView(R.layout.activity_helpsecante);
	             ;
	             return true;
	         default:
	             return super.onOptionsItemSelected(action_help);
	     }
	 }
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secante);
        
        btnGraficarS = (Button)findViewById(R.id.btnGraficarS);
        btnSolucionarS = (Button)findViewById(R.id.btnSolucionarS);
        etFS = (EditText)findViewById(R.id.etFS);
        etX0S = (EditText)findViewById(R.id.etX0S);
        etX1S = (EditText)findViewById(R.id.etX1S);
        etTS = (EditText)findViewById(R.id.etTS);
        etIS = (EditText)findViewById(R.id.etIS);
        
        btnGraficarS.setOnClickListener(this);
        btnSolucionarS.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGraficarS:
			break;	
		case R.id.btnSolucionarS:
			double x0 = Double.parseDouble(etX0S.getText().toString());
			double x1 = Double.parseDouble(etX1S.getText().toString());
			double tol = Double.parseDouble(etTS.getText().toString());
			int iter = Integer.parseInt(etIS.getText().toString());
			String X0 = etX0S.getText().toString();
			String X1 = etX1S.getText().toString();
			String Tol = etTS.getText().toString();
			String Iter = etIS.getText().toString();
			if( X0 == null || X1 == null || Tol == null || Iter == null){
				Mensaje("Por favor llene correctamente todos los campos");
			}else{
				Secante(x0,x1,tol,iter);
			}
			
			break;
		}
	}

	public void Secante(double x0,double x1,double tol, int iter){	
		double Y0 = funcion(x0);
		if( Y0 == 0 ){
			Mensaje(x0 + " es raiz");
		}else{
			double Y1 = funcion(x1);
			int cont = 0;
			double error = tol + 1;
			double deno = Y1 - Y0;
			while( error > tol && Y1 != 0 && deno != 0 && cont < iter ){
				double x2 = x1 - Y1 * (x1 - x0)/ deno;
				error = Math.abs(x2 - x1);
				x0 = x1;
				Y0 = Y1;
				x1 = x2;
				Y1 = funcion(x1);
				deno = Y1 - Y0;
				cont = cont + 1;
			}
			if( Y1 == 0 ){
				Mensaje(x1 + " es raiz");
			}else{
				if( error < tol ){
					Mensaje(x1 + " es una aproximacion a una raiz con una tolerancia de " + tol);
				}else{
					if( deno == 0 ){
						Mensaje("hay una posible raiz multiple");
					}else{
						Mensaje("fracaso en " + iter + " iteraciones");
					}
				}
			}
		}
	}
	
	public double funcion(double x){
		return (x*x*x);
	}
	
	public void Mensaje(String s){
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setMessage(s);
		dialog.setCancelable(false);
		dialog.setPositiveButton("Salir",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						SecanteActivity.this.finish();
					}
				});
		dialog.setNegativeButton("Calcular otra raiz",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		dialog.show();
	}

}
