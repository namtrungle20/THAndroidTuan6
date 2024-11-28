package com.example.myapplication.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.widget.TableLayout;

import androidx.core.database.sqlite.SQLiteDatabaseKt;

import com.example.myapplication.model.Nhanvien;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "nhanvien.sqlite";
    private static final int DB_VERSION =1;

    public DBHelper(Activity context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    public class TABLE implements BaseColumns{
        private static final String TABLE_NAME="nhanvien";
        private static final String COL_MA="manv";
        private static final String COL_TEN="tennv";
        private static final String COL_LUONGCB="luongcb";
        private static final String COL_SONGAYCONG="songaycong";
    }

    public List<Nhanvien> getAll()
    {
        List<Nhanvien> list = new ArrayList<>();
        String[] projection = {
                TABLE.COL_MA,
                TABLE.COL_TEN,
                TABLE.COL_LUONGCB,
                TABLE.COL_SONGAYCONG
        };

        Cursor cursor = getReadableDatabase().query(
                TABLE.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext())
        {
            Nhanvien nv = new Nhanvien();
            nv.setManv(cursor.getInt(cursor.getColumnIndexOrThrow(TABLE.COL_MA)));
            nv.setTen(cursor.getString(cursor.getColumnIndexOrThrow(TABLE.COL_TEN)));
            nv.setLuongcb(cursor.getInt(cursor.getColumnIndexOrThrow(TABLE.COL_LUONGCB)));
            nv.setSongaycong(cursor.getFloat(cursor.getColumnIndexOrThrow(TABLE.COL_SONGAYCONG)));

            list.add(nv);
        }
        cursor.close();
        return list;
    }
    public long XoaNhanVien(Nhanvien nv){
        SQLiteDatabase database = getWritableDatabase();
        String selection = TABLE.COL_MA + " = ?";
        String[] selectionArgs = {nv.getManv()+""};

        long XoaRow = database.delete(TABLE.TABLE_NAME, selection, selectionArgs);
        return XoaRow;
    }

    public long CapNhapNhanVien(Nhanvien nv){
        SQLiteDatabase database = getWritableDatabase();
        String selection = TABLE.COL_MA + " = ?";
        String[] selectionArgs = {nv.getManv()+""};

        ContentValues values = new ContentValues();
        values.put(TABLE.COL_TEN, nv.getTen());
        values.put(TABLE.COL_LUONGCB, nv.getLuongcb());
        values.put(TABLE.COL_SONGAYCONG, nv.getSongaycong());

        long capnhap = database.update(TABLE.TABLE_NAME, values, selection, selectionArgs);
        return capnhap;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
