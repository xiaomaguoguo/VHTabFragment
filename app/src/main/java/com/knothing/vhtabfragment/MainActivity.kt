package com.knothing.vhtabfragment

import android.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.FrameLayout

/**
 * Created by MZH on 2017/11/21.
 * Description: 垂直、水平TAG切换Fragment
 */
class MainActivity : FragmentActivity(),View.OnClickListener {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
    }

    private fun initView() {
        val verticalBtn = findViewById<Button>(R.id.verticalBtn)
        val horBtn = findViewById<Button>(R.id.horBtn)
        verticalBtn.setOnClickListener(this)
        horBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.verticalBtn -> openVerticalContainer()
            R.id.horBtn -> openHorContainer()
            else  -> Log.d(TAG,"未知类型")
        }
    }

    val subHorFragment = SubHorizontalFragment.getInstance()
    private fun openHorContainer() {
        val fragmentManager = supportFragmentManager;
        val transaction = fragmentManager.beginTransaction()
        val tempFragment = fragmentManager.findFragmentByTag("tag")
//        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
        if(tempFragment == null) transaction.add(R.id.frameLayout,subHorFragment,"tag") else transaction.replace(R.id.frameLayout,subHorFragment,"tag")
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    val subFragment = SubVerticalFragment.getInstance()
    private fun openVerticalContainer() {
        val fragmentManager = supportFragmentManager;
        val transaction = fragmentManager.beginTransaction()
        val tempFragment = fragmentManager.findFragmentByTag("tag")
        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
//        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        if(tempFragment == null) transaction.add(R.id.frameLayout,subFragment,"tag") else transaction.replace(R.id.frameLayout,subFragment,"tag")
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        subHorFragment.onKeyDown(keyCode, event!!)
        subFragment.onKeyDown(keyCode, event!!)
        return super.onKeyDown(keyCode, event)
    }
}
