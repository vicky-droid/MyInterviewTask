package com.vigneshtheagarajan.mytest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vigneshtheagarajan.mytest.R
import com.vigneshtheagarajan.mytest.model.Fnblist
import com.vigneshtheagarajan.mytest.viewModel.MainActivityViewModel.Companion.qunatityCallback
import kotlinx.android.synthetic.main.cell_main.view.*


class MainAdapter(val List: ArrayList<Fnblist>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cell_main, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(data: Fnblist) {
            itemView.apply {
                itemName.text = data.name
                imageView.load(data.imageUrl)
                imageView.clipToOutline = true

                minusCount.setOnClickListener {
                    data.apply {
                        if (quantity != 0)
                            quantity -= 1
                        qunatityCallback?.invoke(this)
                        quantity_tv.text = quantity.toString()

                    }
                }
                addCount.setOnClickListener {
                    data.apply {
                        quantity += 1
                        qunatityCallback?.invoke(this)
                        quantity_tv.text = quantity.toString()


                    }
                }

                rg_size.orientation = LinearLayout.HORIZONTAL

                data.subitems.forEach {
                    val rbn = RadioButton(itemView.context)
                    rbn.id = View.generateViewId()
                    rbn.text = it.name

                    val params = RadioGroup.LayoutParams(
                        RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(50, 8, 0, 8);

                    rbn.layoutParams = params
                    rbn.setPadding(30, 20, 30, 20)
                    rbn.setTextColor(ContextCompat.getColor(itemView.context, R.color.gray));
                    rbn.setBackgroundResource(R.drawable.rb_selector)
                    rbn.setButtonDrawable(android.R.color.transparent)
                    rg_size.addView(rbn)
                }
                rg_size.setOnCheckedChangeListener { group, checkedId ->
//                    val radio: RadioButton = group.findViewById(checkedId)
                    val index = rg_size.indexOfChild(findViewById(rg_size.checkedRadioButtonId))
                    price.text = data.subitems[index].subitemPrice
                    data.selectedSubItem = index
                    data.selectedSubItemPrice = data.subitems[index].subitemPrice
                    qunatityCallback?.invoke(data)


                }


                if (data.subItemCount == 0) {
                    rg_size.visibility = View.GONE
                    price.text = data.itemPrice

                } else {
                    rg_size.check(rg_size[0].id)
                    price.text = data.subitems.first().subitemPrice

                }


            }

        }
    }
}