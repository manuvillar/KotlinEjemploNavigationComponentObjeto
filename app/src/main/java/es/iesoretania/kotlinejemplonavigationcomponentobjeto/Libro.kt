package es.iesoretania.kotlinejemplonavigationcomponentobjeto

import android.os.Parcel
import android.os.Parcelable

class Libro (var Titulo: String?, var Publicacion: Int, var Paginas:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Titulo)
        parcel.writeInt(Publicacion)
        parcel.writeInt(Paginas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
}