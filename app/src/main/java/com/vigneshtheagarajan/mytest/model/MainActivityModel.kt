package com.vigneshtheagarajan.mytest.model
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import com.vigneshtheagarajan.utils.one.RecyclerviewUtil.BaseModel
import com.vigneshtheagarajan.utils.one.toast
import kotlinx.android.parcel.Parcelize


data class MainActivityModel(
    @SerializedName("Currency")
    val currency: String,
    @SerializedName("FoodList")
    val foodList:  List<Food>,
    val status: Status
)
    @Parcelize
    data class Food(
        val fnblist: ArrayList<Fnblist>,
        @SerializedName("TabName")
        val tabName: String
    ):Parcelable
        @Parcelize
        data class Fnblist(
            @SerializedName("Cinemaid")
            val cinemaid: String,
            @SerializedName("ImageUrl")
            val imageUrl: String,
            @SerializedName("ImgUrl")
            val imgUrl: String,
            @SerializedName("ItemPrice")
            val itemPrice: String,
            @SerializedName("Name")
            val name: String,
            @SerializedName("OtherExperience")
            val otherExperience: String,
            @SerializedName("PriceInCents")
            val priceInCents: String,
            @SerializedName("SevenStarExperience")
            val sevenStarExperience: String,
            @SerializedName("SubItemCount")
            val subItemCount: Int,
            val subitems: ArrayList<Subitem>,
            @SerializedName("TabName")
            val tabName: String,
            @SerializedName("VegClass")
            val vegClass: String,
            @SerializedName("VistaFoodItemId")
            val vistaFoodItemId: String,
            var quantity : Int = 0,
            var selectedSubItem : Int = 0,
            var selectedSubItemPrice : String = ""
        ) :Parcelable,BaseModel(){



        }
            @Parcelize
            data class Subitem(
                @SerializedName("Name")
                val name: String,
                @SerializedName("PriceInCents")
                val priceInCents: String,
                @SerializedName("SubitemPrice")
                val subitemPrice: String,
                @SerializedName("VistaParentFoodItemId")
                val vistaParentFoodItemId: String,
                @SerializedName("VistaSubFoodItemId")
                val vistaSubFoodItemId: String
            ):Parcelable


    data class Status(
        @SerializedName("Description")
        val description: String,
        @SerializedName("Id")
        val id: String
    )


object test {
    @JvmStatic
    @BindingAdapter("price")
    fun setPrice(view: View , data: Fnblist?) {
        data?.apply {
            var price = 0.0
            if (priceInCents =="0")
                price= selectedSubItemPrice.toDouble() * quantity
            else
                price=itemPrice.toDouble() * quantity

            (view as TextView).text = price.toString()
        }

    }
}