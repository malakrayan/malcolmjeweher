package com.example.malcolmjeweher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerLogIn extends AppCompatActivity {


    EditText customerIdEditText;
    EditText passwordEditText;
    Button signInButton;
    TextView forgotPasswordTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_log_in);


        mAuth = FirebaseAuth.getInstance();


        customerIdEditText = findViewById(R.id.et_customer_id);
        passwordEditText = findViewById(R.id.et_password);
        signInButton = findViewById(R.id.btn_sign_in);
        forgotPasswordTextView = findViewById(R.id.tv_forgot_password);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = customerIdEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CustomerLogIn.this, "الرجاء إدخال البريد وكلمة المرور", Toast.LENGTH_SHORT).show();
                } else {

                    loginUser(email, password);
                }
            }
        });


        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerLogIn.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        Toast.makeText(CustomerLogIn.this, "أهلاً بك مجدداً!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerLogIn.this, Main_view.class);
                        startActivity(intent);
                        finish();
                    } else {

                        Toast.makeText(CustomerLogIn.this, "خطأ: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(CustomerLogIn.this, Main_view.class));
            finish();
        }
    }
}