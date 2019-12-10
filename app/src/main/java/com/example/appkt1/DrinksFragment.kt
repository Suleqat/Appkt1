package com.example.appkt1

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.drinks_fragment.*


class DrinksFragment : Fragment() {

    companion object {
        fun newInstance() = DrinksFragment()
    }

    private lateinit var viewModel: DrinksViewModel
    private lateinit var  factory: DrinksViewModelfactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drinks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api= DrinksApi()
        val repository = DrinksRepository(api)

        factory = DrinksViewModelfactory(repository)
        viewModel = ViewModelProviders.of(this,factory
        ).get(DrinksViewModel::class.java)

        viewModel.getDrinks()

        viewModel.drinks.observe(viewLifecycleOwner, Observer { drinks ->
            recycler_view_drinks.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = DrinksAdapter(drinks) //, this)
            }
        })


    }

}
