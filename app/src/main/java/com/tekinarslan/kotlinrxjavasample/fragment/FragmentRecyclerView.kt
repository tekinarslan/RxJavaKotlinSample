package com.tekinarslan.kotlinrxjavasample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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


/**
 * Created by selimtekinarslan on 6/29/2017.
 */

class FragmentRecyclerView : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var recyclerView: RecyclerView? = null
    var adapter: RecyclerAdapter? = null
    var items: ArrayList<PhotosDataModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view!!)
        runLoadingView()
        fetchData()
    }

    fun runLoadingView() {
        swipeRefreshLayout?.post({ swipeRefreshLayout?.isRefreshing = true })
    }

    fun stopLoadingView() {
        swipeRefreshLayout?.post({ swipeRefreshLayout?.isRefreshing = false })
    }

    fun bindViews(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView?
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout?
        swipeRefreshLayout?.setOnRefreshListener(this)
        adapter = RecyclerAdapter(items)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.addItemDecoration(DividerItemDecoration(20))
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
                                adapter?.setItems(response)
                                adapter?.notifyDataSetChanged()
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