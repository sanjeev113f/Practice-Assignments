package example.micronaut

data class Ball ( val color:String,val radius:Float ): ItemType {

    override fun getSortableProperty(): String {
        return color
    }
}