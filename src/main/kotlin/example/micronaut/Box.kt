package example.micronaut


class Box<T : ItemType> {
    var top: T? = null
    var middle: T? = null
    var bottom: T? = null

    fun put(item: T, position: String) {
        when (position) {
            "top" -> top = item
            "middle" -> middle = item
            "bottom" -> bottom = item
        }
    }

    fun get(position: String): T? {
        when (position) {
            "top" -> return top
            "middle" -> return middle
            "bottom" -> return bottom
        }
        return null
    }

    fun remove(position: String) {
        when (position) {
            "top" -> top = null
            "middle" -> middle = null
            "bottom" -> bottom = null
        }
    }

    fun sortBy(comparator: Comparator<T>) {
        val allItems = mutableListOf<T>()
        top?.let { allItems.add(it) }
        middle?.let { allItems.add(it) }
        bottom?.let { allItems.add(it) }

        allItems.sortWith(comparator)

        allItems.forEachIndexed { index, item ->
            when (index) {
                0 -> top = item
                1 -> middle = item
                2 -> bottom = item
            }
        }
    }

    operator fun iterator(): Iterator<T?> = iterator {
        yield(top)
        yield(middle)
        yield(bottom)
    }

    fun sort() {
        val itemList = listOfNotNull(this.top, this.middle, this.bottom).sortedBy { it.getSortableProperty() }

        for (i in itemList.indices) {
            when (i) {
                0 -> this.top = itemList[i]
                1 -> this.middle = itemList[i]
                2 -> this.bottom = itemList[i]
            }
        }
    }
}