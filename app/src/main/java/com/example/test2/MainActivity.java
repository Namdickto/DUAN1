package com.example.test2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        EditText edtUserName = findViewById(R.id.edtUserName);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView DangKy = findViewById(R.id.DangKy);
        DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = edtUserName.getText().toString();
                String Pass = edtPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(User,Pass).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Đăng Nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,MainActivity3.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Đăng Nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
//        mAuth = FirebaseAuth.getInstance();
//        String email = "namdeptrai@gmail.com";
//        String pass = "Namvu123@";
//        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Log.d("Main", "tạo email thành công");
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.w("Main","thất bại",task.getException());
//                    Toast.makeText(MainActivity.this, "Authentic failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}