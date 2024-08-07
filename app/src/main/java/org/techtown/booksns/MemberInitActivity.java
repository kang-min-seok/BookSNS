package org.techtown.booksns;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class MemberInitActivity extends AppCompatActivity {
    private static final String TAG = "MemberInitActivity";
    private ImageView profileImageView;
    private String profilePath;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);

        profileImageView=findViewById(R.id.profileImageView);
        profileImageView.setOnClickListener(onClickListener);

        findViewById(R.id.btnMbInfo).setOnClickListener(onClickListener);
        findViewById(R.id.takePhoto).setOnClickListener(onClickListener);
        findViewById(R.id.getPhoto).setOnClickListener(onClickListener);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0 :{
                if (resultCode  == Activity.RESULT_OK) {
                    profilePath = data.getStringExtra("profilePath");
                    Glide.with(this).load(profilePath).centerCrop().override(500).into(profileImageView);
                }
                break;
            }

        }
    }
    View.OnClickListener onClickListener = v -> {
        switch (v.getId()) {
            case R.id.btnMbInfo:
                profileUpdate();
                break;
            case R.id.takePhoto:
                myStartActivity(CameraActivity.class);
                break;
            case R.id.getPhoto:
                if (ContextCompat.checkSelfPermission(
                        MemberInitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MemberInitActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MemberInitActivity.this
                            , Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    } else {
                        startToast("권한을 허용해주세요");
                    }
                }else {
                    myStartActivity(GalleryActivity.class);
                }
                break;
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myStartActivity(GalleryActivity.class);
                }  else {
                    startToast("권한을 허용해주세요");
                }
        }
    }


    private void profileUpdate(){
        final String name=((EditText) findViewById(R.id.edtName)).getText().toString();
        final String phoneNum=((EditText) findViewById(R.id.edtPhoneNum)).getText().toString();
        final String birth=((EditText) findViewById(R.id.edtBirth)).getText().toString();
        final String address=((EditText) findViewById(R.id.edtAddress)).getText().toString();


        if(name.length() > 0 && phoneNum.length() > 9 && birth.length() > 5 && address.length() > 0){

            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            final StorageReference mountainImagesRef = storageRef.child("users/"+user.getUid()+"profileImage.jpg");

            if(profilePath == null){
                MemberInfo memberInfo = new MemberInfo(name,phoneNum,birth,address);
                uploader(memberInfo);
            }else {
                try {
                    InputStream stream = new FileInputStream(new File(profilePath));
                    UploadTask uploadTask = mountainImagesRef.putStream(stream);

                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return mountainImagesRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                MemberInfo memberInfo = new MemberInfo(name,phoneNum,birth,address,downloadUri.toString());
                                uploader(memberInfo);
                            } else {
                                startToast("회원정보를 보내는데 실패했습니다");
                            }
                        }
                    });
                }catch (FileNotFoundException e){
                    startToast("오류가 났어요"+e.toString());
                }
            }
        }else{
            startToast("회원정보를 입력해주세요");
        }
    }
    private void uploader(MemberInfo memberInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getUid()).set(memberInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("회원정보 등록을 성공하였습니다.");
                        myStartActivity(LoginActivity.class);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("회원정보 등록을 실패하였습니다.");
                        Log.w(TAG,"Error",e);
                    }
                });
    }


    private void myStartActivity(Class c){
        Intent intent=new Intent(this,c);
        startActivityForResult(intent,0);
    }

    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}