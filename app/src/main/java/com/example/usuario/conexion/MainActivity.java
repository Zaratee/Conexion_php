package com.example.usuario.conexion;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText eTxt;
    TextView txtV;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTxt = (EditText) findViewById(R.id.eTxt_recibir);
        txtV = (TextView) findViewById(R.id.txtV_mostrar);
        submit = (Button) findViewById(R.id.btn_Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSON();
            }
        });
    }

    public void JSON()
    {
        String url = "http://192.168.84.33:8080/act1.php?numero="+ eTxt.getText().toString();

        JsonObjectRequest peticion = new JsonObjectRequest
                (
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    txtV.setText("" + response.getInt("factorial"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        RequestQueue x = Volley.newRequestQueue(this);
        x.add(peticion);
    }
}
