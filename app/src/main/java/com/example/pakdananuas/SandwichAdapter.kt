package com.example.pakdananuas

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class SandwichAdapter(
    private val context: Activity,
    private val list: ArrayList<Sandwich>
) : ArrayAdapter<Sandwich>(context, R.layout.item_menu, list) {

    // Menggunakan ViewHolder untuk performa yang lebih baik
    private class ViewHolder {
        var imgMenu: ImageView? = null
        var txtMenu: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView
        val holder: ViewHolder

        if (rowView == null) {
            val inflater = context.layoutInflater
            rowView = inflater.inflate(R.layout.item_menu, parent, false)

            holder = ViewHolder()
            holder.imgMenu = rowView.findViewById(R.id.imgMenu)
            holder.txtMenu = rowView.findViewById(R.id.txtMenu)
            rowView.tag = holder
        } else {
            holder = rowView.tag as ViewHolder
        }

        val sandwich = list[position]

        holder.imgMenu?.setImageResource(sandwich.gambar)
        holder.txtMenu?.text = sandwich.nama

        return rowView!!
    }
}
