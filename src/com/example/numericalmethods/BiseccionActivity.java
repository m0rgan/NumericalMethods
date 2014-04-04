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

public class BiseccionActivity extends Activity implements OnClickListener{
	
	private Button btnGraficarB;
	private Button btnSolucionarB;
	private EditText etFBi;
	private EditText etXIBi;
	private EditText etXSBi;
	private EditText etTBi;
	private EditText etIBi;
	
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
	        	 setContentView(R.layout.activity_helpbiseccion);
	             ;
	             return true;
	         default:
	             return super.onOptionsItemSelected(action_help);
	     }
	 }
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biseccion);
        
        btnGraficarB = (Button)findViewById(R.id.btnGraficarB);
        btnSolucionarB = (Button)findViewById(R.id.btnSolucionarB);
        etFBi = (EditText)findViewById(R.id.etFBi);
        etXIBi = (EditText)findViewById(R.id.etXIBi);
        etXSBi = (EditText)findViewById(R.id.etXSBi);
        etTBi = (EditText)findViewById(R.id.etTBi);
        etIBi = (EditText)findViewById(R.id.etIBi);
        
        btnGraficarB.setOnClickListener(this);
        btnSolucionarB.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGraficarB:
			break;	
		case R.id.btnSolucionarB:
			double xi = Double.parseDouble(etXIBi.getText().toString());
			double xs = Double.parseDouble(etXSBi.getText().toString());
			double tol = Double.parseDouble(etTBi.getText().toString());
			int iter = Integer.parseInt(etIBi.getText().toString());
			String Xi = etXIBi.getText().toString();
			String Xs = etXSBi.getText().toString();
			String Tol = etTBi.getText().toString();
			String Iter = etIBi.getText().toString();
			if( Xi == null || Xs == null || Tol == null || Iter == null){
				Mensaje("Por favor llene correctamente todos los campos");
			}else{
			Biseccion(xi,xs,tol,iter);
			}
			break;
		}
	}
	
	public void Biseccion(double xi, double xs, double tol, int iter ){
		double yi = funcion(xi);
		double ys = funcion(xs);
		if(yi == 0){
			Mensaje(xi + " es una raiz");
		}else{
			if(ys == 0){
				Mensaje(xs + " es una raiz");
			}else{
				if(yi*ys < 0){
					double xm = (xi+xs)/2;
					double ym = funcion(xm);
					int cont = 1;
					double error = tol + 1;
					while(error > tol && ym != 0 && cont < iter){
						if(ys*ym < 0){
							xs = xm;
							ys = ym;					
						}else{
							xi = xm;
							yi = ym;					
						}
						double aux = xm;
						xm = (xi + xs)/2;
						ym = funcion(xm);
						error = Math.abs(xm - aux);
						cont ++;
					}
					if(ym == 0){
						Mensaje(xm + " es una raiz");				
					}else{
						if(error < tol){
							Mensaje(xm + " es una aproximacion a una raiz con una tolerancia de " + tol);
						}else{
							Mensaje("fracaso en " + iter + " iteraciones");
						}
					}
				}else{
					Mensaje("el intervalo es invalido");
				}
			}	
		}	
	}	

	public double funcion(double x){
		return (x-1);
	}
	
    public void Mensaje(String s){
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setMessage(s);
		dialog.setCancelable(false);
		dialog.setPositiveButton("Salir",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						BiseccionActivity.this.finish();
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
