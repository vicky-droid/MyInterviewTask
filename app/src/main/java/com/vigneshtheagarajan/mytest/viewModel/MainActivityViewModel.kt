package com.vigneshtheagarajan.mytest.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vigneshtheagarajan.mytest.model.Fnblist
import com.vigneshtheagarajan.mytest.repo.MainActivityRepo

class MainActivityViewModel(private val repo: MainActivityRepo) : ViewModel() {

    val foodListBottomSheet = ArrayList<Fnblist>()
    val foodLiveDataBottomSheet = MutableLiveData<ArrayList<Fnblist>>()
    val totalPrice: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    var currency: String = ""


    init {

        qunatityCallback = { data ->
            (data as Fnblist).apply {
                if (!foodListBottomSheet.contains(data))
                    foodListBottomSheet.add(this)
                foodLiveDataBottomSheet.value =
                    foodListBottomSheet.filter { it.quantity != 0 } as ArrayList<Fnblist>

            }

            var price = foodListBottomSheet.map {
                if (it.priceInCents == "0")
                    it.selectedSubItemPrice.toDouble() * it.quantity
                else
                    it.itemPrice.toDouble() * it.quantity
            }.sum()
            totalPrice.value = "$currency $price"

        }

    }

    companion object {
        var qunatityCallback: ((data: Any?) -> Unit)? = null
    }

    fun loadData() = repo.getData()

}





