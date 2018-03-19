package com.example.audiolibros

import android.content.Context

import java.util.ArrayList
import java.util.Vector

class AdaptadorLibrosFiltro(contexto: Context,
                            private val vectorSinFiltro: MutableList<Libro>// Vector con todos los libros
) : AdaptadorLibros(contexto, vectorSinFiltro) {
    private var indiceFiltro: List<Int>? = null // Índice en vectorSinFiltro de
    // Cada elemento de listaLibros
    private var busqueda = ""         // Búsqueda sobre autor o título
    private var genero = ""           // Género seleccionado
    private var novedad = false      // Si queremos ver solo novedades
    private var leido = false        // Si queremos ver solo leidos

    init {
        recalculaFiltro()
    }

    fun setBusqueda(busqueda: String) {
        this.busqueda = busqueda.toLowerCase()
        recalculaFiltro()
    }

    fun setGenero(genero: String) {
        this.genero = genero
        recalculaFiltro()
    }

    fun setNovedad(novedad: Boolean) {
        this.novedad = novedad
        recalculaFiltro()
    }

    fun setLeido(leido: Boolean) {
        this.leido = leido
        recalculaFiltro()
    }

    fun recalculaFiltro() {
        listaLibros = ArrayList()
        indiceFiltro = ArrayList()
        for (i in vectorSinFiltro.indices) {
            val libro = vectorSinFiltro[i]
            if ((libro.titulo.toLowerCase().contains(busqueda) || libro.autor.toLowerCase().contains(busqueda))
                    && libro.genero.startsWith(genero)
                    && (!novedad || novedad && libro.novedad!!)
                    && (!leido || leido && libro.leido!!)) {
                (listaLibros as ArrayList<Libro>).add(libro)
                (indiceFiltro as ArrayList<Int>).add(i)
            }
        }
    }

    fun getItem(posicion: Int): Libro {
        return vectorSinFiltro[indiceFiltro!![posicion]]
    }

    override fun getItemId(posicion: Int): Long {
        return indiceFiltro!![posicion].toLong()
    }

    fun borrar(posicion: Int) {
        vectorSinFiltro.removeAt(getItemId(posicion).toInt())
        recalculaFiltro()
    }

    fun insertar(libro: Libro) {
        vectorSinFiltro.add(libro)
        recalculaFiltro()
    }
}
