package project.sample.com.luke.homeworkimage.activity;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import project.sample.com.luke.homeworkimage.BuildConfig;
import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.fragment.MyFragment1;
import project.sample.com.luke.homeworkimage.util.DataUtil;
import project.sample.com.luke.homeworkimage.util.bitmap.ImageCache;
import project.sample.com.luke.homeworkimage.util.bitmap.ImageFetcher;

public class MainActivity extends BaseFragmentActivity {

    private int mImageThumbSize;
    public ImageFetcher mImageFetcher;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder();
//        builder.detectCustomSlowCalls(); // api level 11
            builder.detectNetwork();
//        builder.detectDiskReads();
//        builder.detectDiskWrites();
            builder.detectNetwork();
            //위반시 로그로 알림
            builder.penaltyLog();
            //위반시 dropbox 에 저장
            builder.penaltyDropBox();
            //위반시 다이얼로그로 알림
            builder.penaltyDialog();
            //위반시 강제종료
            builder.penaltyDeath();
            //네트워크 사용 위반시 강제종료 , 반드시 detectNetwork() 가 활성화 되어 있어야함.
            builder.penaltyDeathOnNetwork(); // api level 11
            //위반시 화면에 빨간 사각형 Spash로 알림.
            builder.penaltyFlashScreen(); // api level 11
            StrictMode.setThreadPolicy(builder.build());
        }

        setContentView(R.layout.activity_main);

        initImageCache();

        DataUtil.replaceFragement(this, R.id.fragment_container, new MyFragment1());

    }

    public void initImageCache() {

        if (mImageFetcher == null) {
            if (Build.VERSION.SDK_INT >= 13) {
                Point point = new Point();
                getWindowManager().getDefaultDisplay().getSize(point);
                mImageThumbSize = point.x;
            } else {
                mImageThumbSize = getWindowManager().getDefaultDisplay().getWidth();
            }

            ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(this, DataUtil.IMAGE_CACHE_DIR);
            cacheParams.setMemCacheSizePercent(0.5f); // Set memory cache to 25% of app memory

            mImageFetcher = new ImageFetcher(this, mImageThumbSize);
            mImageFetcher.addImageCache(getSupportFragmentManager(), cacheParams);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImageFetcher != null) {
            mImageFetcher.setExitTasksEarly(true);
            mImageFetcher.flushCache();
            mImageFetcher.closeCache();
            mImageFetcher = null;
        }
    }
}
