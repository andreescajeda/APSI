package com.andreescajedarios.apsi

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andreescajedarios.apsi.data_classes.Records
import com.andreescajedarios.apsi.databinding.ItemRecordsBinding
import com.andreescajedarios.apsi.history.RecordsViewModel
import com.andreescajedarios.apsi.history.SearchString
import com.andreescajedarios.apsi.history.SearchStringViewModel
import com.andreescajedarios.apsi.retrofit.RetrofitFactory
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_search_string.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        adapter = GroupAdapter()

        ViewModelProvider(this, InjectorUtils.provideSearchStringViewModelFactory(this))
            .get(SearchStringViewModel::class.java).apply {
                selectAll().observe(this@MainActivity, Observer<MutableList<SearchString>> {
                    adapter.clear()

                    it.forEach {
                        adapter.add(SearchStringItem(it))
                    }

                    recyclerView.adapter = adapter
                })
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val searchItem = menu.findItem(R.id.search_item)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean = true

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                ViewModelProvider(
                    this@MainActivity,
                    InjectorUtils.provideSearchStringViewModelFactory(this@MainActivity)
                )
                    .get(SearchStringViewModel::class.java).apply {
                        selectAll().observe(this@MainActivity, Observer<MutableList<SearchString>> {
                            adapter.clear()

                            it.forEach {
                                adapter.add(SearchStringItem(it))
                            }

                            recyclerView.adapter = adapter
                        })
                    }

                return false
            }
        })

        val searchView = menu.findItem(R.id.search_item).actionView as SearchView
        searchView.apply {
            queryHint = "Buscar…"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(p0: String): Boolean = false

                override fun onQueryTextSubmit(p0: String): Boolean {
                    val progressDialog = ProgressDialog(this@MainActivity).apply {
                        isIndeterminate = true
                        title = "Cargando…"
                        show()
                    }

                    ViewModelProvider(
                        this@MainActivity,
                        InjectorUtils.provideSearchStringViewModelFactory(this@MainActivity)
                    )
                        .get(SearchStringViewModel::class.java).apply {
                            insert(SearchString(p0))
                        }

                    CoroutineScope(Dispatchers.IO).launch {
                        val response =
                            RetrofitFactory.makeRetrofitService().plp(true, p0, "1", "10")

                        withContext(Dispatchers.Main) {
                            try {
                                if (response.isSuccessful && response.body() != null) {
                                    adapter.clear()

                                    val records = response.body()!!.plpResults?.records

                                    records?.forEach {
                                        adapter.add(RecordsItem(it).apply { setRecords(it) })
                                    }

                                    recyclerView.adapter = adapter

                                    progressDialog.dismiss()
                                } else {
                                    Log.d("MainActivity", response.code().toString())
                                }
                            } catch (e: HttpException) {
                                e.printStackTrace()
                            }
                        }
                    }

                    return false
                }
            })
        }

        return true
    }

    class RecordsItem(private val records: Records?) : BindableItem<ItemRecordsBinding>() {

        private val recordsViewModel = RecordsViewModel()

        override fun getLayout(): Int = R.layout.item_records

        override fun bind(viewBinding: ItemRecordsBinding, position: Int) {
            viewBinding.record = recordsViewModel

            Picasso.get().load(records?.xlImage).into(viewBinding.itemRecordsImageView)
        }

        fun setRecords(records: Records?) {
            recordsViewModel.apply {
                productDisplayName.set(records?.productDisplayName)
                listPrice.set("$${records?.listPrice}")
            }
        }
    }

    class SearchStringItem(private val searchString: SearchString) : Item() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.item_search_string_textView.text = searchString.searchString
        }

        override fun getLayout(): Int = R.layout.item_search_string
    }
}
