package project.sample.com.luke.homeworkimage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.sample.com.luke.homeworkimage.R;
import project.sample.com.luke.homeworkimage.base.BaseFragment;

/**
 * Created by itsm02 on 2017. 2. 6..
 */

public class MyFragment2 extends BaseFragment {

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

        return view;
    }

}
