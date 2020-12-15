package com.example.quanlytintuc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quanlytintuc.R;
import com.example.quanlytintuc.databinding.ActivitySuaTinTucBinding;

import java.util.Objects;

public class SuaTinTucActivity extends AppCompatActivity {


    ActivitySuaTinTucBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_tin_tuc);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sua_tin_tuc);


        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();


        int id = intent.getIntExtra("Id", -1);
        if (id != -1) {
            setTitle("Sua Tin Tuc");
            String name = intent.getStringExtra("Name");
            String des = intent.getStringExtra("Description");
            String linkAnh = intent.getStringExtra("LinkAnh");
            String TheLoai = intent.getStringExtra("TheLoai");
            binding.edtTieuDe.setText(name);
            binding.edtChiTiet.setText(des);
            binding.edtLinkAnh.setText(linkAnh);
            binding.tvTheLoai.setText(TheLoai);
        }

        binding.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LuuTheLoai();
            }
        });
    }


    private void LuuTheLoai() {
        String name = binding.edtTieuDe.getText().toString().trim();
        String des = binding.edtChiTiet.getText().toString().trim();
        String linkAnh = binding.edtLinkAnh.getText().toString().trim();

        if (name.isEmpty() || des.isEmpty()) {
            Toast.makeText(this, "Làm ơn nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();

        int id = getIntent().getIntExtra("Id", -1);
        data.putExtra("Id", id);
        data.putExtra("Name", name);
        data.putExtra("Description", des);
        data.putExtra("LinkAnh", linkAnh);
        setResult(RESULT_OK, data);
        finish();
    }
}
