package com.tandong.smarttv.view;

import android.view.View;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;
import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by office on 2018/3/29.
 */

class ItemAlignment {

    final static class Axis extends ItemAlignmentFacet.ItemAlignmentDef {
        private int mOrientation;

        Axis(int orientation) {
            mOrientation = orientation;
        }

        /**
         * get alignment position relative to optical left/top of itemView.
         */
        public int getAlignmentPosition(View itemView) {
            return ItemAlignmentFacetHelper.getAlignmentPosition(itemView, this, mOrientation);
        }
    }

    private int mOrientation = HORIZONTAL;

    final public Axis vertical = new Axis(VERTICAL);

    final public Axis horizontal = new Axis(HORIZONTAL);

    private Axis mMainAxis = horizontal;

    private Axis mSecondAxis = vertical;

    final public Axis mainAxis() {
        return mMainAxis;
    }

    final public Axis secondAxis() {
        return mSecondAxis;
    }

    final public void setOrientation(int orientation) {
        mOrientation = orientation;
        if (mOrientation == HORIZONTAL) {
            mMainAxis = horizontal;
            mSecondAxis = vertical;
        } else {
            mMainAxis = vertical;
            mSecondAxis = horizontal;
        }
    }

    final public int getOrientation() {
        return mOrientation;
    }


}
