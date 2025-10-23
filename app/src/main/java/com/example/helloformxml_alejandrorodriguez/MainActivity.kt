package com.example.helloformxml_alejandrorodriguez

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var etNombre: EditText
    private lateinit var btnSaludar: Button
    private lateinit var tvMensaje: TextView
    private lateinit var tvContador: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //asignar los elementos del layout
        etNombre = findViewById(R.id.etNombre)
        btnSaludar = findViewById(R.id.btnSaludar)
        tvMensaje = findViewById(R.id.tvMensaje)
        tvContador = findViewById(R.id.tvContador)

        //habilitar botón + contador para el texto
        etNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val texto = s.toString().trim()
                btnSaludar.isEnabled = texto.isNotEmpty()
                tvContador.text = "${texto.length} / 20"
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        //Evento del botón
        btnSaludar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()

            if (nombre.isEmpty()) {
                tvMensaje.text = "Introduce tu nombre"
            } else {
                tvMensaje.text = "👋 Hola, $nombre"
            }

            //Ocultar el teclado
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(etNombre.windowToken, 0)
        }
    }

    //Resetear la UI al cerrar o salir de la app
    override fun onStop() {
        super.onStop()
        resetUi()
    }

    private fun resetUi() {
        etNombre.text.clear()
        tvMensaje.text = ""
        tvContador.text = "0 / 20"
        btnSaludar.isEnabled = false
    }
}