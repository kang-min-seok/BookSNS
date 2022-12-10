package org.techtown.booksns;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity
{
    CustomAdapter adapter;
    RecyclerView recyclerView;

    private String client_id = "WHaHKJJnzkblsz7QIaXp";
    private String client_pw = "bgCftSj9Jp";

    Books dataList;
    List<Items> dataInfo;

    EditText edt1;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_search);
        edt1=(EditText) findViewById(R.id.edt1);
        btn1=(Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e1=edt1.getText().toString();
                Search(e1);
    }

    void Search(String msg){
        dataInfo = new ArrayList<>();
        recyclerView = findViewById(R.id.bookRecyclerView);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Books> call = apiInterface.getSearchResult(client_id, client_pw, "book.json", msg);

        call.enqueue(new Callback<Books>() {
            // 정상으로 통신 성공시
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                dataList=response.body();
                dataInfo = dataList.items;
                adapter = new CustomAdapter(BookActivity.this, dataInfo);
                recyclerView.setAdapter(adapter);
            }
            // 통신 실패시(예외발생, 인터넷끊김 등의 이유)
            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                Toast.makeText(BookActivity.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
        });

    }

    private  void startToast(String msg){
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}