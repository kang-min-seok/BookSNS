package org.techtown.booksns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnLogout,btnPlus;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null){ //로그인 안된 경우
            startLoginActivity();
        }else{ // 로그인 된 경우
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                            if(document.getData() == null){ // 회원정보 안들어가있으면
                                finish();
                                startMemberInitActivity(); // 메인 페이지말고 회원정보 입력란으로 가라
                            }
                            else { // 로그인시 회원정보 들어가있어서 메인페이지로 그대로 이동
                                Log.d(TAG, "document success");
                            }
                    } else {
                        Log.d(TAG, "document get failed with ", task.getException());
                    }
                }
            });

        }


        btnLogout =(Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startLoginActivity();
            }
        });

        btnPlus =(Button) findViewById(R.id.btnPlus);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWritePostActivity();

            }
        });
    }
    private void startCameraActivity(){
        Intent intent=new Intent(this,CameraActivity.class);
        startActivity(intent);
    }
    private void startLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    private void startMemberInitActivity(){
        Intent intent=new Intent(this,MemberInitActivity.class);
        startActivity(intent);

    }private void startWritePostActivity(){
        Intent intent=new Intent(this,WritePostActivity.class);
        startActivity(intent);
    }
}