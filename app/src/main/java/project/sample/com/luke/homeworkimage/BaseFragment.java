package project.sample.com.luke.homeworkimage;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public class BaseFragment extends Fragment {

    public BaseFragmentActivity fragmentActivity;

    public BaseFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentActivity = (BaseFragmentActivity) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentActivity = null;
    }


}
