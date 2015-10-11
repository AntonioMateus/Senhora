package br.com.kronos.senhora;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antonio on 07/10/15.
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "Senhora";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedReaderContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FeedReaderContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void Downgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
