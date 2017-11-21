package com.knothing.vhtabfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

/**
 * Created by MZH on 2017/11/21.
 * Description: 垂直Fragment
 */
class SubVerticalFragment : Fragment() {

    private val TAG = SubHorizontalFragment::class.java.simpleName

    var passValue: String? = null

    companion object {
        fun getInstance(): SubVerticalFragment {
            val args = Bundle()
            val fragment = SubVerticalFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val passArgument = arguments
        passValue = passArgument.getString("passValue")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.sub_vertical_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rg = view?.findViewById<RadioGroup>(R.id.rg)
        for (i in 0 until 10){
            val rb = View.inflate(activity,R.layout.hor_top_rb,null) as RadioButton
            rb.id = i
            rb.text = "Tab $i"
            rg?.addView(rb)
//            rb?.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.white_cycle_selector)
            rb.setOnFocusChangeListener { v, hasFocus ->
                handleOnFocusListener(v,hasFocus)
            }
        }
//        view?.setOnKeyListener(OnKey)
    }

    private fun handleOnFocusListener(v: View?, hasFocus: Boolean) {
        if (hasFocus){
            val id = v?.id
            Log.d(TAG,"fuck id = $id")
            val subFragment = TempFragment.getInstance()
            val bundle = Bundle()
            bundle.putString("passValue","这是id = $id")
            subFragment.arguments = bundle
            val fragmentManager = childFragmentManager;
            val transaction = fragmentManager.beginTransaction()
            val tempFragment = fragmentManager.findFragmentByTag("tag")
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
//            transaction.setCustomAnimations(R.anim.slide_in_top,R.anim.slide_out_bottom)
            if(tempFragment == null) transaction.add(R.id.frameLayout,subFragment,"tag") else transaction.replace(R.id.frameLayout,subFragment,"tag")
//            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent) {
        Log.d(TAG,"keycode = $keyCode")
    }

}