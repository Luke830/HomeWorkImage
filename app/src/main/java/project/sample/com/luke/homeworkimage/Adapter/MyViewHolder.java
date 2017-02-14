package project.sample.com.luke.homeworkimage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.util.MyLog;
import project.sample.com.luke.homeworkimage.util.bitmap.RecyclingImageView;

/**
 * Created by itsm02 on 2017. 2. 10..
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public RecyclingImageView imageView;
    public TextView textView;

    public MyViewHolder(View itemView) {

        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.row_text_1);
        imageView = (RecyclingImageView) itemView.findViewById(R.id.image_view);

        MyLog.e("imageView =  " + imageView);

    }
}