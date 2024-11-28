package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.dao.DBHelper;
import com.example.myapplication.model.Nhanvien;
import com.example.myapplication.util.DBConfigUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnThem;
    ListView lvDSNV;
    List<Nhanvien> dsnv;
    ArrayAdapter<Nhanvien> adapter;
    DBHelper dbHelper;
    Nhanvien selectedNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DBConfigUtil.coppyDBfromAsset(MainActivity.this);
        addControls();
        display();
        addEvents();
    }



    private void addControls() {
        btnThem = findViewById(R.id.btnThem);
        lvDSNV = findViewById(R.id.lv);
        dbHelper = new DBHelper(MainActivity.this);
        selectedNV =null;
    }

    private void display() {
        dsnv = new ArrayList<>();
        dsnv =dbHelper.getAll();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, dsnv);
        lvDSNV.setAdapter(adapter);

    }
    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1);
            }
        });

        lvDSNV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i>=0 && i<adapter.getCount())
                {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    selectedNV =  adapter.getItem(i);
                    intent.putExtra("CHON", selectedNV);
                    startActivityForResult(intent, 1);

                }
            }
        });

        lvDSNV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nhanvien nv = adapter.getItem(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xoa?");
                builder.setMessage("Co chac muon xoa khong?");
                builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int result = dbHelper.XoaNhanVien(nv);
                        if(nv!=null)
                        {
                            adapter.remove(nv);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            if (data.hasExtra("TRA")) {
                Nhanvien nv = (Nhanvien) data.getSerializableExtra("TRA");
                if (selectedNV == null) {
                    adapter.add(nv);
                } else {
                    selectedNV.setTen(nv.getTen());
                    selectedNV.setLuongcb(nv.getLuongcb());
                    selectedNV.setSongaycong(nv.getSongaycong());
                }
                adapter.notifyDataSetChanged();
            }
        }
        selectedNV = null;
    }
}