package project.sample.com.luke.homeworkimage.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.activity.MainActivity;
import project.sample.com.luke.homeworkimage.adapter.CustomDividerItemDecoration;
import project.sample.com.luke.homeworkimage.adapter.MyRecyclerViewAdapter;
import project.sample.com.luke.homeworkimage.data.ImgItem;
import project.sample.com.luke.homeworkimage.define.Define;
import project.sample.com.luke.homeworkimage.util.DataUtil;
import project.sample.com.luke.homeworkimage.util.MyLog;


public class MyFragment1 extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<ImgItem> arrayList;

//    private int mImageThumbSize;
//    private ImageFetcher mImageFetcher;

    public MyFragment1() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.myfragment1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

//        initImageCache();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        HtmlParsingAsyncTask htmlParsingAsyncTask = new HtmlParsingAsyncTask();
        htmlParsingAsyncTask.execute(null, null, null);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        destoryImageCache();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        MyLog.e("hidden = " + hidden);

        if (hidden) {
//            mImageFetcher.setExitTasksEarly(true);

            ((MainActivity) fragmentActivity).mImageFetcher.setPauseWork(false);
            ((MainActivity) fragmentActivity).mImageFetcher.setExitTasksEarly(true);
            ((MainActivity) fragmentActivity).mImageFetcher.flushCache();

        } else {
//            mImageFetcher.setExitTasksEarly(false);

            ((MainActivity) fragmentActivity).mImageFetcher.setExitTasksEarly(false);
            myRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {

        int position = recyclerView.getChildAdapterPosition((View) view.getTag(R.id.image_view));
        ImgItem imgItem = arrayList.get(position);

        Bundle bundle = new Bundle();
        bundle.putParcelable("item", imgItem);
//        DataUtil.replaceFragement(fragmentActivity, R.id.fragment_container, new MyFragment2(), bundle, this);
        // 이전 Fragement 다시 보여주는 방식으로 수정함.
        DataUtil.hideAddFragment(fragmentActivity, this, R.id.fragment_container, new MyFragment2(), bundle, this);

    }

//    public void initImageCache() {
//
//        if (mImageFetcher == null) {
//            if (Build.VERSION.SDK_INT >= 13) {
//                Point point = new Point();
//                fragmentActivity.getWindowManager().getDefaultDisplay().getSize(point);
//                mImageThumbSize = point.x;
//            } else {
//                mImageThumbSize = fragmentActivity.getWindowManager().getDefaultDisplay().getWidth();
//            }
//
//            ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(fragmentActivity, DataUtil.IMAGE_CACHE_DIR);
//            cacheParams.setMemCacheSizePercent(0.5f); // Set memory cache to 25% of app memory
//
//            mImageFetcher = new ImageFetcher(fragmentActivity, mImageThumbSize);
////        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
//            mImageFetcher.addImageCache(fragmentActivity.getSupportFragmentManager(), cacheParams);
//        }
//    }

//    public void destoryImageCache() {
//        if (mImageFetcher != null) {
//            mImageFetcher.setExitTasksEarly(true);
//            mImageFetcher.flushCache();
////        mImageFetcher.clearCache();
//            mImageFetcher.closeCache();
//            mImageFetcher = null;
//        }
//    }


    public class HtmlParsingAsyncTask extends AsyncTask<Void, Void, ArrayList> {

        private final String getClass1 = "gallery-wrap";
        private final String getClass2 = "gallery-item-group";
        private final String getClass3 = "picture";
        private final String getClass4 = "gallery-item-caption";
        private final String getAttr1 = "src";
        private final String getAttr2 = "href";
        private final String getTag1 = "a";

        private ProgressDialog progressDialog;

        public HtmlParsingAsyncTask() {

            progressDialog = new ProgressDialog(fragmentActivity);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage(getString(R.string.parsing_msg_1));
            progressDialog.show();

        }

        @Override
        protected ArrayList doInBackground(Void... params) {
            try {

                arrayList = new ArrayList<ImgItem>();
                Document doc = Jsoup.connect(Define.DOMAIN + "/collections/archive/slim-aarons.aspx").get();

                /*
                <div class="gallery-wrap exhibitionrepeater">

                  <!-- REPEATER -->
                        <div class="gallery-item-group exitemrepeater">
                            <a href="/Picture-Library/Image.aspx?id=7120"><img src="/Images/Thumbnails/1517/151776.jpg" class="picture"/></a>

                            <div class="gallery-item-caption">
                                <p><a href="/Picture-Library/Image.aspx?id=7120"></a></p>
                            </div>
                        </div>
                    <!-- REPEATER ENDS -->

                   <!-- REPEATER -->
                        <div class="gallery-item-group lastitemrepeater">
                            <a href="/Picture-Library/Image.aspx?id=2455"><img src="/Images/Thumbnails/1343/134346.jpg" class="picture"/></a>

                            <div class="gallery-item-caption">
                                <p><a href="/Picture-Library/Image.aspx?id=2455">Aerial Miami Beach</a></p>
                            </div>
                        </div>
                    <!-- REPEATER ENDS -->
                 */

                Elements elements1 = null;

                String imgPath = null;
                Elements elements2 = null;

                String imgTitleText = null;
                String imgWebId = null;

                Elements elements = doc.getElementsByClass(getClass1);
                for (int i = 0; i < elements.size(); i++) {

                    elements1 = elements.get(i).getElementsByClass(getClass2);

                    for (int j = 0; j < elements1.size(); j++) {

                        ImgItem imgItem = new ImgItem();

                        imgPath = elements1.get(j).getElementsByClass(getClass3).attr(getAttr1);
                        imgItem.setImgPath(imgPath);

                        elements2 = elements1.get(j).getElementsByClass(getClass4);
                        imgTitleText = elements2.get(0).text().toString();
                        imgWebId = elements2.get(0).getElementsByTag(getTag1).attr(getAttr2);

                        imgItem.setImgTitleText(imgTitleText);
                        imgItem.setImgWebId(imgWebId);

                        arrayList.add(imgItem);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }

            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);

            progressDialog.dismiss();
            progressDialog = null;

            if (arrayList != null) {
                recyclerView.setHasFixedSize(true);
                myRecyclerViewAdapter = new MyRecyclerViewAdapter(fragmentActivity, arrayList, ((MainActivity) fragmentActivity).mImageFetcher, MyFragment1.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(myRecyclerViewAdapter);
                recyclerView.addItemDecoration(new CustomDividerItemDecoration(fragmentActivity, CustomDividerItemDecoration.GRID));
            } else {
                Toast.makeText(fragmentActivity, R.string.parsing_error, Toast.LENGTH_SHORT).show();
            }

        }
    }

}
