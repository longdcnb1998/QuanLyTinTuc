package com.example.quanlytintuc;

import android.content.Intent;
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
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytintuc.databinding.ActivityDanhSachTinTucBinding;

import java.util.ArrayList;

public class DanhSachTinTucActivity extends AppCompatActivity {

    ActivityDanhSachTinTucBinding binding;
    private TinTucViewModel viewModel;
    private TinTucAdapter adapter;
    private int ADD_REQUEST_CODE = 1;
    private int EDIT_REQUEST_CODE = 2;
    private Tintuc tintucDuocChon;
    private int id;
    private String theloai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tin_tuc);

        Intent intent = getIntent();
        id = intent.getIntExtra("Id", -1);
        theloai = intent.getStringExtra("Name");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_danh_sach_tin_tuc);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        viewModel = ViewModelProviders.of(this).get(TinTucViewModel.class);
        viewModel.getAllTinTuc(id).observe(this, data -> {
            adapter = new TinTucAdapter(this, (ArrayList<Tintuc>) data, new TinTucAdapter.Callback() {
                @Override
                public void onItemClick(Tintuc tintuc) {
                    Intent intent = new Intent(DanhSachTinTucActivity.this, ChiTietTinTucActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(Tintuc tintuc) {
                    tintucDuocChon = tintuc;
                }
            });
            binding.recyclerView.setAdapter(adapter);
        });
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThemTinTucActivity.class);
                intent.putExtra("Id", id);
                intent.putExtra("Name", theloai);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });
        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachTinTucActivity.this, SuaTinTucActivity.class);
                intent.putExtra("Id", tintucDuocChon.getMa());
                intent.putExtra("Name", tintucDuocChon.getChitiet());
                intent.putExtra("LinkAnh",tintucDuocChon.getLinkanh());
                intent.putExtra("TheLoai",tintucDuocChon.getTheloai());
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.delete(tintucDuocChon);
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
            String linkanh = data.getStringExtra("LinkAnh");
            long time = data.getLongExtra("ThoiGian", System.currentTimeMillis());


            Tintuc tintuc = new Tintuc(id, name, des, linkanh, theloai, time);
            viewModel.insert(tintuc);
            Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra("Id", -1);
            if ((id == -1)) {
                Toast.makeText(this, "Không thể cập nhập", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra("Name");
            String des = data.getStringExtra("Description");

//            Tintuc tintuc = new Tintuc(name, des);
//            tintuc.setMa(id);
//            viewModel.update(tintuc);
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
                viewModel.deleteAll(id);
                Toast.makeText(this, "Đã Xóa Toàn Bộ Dũ Liệu", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
