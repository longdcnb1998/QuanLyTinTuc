package com.example.quanlytintuc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytintuc.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private TheLoaiViewModel viewModel;
    private ArrayList<TheLoai> listTheLoai;
    private TheLoaiAdapter adapter;
    private int ADD_REQUEST_CODE = 1;
    private int EDIT_REQUEST_CODE = 2;
    private TheLoai theLoaiDuocChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        listTheLoai = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        viewModel = ViewModelProviders.of(this).get(TheLoaiViewModel.class);
        viewModel.getTheLoais().observe(this, data -> {
            adapter = new TheLoaiAdapter(this, (ArrayList<TheLoai>) data, new TheLoaiAdapter.Callback() {
                @Override
                public void onItemClick(TheLoai theLoai) {
                    Intent intent = new Intent(MainActivity.this, DanhSachTinTucActivity.class);
                    intent.putExtra("Id", theLoai.getMa());
                    intent.putExtra("Name", theLoai.getTen());
                    intent.putExtra("Description", theLoai.getMota());
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(TheLoai theLoai) {
                    theLoaiDuocChon = theLoai;
                }
            });
            binding.recyclerView.setAdapter(adapter);
            try {
                for (int i = 0; i < data.size(); i++) {
                    TheLoai category = data.get(i);
                    Log.d("Cat_AAA " + i, category.getTen());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThemTheLoaiActivity.class);

                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });
    binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SuaTheLoaiActivty.class);
            intent.putExtra("Id", theLoaiDuocChon.getMa());
            intent.putExtra("Name", theLoaiDuocChon.getTen());
            intent.putExtra("Description", theLoaiDuocChon.getMota());
            startActivityForResult(intent,EDIT_REQUEST_CODE);
        }
    });

    binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            viewModel.delete(theLoaiDuocChon);
            adapter.notifyDataSetChanged();
        }
    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("Code_R", String.valueOf(requestCode));

        if (requestCode == ADD_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra("Name");
            String des = data.getStringExtra("Description");


            TheLoai student = new TheLoai(name, des);

            viewModel.insert(student);
            Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra("Id", -1);
            if ((id == -1)) {
                Toast.makeText(this, "Không thể cập nhập", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra("Name");
            String des = data.getStringExtra("Description");

            TheLoai student = new TheLoai(name, des);
            student.setMa(id);
            viewModel.update(student);
            Toast.makeText(this, "Đã cập nhập", Toast.LENGTH_SHORT).show();
        }

    }
}
