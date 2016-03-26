
package com.codingblocks.filemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Date;

/**
 * Created by manishakhattar on 08/03/16.
 */
public class FileListAdapter extends ArrayAdapter<File> {

    Context context;
    File files[];

    public FileListAdapter(Context context, File[] objects) {
        super(context, 0, objects);
        this.context = context;
        this.files = objects;
    }

    static class ViewHolder{
        TextView fileName;
        ImageView fileIcon;
        Button favButton;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context,R.layout.file_list_item,null);
            ViewHolder vh = new ViewHolder();
            vh.fileName = (TextView) convertView.findViewById(R.id.fileName);
            vh.fileIcon = (ImageView) convertView.findViewById(R.id.fileIcon);
            vh.favButton = (Button) convertView.findViewById(R.id.favButton);
            convertView.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.fileName.setText(files[position].getName());

        //   vh.favButton.setTag(files[position]);

        if(files[position].isDirectory()){
            vh.fileIcon.setImageResource(android.R.drawable.ic_media_play);
        }
        else{
            vh.fileIcon.setImageResource(android.R.drawable.ic_media_pause);
        }
        vh.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("File clicked", files[position].getName());

                FileManagerSQLHelper sqlHelper = new FileManagerSQLHelper(context,1);
                SQLiteDatabase db = sqlHelper.getWritableDatabase();
                File fileClicked = files[position];
                ContentValues cv = new ContentValues();
                cv.put(FileManagerSQLHelper.FAV_TABLE_FILE_PATH,fileClicked.getAbsolutePath());
                Date date = new Date();
                cv.put(FileManagerSQLHelper.FAV_TABLE_DATE_ADDED,date.toString());

                db.insert(FileManagerSQLHelper.FAV_TABLE_NAME,null,cv);

            }
        });
        return convertView;
    }
}

/*package com.codingblocks.filemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Date;

public class FileListAdapter extends ArrayAdapter<File>
{

    Context context;
    File files[];
    public FileListAdapter(Context context,File[] objects)
    {
        super(context,0,objects);
        this.context=context;
        this.files=objects;
    }
    static class ViewHolder{
        TextView fileName;
        ImageView fileIcon;
        Button favButton;
    }

    public View getView(final int position,View convertView,ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=View.inflate(context,R.layout.file_list_item,null);
            ViewHolder vh=new ViewHolder();
            vh.fileName=(TextView) convertView.findViewById(R.id.fileName);
            vh.fileIcon=(ImageView)convertView.findViewById(R.id.fileIcon);
            vh.favButton=(Button)convertView.findViewById(R.id.favButton);
            convertView.setTag(vh);
        }
        ViewHolder vh=(ViewHolder) convertView.getTag();
        vh.fileName.setText(files[position].getName());
        if(files[position].isDirectory())
        {
            vh.fileIcon.setImageResource(android.R.drawable.ic_media_play);
        }
        else
        {
            vh.fileIcon.setImageResource(android.R.drawable.ic_media_pause);
        }
        vh.favButton.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v)
        {
            Log.i("File clicked", files[position].getName());
            FileManagerSQLHelper sqlHelper=new FileManagerSQLHelper(context,1);
            SQLiteDatabase db=sqlHelper.getWritableDatabase();//for changes in db else getReadable
            //if db is there,it returns else make a new db
            File fileClicked=files[position];
            //TO PREVENT SQL INJECTION, we dont use String query and db.exesql and use this
            ContentValues cv=new ContentValues();// in key-value pairs
            cv.put(FileManagerSQLHelper.FAV_TABLE_FILE_PATH,fileClicked.getAbsolutePath());
            Date date=new Date();
            cv.put(FileManagerSQLHelper.FAV_TABLE_DATE_ADDED,date.toString());
            db.insert(FileManagerSQLHelper.FAV_TABLE_NAME,null,cv);//nullColumnHack
            //our col num here is null, means no row shud be empty
        }
         });
        return convertView;
    }

}
*/