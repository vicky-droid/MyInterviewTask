package com.vigneshtheagarajan.mytest.network

import com.vigneshtheagarajan.utils.one.network.NetServiceCreator

object NetRequest {

    val ApiBaseUrl = "http://www.mocky.io/v2/"
    val commonService by lazy(mode = LazyThreadSafetyMode.NONE) {
        NetServiceCreator()
            .setBaseUrl(ApiBaseUrl)
            .enableDebug(true)
            .create(ApiCommonService::class.java)

    }

}



