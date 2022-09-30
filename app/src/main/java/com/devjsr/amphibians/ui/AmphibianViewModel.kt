package com.devjsr.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjsr.amphibians.network.Amphibian
import com.devjsr.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus { LOADING, ERROR, DONE }

class AmphibianViewModel: ViewModel() {

    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status

    private val _amphibianList = MutableLiveData<List<Amphibian>>()
    val amphibianList: LiveData<List<Amphibian>> = _amphibianList

    private val _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> = _amphibian

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _status.value = AmphibianApiStatus.DONE
                _amphibianList.value = AmphibiansApi.retrofitService.getAmphibians()
            }  catch (e: Exception) {
                _status.value = AmphibianApiStatus.ERROR
                _amphibianList.value = listOf()
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        _amphibian.value = amphibian
    }
}