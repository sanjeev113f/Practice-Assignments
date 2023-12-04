package example.micronaut

data class Book(val name: String, val author: String, val numberOfPages: Int): ItemType {

    override fun getSortableProperty(): String {
        return name
    }
}