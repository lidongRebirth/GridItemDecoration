package com.myfittinglife.griditemdecoration

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author LD
 * @Time 2021/5/7 17:13
 * @Describe 默认left和bottom都为0，间隙由一个itemView来全部占有
 * @Modify
 */
class GridItemDecoration2(leftAndRightSpace: Int = 0, topAndBottomSpace: Int = 0) :
    RecyclerView.ItemDecoration() {

    companion object {
        const val TAG = "ceshi"
    }

    var mSpanCount: Int = 0

    //左右间隔
    private val leftAndRightSpace = leftAndRightSpace

    //上下间隔
    private val topAndBottomSpace = topAndBottomSpace
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        //总共的列数
        mSpanCount = (parent.layoutManager as GridLayoutManager).spanCount


        //细研究下getChildAdapterPosition和getChildLayoutPosition()
//        val itemPosition = parent.getChildAdapterPosition(view)
        //当前View所属位置(从0计数)
        val itemPosition = parent.getChildLayoutPosition(view)

        //当前所处行,从0行计数
        val currentRow = itemPosition / mSpanCount
        //当前所处列，从0计数
        val currentColumn = itemPosition % mSpanCount


        //另外一种方法-------------------------------------------------------------------------------
        outRect.set(
            leftAndRightSpace / 2,
            topAndBottomSpace / 2,
            leftAndRightSpace / 2,
            topAndBottomSpace / 2
        )
        //总共的个数
        val totalCount = (parent.layoutManager as GridLayoutManager).itemCount
        Log.i(TAG, "getItemOffsets: 总共个数$totalCount")
        //总共的行数
        val totalRow = totalCount/mSpanCount+ if(totalCount%mSpanCount>0) 1 else 0
        //第一行
        if (currentRow == 0) {
            outRect.top = 0
        }
        //最后一行
        //第一种方式看是否是在最后一行
//        if(currentRow==totalRow-1){
//            outRect.bottom=0
//        }
        //第二种方式看是否在最后一行
        if (totalCount <= (currentRow + 1) * mSpanCount) {
            Log.i(TAG, "getItemOffsets: 最后一行")
            outRect.bottom = 0
        }
        //第0列
        if (currentColumn == 0) {
            outRect.left = 0
        }
        //最后一列
        if (currentColumn == mSpanCount - 1) {
            outRect.right = 0
        }


    }


}