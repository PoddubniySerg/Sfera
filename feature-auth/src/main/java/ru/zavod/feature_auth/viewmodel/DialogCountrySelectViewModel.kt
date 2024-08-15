package ru.zavod.feature_auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.simon.xmaterialccp.data.utils.getLibCountries
import com.simon.xmaterialccp.utils.searchCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.zavod.app_core.EMPTY_STRING
import javax.inject.Inject

class DialogCountrySelectViewModel @Inject constructor() : ViewModel() {

    private val countriesMutableStateFlow = MutableStateFlow(value = getLibCountries())
    private val searchValueMutableStateFlow = MutableStateFlow(value = EMPTY_STRING)

    val countriesStateFlow = countriesMutableStateFlow.asStateFlow()
    val searchValueStateFlow = searchValueMutableStateFlow.asStateFlow()

    fun search(searchValue: String, context: Context) {
        searchValueMutableStateFlow.value = searchValue
        val countries = countriesStateFlow.value.searchCountry(key = searchValue, context = context)
        countriesMutableStateFlow.value = countries
    }
}