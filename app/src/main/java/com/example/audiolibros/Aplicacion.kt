package com.example.audiolibros

import android.app.Application
import android.graphics.Bitmap
import android.util.LruCache

import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

import com.example.audiolibros.ejemploLibros

class Aplicacion : Application() {

    lateinit var listaLibros: MutableList<Libro> // añado private
        private set
    var adaptador: AdaptadorLibrosFiltro? = null // añado private
        private set
    var colaPeticiones: RequestQueue? = null
        private set
    var lectorImagenes: ImageLoader? = null
        private set

    override fun onCreate() {
        super.onCreate()
        listaLibros = ejemploLibros() as MutableList<Libro>
        adaptador = AdaptadorLibrosFiltro(this, listaLibros)
        colaPeticiones = Volley.newRequestQueue(this)
        lectorImagenes = ImageLoader(colaPeticiones,
                object : ImageLoader.ImageCache {
                    private val cache = LruCache<String, Bitmap?>(10) // añado ?

                    override fun putBitmap(url: String, bitmap: Bitmap) {
                        cache.put(url, bitmap)
                    }

                    override fun getBitmap(url: String): Bitmap? { // añado ?
                        return cache.get(url)
                    }
                })
    }

//    companion object {
//        var colaPeticiones: RequestQueue? = null
//            private set
//        var lectorImagenes: ImageLoader? = null
//            private set
//    }
}
