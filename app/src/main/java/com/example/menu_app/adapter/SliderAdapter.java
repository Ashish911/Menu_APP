package com.example.menu_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.menu_app.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH>{

    private Context mcontext;
    private int count;

    public SliderAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "Item clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });

        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView).load(R.drawable.pizza).into(viewHolder.imageSlideBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView).load(R.drawable.food).into(viewHolder.imageSlideBackground);
                break;
            default:
                Glide.with(viewHolder.itemView).load(R.drawable.kebab).into(viewHolder.imageSlideBackground);
                break;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageSlideBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageSlideBackground = itemView.findViewById(R.id.img_slider);
            this.itemView = itemView;
        }
    }
}
