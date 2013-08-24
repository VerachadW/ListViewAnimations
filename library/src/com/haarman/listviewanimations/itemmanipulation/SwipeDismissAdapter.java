/*
 * Copyright 2013 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haarman.listviewanimations.itemmanipulation;

import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.haarman.listviewanimations.BaseAdapterDecorator;

/**
 * Adds an option to swipe items in a ListView away. This does nothing more than
 * setting a new SwipeDismissListViewTouchListener to the ListView. You can
 * achieve the same effect by calling listView.setOnTouchListener(new
 * SwipeDismissListViewTouchListener(...)).
 */
public class SwipeDismissAdapter extends BaseAdapterDecorator {

    public static final int TO_RIGHT_ONLY = 10;
    public static final int TO_LEFT_ONLY = 11;

	private OnDismissCallback mCallback;
    private int mAllowedDirection = -1;

	public SwipeDismissAdapter(BaseAdapter baseAdapter, OnDismissCallback callback) {
		super(baseAdapter);
		mCallback = callback;
	}

    public SwipeDismissAdapter(BaseAdapter baseAdapter, OnDismissCallback callback, int swipeDirection) {
        super(baseAdapter);
        mCallback = callback;
        mAllowedDirection = swipeDirection;
    }

	@Override
	public void setAbsListView(AbsListView listView) {
		super.setAbsListView(listView);
        if (mAllowedDirection != -1){
		    listView.setOnTouchListener(new SwipeDismissListViewTouchListener(listView, mCallback, mAllowedDirection));
        } else{
            listView.setOnTouchListener(new SwipeDismissListViewTouchListener(listView, mCallback));
        }
	}
}
