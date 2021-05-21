package com.myfittinglife.griditemdecoration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


/**
@Author LD
@Time 2021/5/13 15:26
@Describe GridLayoutManager，人为的设置item间隙
@Modify
*/
class MainActivity : AppCompatActivity() {

    var position = 0

    private val dataList = mutableListOf<String>()
    private val mAdapter: MyAdapter by lazy { MyAdapter(dataList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //填充数据
        for (i in 0..15) {
            dataList.add("Item$i")
        }

        val layoutManager = GridLayoutManager(this, 3)
        mRecyclerview.layoutManager = layoutManager
        mRecyclerview.adapter = mAdapter


        //思路二：每个itemview的left和top都为0
        mRecyclerview.addItemDecoration(GridItemDecoration(20, 10))
        //思路一：每个itemview的四个方向都偏移
//        mRecyclerview.addItemDecoration(GridItemDecoration2(20, 10))


        btnRemove.setOnClickListener {
            position = 4
            if (position <= dataList.size - 1) {

                mAdapter.notifyItemRemoved(position)
                //先数据删除或者先notifyItemRemoved都可以，但是别忘记要notifyItemRangeChanged
                dataList.removeAt(position)
                mAdapter.notifyItemRangeChanged(position, dataList.size - position)

            }
        }
    }
}
