package com.junhee.android.practicingormlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.junhee.android.practicingormlite.MainActivity.memos;

public class DetailActivity extends AppCompatActivity {

    EditText edit_title, edit_content;
    Button btnSave, btnDel;
    Memo memo;
    MemoDao dao;
    public static final String DOC_KEY = "memo_id";
    int memo_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("메모쓰기");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            memo_id = bundle.getInt(DOC_KEY);
        }

        init();
        clickListener();
        loadMemo();
    }

    private void init() {

        dao = new MemoDao(this);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_content = (EditText) findViewById(R.id.edit_content);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
    }

    private void clickListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeMemo();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMemo();
            }
        });
    }

    private void writeMemo() {
        if (memo_id > 0) {
            memo = dao.readOne(memo_id);
            memo.setTitle(edit_title.getText().toString());
            memo.setContent(edit_content.getText().toString());
            Log.i("메모 업데이트", "===================[ 메모 업데이트 ]");
            dao.update(memo);

        } else {
            memo = new Memo();
            memo.setTitle(edit_title.getText().toString());
            memo.setContent(edit_content.getText().toString());
            Log.i("메모 생성", "===================[ 새메모 생성 ]");
            dao.create(memo);
        }
        // TODO === [ .notifiyDateChanged ] ===
        memos.clear();
        // 새로 추가한 로직
        memos.add(memo);
        finish();
    }

    private void loadMemo() {
        if (memo_id > 0) {
            memo = dao.readOne(memo_id);
            edit_title.setText(memo.getTitle());
            edit_content.setText(memo.getContent());
        }
    }
    // TODO === [ .notifiyDateChanged ] ===
    private void deleteMemo(){
        dao.delete(memo_id);
        memos.remove(memo);
        memos.clear();
        finish();
    }


}
