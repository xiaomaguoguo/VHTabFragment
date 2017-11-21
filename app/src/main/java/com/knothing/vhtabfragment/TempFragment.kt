package com.knothing.vhtabfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by MZH on 2017/11/21.
 * Description: 临时用来展示的Fragment
 */
class TempFragment : Fragment() {
    var passValue: String? = null

    companion object {
        fun getInstance(): TempFragment {
            val args = Bundle()
            val fragment = TempFragment()
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
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.temp_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view!!.findViewById<TextView>(R.id.textView)
        if(!TextUtils.isEmpty(passValue)){
            textView.text = passValue
        }
    }
}