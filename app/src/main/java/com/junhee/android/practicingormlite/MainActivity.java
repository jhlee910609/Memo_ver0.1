package com.junhee.android.practicingormlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<Memo> memos = null;
    Button btnAdd, btnDel;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    MemoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        btnDel = (Button) findViewById(R.id.btnDel);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        clickListener();

        dao = new MemoDao(this);
        Log.i("READ ALL", "================= [ Main : readall ] ");
        // TODO 다시 생각하기
        memos = dao.readAll();

        // 리사이클러 뷰와 어댑터 생성
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter(memos, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void clickListener() {

        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                startActivity(intent);
                break;

            case R.id.btnDel:

                // TODO 체크박스 삽입 혹은 다중 선택 삭제 로직 처리
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("메모 다시 무르기", "============ [ dao.readall] ");
        memos = dao.readAll();
        adapter.setMemoList(memos);
        Log.i("Notify", "================ [ 데이터 갱신 ]");
        adapter.notifyDataSetChanged();
    }
}
