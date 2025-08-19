package com.example.malcolmjeweher;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class activity_login_sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        Button signUpButton = findViewById(R.id.btn_sign_up);
        Button logInButton = findViewById(R.id.btn_log_in);


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_login_sign_up.this, CustomerLogIn.class);
                startActivity(intent);
            }

        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_login_sign_up.this, register.class);
                startActivity(intent);
            }
        });
    }
}