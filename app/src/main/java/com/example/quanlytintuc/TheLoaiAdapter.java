package com.example.quanlytintuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytintuc.databinding.ItemTheLoaiBinding;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<TheLoai> theLoais;
    private Callback callback;
    private int pos = -1;

    public TheLoaiAdapter(Context mContext, ArrayList<TheLoai> theLoais, Callback callback) {
        this.mContext = mContext;
        this.theLoais = theLoais;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTheLoaiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_the_loai, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(position);
        pos = position;
    }

    @Override
    public int getItemCount() {
        return theLoais.size();
    }

    public void setStudents(List<TheLoai> theLoais) {
        this.theLoais = (ArrayList<TheLoai>) theLoais;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemTheLoaiBinding binding;

        public ViewHolder(@NonNull ItemTheLoaiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null && pos != RecyclerView.NO_POSITION) {
                        callback.onItemClick(theLoais.get(pos));
                    }
                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (callback != null && pos != RecyclerView.NO_POSITION) {
                        callback.onItemLongClick(theLoais.get(pos));
                    }
                    return true;
                }
            });
        }

        public void bindData(int position) {
            TheLoai theLoai = theLoais.get(position);
            if (theLoai != null) {
                binding.name.setText(theLoai.getTen());
                binding.des.setText(theLoai.getMota());
            }
        }
    }

    public interface Callback {
        void onItemClick(TheLoai theLoai);

        void onItemLongClick(TheLoai theLoai);
    }

    public void setOnClickListener(Callback callback) {
        this.callback = callback;
    }
}
