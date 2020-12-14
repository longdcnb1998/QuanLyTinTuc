package com.example.quanlytintuc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quanlytintuc.databinding.ActivityThemTinTucBinding;

public class ThemTinTucActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityThemTinTucBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tin_tuc);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_them_tin_tuc);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        setTitle("Thêm tin tức");
        String theloai = intent.getStringExtra("Name");
        binding.tvTheLoai.setText("The Loai : " + theloai);
        binding.btnThem.setOnClickListener(this);
    }

    private void LuuTinTuc() {
        String name = binding.edtTieuDe.getText().toString().trim();
        String des = binding.edtChiTiet.getText().toString().trim();
        String linkAnh = binding.edtLinkAnh.getText().toString().trim();


        if (name.isEmpty() || des.isEmpty()) {
            Toast.makeText(this, "Làm ơn nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra("Name", name);
        data.putExtra("Description", des);
        data.putExtra("LinkAnh", linkAnh);
        data.putExtra("ThoiGian", System.currentTimeMillis());


        int id = getIntent().getIntExtra("Id", -1);
        if (id != -1) {
            data.putExtra("Id", id);

        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClick(View view) {
        LuuTinTuc();
    }
}
