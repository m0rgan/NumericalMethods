package com.example.numericalmethods;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button btnEcuaciones;
	private Button btnSistemas;
	private Button btnInterpolacion;
	private Button btnIntegracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnEcuaciones = (Button)findViewById(R.id.btnEcuaciones);
        btnSistemas = (Button)findViewById(R.id.btnSistemas);
        btnInterpolacion = (Button)findViewById(R.id.btnInterpolacion);
        btnIntegracion = (Button)findViewById(R.id.btnIntegracion);
        
        btnEcuaciones.setOnClickListener(this);
        btnSistemas.setOnClickListener(this);
        btnInterpolacion.setOnClickListener(this);
        btnIntegracion.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnEcuaciones:
			startActivity(new Intent("Ecuaciones"));
			break;	
		case R.id.btnSistemas:
			break;
		case R.id.btnInterpolacion:
			break;
		case R.id.btnIntegracion:
			break;
		}	
	}

    
}
