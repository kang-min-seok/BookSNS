package org.techtown.booksns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin,btnPwResetPage,btnSignUpPage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnPwResetPage=(Button) findViewById(R.id.btnPwResetPage);
        btnSignUpPage=(Button) findViewById(R.id.btnSignUpPage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnPwResetPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startPasswordResetActivity();
            }
        });

        btnSignUpPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startSignUpActivity();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void login(){
        String LoginEmail=((EditText) findViewById(R.id.edtLoginEmail)).getText().toString();
        String LoginPw=((EditText) findViewById(R.id.edtLoginPw)).getText().toString();
        if(LoginEmail.length() > 0 && LoginPw.length() > 0){
            mAuth.signInWithEmailAndPassword(LoginEmail, LoginPw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인 성공");
                                startMainActivity();
                            } else {
                                if (task.getException() != null) {
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        }else{
            startToast("아이디 또는 비밀번호를 입력해주세요");
        }
    }



    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void startPasswordResetActivity(){
        Intent intent=new Intent(this,PasswordResetActivity.class);
        startActivity(intent);
    }
    private void startSignUpActivity(){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}