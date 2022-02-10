package uz.adkhamjon.cryptocurrency.presentation.coin_list

import uz.adkhamjon.cryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean=false,
    val coins:List<Coin> = emptyList(),
    val error:String = ""
)
