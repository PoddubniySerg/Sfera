package ru.zavod.data_api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import ru.zavod.data_api.dto.CheckAuthCodeDto
import ru.zavod.data_api.dto.CheckAuthCodeResultDto
import ru.zavod.data_api.dto.GetMeResultDto
import ru.zavod.data_api.dto.RefreshTokenDto
import ru.zavod.data_api.dto.RefreshTokenResultDto
import ru.zavod.data_api.dto.RegisterDto
import ru.zavod.data_api.dto.RegisterResultDto
import ru.zavod.data_api.dto.SendAuthCodeDto
import ru.zavod.data_api.dto.SendAuthCodeResultDto
import ru.zavod.data_api.dto.UpdateMeDto
import ru.zavod.data_api.dto.UpdateMeResultDto

internal interface Api {

    @POST("$USERS_PATH/$SEND_AUTH_CODE_PATH/")
    suspend fun sendAuthCode(
        @Body sendAuthCodeDto: SendAuthCodeDto
    ): Response<SendAuthCodeResultDto>

    @POST("$USERS_PATH/$CHECK_AUTH_CODE_PATH/")
    suspend fun checkAuthCode(
        @Body checkAuthCodeDto: CheckAuthCodeDto
    ): Response<CheckAuthCodeResultDto>

    @POST("$USERS_PATH/$REGISTER_PATH/")
    suspend fun register(@Body registerDto: RegisterDto): Response<RegisterResultDto>

    @GET("$USERS_PATH/$ME_PATH/")
    suspend fun getMe(): Response<GetMeResultDto>

    @PUT("$USERS_PATH/$ME_PATH/")
    suspend fun updateMe(
        @Body updateMeDto: UpdateMeDto
    ): Response<UpdateMeResultDto>

    @POST("$USERS_PATH/$REFRESH_TOKEN_PATH/")
    suspend fun refreshToken(
        @Body refreshTokenDto: RefreshTokenDto
    ): Response<RefreshTokenResultDto>

    @GET("$USERS_PATH/$CHECK_JWT_PATH/")
    suspend fun checkJwt(): Response<String?>
}