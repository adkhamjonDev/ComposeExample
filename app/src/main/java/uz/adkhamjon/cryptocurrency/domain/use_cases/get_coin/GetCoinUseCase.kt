package uz.adkhamjon.cryptocurrency.domain.use_cases.get_coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import uz.adkhamjon.cryptocurrency.common.Resource
import uz.adkhamjon.cryptocurrency.data.remote.dto.toCoin
import uz.adkhamjon.cryptocurrency.data.remote.dto.toCoinDetail
import uz.adkhamjon.cryptocurrency.domain.model.Coin
import uz.adkhamjon.cryptocurrency.domain.model.CoinDetail
import uz.adkhamjon.cryptocurrency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId:String): Flow<Resource<CoinDetail>> = flow{
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e:HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?:"An unexpected error occured"))
        } catch (e: IOException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?:"Couldn't reach server. Check your internet connection"))
        }
    }
}