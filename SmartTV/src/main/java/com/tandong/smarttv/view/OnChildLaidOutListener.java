package com.tandong.smarttv.view;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by office on 2018/3/29.
 */

public interface OnChildLaidOutListener {
    /**
     * Callback method to be invoked when a child of this ViewGroup has been
     * added to the view hierarchy and has been laid out.
     *
     * @param parent The ViewGroup where the layout happened.
     * @param view The view within the ViewGroup that was laid out.
     * @param position The position of the view in the adapter.
     * @param id The id of the child.
     */
    void onChildLaidOut(ViewGroup parent, View view, int position, long id);
}
