package com.tandong.smarttv.view;

/**
 * Created by office on 2018/3/29.
 */

import android.support.annotation.IntDef;
import android.support.annotation.RestrictTo;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/** @hide */
@RestrictTo(LIBRARY_GROUP)
@IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
@Retention(RetentionPolicy.SOURCE)
public @interface Visibility {}
