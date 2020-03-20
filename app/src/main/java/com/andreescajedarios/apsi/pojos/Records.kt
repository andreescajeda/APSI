package com.andreescajedarios.apsi.data_classes

class Records (
	val productId : Long?,
	val skuRepositoryId : Long?,
	val productDisplayName : String?,
	val productType : String?,
	val productRatingCount : Long?,
	val productAvgRating : Double?,
	val listPrice : Double?,
	val minimumListPrice : Double?,
	val maximumListPrice : Double?,
	val promoPrice : Double?,
	val minimumPromoPrice : Double?,
	val maximumPromoPrice : Double?,
	val isHybrid : Boolean?,
	val isMarketPlace : Boolean?,
	val smImage : String?,
	val lgImage : String?,
	val xlImage : String?,
	val groupType : String?,
	val plpFlags : List<PlpFlags?>?,
	val variantsColor : List<VariantsColor?>?
)