package com.andreescajedarios.apsi.history

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.andreescajedarios.apsi.data_classes.PlpFlags
import com.andreescajedarios.apsi.data_classes.VariantsColor

class RecordsViewModel : BaseObservable() {
    val productDisplayName :  ObservableField<String> = ObservableField()
    val listPrice :  ObservableField<String> = ObservableField()
    // TODO: ubicación
    val xlImage :  ObservableField<String> = ObservableField()

    /*
    val productId :  ObservableField<Long> = ObservableField()
    val skuRepositoryId :  ObservableField<Long> = ObservableField()
    val productDisplayName :  ObservableField<String> = ObservableField()
    val productType :  ObservableField<String> = ObservableField()
    val productRatingCount :  ObservableField<Long> = ObservableField()
    val productAvgRating :  ObservableField<Double> = ObservableField()
    val listPrice :  ObservableField<Double> = ObservableField()
    val minimumListPrice :  ObservableField<Double> = ObservableField()
    val maximumListPrice :  ObservableField<Double> = ObservableField()
    val promoPrice :  ObservableField<Double> = ObservableField()
    val minimumPromoPrice :  ObservableField<Double> = ObservableField()
    val maximumPromoPrice :  ObservableField<Double> = ObservableField()
    val isHybrid :  ObservableField<Boolean>? = ObservableField()
    val isMarketPlace :  ObservableField<Boolean>? = ObservableField()
    val smImage :  ObservableField<String> = ObservableField()
    val lgImage :  ObservableField<String> = ObservableField()
    val xlImage :  ObservableField<String> = ObservableField()
    val groupType :  ObservableField<String> = ObservableField()
    */
}