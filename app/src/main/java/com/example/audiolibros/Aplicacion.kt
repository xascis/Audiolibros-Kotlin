package com.example.audiolibros

import android.app.Application
import android.graphics.Bitmap
import android.util.LruCache

import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

import com.example.audiolibros.ejemploLibros

class Aplicacion : Application() {

    var listaLibros: List<Libro>? = null // añado private
        private set
    var adaptador: AdaptadorLibrosFiltro? = null // añado private
        private set

    override fun onCreate() {
        super.onCreate()
        listaLibros = ejemploLibros()
        adaptador = AdaptadorLibrosFiltro(this, listaLibros)
        colaPeticiones = Volley.newRequestQueue(this)
        lectorImagenes = ImageLoader(colaPeticiones,
                object : ImageLoader.ImageCache {
                    private val cache = LruCache<String, Bitmap?>(10) // añado ?

                    override fun putBitmap(url: String, bitmap: Bitmap) {
                        cache?.put(url, bitmap) // añado ?
                    }

                    override fun getBitmap(url: String): Bitmap? { // añado ?
                        return cache?.get(url) // añado ?
                    }
                })
    }

    companion object {
        var colaPeticiones: RequestQueue? = null
            private set
        var lectorImagenes: ImageLoader? = null
            private set
    }
}
