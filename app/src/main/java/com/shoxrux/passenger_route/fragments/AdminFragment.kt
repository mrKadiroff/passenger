package com.shoxrux.passenger_route.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shoxrux.passenger_route.R
import com.shoxrux.passenger_route.adapters.ConducterAdapter
import com.shoxrux.passenger_route.adapters.RouteAdapter
import com.shoxrux.passenger_route.database.AppDatabase
import com.shoxrux.passenger_route.database.entity.CityEntity
import com.shoxrux.passenger_route.database.entity.ConducterEntity
import com.shoxrux.passenger_route.database.entity.RouteEntity
import com.shoxrux.passenger_route.databinding.FragmentAdminBinding
import com.shoxrux.passenger_route.repository.UserRepository
import com.shoxrux.passenger_route.utils.NetworkHelper
import com.shoxrux.passenger_route.viewmodels.UserViewModel
import com.shoxrux.passenger_route.viewmodels.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment() {
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

    lateinit var binding: FragmentAdminBinding
    lateinit var appDatabase: AppDatabase
    lateinit var userViewModel: UserViewModel
    lateinit var routeAdapter: RouteAdapter
    lateinit var conducterAdapter: ConducterAdapter
    private val TAG = "AdminFragment"

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminBinding.inflate(layoutInflater)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        val userRepository = UserRepository(AppDatabase.getInstance(binding.root.context))
        val networkHelper = NetworkHelper(binding.root.context )
        userViewModel = ViewModelProvider(this, ViewModelFactory(userRepository,networkHelper))[UserViewModel::class.java]



        setRoute()


        firebaseDatabase = FirebaseDatabase.getInstance()
//        reference = firebaseDatabase.getReference("cities")




        binding.savee.setOnClickListener {
            val city = binding.city.text.toString()
            val allRoute = appDatabase.routeDao().getAllRoute(binding.city.text.toString())
            val allCon = appDatabase.conducterDao().getAllCon(binding.city.text.toString())



            val cityEntity = CityEntity()
            cityEntity.cityy = city
            appDatabase.cityDao().addCity(cityEntity)
            findNavController().navigate(R.id.cityFragment)

//            val key = reference.push().key
//            reference.child("$city/information/$key")
//                .setValue(allRoute)
//
//            reference.child("$city/conducter/$key")
//                .setValue(allCon)
        }





//        GlobalScope.launch(Dispatchers.Main) {
//            userViewModel.getRoute()
//                .observe(viewLifecycleOwner) {
//
//
//                    when (it.status) {
//                        Status.LOADING -> {
//
//                        }
//
//                        Status.ERROR -> {
//                            Log.d(TAG, "onCreateView: ${it.message}")
//                        }
//
//                        Status.SUCCESS -> {
//
//                            Log.d(TAG, "onCreateView: ${it.data}")
//
//
//                        }
//                    }
//                }}



        return binding.root
    }

    private fun setConducter() {
//        binding.admin.setOnClickListener {
//                    val alertDialog = AlertDialog.Builder(binding.root.context)
//                    val dialog = alertDialog.create()
//                    val dialogView =
//                        com.shoxrux.passenger_route.databinding.MyDialogBinding.inflate(LayoutInflater.from(binding.root.context),null,false)
//
//
//                    var same = false
//                    dialogView.save.setOnClickListener {
//
//                        val conducterEntity = ConducterEntity()
//                        conducterEntity.name = dialogView.name.text.toString()
//                        conducterEntity.surname = dialogView.surnamme.text.toString()
//                        conducterEntity.logincha = dialogView.logincha.text.toString()
//                        conducterEntity.parol = dialogView.parol.text.toString()
//                        conducterEntity.shahrcha = binding.city.text.toString()
//                        appDatabase.conducterDao().addConducter(conducterEntity)
//
//                        conducterAdapter = ConducterAdapter()
//
//                        appDatabase.conducterDao().getAllConducter(binding.city.text.toString())
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(object: Consumer<List<ConducterEntity>> {
//                                override fun accept(t: List<ConducterEntity>?) {
//                                    conducterAdapter.submitList(t)
//                                }
//
//                            }, object : Consumer<Throwable> {
//                                override fun accept(t: Throwable?) {
//
//                                }
//
//                            })
//                        binding.conducterRv.adapter = conducterAdapter
//
//
//                        dialog.dismiss()
//
//
//
//
//
//
//
//
//                    }
//
//                    dialogView.cancel.setOnClickListener {
//                        dialog.dismiss()
//                    }
//
//
//                    dialog.setView(dialogView.root)
//                    dialog.show()
//
//                true
//
//
//        }
    }

    private fun setRoute() {
        binding.addRoute.setOnClickListener {
            val route = binding.routes.text.toString()
            val city = binding.city.text.toString()
            val routeEntity = RouteEntity()
            routeEntity.marshrut = route
            routeEntity.shahr = city
            appDatabase.routeDao().addRoute(routeEntity)


            routeAdapter = RouteAdapter()


//            val city = binding.city.text.toString()
            appDatabase.routeDao().getAllRouteDb(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Consumer<List<RouteEntity>> {
                    override fun accept(t: List<RouteEntity>?) {
                        routeAdapter.submitList(t)
                    }

                }, object : Consumer<Throwable> {
                    override fun accept(t: Throwable?) {

                    }

                })
            binding.routeRv.adapter = routeAdapter

        }




    }

    override fun onResume() {
        super.onResume()


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdminFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdminFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}