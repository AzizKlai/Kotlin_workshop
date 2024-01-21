package com.gl4.tp2_presencelist_recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_student.view.*
import java.util.*

//import kotlinx.android.synthetic.main.item_todo.view.*

class StudentAdapter(
    private var students: MutableList<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(), Filterable{

    private var filteredStudents: List<Student> =students ;
    var myconstraint:String="0" //this is the search variable will be updated with every filter



    class StudentViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_student,
                parent,
                false
            )
        )
    }
    fun addStudent(student: Student){
        students.add(student)
        notifyItemInserted(students.size-1)
    }
   /* fun deleteDoneStudents(){
        students.removeAll { element-> element.isChecked }
        notifyDataSetChanged()
    }*/


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val curStudent = filteredStudents[position]
        holder.itemView.apply {
            firstName.text=curStudent.firstName//+" "+position.toString()+" "+myconstraint
            lastName.text=curStudent.lastName
            cbChecked.isChecked=curStudent.isPresent
            //the problem with setOnCheckedChangeListener: when going from course to tp we 'll conserve the same item with the same checkbox so when doing that having two instance with different check state the onCheckedChangeListener is triggered
            cbChecked.setOnClickListener{
                if(position < filteredStudents.size){
                students[filteredStudents[position].id].isPresent=cbChecked.isChecked
                filter.filter(myconstraint)}
            }


        }

    }

    override fun getItemCount(): Int {
        return filteredStudents.size
    }



    override fun getFilter(): Filter {
     return object :Filter(){
         override fun performFiltering(constraint: CharSequence?): FilterResults {
             // the constraint will be: a stirng : number(0 or1) and a string to serach on: example: 0aziz
             val charSearch=constraint.toString()
             myconstraint=charSearch
             if(charSearch.isEmpty()){
                filteredStudents=students;
             }
             else {
                 val state=charSearch[0]
                 val search=charSearch.substring(1);

                 filteredStudents = if(state=='0') students//all
                         else if(state=='1')    students.filter { s -> s.isPresent}.toMutableList() //present
                         else students.filter { s -> !s.isPresent}.toMutableList()  //all

                 filteredStudents=filteredStudents.filter { s->
                     (   s.firstName.lowercase(Locale.getDefault()).contains(search.lowercase(Locale.ROOT))
                      || s.lastName.lowercase(Locale.getDefault()).contains(search.lowercase(Locale.ROOT)))
                 }
             }
             val filterResults = FilterResults()
             filterResults.values = filteredStudents
             return filterResults
         }

         override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
             filteredStudents = results?.values as List<Student>
             notifyDataSetChanged()
         }


     }
     }
}