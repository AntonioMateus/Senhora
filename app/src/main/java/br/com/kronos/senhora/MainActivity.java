package br.com.kronos.senhora;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends Activity {
    protected EditText mUsername;
    protected EditText mEmail;
    protected EditText mPassword;
    protected Button registerButton;
    protected Button botaoExibir;
    ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d
        setContentView(R.layout.activity_main);
        ArrayList<String> dadosListView = new ArrayList<String>();
        //inicializar
        mUsername = (EditText) findViewById(R.id.usernameRegisterEditText);
        mEmail = (EditText) findViewById(R.id.emailRegisterEditText);
        mPassword = (EditText) findViewById(R.id.passwordRegisterEditText);
        registerButton = (Button) findViewById(R.id.submit);
        botaoExibir = (Button) findViewById(R.id.exibir);
        final FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(MainActivity.this);
          //listen to register button click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NOME,mUsername.getText().toString().trim());
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL,mEmail.getText().toString().trim());
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SENHA,mPassword.getText().toString().trim());

                long id_nova_linha;
                id_nova_linha = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);
                Toast.makeText(MainActivity.this,"Inscrição concluida",Toast.LENGTH_LONG).show();
            }
        });
        botaoExibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                String[] projection = {FeedReaderContract.FeedEntry.COLUMN_NAME_NOME};
                Cursor c = db.query(FeedReaderContract.FeedEntry.TABLE_NAME,projection,null,null,null,null,null);
                ArrayAdapter<String> listAdapter;
                ArrayList<String> nomes = new ArrayList<>();
                if (c.getCount()>0) {
                    c.moveToFirst();
                    while (!c.isAfterLast()) {
                        nomes.add(c.getString(0));
                        c.moveToNext();
                    }
                }
                mainListView = (ListView) findViewById(R.id.listView);
                listAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, nomes);
                mainListView.setAdapter(listAdapter);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}