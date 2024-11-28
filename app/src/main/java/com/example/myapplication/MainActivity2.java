package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.model.Nhanvien;

public class MainActivity2 extends AppCompatActivity {

    EditText ten,luong,songaycong;
    Button btnLuu, btnHuy;
    Nhanvien selectedNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        addEvents();
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if(intent.hasExtra("CHON"))
        {
            selectedNV = (Nhanvien) intent.getSerializableExtra("CHON");
            if(selectedNV!=null)
            {
                ten.setText(selectedNV.getTen());
                luong.setText(String.valueOf(selectedNV.getLuongcb()));
                songaycong.setText(String.valueOf(selectedNV.getSongaycong()));
            }
            else
            {
                resetView();
            }
        }
        else {
            resetView();
        }
    }

    private void resetView() {
        ten.setText("");
        luong.setText("");
        songaycong.setText("");
        selectedNV=null;
    }

    private void addControls() {
        ten = findViewById(R.id.txtten);
        luong= findViewById(R.id.txtluong);
        songaycong = findViewById(R.id.txtngaycong);

        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        selectedNV =null;
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedNV==null)
                {
                    selectedNV = new Nhanvien();
                }
                selectedNV.setTen(ten.getText().toString());
                selectedNV.setLuongcb(Integer.parseInt(luong.getText().toString()));
                selectedNV.setSongaycong(Float.parseFloat(songaycong.getText().toString()));

                Intent intent = getIntent();
                intent.putExtra("TRA", selectedNV);
                setResult(2, intent);
                finish();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}