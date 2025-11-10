package es.iesoretania.kotlinejemplonavigationcomponentobjeto.fragmentos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import es.iesoretania.kotlinejemplonavigationcomponentobjeto.Libro
import es.iesoretania.kotlinejemplonavigationcomponentobjeto.databinding.FragmentPrimerBinding

/**
 * Este es el PrimerFragment, la pantalla inicial donde el usuario introduce datos del libro.
 *
 * ¿Qué aprenderéis aquí?
 * - Cómo usar ViewBinding en Fragments
 * - Cómo validar datos de entrada del usuario
 * - Cómo manejar errores (try-catch)
 * - Cómo navegar entre fragments pasando objetos
 * - Cómo crear funciones de extensión (extension functions)
 */
class PrimerFragment : Fragment() {

    // ViewBinding para acceder a las vistas del fragment de forma segura
    private lateinit var binding: FragmentPrimerBinding

    /**
     * onCreate: Se ejecuta al crear el fragment
     * Aquí podríamos inicializar datos, pero no tenemos acceso aún a las vistas
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * onCreateView: Aquí inflamos el layout y lo convertimos en vistas
     *
     * ¿Qué es inflar?
     * - Convertir el XML en objetos View que Android puede mostrar en pantalla
     *
     * Parámetros importantes:
     * - inflater: el objeto que convierte XML en vistas
     * - container: el contenedor padre donde se colocará este fragment
     * - false: indica que NO queremos adjuntar la vista al contenedor aún (lo hace el sistema)
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicializamos el binding inflando el layout del fragment
        binding = FragmentPrimerBinding.inflate(inflater, container, false)

        // Devolvemos la vista raíz para que Android la muestre
        return binding.root
    }

    /**
     * onViewCreated: Se ejecuta DESPUÉS de crear las vistas
     * Aquí es donde configuramos listeners, accedemos a las vistas, etc.
     * Es el lugar perfecto para programar la lógica de interacción con el usuario
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenemos el NavController para poder navegar entre fragments
        val navController = view.findNavController()

        /**
         * Configuramos el click del botón "Segunda Pantalla"
         * Cuando el usuario pulse, validaremos los datos y navegaremos al siguiente fragment
         */
        binding.botonSegundo.setOnClickListener {

            // PASO 1: Validación de campos vacíos
            if (!camposValidos()) {
                requireContext().muestraToast("Debes introducir un valor válido en todos los campos")
            } else {
                // PASO 2: Intentamos crear el objeto Libro con los datos introducidos
                try {
                    // Creamos un objeto Libro con los datos de los EditText
                    val registro = Libro(
                        binding.edTitulo.text.toString(),
                        binding.edPublicacion.text.toString().toInt(),  // Convertimos String a Int
                        binding.edPaginas.text.toString().toInt()
                    )

                    // PASO 3: Creamos la acción de navegación con Safe Args
                    // Safe Args nos permite pasar objetos de forma segura entre fragments
                    val action = PrimerFragmentDirections.actionPrimerFragmentToSegundoFragment(registro)

                    // PASO 4: Navegamos al siguiente fragment
                    navController.navigate(action)

                    // Mostramos un Toast confirmando el envío
                    requireContext().muestraToast("Libro enviado: ${registro.titulo}")

                } catch (e: NumberFormatException) {
                    requireContext().muestraToast("Publicación y páginas deben ser números válidos")
                }
            }
        }
    }

    /**
     * Función privada que valida si los campos tienen contenido
     *
     * ¿Por qué usamos trim()?
     * - Elimina espacios en blanco al principio y final
     * - Así evitamos que el usuario solo ponga espacios y pase la validación
     *
     * Devuelve true si TODOS los campos tienen contenido, false si alguno está vacío
     */
    private fun camposValidos(): Boolean =
        binding.edTitulo.text.toString().trim().isNotEmpty() &&
                binding.edPublicacion.text.toString().trim().isNotEmpty() &&
                binding.edPaginas.text.toString().trim().isNotEmpty()

    /**
     * Extension function (función de extensión): Añade funcionalidad a la clase Context
     *
     * ¿Qué son las extension functions?
     * - Permiten añadir métodos a clases existentes sin modificarlas
     * - Hacen el código más limpio y reutilizable
     *
     * ¿Cómo se usa?
     * - Ahora cualquier Context puede llamar a .muestraToast()
     * - Ejemplo: requireContext().muestraToast("Hola")
     *
     * Parámetros:
     * - message: el texto que se mostrará
     * - duration: cuánto tiempo se mostrará (por defecto SHORT = 2 segundos)
     */
    private fun Context.muestraToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}