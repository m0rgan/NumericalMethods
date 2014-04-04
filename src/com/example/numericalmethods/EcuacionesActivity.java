package com.example.numericalmethods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EcuacionesActivity extends Activity implements OnClickListener{

	private Button btnBusquedas;
	private Button btnBiseccion;
	private Button btnReglaFalsa;
	private Button btnPuntofijo;
	private Button btnNewton;
	private Button btnSecante;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecuaciones);
        
        btnBusquedas = (Button)findViewById(R.id.btnBusquedas);
        btnBiseccion = (Button)findViewById(R.id.btnBiseccion);
        btnReglaFalsa = (Button)findViewById(R.id.btnReglaFalsa);
        btnPuntofijo = (Button)findViewById(R.id.btnPuntofijo);
        btnNewton = (Button)findViewById(R.id.btnNewton);
        btnSecante = (Button)findViewById(R.id.btnSecante);
        
        btnBusquedas.setOnClickListener(this);
        btnBiseccion.setOnClickListener(this);
        btnReglaFalsa.setOnClickListener(this);
        btnPuntofijo.setOnClickListener(this);
        btnNewton.setOnClickListener(this);
        btnSecante.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnBusquedas:
			startActivity(new Intent("Busquedas"));
			break;	
		case R.id.btnBiseccion:
			startActivity(new Intent("Biseccion"));
			break;
		case R.id.btnReglaFalsa:
			startActivity(new Intent("ReglaFalsa"));
			break;
		case R.id.btnPuntofijo:
			startActivity(new Intent("PuntoFijo"));
			break;
		case R.id.btnNewton:
			startActivity(new Intent("Newton"));
			break;
		case R.id.btnSecante:
			startActivity(new Intent("Secante"));
			break;
		}
	}

}
