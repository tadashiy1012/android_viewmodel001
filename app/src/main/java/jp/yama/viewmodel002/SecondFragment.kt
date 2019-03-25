package jp.yama.viewmodel002

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer


class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel
    private lateinit var backBtn: Button
    private lateinit var txt2: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.second_fragment, container, false)
        backBtn = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        txt2 = view.findViewById(R.id.txt2)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        viewModel.users.observe(this, Observer {
            txt2.text = it.map { it.name }.joinToString { it }
        })
        val ary = arguments?.getStringArrayList("users")
        ary?.forEach { viewModel.appendUser(it) }
    }

}
