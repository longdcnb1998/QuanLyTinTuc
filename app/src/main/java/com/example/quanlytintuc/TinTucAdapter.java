package com.example.quanlytintuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytintuc.databinding.ItemTinTucBinding;

import java.util.ArrayList;

public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Tintuc> tintucs;
    private Callback callback;

    public TinTucAdapter(Context mContext, ArrayList<Tintuc> tintucs, Callback callback) {
        this.mContext = mContext;
        this.tintucs = tintucs;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTinTucBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_tin_tuc, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return tintucs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemTinTucBinding binding;

        public ViewHolder(@NonNull ItemTinTucBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (callback != null && position != RecyclerView.NO_POSITION) {
                        callback.onItemClick(tintucs.get(position));
                    }
                }
            });

            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    if (callback != null && position != RecyclerView.NO_POSITION) {
                        callback.onItemLongClick(tintucs.get(position));
                    }
                    for (int i = 0; i < tintucs.size(); i++) {
                        tintucs.get(i).setDuocchon(false);
                    }
                    tintucs.get(position).setDuocchon(true);
                    notifyDataSetChanged();
                    return true;
                }
            });
        }

        public void bindData(int position) {
            Tintuc tintuc = tintucs.get(position);
            if (tintuc != null) {
                binding.name.setText("Tiêu đề : " + tintuc.getTieude());
                binding.des.setText("Chi tiết" + tintuc.getChitiet());
                if (tintuc.isDuocchon()) {
                    binding.layoutItem.setBackground(mContext.getResources().getDrawable(R.drawable.bg_item_selected));
                } else {
                    binding.layoutItem.setBackground(mContext.getResources().getDrawable(R.drawable.bg_item_the_loai));
                }
            }

        }
    }

    public interface Callback {
        void onItemClick(Tintuc tintuc);

        void onItemLongClick(Tintuc tintuc);
    }
}
