package uz.adkhamjon.cryptocurrency.domain.repository

import uz.adkhamjon.cryptocurrency.data.remote.dto.CoinDetailDto
import uz.adkhamjon.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins():List<CoinDto>

    suspend fun getCoinById(coinId:String):CoinDetailDto
}