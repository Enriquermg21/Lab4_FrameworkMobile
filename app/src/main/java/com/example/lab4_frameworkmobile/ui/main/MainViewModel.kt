import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab4_frameworkmobile.ui.user.User

class MainViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>> get() = _users

    fun addUser(user: User) {
        val userList = _users.value?.toMutableList() ?: mutableListOf()
        userList.add(user)
        _users.value = userList
    }
}
