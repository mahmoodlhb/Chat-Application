package com.example.chatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var database= FirebaseDatabase.getInstance();
        var databaseRef = database.getReference("messages").push();

       // databaseRef.setValue("from android project");

        var newP=Person("mahmood",30,"USA")
        databaseRef.setValue(newP);

        //read from database
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               // var data = snapshot.value;
                var data = snapshot.value as HashMap<String,Any>
                //Toast.makeText(applicationContext,data.toString(), Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext,data.get("name").toString(), Toast.LENGTH_LONG).show()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("Error", error.message)
            }


        })
    }
}
data class Person(
    var name:String,
    var age:Int,
    var address:String
)