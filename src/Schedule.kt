/**
 * This file is where the schedule will be implemented
 * Created by Kristian Recio on 5/12/2016.
 */

class Schedule {
    var x = Array<IntArray>(10, {IntArray(10)})
    val timeList = timesList()

    fun timesList() : Array<String?> {
        val totalTicks = 57
        var list = arrayOfNulls<String>(57)

        var i = 0
        while (i < 57) {
            var offsetHour: Int = (i / 4) + 8
            var am_pm = if (offsetHour < 12) "AM" else "PM"
            offsetHour %= 12
            offsetHour = if (offsetHour == 0) 12 else offsetHour

            var offsetSubHour = (i % 4) * 15

            var formatOffsetHour = String.format("%02d", offsetHour)
            var formatOffsetSubHour = String.format("%02d", offsetSubHour)

            list.set(i, "$formatOffsetHour:$formatOffsetSubHour $am_pm")
            i++
        }

        return list
    }
}
