package com.example.jonaschrtest_002


import android.os.AsyncTask
import android.arch.lifecycle.MutableLiveData
import kotlin.collections.List
import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.jonaschrtest_002.Models.Product

class ProductRepository(application: Application) {

    val searchResults = MutableLiveData<List<Product>>()
    val allProducts: LiveData<List<Product>>?

    private var productDao: ProductDao?

    init {
        val db: ProductRoomDatabase? =
            ProductRoomDatabase.getDatabase(application)
        productDao = db?.productDao()
        allProducts = productDao?.getAllProducts()
    }

    fun insertProduct(newproduct: Product) {
        val task = InsertAsyncTask(productDao)
        task.execute(newproduct)
    }

    fun deleteProduct(name: String) {
        val task = DeleteAsyncTask(productDao)
        task.execute(name)
    }

    fun findProduct(name: String) {
        val task = QueryAsyncTask(productDao)
        task.delegate = this
        task.execute(name)
    }

    fun asyncFinished(results: List<Product>) {
        searchResults.value = results
    }

    private class QueryAsyncTask constructor(val asyncTaskDao: ProductDao?) :
        AsyncTask<String, Void, List<Product>>() {
        var delegate: ProductRepository? = null

        override fun doInBackground(vararg params: String): List<Product>? {
            return asyncTaskDao?.findProduct(params[0])
        }

        override fun onPostExecute(result: List<Product>) {
            delegate?.asyncFinished(result)
        }
    }

    private class InsertAsyncTask constructor(private val asyncTaskDao: ProductDao?) : AsyncTask<Product, Void, Void>() {

        override fun doInBackground(vararg params: Product): Void? {
            asyncTaskDao?.insertProduct(params[0])
            return null
        }
    }

    private class DeleteAsyncTask constructor(private val asyncTaskDao: ProductDao?) : AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg params: String): Void? {
            asyncTaskDao?.deleteProduct(params[0])
            return null
        }
    }

}