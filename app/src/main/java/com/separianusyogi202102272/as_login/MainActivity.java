package com.separianusyogi202102272.as_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    private Button _loginButton;
    private EditText _idEditText;
    private EditText _passwordEditText;
    private Intent _menuIntent;
    private String _id;
    private String _password;
    private String _url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _idEditText = (EditText) findViewById(R.id.idEditext);
        _loginButton = (Button) findViewById(R.id.loginButton);
        _passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        AsynHttpClient asynHttpClient = new AsynHttpClient();
        asynHttpClient.get(_url, new AsynHttpResponselHandler() {
            @Override
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, byte[] responseBody) {
                String hasil = new String(responseBody);

                if (!hasil.equals("[{\"idCount\":\"1\"}]")) {
                    Toast.makeText(getApplicationContext(), "ID dan password anda tidak cocok.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), "Selamat datang," + _id, Toast.LENGTH_SHORT).show();

                _menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(_menuIntent);

            }

            @Override
            public void onFairlure(int statusCode, PreferenceActivity.Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
