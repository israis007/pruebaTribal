package com.test.tribal.ui.mainscreen

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.test.tribal.App
import com.test.tribal.R
import com.test.tribal.rest.punkapi.RepositoryUnsplash
import com.test.tribal.ui.mainscreen.models.BeerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    val page = MutableLiveData<Int>()
    private val elementsByPage = MutableLiveData<Int>()
    var beerRetro = MutableLiveData<List<BeerModel>>()
    var busy = MutableLiveData<Int>()
    var showNoInternet = MutableLiveData<Int>()
    var showMainLayout = MutableLiveData<Int>()
    var tagline = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var brewedDate = MutableLiveData<String>()
    var foodPairing = MutableLiveData<String>()
    var nameToolbar = MutableLiveData<String>()
    var imageURL = MutableLiveData<String>()

    private val TAG = "MainVM"
    private var pageAfter = App.instance.resources.getInteger(R.integer.pageBegin)

    init {
        busy.value = View.VISIBLE
        showNoInternet.postValue(View.GONE)
        showMainLayout.postValue(View.VISIBLE)
        page.value = App.instance.resources.getInteger(R.integer.pageBegin)
        elementsByPage.value = App.instance.resources.getInteger(R.integer.elementsByPage)
        tagline.value = ""
        description.value = ""
        brewedDate.value = ""
        foodPairing.value = ""
        nameToolbar.value = App.instance.getString(R.string.app_name)
    }

    lateinit var activityViewModel: ViewModelStoreOwner

    fun changeViews(noInternet: Boolean){
        showNoInternet.value = if (noInternet) View.GONE else View.VISIBLE 
        showMainLayout.value = if (noInternet) View.VISIBLE else View.GONE 
    }

    fun updatePage(isUp: Boolean){
        var pageN = page.value!!.toInt()
        if (isUp){
            if (pageAfter > 1) {
                pageAfter--
                page.value = pageAfter
            }
        } else {
            pageN++
            page.value = pageN
        }
    }

    fun getBeersRetro(){
        busy.value = View.VISIBLE 
        viewModelScope.launch(Dispatchers.IO) {
//            val chelas = RepositoryUnsplash.getBeers(
//                activityViewModel,
//                page.value!!.toInt(),
//                elementsByPage.value!!.toInt()
//            )
//            if (chelas == 44) {
//                showNoInternet.postValue(View.VISIBLE )
//                showMainLayout.postValue(View.GONE )
//                return@launch
//            } else {
//                showNoInternet.postValue(View.GONE )
//                showMainLayout.postValue(View.VISIBLE )
//                beerRetro.postValue(chelas as List<BeerModel>)
//            }
        }
    }

}