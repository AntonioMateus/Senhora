package br.com.kronos.senhora;

import android.provider.BaseColumns;

/**
 * Created by antonio on 07/10/15.
 */
public final class FeedReaderContract {
    public FeedReaderContract() {}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_SENHA = "senha";
    }

    public static final String TEXT_TYPE = " TEXT";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry.COLUMN_NAME_NOME + TEXT_TYPE + ", " +
                    FeedEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + ", " +
                    FeedEntry.COLUMN_NAME_SENHA + TEXT_TYPE + " )";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +FeedEntry.TABLE_NAME;
}