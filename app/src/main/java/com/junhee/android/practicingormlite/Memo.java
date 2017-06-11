package com.junhee.android.practicingormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JunHee on 2017. 6. 10..
 */
@DatabaseTable(tableName = "memo")
public class Memo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String content;

    @DatabaseField
    private String date;

    private boolean isChecked;



    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Memo() {
        setDate();
    }

    public Memo(String title, String content){
        this.title = title;
        this.content = content;
        setDate();
    }


    public String getDate() {
        return date;
    }

    public void setDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
