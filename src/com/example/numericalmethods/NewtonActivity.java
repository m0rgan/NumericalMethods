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

public class NewtonActivity extends Activity implements OnClickListener{

	private Button btnGraficarN;
	private Button btnSolucionarN;
	private EditText etFN;
	private EditText etXN;
	private EditText etTN;
	private EditText etIN;
	private EditText etDN;
	
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
	        	 setContentView(R.layout.activity_helpnewton);
	             ;
	             return true;
	         default:
	             return super.onOptionsItemSelected(action_help);
	     }
	 }
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton);
        
        btnGraficarN = (Button)findViewById(R.id.btnGraficarN);
        btnSolucionarN = (Button)findViewById(R.id.btnSolucionarN);
        etFN = (EditText)findViewById(R.id.etFN);
        etXN = (EditText)findViewById(R.id.etXN);
        etTN = (EditText)findViewById(R.id.etTN);
        etIN = (EditText)findViewById(R.id.etIN);
        etDN = (EditText)findViewById(R.id.etDN);
        
        btnGraficarN.setOnClickListener(this);
        btnSolucionarN.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGraficarN:
			break;	
		case R.id.btnSolucionarN:
			double X0 = Double.parseDouble(etXN.getText().toString());
			double tol = Double.parseDouble(etTN.getText().toString());
			int iter = Integer.parseInt(etIN.getText().toString());
			String X = etXN.getText().toString();
			String Tol = etTN.getText().toString();
			String Iter = etIN.getText().toString();
			if( X == null || Tol == null || Iter == null){
				Mensaje("Por favor llene correctamente todos los campos");
			}else{
				Newton(X0,tol,iter);
			}	
			break;
		}
	}
	
	public void Newton(double X0,double tol, int iter){
		double Yx = funcion(X0);
		double DYx = derivada(X0);
		int cont = 0;
		double error = tol + 1;
		while (error > tol && Yx != 0 && DYx != 0 && cont < iter){
			double X1 = X0 - Yx / DYx;
			Yx = funcion(X1);
			DYx = derivada(X1);
			error = Math.abs(X1 - X0);
			X0 = X1;
			cont = cont + 1;
		}
		if( Yx == 0){
			Mensaje(X0 + " es unra raiz");
		}else{
			if( error < tol ){
				Mensaje(X0 + " es una aproximacion a una raiz con una tolerancia de " + tol);
			}else{
				if( DYx == 0 ){
					Mensaje(X0 + "es una posible raiz multiple");
				}else{
					Mensaje("fracaso en " +  iter + " iteraciones");
				}
			}
		}
	}
	
	public double funcion(double x){
		return (x*x*x);
	}

	public double derivada(double x){
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
					NewtonActivity.this.finish();
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
