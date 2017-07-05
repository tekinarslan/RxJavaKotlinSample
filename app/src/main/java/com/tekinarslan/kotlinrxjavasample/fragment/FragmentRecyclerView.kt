package com.tekinarslan.kotlinrxjavasample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tekinarslan.kotlinrxjavasample.R
import com.tekinarslan.kotlinrxjavasample.adapter.DividerItemDecoration
import com.tekinarslan.kotlinrxjavasample.adapter.RecyclerAdapter
import com.tekinarslan.kotlinrxjavasample.core.CoreApp
import com.tekinarslan.kotlinrxjavasample.model.PhotosDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recycler_view.*
/**
 * Created by selimtekinarslan on 6/29/2017.
 */

class FragmentRecyclerView : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var adapter: RecyclerAdapter
    var items: ArrayList<PhotosDataModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        runLoadingView()
        fetchData()
    }

    fun runLoadingView() {
        swipe_refresh_layout.post({ swipe_refresh_layout.isRefreshing = true })
    }

    fun stopLoadingView() {
        swipe_refresh_layout.post({ swipe_refresh_layout.isRefreshing = false })
    }

    fun bindViews() {
        swipe_refresh_layout.setOnRefreshListener(this)
        adapter = RecyclerAdapter(items)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.addItemDecoration(DividerItemDecoration(20))
    }

    fun fetchData() {
        CoreApp.instance?.getNetworkManager()
                ?.service
                ?.getData()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        object : DisposableObserver<ArrayList<PhotosDataModel>>() {
                            override fun onNext(response: ArrayList<PhotosDataModel>) {
                                adapter.setItems(response)
                                adapter.notifyDataSetChanged()
                                stopLoadingView()
                            }

                            override fun onError(e: Throwable) {
                                stopLoadingView()
                            }

                            override fun onComplete() {
                                Log.e("", "")
                            }
                        }
                )

    }

    override fun onRefresh() {
        fetchData()
    }
}