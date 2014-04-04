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

public class ReglaFalsaActivity extends Activity implements OnClickListener{
	
	private Button btnGraficarR;
	private Button btnSolucionarR;
	private EditText etFR;
	private EditText etXIR;
	private EditText etXSR;
	private EditText etTR;
	private EditText etIR;
	
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
	        	 setContentView(R.layout.activity_helpreglafalsa);
	             ;
	             return true;
	         default:
	             return super.onOptionsItemSelected(action_help);
	     }
	 }
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglafalsa);
        
        btnGraficarR = (Button)findViewById(R.id.btnGraficarR);
        btnSolucionarR = (Button)findViewById(R.id.btnSolucionarR);
        etFR = (EditText)findViewById(R.id.etFBi);
        etXIR = (EditText)findViewById(R.id.etXIR);
        etXSR = (EditText)findViewById(R.id.etXSR);
        etTR = (EditText)findViewById(R.id.etTR);
        etIR = (EditText)findViewById(R.id.etIR);
        
        btnGraficarR.setOnClickListener(this);
        btnSolucionarR.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGraficarR:
			break;	
		case R.id.btnSolucionarR:
			double xi = Double.parseDouble(etXIR.getText().toString());
			double xs = Double.parseDouble(etXSR.getText().toString());
			double tol = Double.parseDouble(etTR.getText().toString());
			int iter = Integer.parseInt(etIR.getText().toString());
			String Xi = etXIR.getText().toString();
			String Xs = etXSR.getText().toString();
			String Tol = etTR.getText().toString();
			String Iter = etIR.getText().toString();
			if( Xi == null || Xs == null || Tol == null || Iter == null){
				Mensaje("Por favor llene correctamente todos los campos");
			}else{
				ReglaFalsa(xi,xs,iter,tol);
			}
			
			break;
		}
	}
	
	public void ReglaFalsa(double xi,double xs, int iter, double tol){
		double yi = funcion(xi);
		double ys = funcion(xs);
		if(yi == 0){
			Mensaje(xi + " es una raiz");
		}else{
			if(ys == 0){
				Mensaje(xs + " es una raiz");
			}else{
				if(yi*ys < 0){
					double xm = xi - ((funcion(xi)*(xi - xs))/(funcion(xi)-funcion(xs)));
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
						xm = xi - ((funcion(xi)*(xi - xs))/(funcion(xi)-funcion(xs)));
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
						ReglaFalsaActivity.this.finish();
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
