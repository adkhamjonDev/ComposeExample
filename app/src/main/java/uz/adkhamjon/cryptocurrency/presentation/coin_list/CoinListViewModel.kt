package uz.adkhamjon.cryptocurrency.presentation.coin_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.adkhamjon.cryptocurrency.domain.use_cases.get_coins.GetCoinsUseCase
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.adkhamjon.cryptocurrency.common.Resource

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
):ViewModel(){

    private val _state= mutableStateOf(CoinListState())
    val state:State <CoinListState> =_state

    init {
        getCoins()
    }

    private  fun getCoins(){
        getCoinsUseCase().onEach {
            when(it){
                is Resource.Success->{
                    _state.value=CoinListState(coins = it.data?: emptyList())
                }
                is Resource.Error->{
                    _state.value= CoinListState(
                        error = it.message?: "An expected error occured"
                    )
                }
                is Resource.Loading->{
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}