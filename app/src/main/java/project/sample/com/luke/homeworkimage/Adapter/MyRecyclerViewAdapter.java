package project.sample.com.luke.homeworkimage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.data.ImgItem;
import project.sample.com.luke.homeworkimage.define.Define;
import project.sample.com.luke.homeworkimage.util.MyLog;
import project.sample.com.luke.homeworkimage.util.bitmap.ImageFetcher;

/**
 * Created by itsm02 on 2017. 2. 6..
 */

public class MyRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    private ArrayList<T> arrayList;
    private ImageFetcher imageFetcher;

    public MyRecyclerViewAdapter(ArrayList<T> arrayList, ImageFetcher imageFetcher) {
        this.arrayList = arrayList;
        this.imageFetcher = imageFetcher;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_mage_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MyViewHolder myViewHolder = (MyViewHolder) holder;
        ImgItem imgItem = (ImgItem) arrayList.get(position);

        MyLog.d("position =  " + position);
        MyLog.d("imgItem =  " + imgItem.toString());

        imageFetcher.loadImage(Define.DOMAIN + imgItem.getImgPath(), myViewHolder.imageView);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public MyViewHolder(View itemView) {

            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);

        }
    }

}
