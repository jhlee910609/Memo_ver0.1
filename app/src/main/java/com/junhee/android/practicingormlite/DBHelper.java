package com.junhee.android.practicingormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunHee on 2017. 6. 10..
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_NAME = "database.db";
    public static final int DB_VER = 1;
    private static DBHelper instance = null;

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }


    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Memo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // 데이터베이스 업그레이드 내용 없음

    }

    // 메모를 테이블에 만들어주는 메소드
    public void create(Memo memo) {

        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            dao.create(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 테이블 항목의 전부를 읽어낸다.
    public List<Memo> readAll() {

        List<Memo> memos = null;

        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            memos = dao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memos;
    }

    // 테이블 항목 중, memoid를 활용하여 하나의 데이터만 찾아낸다.
    public Memo readOne(int id) {
        Memo memo = null;

        try {

            Dao<Memo, Integer> dao = getDao(Memo.class);
            memo = dao.queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;
    }

    // 해당 컬럼에 키워드 검색을 통해 관련 검색어가 들어 있는 데이터 레코드를 추출한다
    public List<Memo> search(String keyword) {
        List<Memo> memos = null;
        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            String searchQuery = "select * from memo where edit_content like '%" + keyword + "%'";
            GenericRawResults<Memo> temps = dao.queryRaw(searchQuery, dao.getRawRowMapper());
            memos = temps.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memos;
    }

    public void update(Memo memo) {

        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            dao.update(memo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 해당 항목을 삭제함
    public void delete(Memo memo) {

        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            dao.delete(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        try {
            Dao<Memo, Integer> dao = getDao(Memo.class);
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
