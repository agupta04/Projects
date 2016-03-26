package com.codingblocks.filemanager;


        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.ListView;
        import java.io.File;
        import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    FileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ListView lv = (ListView) findViewById(R.id.favourites_listView);
        // currentFolder = new File(filePath);
        ArrayList<String> filePaths = getFilePaths();
        File[] favouriteFiles = new File[filePaths.size()];
        //   int i = 0;
        for (int i = 0; i < filePaths.size(); i++) {
            File f = new File(filePaths.get(i));
            favouriteFiles[i] = f;
        }
        adapter = new FileListAdapter(this, favouriteFiles);
        lv.setAdapter(adapter);
    }

    public ArrayList<String> getFilePaths() {

        //  int[] a = {1,2,3,4};

        FileManagerSQLHelper sqlHelper = new FileManagerSQLHelper(this, 1);
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        String[] columns = {FileManagerSQLHelper.FAV_TABLE_FILE_PATH};
        Cursor c = db.query(true, FileManagerSQLHelper.FAV_TABLE_NAME, columns, null, null,
                null, null, FileManagerSQLHelper.FAV_TABLE_DATE_ADDED + " DESC", null);

        ArrayList<String> FilePaths = new ArrayList<>();

        while (c.moveToNext()) {
            String path = c.getString(c.getColumnIndexOrThrow
                    (FileManagerSQLHelper.FAV_TABLE_FILE_PATH));
            FilePaths.add(path);
        }
        return FilePaths;

    }
}


/*import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    FileListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ListView lv=(ListView)findViewById(R.id.favourites_listView);
        //
        ArrayList<String> filePaths=getFilePaths();
        File[] favouriteFiles=new File[filePaths.size()];
        for(int i=0;i<filePaths.size();i++)
        {
            File f=new File(filePaths.get(i));
            favouriteFiles[i]=f;
        }
        adapter=new FileListAdapter(this,favouriteFiles);
        lv.setAdapter(adapter);
    }
    public ArrayList<String> getFilePaths()
    {
        FileManagerSQLHelper sqlHelper=new FileManagerSQLHelper(this,1);
        SQLiteDatabase db=sqlHelper.getReadableDatabase();
        String[] columns={FileManagerSQLHelper.FAV_TABLE_FILE_PATH};
        Cursor c=db.query(true,FileManagerSQLHelper.FAV_TABLE_NAME,columns,null,null,null,null,
                FileManagerSQLHelper.FAV_TABLE_DATE_ADDED+ " DESC",null);
        ArrayList<String> FilePaths=new ArrayList<>();
        while(c.moveToNext())
        {
            String path=c.getString(c.getColumnIndexOrThrow(FileManagerSQLHelper.FAV_TABLE_FILE_PATH));
            FilePaths.add(path);
        }
        return FilePaths;
    }
}
*/