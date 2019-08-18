package com.example.priyansh.dmrc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private TextView mess;
    private Button login;
    private boolean loggedin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText) findViewById(R.id.editText);
        pass=(EditText) findViewById(R.id.editText2);
        login=(Button) findViewById(R.id.button);
        mess=(TextView) findViewById(R.id.message);
        Toast.makeText(this, "Connection Success", Toast.LENGTH_SHORT).show();
        if(loggedin){
            Intent intent=new Intent(MainActivity.this,Main2Activity_complaint.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString(), pass.getText().toString());
            }
        });

    }


    private void validate(String email,String pass){

        if((email.equals("Admin")) && (pass.equals("12345"))){
            Intent intent=new Intent(MainActivity.this,Main2Activity_complaint.class);
            startActivity(intent);
            loggedin=true;
        }else{
            mess.setText("Incorrect Username or Password");
        }
    }
}
