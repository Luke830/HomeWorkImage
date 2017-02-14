package project.sample.com.luke.homeworkimage.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.adapter.MyRecyclerViewAdapter;
import project.sample.com.luke.homeworkimage.base.BaseFragment;
import project.sample.com.luke.homeworkimage.data.ImgItem;
import project.sample.com.luke.homeworkimage.define.Define;
import project.sample.com.luke.homeworkimage.util.MyLog;

/**
 * Created by itsm02 on 2017. 2. 6..
 */

public class MyFragment1 extends BaseFragment {

    RecyclerView recyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    LinearLayoutManager linearLayoutManager;

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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        HtmlParsingAsyncTask htmlParsingAsyncTask = new HtmlParsingAsyncTask();
        htmlParsingAsyncTask.execute(null, null, null);

        return view;
    }

    public class HtmlParsingAsyncTask extends AsyncTask<Void, Void, ArrayList> {

        private final String getClass1 = "gallery-wrap";
        private final String getClass2 = "gallery-item-group";
        private final String getClass3 = "picture";
        private final String getClass4 = "gallery-item-caption";
        private final String getAttr1 = "src";
        private final String getAttr2 = "href";
        private final String getTag1 = "a";

        private ArrayList<ImgItem> arrayList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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

//                ImgItem imgItem = null;
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
                        MyLog.d(imgItem.toString());

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);

            ImgItem item = (ImgItem) arrayList.get(0);

            MyLog.d(" arrayList = " + arrayList.size());

            MyLog.d(" item = " + item);
            recyclerView.setHasFixedSize(true);

            myRecyclerViewAdapter = new MyRecyclerViewAdapter(arrayList, ((MainActivity) fragmentActivity).mImageFetcher);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false);


            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            gridLayoutManager.canScrollVertically();

            recyclerView.setLayoutManager(gridLayoutManager);
//            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(myRecyclerViewAdapter);

            recyclerView.addItemDecoration(new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));


//            recyclerView.setLayoutManager(linearLayoutManager);

        }
    }


}
