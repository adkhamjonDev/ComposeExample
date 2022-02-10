package uz.adkhamjon.cryptocurrency.presentation.coin_detail

import uz.adkhamjon.cryptocurrency.domain.model.Coin
import uz.adkhamjon.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean=false,
    val coin:CoinDetail?=null,
    val error:String = ""
)
