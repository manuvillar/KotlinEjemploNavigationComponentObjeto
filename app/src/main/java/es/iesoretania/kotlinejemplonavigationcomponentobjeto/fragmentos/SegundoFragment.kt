package es.iesoretania.kotlinejemplonavigationcomponentobjeto.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import es.iesoretania.kotlinejemplonavigationcomponentobjeto.databinding.FragmentSegundoBinding

/**
 * Este es el SegundoFragment, la pantalla donde mostramos los datos del libro recibidos.
 *
 * ¿Qué aprenderéis aquí?
 * - Cómo recibir argumentos (objetos) desde otro fragment
 * - Cómo mostrar datos en TextViews
 * - Cómo navegar hacia atrás en la pila de navegación
 * - Uso del operador Elvis (?:) para valores por defecto
 */
class SegundoFragment : Fragment() {

    // ViewBinding para acceder a las vistas del fragment
    private lateinit var binding: FragmentSegundoBinding

    /**
     * onCreate: Se ejecuta al crear el fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * onCreateView: Inflamos el layout del fragment
     * Convertimos el XML en objetos View mediante ViewBinding
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSegundoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    /**
     * onViewCreated: Aquí configuramos las vistas y mostramos los datos recibidos
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenemos el NavController para poder navegar
        val navController = Navigation.findNavController(view)

        /**
         * Recibimos los argumentos con Safe Args
         *
         * ¿Qué es Safe Args?
         * - Es un plugin de Navigation Component que genera clases para pasar datos de forma segura
         * - En vez de usar Bundle manualmente, usamos las clases generadas automáticamente
         *
         * SegundoFragmentArgs.fromBundle():
         * - Extrae los argumentos del Bundle de forma automática
         * - Es type-safe: el compilador verifica que los tipos son correctos
         */
        val args = SegundoFragmentArgs.fromBundle(requireArguments())

        // Extraemos el objeto Libro de los argumentos
        val registro = args.Libro

        /**
         * Mostramos los datos en los TextViews
         *
         * ¿Qué es el operador Elvis (?:)?
         * - Se usa cuando un valor puede ser null
         * - Si titulo es null, se usa el valor después de "?:"
         * - Ejemplo: registro.titulo ?: "Título no disponible"
         *
         * ¿Por qué toString()?
         * - Los TextViews solo aceptan String
         * - Los Int necesitan convertirse a String con toString()
         */
        binding.tvTitulo.text = registro.titulo ?: "Título no disponible"
        binding.tvPublicacion.text = registro.publicacion.toString()
        binding.tvPaginas.text = registro.paginas.toString()

        /**
         * Configuramos el botón "Volver"
         *
         * navigateUp():
         * - Navega hacia atrás en la pila de navegación
         * - Es como pulsar el botón "atrás" del sistema
         * - Vuelve al fragment anterior (PrimerFragment)
         */
        binding.botonFinal.setOnClickListener {
            navController.navigateUp()
        }
    }
}