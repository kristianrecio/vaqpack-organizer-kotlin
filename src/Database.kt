import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * This file is where all the functions to communicate with the database will be.
 * Created by Kristian Recio on 5/12/2016.
 */

class Database {
    val connection: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaqpackorganizer" +
            "?characterEncoding=UTF-8&useSSL=false", "root", "root")
    var preparedStatement: PreparedStatement? = null
    var resultSet: ResultSet? = null

    fun getUserInfo() {
        val user = User()
    }

    fun checkIdAndPass(enteredID: String, enteredPassword: String): Boolean {
        var success = false
        var sql = "SELECT * FROM user"
        preparedStatement = connection.prepareStatement(sql)
        resultSet = preparedStatement?.executeQuery()

        while (resultSet!!.next()) {
            val student_id = resultSet!!.getString("student_id")
            val password = resultSet!!.getString("password")
            if (student_id.equals(enteredID) && password.equals(enteredPassword))
                success = true
            else
                success = false
        }

        return success
    }
}