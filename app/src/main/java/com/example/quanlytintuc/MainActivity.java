package com.example.quanlytintuc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetSequenceAction();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            verifyStoragePermissions(this);
        }

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
                    Intent intent = new Intent(MainActivity.this, SuaTheLoaiActivty.class);
                    intent.putExtra("Id", theLoai.getMa());
                    intent.putExtra("Name", theLoai.getTen());
                    intent.putExtra("Description", theLoai.getMota());
                    startActivityForResult(intent, EDIT_REQUEST_CODE);
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void verifyStoragePermissions(MainActivity activity) {
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permission = ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void resetSequenceAction() {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll_menu:
                viewModel.deleteAll();
                Toast.makeText(this, "Đã Xóa Toàn Bộ Dũ Liệu", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
