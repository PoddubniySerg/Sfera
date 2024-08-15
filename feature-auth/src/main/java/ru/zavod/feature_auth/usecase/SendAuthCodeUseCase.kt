package ru.zavod.feature_auth.usecase

import com.simon.xmaterialccp.data.utils.checkPhoneNumber
import ru.zavod.feature_auth.di.RemoteAuthRepository
import ru.zavod.feature_auth.exception.InvalidPhoneException
import ru.zavod.feature_auth.exception.UnauthorizedException
import ru.zavod.feature_auth.model.SendAuthCodeParams
import ru.zavod.feature_auth.model.SendAuthCodeResult
import javax.inject.Inject

class SendAuthCodeUseCase @Inject constructor(
    private val remoteAuthRepository: RemoteAuthRepository
) {

    suspend fun execute(code: String, phone: String, countryAlias: String) {
        val fullPhone = "$code$phone"
        validatePhone(countryAlias = countryAlias, phone = phone, fullPhone = fullPhone)
        val params = SendAuthCodeParams(phone = fullPhone)
        val result = remoteAuthRepository.sendAuthCode(params = params)
        assertAuthorized(result = result)
    }

    private fun validatePhone(countryAlias: String, phone: String, fullPhone: String) {
        val isValid = checkPhoneNumber(
            phone = phone,
            fullPhoneNumber = fullPhone,
            countryCode = countryAlias
        )
        if (!isValid) {
            throw InvalidPhoneException()
        }
    }

    private fun assertAuthorized(result: SendAuthCodeResult?) {
        if (result?.success != true) {
            throw UnauthorizedException()
        }
    }
}