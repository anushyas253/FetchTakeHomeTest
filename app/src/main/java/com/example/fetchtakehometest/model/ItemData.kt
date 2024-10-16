package com.example.fetchtakehometest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemData(val id:Long ,
                    val listId: Int,
                    val name: String?,) : Parcelable

