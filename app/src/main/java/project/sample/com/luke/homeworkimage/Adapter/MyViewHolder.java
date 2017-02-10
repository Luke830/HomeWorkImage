package project.sample.com.luke.homeworkimage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.util.MyLog;

/**
 * Created by itsm02 on 2017. 2. 10..
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public MyViewHolder(View itemView) {

        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.row_text_1);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);

        MyLog.e("imageView =  " + imageView);

    }
}