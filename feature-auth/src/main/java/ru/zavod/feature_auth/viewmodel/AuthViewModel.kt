package ru.zavod.feature_auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simon.xmaterialccp.data.CountryData
import com.simon.xmaterialccp.data.utils.getDefaultLangCode
import com.simon.xmaterialccp.data.utils.getLibCountries
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_auth.usecase.SendAuthCodeUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    context: Context,
    private val sendAuthCodeUseCase: SendAuthCodeUseCase
) : ViewModel() {

    private val countryList: List<CountryData> = getLibCountries()
    private val codes = countryList.map { it.countryPhoneCode }
    private val loadStateMutableStateFlow = MutableStateFlow<LoadStateApp?>(value = null)
    private val countriesMutableStateFlow = MutableStateFlow<List<CountryData>?>(value = null)
    private val openDialogMutableStateFlow = MutableStateFlow(value = false)
    private val selectedMutableStateFlow: MutableStateFlow<CountryData>
    private val codeMutableStateFlow: MutableStateFlow<String>
    private val phoneMutableStateFlow: MutableStateFlow<String>

    init {
        val defaultLang = getDefaultLangCode(context = context)
        val defaultCountry = getLibCountries().single { it.countryCode == defaultLang }
        selectedMutableStateFlow = MutableStateFlow(value = defaultCountry)
        codeMutableStateFlow = MutableStateFlow(value = defaultCountry.countryPhoneCode)
        phoneMutableStateFlow = MutableStateFlow(value = EMPTY_STRING)
    }

    val loadStateStateFlow = loadStateMutableStateFlow.asStateFlow()
    val countriesStateFlow = countriesMutableStateFlow.asStateFlow()
    val openDialogStateFlow = openDialogMutableStateFlow.asStateFlow()
    val selectedStateFlow = selectedMutableStateFlow.asStateFlow()
    val codeStateFlow = codeMutableStateFlow.asStateFlow()
    val phoneStateFlow = phoneMutableStateFlow.asStateFlow()

    fun setCountries(countries: List<CountryData>?) {
        countriesMutableStateFlow.value = countries
    }

    fun openDialog(open: Boolean) {
        openDialogMutableStateFlow.value = open
    }

    fun setCode(code: String) {
        val any = codes.firstOrNull { it.contains(code) }
        if (any == null) {
            return
        }
        loadStateMutableStateFlow.value = null
        codeMutableStateFlow.value = code
        val countries = countryList.filter { it.countryPhoneCode.contains(code) }
        setCountries(countries = countries)
    }

    fun setPhone(phone: String) {
        phoneMutableStateFlow.value = phone
        loadStateMutableStateFlow.value = null
    }

    fun selectCountry(country: CountryData) {
        setCode(code = country.countryPhoneCode)
        selectedMutableStateFlow.value = country
        setCountries(countries = null)
    }

    fun sendPhone() {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                sendAuthCodeUseCase.execute(
                    code = codeStateFlow.value,
                    phone = phoneStateFlow.value,
                    countryAlias = selectedStateFlow.value.countryCode
                )
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (e: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = e)
            }
        }
    }

    fun changePhone() {
        loadStateMutableStateFlow.value = null
    }
}