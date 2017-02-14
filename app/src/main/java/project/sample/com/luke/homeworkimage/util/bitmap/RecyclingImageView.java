/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package project.sample.com.luke.homeworkimage.util.bitmap;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Sub-class of ImageView which automatically notifies the drawable when it is being displayed.
 */

public class RecyclingImageView extends ImageView {
    private boolean invalidated = false;
    private OnInvalidated onInvalidated;

    public RecyclingImageView(Context context) {
        super(context);
    }

    public RecyclingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RecyclingImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Drawable drawable = this.getDrawable();
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                if (isBitmapRecycled(drawable)) {
                    this.setImageDrawable(null);
                    setInvalidated(true);
                }
            } else if (drawable instanceof TransitionDrawable) {
                TransitionDrawable transitionDrawable = (TransitionDrawable) drawable;

                // If last bitmap in chain is recycled, just blank this out since it would be invalid anyways
                Drawable lastDrawable = transitionDrawable.getDrawable(transitionDrawable.getNumberOfLayers() - 1);
                if (isBitmapRecycled(lastDrawable)) {
                    this.setImageDrawable(null);
                    setInvalidated(true);
                } else {
                    // Go through earlier bitmaps and make sure that they are not recycled
                    for (int i = 0; i < transitionDrawable.getNumberOfLayers(); i++) {
                        Drawable layerDrawable = transitionDrawable.getDrawable(i);
                        if (isBitmapRecycled(layerDrawable)) {
                            // If anything in the chain is broken, just get rid of transition and go to last drawable
                            this.setImageDrawable(lastDrawable);
                            break;
                        }
                    }
                }
            }
        }

        super.onDraw(canvas);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        setInvalidated(false);
    }

    private boolean isBitmapRecycled(Drawable drawable) {
        if (!(drawable instanceof BitmapDrawable)) {
            return false;
        }

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        if (bitmapDrawable.getBitmap() != null && bitmapDrawable.getBitmap().isRecycled()) {
            return true;
        } else {
            return false;
        }
    }

    public void setInvalidated(boolean invalidated) {
        this.invalidated = invalidated;

        if (invalidated && onInvalidated != null) {
            onInvalidated.onInvalidated(this);
        }
    }

    public boolean isInvalidated() {
        return invalidated;
    }

    public void setOnInvalidated(OnInvalidated onInvalidated) {
        this.onInvalidated = onInvalidated;
    }

    public interface OnInvalidated {
        void onInvalidated(RecyclingImageView imageView);
    }
}
