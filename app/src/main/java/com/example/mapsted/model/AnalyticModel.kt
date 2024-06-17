package com.example.mapsted.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName


data class DeviceModel(
    @SerializedName("manufacturer")
    val mManufacturer: String,
    @SerializedName("market_name")
    val mMarketName: String,
    @SerializedName("codename")
    val mCodename: String,
    @SerializedName("model")
    val mModel: String,
    @SerializedName("usage_statistics")
    val mUsageStatistics: UsageStatisticsModel
) {
    fun getBuildId(): Int {
        return mUsageStatistics.mSessionInfos[0].mBuildingId
    }
}

data class PurchaseModel(
    @SerializedName("item_id")
    val mItemId: Int,
    @SerializedName("item_category_id")
    val mItemCategoryId: Int,
    @SerializedName("cost")
    val mCost: Double
) {
    fun getItemCategoryIdString(): String {
        return "$mItemCategoryId"
    }

    fun getCost(): String {
        val currencySymbol = "$"
        return "$currencySymbol$mCost"
    }

    val itemID: String
        get() {
            return "$mItemId"
        }
}

data class BuildingModel(
    @SerializedName("building_id") val buildingId: Int?,
    @SerializedName("building_name") val buildingName: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("state") val state: String?,
    @SerializedName("country") val country: String?
)

data class SessionInfoModel(
    @SerializedName("building_id")
    val mBuildingId: Int,
    @SerializedName("purchases")
    val mPurchases: List<PurchaseModel>
) {
    fun getTotalPurchaseCost(): Double {
        return mPurchases.sumOf { it.mCost }
    }
}

data class UsageStatisticsModel(
    @SerializedName("session_infos")
    val mSessionInfos: List<SessionInfoModel>
) {
    @SuppressLint("DefaultLocale")
    fun getTotalCost(): String {
        val currencySymbol = "$"
        val cost = mSessionInfos.sumOf { it.getTotalPurchaseCost() }
        return "$currencySymbol${String.format("%.2f", cost)}"
    }
}
