package com.test.tribal.ui.mainscreen.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.tribal.BuildConfig
import com.test.tribal.models.Photos
import com.test.tribal.rest.objects.Resource
import com.test.tribal.rest.unsplashapi.RepositoryUnsplash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _response = MutableLiveData<Resource<Photos>>()
    val response : LiveData<Resource<Photos>> get() = _response

    fun getPhotosFromServer(page: Int){
        _response.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO){
            try {
                val api = RepositoryUnsplash.clientAPI().getPhotos(page, BuildConfig.Secret_Key)
                if (api.isSuccessful && api.code() == 200)
                    _response.postValue(Resource.success(api.body()!!))
                else
                    _response.postValue(Resource.error("Ocurrió un error al obtener las fotografías {${api.code()}}", null))
            } catch (e: Exception){
                _response.postValue(Resource.error(e.message!!, null))
            }
        }
    }
}