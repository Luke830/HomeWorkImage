package project.sample.com.luke.homeworkimage;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import project.sample.com.luke.homeworkimage.activity.MainActivity;

/**
 * Created by itsm02 on 2017. 2. 16..
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testModule() {

    }

}
