package project.sample.com.luke.homeworkimage;

import android.os.Bundle;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataUtil.replaceFragement(this, R.id.fragment_container, new MyFragment1());
    }


}
