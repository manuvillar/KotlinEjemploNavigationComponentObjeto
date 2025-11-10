package es.iesoretania.kotlinejemplonavigationcomponentobjeto

import android.os.Parcel
import android.os.Parcelable

/**
 * Esta es nuestra clase modelo Libro, que representa los datos de un libro.
 *
 * ¿Por qué usamos data class?
 * - En Kotlin, cuando una clase solo guarda datos, usamos "data class"
 * - Nos genera automáticamente métodos útiles como toString(), equals(), copy()
 * - Es más limpio y conciso que usar "class" normal
 *
 * ¿Por qué implementa Parcelable?
 * - Parcelable nos permite enviar objetos entre Activities o Fragments
 * - Navigation Component lo necesita para pasar objetos entre pantallas
 * - Es más eficiente que Serializable en Android
 */
data class Libro(
    var titulo: String?,      // Usamos camelCase (minúsculas al principio) por convención Kotlin
    var publicacion: Int,     // Año de publicación del libro
    var paginas: Int          // Número de páginas
) : Parcelable {

    /**
     * Constructor secundario que lee los datos desde un Parcel
     * Parcel es como un contenedor que empaqueta nuestros datos para enviarlos
     */
    constructor(parcel: Parcel) : this(
        parcel.readString(),      // Leemos el título
        parcel.readInt(),         // Leemos la publicación
        parcel.readInt()          // Leemos las páginas
    )

    /**
     * Este método escribe nuestros datos en el Parcel para poder enviarlos
     * IMPORTANTE: El orden al escribir debe ser el mismo que al leer
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeInt(publicacion)
        parcel.writeInt(paginas)
    }

    /**
     * Este método indica si hay descriptores de archivos especiales
     * Normalmente devolvemos 0 (no hay descriptores especiales)
     */
    override fun describeContents(): Int = 0

    /**
     * Companion object CREATOR: Es necesario para que Android sepa cómo crear objetos Libro
     * desde un Parcel. Es parte obligatoria de la implementación de Parcelable
     */
    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro = Libro(parcel)
        override fun newArray(size: Int): Array<Libro?> = arrayOfNulls(size)
    }
}