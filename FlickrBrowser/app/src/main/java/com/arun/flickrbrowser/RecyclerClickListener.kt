package com.arun.flickrbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerClickListener
) : RecyclerView.SimpleOnItemTouchListener() {
    private val TAG = "RVClickListen"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private val gestureEvents =
        GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                Log.d(TAG, "onSingleTap")
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                if (childView != null) {
                    listener.onItemClick(
                        childView,
                        recyclerView.getChildAdapterPosition(childView)
                    )
                }
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                Log.d(TAG, "onLongPress")
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                listener.onItemLongClick(
                    childView!!,
                    recyclerView.getChildAdapterPosition(childView)
                )
            }
        })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return gestureEvents.onTouchEvent(e)
    }
}