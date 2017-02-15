package project.sample.com.luke.homeworkimage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.data.ImgItem;
import project.sample.com.luke.homeworkimage.define.Define;
import project.sample.com.luke.homeworkimage.util.bitmap.ImageFetcher;

/**
 * Created by itsm02 on 2017. 2. 6..
 */

public class MyRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<T> arrayList;
    private ImageFetcher imageFetcher;
    private View.OnClickListener onClickListener;

    public MyRecyclerViewAdapter(Context context, ArrayList<T> arrayList, ImageFetcher imageFetcher, View.OnClickListener onClickListener) {
        super();
        this.context = context;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_mage_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, onClickListener);

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

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        ImgItem imgItem = (ImgItem) arrayList.get(position);
//        imageFetcher.loadImage(Define.DOMAIN + imgItem.getImgPath(), myViewHolder.imageView);

        Picasso.with(context)
                .load(Define.DOMAIN + imgItem.getImgPath())
                .priority(Picasso.Priority.NORMAL)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(myViewHolder.imageView);

//        Glide.with(myViewHolder.imageView.getContext())
//                .load(Define.DOMAIN + imgItem.getImgPath())
//                .crossFade()
//                .into(myViewHolder.imageView);

    }

}
