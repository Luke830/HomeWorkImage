package project.sample.com.luke.homeworkimage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private View.OnClickListener onClickListener;

    public MyRecyclerViewAdapter(ArrayList<T> arrayList, ImageFetcher imageFetcher, View.OnClickListener onClickListener) {
        super();
        this.arrayList = arrayList;
        this.imageFetcher = imageFetcher;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.row_mage_item;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_mage_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, onClickListener);


        MyLog.d("myViewHolder =  " + myViewHolder + "pos " + myViewHolder.getAdapterPosition());

        return myViewHolder;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        final int pos = holder.getAdapterPosition();
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        ImgItem imgItem = (ImgItem) arrayList.get(position);

        MyLog.d("position =  " + pos + " position = " + position);
//        MyLog.d("imgItem =  " + imgItem.toString());
        MyLog.d("imageView =  " + myViewHolder.imageView);

        myViewHolder.textView.setText("" + pos);

        imageFetcher.loadImage(Define.DOMAIN + imgItem.getImgPath(), myViewHolder.imageView, position);


    }


}
