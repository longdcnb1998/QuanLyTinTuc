package com.example.quanlytintuc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytintuc.databinding.ItemTheLoaiBinding;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<TheLoai> theLoais;
    private Callback callback;

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
    }

    @Override
    public int getItemCount() {
        return theLoais.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemTheLoaiBinding binding;

        public ViewHolder(@NonNull ItemTheLoaiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (callback != null && position != RecyclerView.NO_POSITION) {
                        callback.onItemClick(theLoais.get(position));
                    }
                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    if (callback != null && position != RecyclerView.NO_POSITION) {
                        callback.onItemLongClick(theLoais.get(position));
                    }
                    for (int i = 0; i < theLoais.size(); i++) {
                        theLoais.get(i).setDuocchon(false);
                    }
                    theLoais.get(position).setDuocchon(true);
                    notifyDataSetChanged();
                    return true;
                }
            });
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bindData(int position) {
            TheLoai theLoai = theLoais.get(position);
            if (theLoai != null) {
                binding.name.setText("Tiêu đề :"+theLoai.getTen());
                binding.des.setText("Mô tả : "+theLoai.getMota());
                if (theLoai.isDuocchon()) {
                    binding.layoutItem.setBackground(mContext.getResources().getDrawable(R.drawable.bg_item_selected));
                } else {
                    binding.name.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                    binding.layoutItem.setBackground(mContext.getResources().getDrawable(R.drawable.bg_item_the_loai));
                }
            }
        }
    }

    public interface Callback {
        void onItemClick(TheLoai theLoai);

        void onItemLongClick(TheLoai theLoai);
    }

}
