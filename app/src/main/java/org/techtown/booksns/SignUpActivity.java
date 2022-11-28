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

public class SignUpActivity extends AppCompatActivity {
    Button btnSign,btnLoginPage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        btnSign=(Button) findViewById(R.id.btnSignUp);
        btnLoginPage=(Button) findViewById(R.id.btnLoginPage);


        btnSign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signUp();
            }
        });

        btnLoginPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startLoginActivity();
            }
        });

    }


    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    private void signUp(){
        String Email=((EditText) findViewById(R.id.edtSignUpEmail)).getText().toString();
        String Pw=((EditText) findViewById(R.id.edtSignUpPw)).getText().toString();
        String PwChk=((EditText) findViewById(R.id.edtPwChk)).getText().toString();

        if(Email.length() > 0 && Pw.length() > 0 && PwChk.length() > 0){
            if(Pw.equals(PwChk)) {
                mAuth.createUserWithEmailAndPassword(Email, Pw)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입 성공");
                                    startMemberInitActivity();
                                } else {
                                    if(task.getException() != null){
                                        startToast(task.getException().toString());
                                    }
                                }
                            }
                        });
            }else{
                startToast("비밀번호가 일치하지 않습니다");
            }
        }else {
            startToast("이메일 또는 비밀번호를 입력해주세요");
        }

    }

    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    private void startMemberInitActivity(){
        Intent intent=new Intent(this,MemberInitActivity.class);
        startActivity(intent);
    }

}