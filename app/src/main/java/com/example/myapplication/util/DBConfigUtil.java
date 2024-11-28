package com.example.myapplication.util;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBConfigUtil {

    final static String DB_Name="nhanvien.sqlite";
    final static String DB_Path_Suffix = "/databases/";

    public static void coppyDBfromAsset(Context context)
    {
        File dbfile = context.getDatabasePath(context.getApplicationInfo().dataDir + DB_Path_Suffix);
        if(!dbfile.exists())
            dbfile.mkdir();
        InputStream is = null;
        OutputStream os=null;
        try {
            is = context.getAssets().open(DB_Name);
            String outputPath = context.getApplicationInfo().dataDir + DB_Path_Suffix +DB_Name;
            os = new FileOutputStream(outputPath);

            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght=is.read(buffer)) >0)
            {
                os.write(buffer,0,lenght);
            }
            os.flush();
            Toast.makeText(context, "Đã chép xong CSDL", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {

            }
        }
    }
}
