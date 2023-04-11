package com.app.commerse.commerseapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.commerse.commerseapp.models.WatchModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {

    var listWatches: MutableLiveData<ArrayList<WatchModel>> =MutableLiveData()
    var watches: ArrayList<WatchModel>? = null
    fun getWatchList(category: String) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("watches")
        watches = ArrayList<WatchModel>()

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (areaSnapshot in snapshot.getChildren()) {
                    watches?.add(
                        WatchModel(
                            areaSnapshot.child("name").value.toString(),
                            areaSnapshot.child("series").value.toString(),
                            areaSnapshot.child("category").value.toString(),
                            areaSnapshot.child("price").value.toString().toInt()
                        )
                    )

                }
                listWatches.value=watches

                Log.d("TAG", "Value is: " + watches?.size)
            }

            override fun onCancelled(error: DatabaseError) {
                listWatches.value=null
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }


}