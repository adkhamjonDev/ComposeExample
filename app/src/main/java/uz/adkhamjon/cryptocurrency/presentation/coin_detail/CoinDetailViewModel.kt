package uz.adkhamjon.cryptocurrency.presentation.coin_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.adkhamjon.cryptocurrency.domain.use_cases.get_coins.GetCoinsUseCase
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.adkhamjon.cryptocurrency.common.Constants
import uz.adkhamjon.cryptocurrency.common.Resource
import uz.adkhamjon.cryptocurrency.domain.use_cases.get_coin.GetCoinUseCase

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _state= mutableStateOf(CoinDetailState())
    val state:State <CoinDetailState> =_state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoin(it)
        }
    }

    private  fun getCoin(coinId:String){
        getCoinUseCase(coinId).onEach {
            when(it){
                is Resource.Success->{
                    _state.value=CoinDetailState(coin = it.data)
                }
                is Resource.Error->{
                    _state.value= CoinDetailState(
                        error = it.message?: "An expected error occured"
                    )
                }
                is Resource.Loading->{
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}