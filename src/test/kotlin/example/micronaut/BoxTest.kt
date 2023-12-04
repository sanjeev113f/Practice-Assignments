package example.micronaut

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoxTest {
    @Test
    fun `should be able to declare empty box`(){
        val box = Box<Ball>()

        assertEquals(null,box.top)
        assertEquals(null,box.middle)
        assertEquals(null,box.bottom)
    }

    @Test
    fun `should be able to get an item from box`() {
        val box = Box<Ball>()
        val item = Ball("red", 2.1f)

        box.put(item, "middle")

        assertEquals(item, box.get("middle"))
    }

    @Test
    fun `should be able to put a ball item in box`() {
        val box = Box<Ball>()
        val item = Ball("red", 1.0f)

        box.put(item, "top")

        assertEquals(item, box.get("top"))
    }

    @Test
    fun `should be able to put a book item in box`() {
        val box = Box<Book>()
        val item = Book("The magic", "ABC", 100)

        box.put(item, "top")

        assertEquals(item, box.get("top"))
    }

    @Test
    fun `should be able to remove a ball type item in box`() {
        val box = Box<Ball>()
        val item = Ball("red", 1.01f)

        box.put(item, "top")
        box.remove("top")

        assertNull(box.get("top"))
    }

    @Test
    fun `should be able to sort box items based on color`() {
        val box = Box<Ball>()
        val item1 = Ball("c", 1.01f)
        val item2 = Ball("b", 1.01f)
        val item3 = Ball("a", 1.01f)

        box.put(item1, "top")
        box.put(item2, "middle")
        box.put(item3, "bottom")
        val colorComparator: Comparator<Ball> = compareBy { it.color }
        box.sortBy(colorComparator)

        assertEquals(item3, box.top)
        assertEquals(item2, box.middle)
        assertEquals(item1, box.bottom)
    }

    @Test
    fun `should be able iterate over box`() {
        val box = Box<Ball>()
        var index=0
        val item1 = Ball("c", 1.01f)
        val item2 = Ball("b", 1.01f)
        val item3 = Ball("a", 1.01f)
        val actualItemList = Array<Ball?>(3){null}
        val expectedItemList = arrayOf(item1,item2,item3)

        box.put(item1, "top")
        box.put(item2, "middle")
        box.put(item3, "bottom")
        for ( item in box)  actualItemList[index++] = item

        assertArrayEquals(expectedItemList, actualItemList)
    }

    @Test
    fun `should sort box containing balls in default order`() {
        var index=0
        val box = Box<Ball>()
        val item1 = Ball("red", 1.0f)
        val item2 = Ball("purple", 1.0f)
        val actualItemList = Array<Ball?>(3){null}
        val expectedItemList = arrayOf(item2,item1, null)

        box.put(item1, "top")
        box.put(item2, "middle")
        box.sort()
        for(item in box) actualItemList[index++] = item

        assertArrayEquals(expectedItemList, actualItemList)
    }
}