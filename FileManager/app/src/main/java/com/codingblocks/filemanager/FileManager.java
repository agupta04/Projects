package com.codingblocks.filemanager;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.Scanner;


public class FileManager extends AppCompatActivity {

    File currentFolder;
    FileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        ListView lv = (ListView) findViewById(R.id.fileListView);
        // = new File("/");
        // currentFolder.

        try {
            FileOutputStream fos = openFileOutput("textFile", Context.MODE_PRIVATE);
            fos.write("abcdefghhhhh".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fin = null;
        try {
            fin = openFileInput("textFile");
            Scanner s = new Scanner(fin);
            Log.i("textFile", s.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Intent i = getIntent();
        String filePath = i.getStringExtra("folder");
        if (filePath == null) {
            filePath = "/";
        }
        currentFolder = new File(filePath);
        File[] subFiles = currentFolder.listFiles();
        if (subFiles == null) {
            subFiles = new File[0];
        }
        adapter = new FileListAdapter(this, subFiles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File fileClicked = adapter.getItem(position);
                if (fileClicked.isDirectory()) {
                    Intent i = new Intent();
                    i.setClass(FileManager.this, FileManager.class);
                    i.putExtra("folder", fileClicked.getAbsolutePath());
                    startActivity(i);
                } else {
                    try {
                        Scanner s = new Scanner(fileClicked);
                        Log.i("File Content", s.nextLine());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
//        public void addToFav(View v){
//            Button fav = (Button) v.findViewById(R.id.favButton);
//            File file = (File)fav.getTag();
//
//
//        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_favourites){
            Intent i = new Intent();
            i.setClass(this,FavouritesActivity.class);
            startActivity(i);
        }
        return true;
    }
}



/*import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileManager extends AppCompatActivity {
    File currentFolder;
    FileListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        ListView lv=(ListView) findViewById(R.id.fileListView);

        //currentFolder=new File("/"); //means path is root of file ie start

        Intent i=getIntent();
        String filePath=i.getStringExtra("folder");
        if(filePath==null)
        {
            filePath="/";
        }
        currentFolder=new File(filePath);

        //to create file
       try{
           FileOutputStream fos=openFileOutput("textFile", Context.MODE_PRIVATE);
           fos.write("Abcdefghhhhhh".getBytes());
           fos.close();
       }
       catch(FileNotFoundException e)
       {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        //to read it
        FileInputStream fin;
        try{
            fin=openFileInput("textFile");
            Scanner s=new Scanner(fin);//reading with help of scanner
            Log.i("textFile",s.nextLine());

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //file creation ends

       //to deal with null pointer exception
        //which occurs when there are no subfiles
        File[] subFiles=currentFolder.listFiles();
        if(subFiles==null)
        {
            subFiles=new File[0];
        }

        adapter=new FileListAdapter(this,subFiles);
        //returns array of files to adapter
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File fileClicked=adapter.getItem(position);
                if(fileClicked.isDirectory())
                {
                   Intent i=new Intent();
                    i.setClass(FileManager.this,FileManager.class);
                    i.putExtra("folder",fileClicked.getAbsolutePath());
                    startActivity(i);
                }
                else
                {
                    try{
                        Scanner s=new Scanner(fileClicked);
                        Log.i("File Content", s.nextLine());

                    }
                    catch(FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    //public void addToFav(View v)
    //{
    //Button fav=v.findViewById(R.id.favButton);
      //  File file=(File)fav.getTag();
    //}
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent();
        i.setClass()

        startActivity(i);

    }

}


*/