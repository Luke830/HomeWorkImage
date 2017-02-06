package project.sample.com.luke.homeworkimage.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity {

    public Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        context = null;
    }
}
