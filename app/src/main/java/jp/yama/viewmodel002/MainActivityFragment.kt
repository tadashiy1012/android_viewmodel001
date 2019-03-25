package jp.yama.viewmodel002

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer


class MainActivityFragment : Fragment() {

    companion object {
        fun newInstance() = MainActivityFragment()
    }

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var txt1: TextView
    private lateinit var edit1: EditText
    private lateinit var btn1: Button
    private lateinit var nextBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("yama", "onCreateView@MainActivityFragment")
        val view = inflater.inflate(R.layout.main_activity_fragment, container, false)
        txt1 = view.findViewById(R.id.txt1)
        edit1 = view.findViewById(R.id.edit1)
        btn1 = view.findViewById(R.id.btn1)
        btn1.setOnClickListener {
            val name = edit1.text.toString()
            if (!name.isEmpty()) {
                viewModel.appendUser(name)
            }
        }
        nextBtn = view.findViewById(R.id.nextBtn)
        nextBtn.setOnClickListener {
            val fragment = SecondFragment.newInstance()
            val ary = arrayListOf<String>()
            viewModel.users.value?.mapTo(ary) {it.name}
            val bundle = Bundle()
            bundle.putStringArrayList("users", ary)
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container, fragment)
                ?.commit()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("yama", "onActibityCreated@MainActivityFragment")
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.users.observe(this, Observer {
            txt1.text = it?.map {
                it.name
            }?.joinToString { it }
        })
    }

}
