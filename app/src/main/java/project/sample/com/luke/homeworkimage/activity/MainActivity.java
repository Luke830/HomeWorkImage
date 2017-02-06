package project.sample.com.luke.homeworkimage.activity;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.base.BaseFragmentActivity;
import project.sample.com.luke.homeworkimage.util.DataUtil;
import project.sample.com.luke.homeworkimage.util.bitmap.ImageCache;
import project.sample.com.luke.homeworkimage.util.bitmap.ImageFetcher;

public class MainActivity extends BaseFragmentActivity {

    private int mImageThumbSize;
    public ImageFetcher mImageFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageCache();

        DataUtil.replaceFragement(this, R.id.fragment_container, new MyFragment1());

    }

    @SuppressWarnings("deprecation")
    public void initImageCache() {
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
        // mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(this.getSupportFragmentManager(), cacheParams);
    }


}
