package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

/**
 * ViewModel que cuidará das Views de forma a cuidar da persistência dos dados do cliente durante
 * o processo de pedido.
 */
class OrderViewModel: ViewModel() {

    val dateOptions = getPickupOptions()

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }


    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
    }

    fun setFlavour(desiredFlavor: String){
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String){
        _date.value = pickupDate
        updatePrice()
    }

    fun hasNoFlavorSet(): Boolean{
        updatePrice()
        return _flavor.value.isNullOrEmpty()
    }

    fun getPickupOptions(): List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        //Cria as opcões de 4 datas, a de hoje e outras 3 seguintes, com base no calendário.
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    /**
     * Método que seta valores fixos para as variáveis quantitativas que iremos usar no processo.
     */
    fun resetOrder(){
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    /**
     * Função init para iniciar a resetOrder() toda vez que iniciarmos o código
     */
    init{
        resetOrder()
    }

    /**
     * Método que atualiza o preço do pedido com base na quantidade de cupcakes e
     * pela constante que seria o valor de cada cupcake.
     */
    private fun updatePrice(){
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if(dateOptions[0] == _date.value){
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

}