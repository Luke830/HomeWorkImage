package project.sample.com.luke.homeworkimage.util;


import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class DataUtil {

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), null, false);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, boolean isCommitAllowingStateLoss) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), null, isCommitAllowingStateLoss);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), null, false);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Object object) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), (object != null ? object.getClass().getName() : null), false);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, String object) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), (object != null ? object : null), false);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, Object object) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), (object != null ? object.getClass().getName() : null), false);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, Object object, boolean isCommitAllowingStateLoss) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), (object != null ? object.getClass().getName() : null), isCommitAllowingStateLoss);
    }


    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, String object) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), (object != null ? object : null), false);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, String tag, String addToBackStack, boolean isCommitAllowingStateLoss) {
//        MyLog.e("activity = " + activity + " layoutId = " + layoutId + " bundle = " + bundle + " tag = " + tag + " addToBackStack = " + addToBackStack);

//        MyLog.e(" layoutId = " + layoutId + " bundle = " + bundle + " tag = " + tag + " addToBackStack = " + addToBackStack);

        try {
            if (activity != null) {
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                if (transaction != null) {

                    if (bundle != null) {
                        fragment.setArguments(bundle);
                    }
                    transaction.replace(layoutId, fragment, tag);
                    if (addToBackStack != null) {
                        transaction.addToBackStack(addToBackStack);
                    }

                    if (isCommitAllowingStateLoss) {
                        transaction.commitAllowingStateLoss();
                    } else {
                        transaction.commit();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideAddFragment(FragmentActivity activity, Fragment hideFragment, int layoutId, Fragment addFragment, Bundle bundle, Object object) {
        hideAddFragment(activity, hideFragment, layoutId, addFragment, bundle, addFragment.getClass().getName(), (object != null ? object.getClass().getName() : null));
    }

    public static void hideAddFragment(FragmentActivity activity, Fragment hideFragment, int layoutId, Fragment addFragment, Bundle bundle, String tag, String addToBackStack) {
        if (activity != null) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            if (transaction != null) {
                if (bundle != null) {
                    addFragment.setArguments(bundle);
                }
                transaction.hide(hideFragment);
                transaction.add(layoutId, addFragment, tag);
                if (addToBackStack != null) {
                    transaction.addToBackStack(addToBackStack);
                }
                transaction.commit();
            }
        }
    }

    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT;
    }

    public static final String IMAGE_CACHE_DIR = "homework";
}
