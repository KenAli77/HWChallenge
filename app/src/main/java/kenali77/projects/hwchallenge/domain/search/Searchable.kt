package kenali77.projects.hwchallenge.domain.search

interface Searchable {

    fun doesMatchSearchQuery(query:String,combinations:List<String>):Boolean

}