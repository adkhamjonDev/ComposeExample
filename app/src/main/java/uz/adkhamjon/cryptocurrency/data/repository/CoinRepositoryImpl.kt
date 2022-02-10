package uz.adkhamjon.cryptocurrency.data.repository

import uz.adkhamjon.cryptocurrency.data.remote.CoinPaprikaApi
import uz.adkhamjon.cryptocurrency.data.remote.dto.CoinDetailDto
import uz.adkhamjon.cryptocurrency.data.remote.dto.CoinDto
import uz.adkhamjon.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api:CoinPaprikaApi
):CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}