package kenali77.projects.hwchallenge.domain.search

/**
This interface defines the methods of Searchable classes
 It defines a method that takes in a query and it compares with the
 given matching combinations
 **/
interface Searchable {

    fun doesMatchSearchQuery(query:String,combinations:List<String>):Boolean

}