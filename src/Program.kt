
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.TabPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.Stage

/**
 * This file is where the application will be implemented
 * Created by Kristian Recio on 5/12/2016.
 */

class Program : Application() {
    val login = Login()
    val register = Register()
    val weeklyScheduleTab = WeeklySchedule().tab
    val database = Database()
    val mainPane = Pane()
    var tabPane: TabPane = TabPane()

    override fun start(primaryStage: Stage) {
        tabPane.tabs.add(weeklyScheduleTab)
        setMainPane()

        val loginPane = login.loginPane
        primaryStage.apply {
            scene = Scene(loginPane, 450.0, 250.0)
            title = "VaqPack Organizer"
            show()
        }

        login.loginBn.setOnAction {
            primaryStage.scene = login.login(database, mainPane, primaryStage.scene)
            primaryStage.centerOnScreen()
        }

        login.registerBn.setOnAction {
            primaryStage.scene = Scene(register.registerPane, 450.0, 300.0)
            primaryStage.centerOnScreen()
        }
    }

    fun setMainPane() {
        val menuFile = Menu("File")
        val menuEdit = Menu("Edit")
        val menuBar = MenuBar().apply { menus.addAll(menuFile, menuEdit) }

        val vBox = VBox().apply { children.addAll(menuBar, tabPane)}

        mainPane.children.add(vBox)
    }

}

data class User(var user_id: Int = -1, var student_id: String = "", var password: String = "", var name: String = "",
                var email: String = "", var semester: String = "", var major: String = "", var time_increment: Int = 15)