package uz.adkhamjon.cryptocurrency.domain.use_cases.get_coins

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import uz.adkhamjon.cryptocurrency.common.Resource
import uz.adkhamjon.cryptocurrency.data.remote.dto.toCoin
import uz.adkhamjon.cryptocurrency.domain.model.Coin
import uz.adkhamjon.cryptocurrency.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e:HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage?:"An unexpected error occured"))
        } catch (e: IOException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage?:"Couldn't reach server. Check your internet connection"))
        }
    }
}