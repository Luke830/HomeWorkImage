package project.sample.com.luke.homeworkimage.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.data.ImgItem;
import project.sample.com.luke.homeworkimage.define.Define;
import project.sample.com.luke.homeworkimage.util.MyLog;

public class MyFragment2 extends BaseFragment {

    private ImageView imageView;
    private TextView textView;

//    private int mImageThumbSize;
//    private ImageFetcher mImageFetcher;

    public MyFragment2() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.myfragment2, container, false);

        textView = (TextView) view.findViewById(R.id.title_text_view);
        imageView = (ImageView) view.findViewById(R.id.big_image_view);

        ImgItem imgItem = getArguments().getParcelable("item");

        textView.setText(imgItem.getImgTitleText());

//        initImageCache();

        HtmlParsingAsyncTask htmlParsingAsyncTask = new HtmlParsingAsyncTask();
        htmlParsingAsyncTask.execute(imgItem.getImgWebId(), null, null);

        return view;
    }

//    public void initImageCache() {
//        if (Build.VERSION.SDK_INT >= 13) {
//            Point point = new Point();
//            fragmentActivity.getWindowManager().getDefaultDisplay().getSize(point);
//            mImageThumbSize = point.x;
//        } else {
//            mImageThumbSize = fragmentActivity.getWindowManager().getDefaultDisplay().getWidth();
//        }
//
//        ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(fragmentActivity, DataUtil.IMAGE_CACHE_DIR);
//        cacheParams.setMemCacheSizePercent(0.5f); // Set memory cache to 25% of app memory
//
//        mImageFetcher = new ImageFetcher(fragmentActivity, mImageThumbSize);
////        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
//        mImageFetcher.addImageCache(fragmentActivity.getSupportFragmentManager(), cacheParams);
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        mImageFetcher.setExitTasksEarly(true);
//        mImageFetcher.flushCache();
////        mImageFetcher.clearCache();
//        mImageFetcher.closeCache();
//        mImageFetcher = null;
//    }

    public class HtmlParsingAsyncTask extends AsyncTask<String, Void, String> {

        private final String getClass1 = "innerImageArea";
        private final String getClass2 = "innerImage";
        private final String getAttr1 = "src";

        private ProgressDialog progressDialog;

        public HtmlParsingAsyncTask() {

            progressDialog = new ProgressDialog(fragmentActivity);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage(getString(R.string.parsing_msg_2));
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                /*

               <div class="innerImageArea">
               <img src="/Images/Thumbnails/1338/133859.jpg" id="ctl00_ContentPlaceHolder1_mainImage" class="innerImage">
               </div>

                */

                Document doc = Jsoup.connect(Define.DOMAIN + params[0]).get();
                Elements elements = doc.getElementsByClass(getClass1);
                String imgPath = elements.get(0).getElementsByClass(getClass2).attr(getAttr1);

                MyLog.e("imgPath = " + imgPath);
                return imgPath;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String imgPath) {
            super.onPostExecute(imgPath);

            progressDialog.dismiss();
            progressDialog = null;

            MyLog.e("imgPath = " + imgPath);

            if (!TextUtils.isEmpty(imgPath)) {

//                ((MainActivity) fragmentActivity).mImageFetcher.setExitTasksEarly(false);
//                ((MainActivity) fragmentActivity).mImageFetcher.loadImage(Define.DOMAIN + imgPath, imageView);

                Picasso.with(fragmentActivity)
                        .load(Define.DOMAIN + imgPath)
                        .priority(Picasso.Priority.HIGH)
                        .into(imageView);

//                Glide.with(fragmentActivity)
//                        .load(Define.DOMAIN + imgPath)
//                        .crossFade()
//                        .into(imageView);

            } else {
                Toast.makeText(fragmentActivity, R.string.parsing_error, Toast.LENGTH_SHORT).show();
            }

        }
    }

}
