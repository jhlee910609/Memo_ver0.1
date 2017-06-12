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

    // DetailActivity.class에서 가져다 쓰려고 스태틱변수 만듬
    // TODO 좋은 설계란 느낌은 안들지만 대안이 있다면 물어보자
    public static List<Memo> memos = null;
    List<Memo> delMemos = null;
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
        // TODO 싱클턴 로직 추가
        dao = MemoDao.getInstance(this);

        //dao = new MemoDao(this);
        Log.i("READ ALL", "================= [ Main : readall ] ");
        // TODO onResume(); 시, 두 번 호출되기 때문에 다시 생각하기
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
            // TODO 코드 수정해야함
            case R.id.btnDel:
/*
                delMemos = RecyclerAdapter.checkedList;
                for(Memo memo : delMemos){
                    memos.remove(memo.getId());
                    dao.delete(memo.getId());
                }
                memos.clear();
                memos = dao.readAll();
                adapter.setMemoList(memos);
                adapter.notifyDataSetChanged();
*/




                // TODO 체크박스 삽입 혹은 다중 선택 삭제 로직 처리
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("데이터 최신화 ", "============ [ dao.readAll(); ]");
        memos = dao.readAll();
        adapter.setMemoList(memos);
        Log.i("Notify", "================ [ .notifyDataChanged(); ]");
        adapter.notifyDataSetChanged();
    }
}
