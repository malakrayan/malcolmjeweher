package com.example.malcolmjeweher;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerLogIn extends AppCompatActivity {

    EditText customerIdEditText;
    EditText passwordEditText;
    Button signInButton;
    TextView forgotPasswordTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_log_in);

        customerIdEditText = findViewById(R.id.et_customer_id);
        passwordEditText = findViewById(R.id.et_password);
        signInButton = findViewById(R.id.btn_sign_in);
        forgotPasswordTextView = findViewById(R.id.tv_forgot_password);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerId = customerIdEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (customerId.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CustomerLogIn.this, "الرجاء إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
                } else {
                    if (customerId.equals("user123") && password.equals("pass123")) {
                        Toast.makeText(CustomerLogIn.this, "تم تسجيل الدخول بنجاح!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerLogIn.this, Main_view.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(CustomerLogIn.this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerLogIn.this, ForgotPassword.class);
                startActivity(intent);
                finish();

            }
        });
    }
}