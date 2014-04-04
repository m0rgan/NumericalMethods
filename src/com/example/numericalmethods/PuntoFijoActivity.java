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

public class PuntoFijoActivity extends Activity implements OnClickListener{
	
	private Button btnGraficarP;
	private Button btnSolucionarP;
	private EditText etFP;
	private EditText etXP;
	private EditText etTP;
	private EditText etIP;
	private EditText etFGP;
	
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
	        	 setContentView(R.layout.activity_helppuntofijo);
	             ;
	             return true;
	         default:
	             return super.onOptionsItemSelected(action_help);
	     }
	 }
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntofijo);
        
        btnGraficarP = (Button)findViewById(R.id.btnGraficarP);
        btnSolucionarP = (Button)findViewById(R.id.btnSolucionarP);
        etFP = (EditText)findViewById(R.id.etFP);
        etXP = (EditText)findViewById(R.id.etXP);
        etTP = (EditText)findViewById(R.id.etTP);
        etIP = (EditText)findViewById(R.id.etIP);
        etFGP = (EditText)findViewById(R.id.etFGP);
        
        btnGraficarP.setOnClickListener(this);
        btnSolucionarP.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGraficarP:
			break;	
		case R.id.btnSolucionarP:
			double xi = Double.parseDouble(etXP.getText().toString());
			double tol = Double.parseDouble(etTP.getText().toString());
			int iter = Integer.parseInt(etIP.getText().toString());
			String Xi = etXP.getText().toString();
			String Tol = etTP.getText().toString();
			String Iter = etIP.getText().toString();
			if( Xi == null || Tol == null || Iter == null){
				Mensaje("Por favor llene correctamente todos los campos");
			}else{
				PuntoFijo(xi,tol,iter);
			}
			break;
		}
	}
	
	public void PuntoFijo(double xa, double tol, int iter){
		double ya = funcionf(xa);
		int cont = 0;
		double error = tol + 1;
		while(ya != 0 && error > tol && cont < iter){
			double xn = funciong(xa);
			ya = funciong(xn);
			error = Math.abs(xn - xa);
			xa = xn;
			cont ++;		
		}
		if(xa == 0){
			 Mensaje(xa + " es raiz");
		}else{
			if(error < tol){
				 Mensaje(xa + " es una aproximacion a una raiz con una tolerancia de " + tol);
			}else{
				 Mensaje("fracaso en " + iter + " iteraciones");
			}
		}
	}

	public double funcionf(double x){
			return (x*x*x);
	}

	public double funciong(double x){
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
						PuntoFijoActivity.this.finish();
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
