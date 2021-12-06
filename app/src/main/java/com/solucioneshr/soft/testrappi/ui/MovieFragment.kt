package com.solucioneshr.soft.testrappi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solucioneshr.soft.testrappi.R
import com.solucioneshr.soft.testrappi.data.Movie
import com.solucioneshr.soft.testrappi.viewmodel.FunViewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {
    private lateinit var vm: FunViewModels
    private lateinit var next: FunViewModels
    private lateinit var adapter: MovieAdapter
    private lateinit var rv_home: RecyclerView
    private lateinit var layout_progress: CardView
    private lateinit var button_next: FloatingActionButton
    private lateinit var layout_text_total_pages: CardView
    private lateinit var button_ff: FloatingActionButton
    private lateinit var total_pages: TextView
    private var index_page: Int = 0

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val theView = inflater.inflate(R.layout.fragment_movie, container, false)

        rv_home = theView.findViewById(R.id.rv_home)
        layout_progress = theView.findViewById(R.id.layoutProgress)

        button_ff = theView.findViewById(R.id.buttonFF)
        button_ff.setOnClickListener(View.OnClickListener {
            layout_progress.visibility = View.VISIBLE
            index_page --
            next.getPageMovieTopRate(index_page.toString())
            next.getPageTopRateModelLiveData?.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    total_pages.text = it.page.toString() + " de " + it.total_pages.toString()
                    adapter.setData(it.results as ArrayList<Movie>)
                } else {
                    showToast("Something went wrong")
                }
                layout_progress.visibility = View.GONE

                if (it.page > 1){
                    button_ff.visibility = View.VISIBLE
                } else{
                    button_ff.visibility = View.INVISIBLE
                }
            })
        })

        layout_text_total_pages = theView.findViewById(R.id.layoutTextTotalPage)
        total_pages = theView.findViewById(R.id.textTotalPage)

        button_next = theView.findViewById(R.id.buttonNext)
        button_next.setOnClickListener(View.OnClickListener {
            layout_progress.visibility = View.VISIBLE
            index_page ++
            next.getPageMovieTopRate(index_page.toString())
            next.getPageTopRateModelLiveData?.observe(viewLifecycleOwner, Observer{
                if (it != null){
                    total_pages.text = it.page.toString() + " de " + it.total_pages.toString()
                    adapter.setData(it.results as ArrayList<Movie>)
                } else{
                    showToast("Something went wrong")
                }
                layout_progress.visibility = View.GONE
                button_ff.visibility = View.VISIBLE

            })
        })

        vm = ViewModelProvider(this)[FunViewModels::class.java]

        next = ViewModelProvider(this)[FunViewModels::class.java]

        initAdapter()

        vm.getMovieTopRate()

        vm.getModelLiveData?.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                rv_home.visibility = View.VISIBLE
                adapter.setData(it.results as ArrayList<Movie>)
            }else{
                showToast("Something went wrong")
            }
            layout_progress.visibility = View.GONE
            if (it.total_pages > 1) {
                button_next.visibility = View.VISIBLE
                layout_text_total_pages.visibility = View.VISIBLE
                val cantPages = it.total_pages - index_page
                total_pages.text = it.page.toString() + " de " + it.total_pages.toString()
                index_page = it.page
            }
        })

        return theView
    }

    private fun initAdapter() {
        adapter = MovieAdapter(this)
        // rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.setHasFixedSize(true)
        rv_home.layoutManager = GridLayoutManager(context,2)
        rv_home.adapter = adapter
    }

    private fun showToast(msg:String){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}