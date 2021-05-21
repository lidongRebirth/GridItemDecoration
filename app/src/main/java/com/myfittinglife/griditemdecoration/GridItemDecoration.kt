package com.myfittinglife.griditemdecoration

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author LD
 * @Time 2021/5/7 17:13
 * @Describe 默认left和bottom都为0，间隙由一个itemView来全部占有
 * @Modify
 */
class GridItemDecoration(leftAndRightSpace: Int = 0, topAndBottomSpace: Int = 0) :
    RecyclerView.ItemDecoration() {


    //总共的列数
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



        //当前View所属位置(从0计数)
        val itemPosition = parent.getChildAdapterPosition(view)
//        val itemPosition2 = parent.getChildLayoutPosition(view)
//        Log.i("ceshi", "getItemOffsets: getChildLayoutPosition为：$itemPosition2 getChildAdapterPosition为：$itemPosition" )
        if (itemPosition!=-1){
            //当前所处行,从0行计数
            val currentRow = itemPosition / mSpanCount
            //当前所处列，从0计数
            val currentColumn = itemPosition % mSpanCount


//-------
        if ((parent.layoutManager as GridLayoutManager).orientation == LinearLayoutManager.VERTICAL) {
            //默认是这种方式，因为默认的都是VERTICAL
            outRect.set(0, topAndBottomSpace, leftAndRightSpace, 0)
            //第一行
            if (currentRow == 0) {
                outRect.top = 0
            }
            //最后一列
            if (currentColumn == mSpanCount - 1) {
                outRect.right = 0
            }
        }
        //当recyclerview是按照竖排方式排列的时候，就用这个，此时itemview的最外层应该是高度为match_parent，宽度为wrap_content,那样才可以实现高度的平分
        else if ((parent.layoutManager as GridLayoutManager).orientation == LinearLayoutManager.HORIZONTAL) {

            outRect.set(leftAndRightSpace, 0, 0, topAndBottomSpace)
            //第一列
            if (currentRow == 0) {
                outRect.left = 0
            }
            //最后一行
            if (currentColumn == mSpanCount - 1) {
                outRect.bottom = 0
            }
        }
        }

    }


}