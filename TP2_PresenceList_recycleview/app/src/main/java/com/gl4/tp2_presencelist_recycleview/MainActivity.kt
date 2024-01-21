package com.gl4.tp2_presencelist_recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var searchText:String=""
    private lateinit var studentAdapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentList = mutableListOf(
            Student(0,"Alice", "Smith",true),
            Student(1 ,"Bob", "Johnson",true),
            Student(2 ,"Charlie", "Brown",true),
            Student(3 ,"David", "Miller",true),
            Student(4 ,"Eva", "Wilson",true),
            Student(5 ,"Frank", "Taylor",false),
            Student(6 ,"Grace", "Martin",true),
            Student(7 ,"Hank", "Anderson",false),
            Student(8 ,"Ivy", "Clark",false),
            Student(9 ,"Jack", "Moore",false),
            Student(10 ,"Katie", "Adams",false),
            Student(11,"Liam", "Walker",false),
            Student(12 ,"Mia", "Cooper",false),
            Student(13,"Noah", "Parker",false),
            Student(14 ,"Olivia", "Baker",false),
            Student(15 ,"Patrick", "Hall",false),
            Student(16 ,"Quinn", "Young",false),
            Student(17 ,"Rachel", "Hill",false),
            Student(18 ,"Samuel", "Ward",false),
            Student(19 ,"Taylor", "Fisher",false))
        var states=listOf<String>("All","Present","Absent")
        var filteredlist= mutableListOf<Student>()
        var posState=0
        spinnerSelect.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,states)
        spinnerSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                posState=position
                studentAdapter.filter.filter(posState.toString()+searchText)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }



        //serach bar

        searchBar.addTextChangedListener( object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               // studentAdapter.filter(pos,p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               // studentAdapter.filter(pos,p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                searchText=p0.toString()
                studentAdapter.filter.filter(posState.toString()+searchText)
            }

        })
        //adapter recycle view
        studentAdapter= StudentAdapter(studentList)

        rvStudent.adapter = studentAdapter
        rvStudent.layoutManager = LinearLayoutManager(this)



    }
}

