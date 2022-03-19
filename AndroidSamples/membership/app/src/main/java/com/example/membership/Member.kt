package com.example.membership

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Member(var seq:Int,var name:String, var age:Int, var relationship:String, var job:String, var address:String, var phone:String):Parcelable