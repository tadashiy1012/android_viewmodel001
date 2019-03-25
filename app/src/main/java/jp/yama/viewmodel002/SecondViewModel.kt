package jp.yama.viewmodel002

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class SecondViewModel : ViewModel() {

    val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also { it.value = listOf() }
    }

    fun appendUser(name: String) {
        val mutable = users.value?.toMutableList()
        mutable?.add(User(name))
        users.value = mutable?.toList()!!
    }

}
