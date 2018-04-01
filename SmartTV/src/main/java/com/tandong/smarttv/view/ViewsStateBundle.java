package com.tandong.smarttv.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.LruCache;
import android.util.SparseArray;
import android.view.View;

import java.util.Iterator;
import java.util.Map;

import static com.tandong.smarttv.view.BaseGridView.SAVE_ALL_CHILD;
import static com.tandong.smarttv.view.BaseGridView.SAVE_LIMITED_CHILD;
import static com.tandong.smarttv.view.BaseGridView.SAVE_NO_CHILD;
import static com.tandong.smarttv.view.BaseGridView.SAVE_ON_SCREEN_CHILD;

/**
 * Created by office on 2018/3/29.
 */

class ViewsStateBundle {

    public static final int LIMIT_DEFAULT = 100;
    public static final int UNLIMITED = Integer.MAX_VALUE;

    private int mSavePolicy;
    private int mLimitNumber;

    private LruCache<String, SparseArray<Parcelable>> mChildStates;

    public ViewsStateBundle() {
        mSavePolicy = SAVE_NO_CHILD;
        mLimitNumber = LIMIT_DEFAULT;
    }

    public void clear() {
        if (mChildStates != null) {
            mChildStates.evictAll();
        }
    }

    public void remove(int id) {
        if (mChildStates != null && mChildStates.size() != 0) {
            mChildStates.remove(getSaveStatesKey(id));
        }
    }

    /**
     * @return the saved views states
     */
    public final Bundle saveAsBundle() {
        if (mChildStates == null || mChildStates.size() == 0) {
            return null;
        }
        Map<String, SparseArray<Parcelable>> snapshot = mChildStates.snapshot();
        Bundle bundle = new Bundle();
        for (Iterator<Map.Entry<String, SparseArray<Parcelable>>> i =
             snapshot.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<String, SparseArray<Parcelable>> e = i.next();
            bundle.putSparseParcelableArray(e.getKey(), e.getValue());
        }
        return bundle;
    }

    public final void loadFromBundle(Bundle savedBundle) {
        if (mChildStates != null && savedBundle != null) {
            mChildStates.evictAll();
            for (Iterator<String> i = savedBundle.keySet().iterator(); i.hasNext(); ) {
                String key = i.next();
                mChildStates.put(key, savedBundle.getSparseParcelableArray(key));
            }
        }
    }

    /**
     * @return the savePolicy, see {@link #} {@link #}
     *         {@link #} {@link #}
     */
    public final int getSavePolicy() {
        return mSavePolicy;
    }

    /**
     * @return the limitNumber, only works when {@link #getSavePolicy()} is
     *         {@link #}
     */
    public final int getLimitNumber() {
        return mLimitNumber;
    }

    /**
     * @see
     */
    public final void setSavePolicy(int savePolicy) {
        this.mSavePolicy = savePolicy;
        applyPolicyChanges();
    }

    /**
     * @see
     */
    public final void setLimitNumber(int limitNumber) {
        this.mLimitNumber = limitNumber;
        applyPolicyChanges();
    }

    protected void applyPolicyChanges() {
        if (mSavePolicy == SAVE_LIMITED_CHILD) {
            if (mLimitNumber <= 0) {
                throw new IllegalArgumentException();
            }
            if (mChildStates == null || mChildStates.maxSize() != mLimitNumber) {
                mChildStates = new LruCache<String, SparseArray<Parcelable>>(mLimitNumber);
            }
        } else if (mSavePolicy == SAVE_ALL_CHILD || mSavePolicy == SAVE_ON_SCREEN_CHILD) {
            if (mChildStates == null || mChildStates.maxSize() != UNLIMITED) {
                mChildStates = new LruCache<String, SparseArray<Parcelable>>(UNLIMITED);
            }
        } else {
            mChildStates = null;
        }
    }

    /**
     * Load view from states, it's none operation if the there is no state associated with the id.
     *
     * @param view view where loads into
     * @param id unique id for the view within this ViewsStateBundle
     */
    public final void loadView(View view, int id) {
        if (mChildStates != null) {
            String key = getSaveStatesKey(id);
            // Once loaded the state, do not keep the state of child. The child state will
            // be saved again either when child is offscreen or when the parent is saved.
            SparseArray<Parcelable> container = mChildStates.remove(key);
            if (container != null) {
                view.restoreHierarchyState(container);
            }
        }
    }

    /**
     * Save views regardless what's the current policy is.
     *
     * @param view view to save
     * @param id unique id for the view within this ViewsStateBundle
     */
    protected final void saveViewUnchecked(View view, int id) {
        if (mChildStates != null) {
            String key = getSaveStatesKey(id);
            SparseArray<Parcelable> container = new SparseArray<Parcelable>();
            view.saveHierarchyState(container);
            mChildStates.put(key, container);
        }
    }

    /**
     * The on screen view is saved when policy is not {@link #}.
     *
     * @param bundle   Bundle where we save the on screen view state.  If null,
     *                 a new Bundle is created and returned.
     * @param view     The view to save.
     * @param id       Id of the view.
     */
    public final Bundle saveOnScreenView(Bundle bundle, View view, int id) {
        if (mSavePolicy != SAVE_NO_CHILD) {
            String key = getSaveStatesKey(id);
            SparseArray<Parcelable> container = new SparseArray<Parcelable>();
            view.saveHierarchyState(container);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray(key, container);
        }
        return bundle;
    }

    /**
     * Save off screen views according to policy.
     *
     * @param view view to save
     * @param id unique id for the view within this ViewsStateBundle
     */
    public final void saveOffscreenView(View view, int id) {
        switch (mSavePolicy) {
            case SAVE_LIMITED_CHILD:
            case SAVE_ALL_CHILD:
                saveViewUnchecked(view, id);
                break;
            case SAVE_ON_SCREEN_CHILD:
                remove(id);
                break;
            default:
                break;
        }
    }

    static String getSaveStatesKey(int id) {
        return Integer.toString(id);
    }
}
