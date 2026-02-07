package com.example.malcolmjeweher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// استيراد مكتبة Firebase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerLogIn extends AppCompatActivity {

    // تعريف العناصر بناءً على الـ XML الخاص بكِ
    EditText customerIdEditText; // هذا هو et_customer_id
    EditText passwordEditText;   // هذا هو et_password
    Button signInButton;         // هذا هو btn_sign_in
    TextView forgotPasswordTextView; // هذا هو tv_forgot_password

    // تعريف Firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_log_in);

        // تهيئة Firebase
        mAuth = FirebaseAuth.getInstance();

        // ربط العناصر بالـ XML
        customerIdEditText = findViewById(R.id.et_customer_id);
        passwordEditText = findViewById(R.id.et_password);
        signInButton = findViewById(R.id.btn_sign_in);
        forgotPasswordTextView = findViewById(R.id.tv_forgot_password);

        // برمجة زر تسجيل الدخول
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = customerIdEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CustomerLogIn.this, "الرجاء إدخال البريد وكلمة المرور", Toast.LENGTH_SHORT).show();
                } else {
                    // الاتصال بـ Firebase للتحقق من البيانات
                    loginUser(email, password);
                }
            }
        });

        // الانتقال لصفحة نسيت كلمة المرور
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
                        // نجح تسجيل الدخول
                        Toast.makeText(CustomerLogIn.this, "أهلاً بك مجدداً!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerLogIn.this, Main_view.class);
                        startActivity(intent);
                        finish(); // إغلاق الصفحة الحالية
                    } else {
                        // فشل تسجيل الدخول (كلمة مرور خطأ أو إيميل غير مسجل)
                        Toast.makeText(CustomerLogIn.this, "خطأ: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // فحص إذا كان المستخدم مسجل دخول من قبل (تلقائي)
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