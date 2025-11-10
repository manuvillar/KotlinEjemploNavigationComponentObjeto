package es.iesoretania.kotlinejemplonavigationcomponentobjeto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.iesoretania.kotlinejemplonavigationcomponentobjeto.databinding.ActivityMainBinding

/**
 * Este es el MainActivity, el punto de entrada de nuestra aplicación.
 *
 * ¿Qué hace este Activity?
 * - Es el contenedor principal que aloja nuestros fragments
 * - Configura el ViewBinding para acceder a las vistas de forma segura
 * - Gestiona los insets (márgenes) para que el contenido no se solape con las barras del sistema
 */
class MainActivity : AppCompatActivity() {

    // ViewBinding: nos permite acceder a las vistas del layout de forma segura y sin findViewById
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // enableEdgeToEdge(): permite que nuestra app use toda la pantalla (incluso detrás de barras)
        enableEdgeToEdge()

        // Inicializamos ViewBinding: inflamos el layout y lo convertimos en objeto binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Establecemos la vista raíz como contenido de la Activity
        setContentView(binding.root)

        /**
         * WindowInsets: Gestión de márgenes para las barras del sistema
         *
         * ¿Qué son los insets?
         * - Son los espacios ocupados por barras del sistema (barra de estado, navegación, etc.)
         * - Necesitamos aplicar padding para que nuestro contenido no quede oculto debajo
         *
         * ¿Por qué usamos binding.main?
         * - Accedemos directamente al ConstraintLayout con id="main" desde el binding
         * - Es más seguro que findViewById porque el compilador verifica que existe
         */
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            // Obtenemos las dimensiones de las barras del sistema
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Aplicamos padding para que el contenido no se solape con las barras
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Devolvemos los insets para que otros listeners también puedan usarlos
            insets
        }
    }
}