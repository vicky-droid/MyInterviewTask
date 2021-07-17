package com.vigneshtheagarajan.mytest.viewModel

import androidx.lifecycle.ViewModel
import com.vigneshtheagarajan.mytest.model.Food

class PlaceholderFragmentViewModel: ViewModel() {


    fun calculatePrice(data : Food?=null){
        data?.fnblist?.map { it.itemPrice }

    }
}