package uz.adkhamjon.cryptocurrency.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import uz.adkhamjon.cryptocurrency.data.remote.dto.CoinDetailDto
import uz.adkhamjon.cryptocurrency.data.remote.dto.CoinDto

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins():List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId:String):CoinDetailDto


}