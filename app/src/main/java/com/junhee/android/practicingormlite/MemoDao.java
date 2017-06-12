package com.junhee.android.practicingormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunHee on 2017. 6. 10..
 */

public class MemoDao {

    DBHelper dbHelper = null;
    Dao<Memo, Integer> dao = null;
    private static MemoDao memoDao = null;

    public static MemoDao getInstance(Context context){
        if(memoDao == null){
            memoDao = new MemoDao(context);
        }
        return memoDao;
    }

    public MemoDao(Context context) {
        dbHelper = DBHelper.getInstance(context);
        try {
            dao = dbHelper.getDao(Memo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Memo memo) {
        try {
            dao.create(memo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Memo> readAll() {
        List<Memo> memos = null;
        try {
            memos = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memos;
    }

    public Memo readOne(int memoid) {

        Memo memo = null;
        try {
            memo = dao.queryForId(memoid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;
    }

    public void update(Memo memo) {
        try {
            dao.update(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
