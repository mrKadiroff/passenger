package com.shoxrux.passenger_route.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shoxrux.passenger_route.R
import com.shoxrux.passenger_route.adapters.CityAdapter
import com.shoxrux.passenger_route.adapters.ConducterAdapter
import com.shoxrux.passenger_route.database.AppDatabase
import com.shoxrux.passenger_route.database.entity.CityEntity
import com.shoxrux.passenger_route.database.entity.ConducterEntity
import com.shoxrux.passenger_route.database.entity.RouteEntity
import com.shoxrux.passenger_route.databinding.FragmentCityBinding
import com.shoxrux.passenger_route.databinding.FragmentConductBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityFragment : Fragment() {
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

    lateinit var binding: FragmentCityBinding
    lateinit var appDatabase: AppDatabase
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var cityAdapter: CityAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityBinding.inflate(layoutInflater)
        appDatabase = AppDatabase.getInstance(binding.root.context)

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")




        cityAdapter = CityAdapter(object :CityAdapter.OnItemClickListener{
            override fun onItemClick(cityEntity: CityEntity) {
                    val alertDialog = AlertDialog.Builder(binding.root.context)
                    val dialog = alertDialog.create()
                    val dialogView =
                        com.shoxrux.passenger_route.databinding.MyDialogBinding.inflate(LayoutInflater.from(binding.root.context),null,false)


                    var same = false
                    dialogView.save.setOnClickListener {

                        val conducterEntity = ConducterEntity()
                        val name =dialogView.name.text.toString()
                        conducterEntity.surname = dialogView.surnamme.text.toString()
                        conducterEntity.logincha = dialogView.logincha.text.toString()
                        val password  = dialogView.parol.text.toString()
                        conducterEntity.shahrcha = cityEntity.cityy
                        appDatabase.conducterDao().addConducter(conducterEntity)

                        val allRoute = appDatabase.routeDao().getAllRoute(cityEntity.cityy!!)
                        val list = ArrayList<List<RouteEntity>>()
                        list.add(allRoute)


                        val key = reference.push().key
            reference.child("$password/information/$key")
                .setValue(allRoute)

                        dialog.dismiss()








                    }

                    dialogView.cancel.setOnClickListener {
                        dialog.dismiss()
                    }


                    dialog.setView(dialogView.root)
                    dialog.show()

                    true



            }

        })

        appDatabase.cityDao().getAllCityDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Consumer<List<CityEntity>> {
                override fun accept(t: List<CityEntity>?) {
                    cityAdapter.submitList(t)
                }

            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {

                }

            })
        binding.rv.adapter = cityAdapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CityFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}