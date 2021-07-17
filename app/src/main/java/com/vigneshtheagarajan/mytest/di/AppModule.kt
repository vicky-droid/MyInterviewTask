package com.vigneshtheagarajan.mystore.di


import com.vigneshtheagarajan.mytest.network.NetRequest.commonService
import com.vigneshtheagarajan.mytest.repo.MainActivityRepo
import com.vigneshtheagarajan.mytest.viewModel.MainActivityViewModel
import com.vigneshtheagarajan.mytest.viewModel.PlaceholderFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { commonService }
}

val repositoryModule = module {
    single { MainActivityRepo(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { PlaceholderFragmentViewModel() }
}