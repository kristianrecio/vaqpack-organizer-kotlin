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
    var sql: String = ""

    fun getUser(student_id: String): User {
        sql = "SELECT * FROM user WHERE student_id=?"
        preparedStatement = connection.prepareStatement(sql)
        preparedStatement!!.setString(1, student_id)
        resultSet = preparedStatement!!.executeQuery()


        if (resultSet!!.next())
            return User(resultSet!!.getInt("id"), resultSet!!.getString("student_id"), resultSet!!.getString("password"), resultSet!!.getString("name"),
                resultSet!!.getString("email"), resultSet!!.getString("semester"), resultSet!!.getString("major"), resultSet!!.getInt("time_increment"))
        else
            return User()
    }

    fun checkIdAndPass(enteredID: String, enteredPassword: String): Boolean {
        sql = "SELECT * FROM user"
        preparedStatement = connection.prepareStatement(sql)
        resultSet = preparedStatement?.executeQuery()

        while (resultSet!!.next()) {
            val student_id = resultSet!!.getString("student_id")
            val password = resultSet!!.getString("password")
            if (student_id.equals(enteredID) && password.equals(enteredPassword))
                return true
        }

        return false
    }

    fun addUser(user: User) {
        sql = "INSERT INTO user (student_id, password, name, email, semester, major, time_increment) VALUES (?,?,?,?,?,?,?)"
        preparedStatement = connection.prepareStatement(sql)
        preparedStatement!!.apply {
            setString(1, user.student_id)
            setString(2, user.password)
            setString(3, user.name)
            setString(4, user.email)
            setString(5, user.semester)
            setString(6, user.major)
            setInt(7, user.time_increment)
            executeUpdate()
        }
    }
}