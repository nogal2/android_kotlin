package com.example.mydocument

import android.os.Parcel
import android.os.Parcelable

class DataVo(var seq:Int, var imagePath:String?, var address:String?, var content:String?, var date:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(seq)
        parcel.writeString(imagePath)
        parcel.writeString(address)
        parcel.writeString(content)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataVo> {
        override fun createFromParcel(parcel: Parcel): DataVo {
            return DataVo(parcel)
        }

        override fun newArray(size: Int): Array<DataVo?> {
            return arrayOfNulls(size)
        }
    }

}