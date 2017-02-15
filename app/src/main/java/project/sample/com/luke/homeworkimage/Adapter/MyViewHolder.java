package project.sample.com.luke.homeworkimage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import project.sample.com.luke.homeworkimage.R;

/**
 * Created by itsm02 on 2017. 2. 10..
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public MyViewHolder(View itemView, View.OnClickListener onClickListener) {

        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.image_view);
        imageView.setOnClickListener(onClickListener);
        imageView.setTag(R.id.image_view, itemView);

    }


}