package jp.yama.viewmodel002

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class MainActivityViewModel : ViewModel() {

    val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also {
            it.value = loadUsers()
        }
    }

    private fun loadUsers(): List<User> {
        return listOf(User("yama"), User("tarou"))
    }

    fun appendUser(name: String) {
        val mutable = users.value?.toMutableList()
        mutable?.add(User(name))
        users.value = mutable?.toList()!!
    }

}
