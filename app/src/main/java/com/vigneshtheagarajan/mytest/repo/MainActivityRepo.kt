package com.vigneshtheagarajan.mytest.repo

import com.vigneshtheagarajan.mytest.network.ApiCommonService
import com.vigneshtheagarajan.utils.one.network.baseRepository.BaseRepositorySuspend

class MainActivityRepo(private val apiCommonService: ApiCommonService) :
    BaseRepositorySuspend<ApiCommonService>(apiCommonService) {

    fun getData() = run {
        apiCommonService.gettest()
    }

}