package com.knothing.vhtabfragment

import android.annotation.SuppressLint
import android.content.Context
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
import android.widget.Toast
import android.view.KeyEvent.KEYCODE_BACK



/**
 * Created by MZH on 2017/11/21.
 * Description: 水平排列切换Fragment
 */
class SubHorizontalFragment : Fragment()  {

    private val TAG = SubHorizontalFragment::class.java.simpleName

    companion object {
        fun getInstance(): SubHorizontalFragment {
            val args = Bundle()
            val fragment = SubHorizontalFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val passArgument = arguments
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.sub_hor_fragment,container,false)
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rg = view?.findViewById<RadioGroup>(R.id.rg)
        for (i in 0 until 10){
            val rb = View.inflate(activity,R.layout.hor_top_rb,null) as RadioButton
            rb.id = i
            rb.text = "Tab $i"
            rg?.addView(rb)
//            rb?.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.white_cycle_selector)
//            rb.setCompoundDrawables(null,null,null,resources.getDrawable(R.drawable.white_cycle_selector,null))
            rb.setOnFocusChangeListener { v, hasFocus ->
                handleOnFocusListener(v,hasFocus)
            }
        }
//        view?.setOnKeyListener(OnKey)
    }

    var preId : Int = -1
    private fun handleOnFocusListener(v: View?, hasFocus: Boolean) {
        if (hasFocus){
            val id = v?.id as Int
            var isReverse  = if (preId < id ) false else true
            Log.d(TAG,"fuck id = $id")
            val subFragment = TempFragment.getInstance()
            val bundle = Bundle()
            bundle.putString("passValue","这是id = $id")
            subFragment.arguments = bundle
            val fragmentManager = childFragmentManager;
            val transaction = fragmentManager.beginTransaction()
            val tempFragment = fragmentManager.findFragmentByTag("tag")
//            val inAnim = if (isReverse) android.R.anim.slide_in_left else android.R.anim.slide_out_right
//            val outAnim = if (isReverse) android.R.anim.slide_out_right else android.R.anim.slide_out_right
            transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
            if(tempFragment == null) transaction.add(R.id.frameLayout,subFragment,"tag") else transaction.replace(R.id.frameLayout,subFragment,"tag")
//            transaction.addToBackStack(null)
            transaction.commit()
            preId = id
        }
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent) {
        when (keyCode){
            KeyEvent.KEYCODE_DPAD_LEFT -> ""
            KeyEvent.KEYCODE_DPAD_RIGHT -> ""
            KeyEvent.KEYCODE_DPAD_DOWN -> ""
            KeyEvent.KEYCODE_DPAD_UP -> ""

        }
        Log.d(TAG,"keycode = $keyCode")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context != null){

        }
    }


}