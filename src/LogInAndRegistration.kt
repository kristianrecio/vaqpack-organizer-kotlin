
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import java.util.regex.Pattern

/**
 * This file is where the registration and login features will be implemented
 * Created by Kristian Recio on 5/13/2016.
 */


class Login {
    val loginPane = GridPane()
    val loginBn = Button("Log In")
    val registerBn = Button("Register")
    val studentIDLb = Label("Student ID: ")
    val passwordLb = Label("Password: ")
    val studentIDTf = TextField()
    val passwordTf = PasswordField()
    val requiredFields = Label("*Missing info in required fields.")
    val incorrectInput = Label("ID or password\n is incorrect.")

    init {
        setLoginPane()
    }

    fun setLoginPane() {
        loginPane.apply {
            add(studentIDLb, 0, 0)
            add(passwordLb, 0, 1)
            add(studentIDTf, 1, 0)
            add(passwordTf, 1, 1)
            add(loginBn, 1, 3)
            add(registerBn, 0, 3)
            add(requiredFields, 1, 2)
            add(incorrectInput, 2, 0)
            alignment = Pos.CENTER
            padding = Insets(10.0, 0.0, 10.0, 10.0)
            hgap = 10.0
            vgap = 10.0
        }

        GridPane.setHalignment(loginBn, HPos.RIGHT)

        requiredFields.visibleProperty().set(false)
        requiredFields.textFill = Color.RED
        incorrectInput.visibleProperty().set(false)
        incorrectInput.textFill = Color.RED
    }

    fun login(database: Database, pane: Pane, scene: Scene) : Scene {
        if (!fieldsEmpty()) {
            if (database.checkIdAndPass(studentIDTf.text, passwordTf.text)) {
                incorrectInput.visibleProperty().set(false)
                val alert = Alert(Alert.AlertType.INFORMATION)
                alert.headerText = "Login was successful!"
                alert.showAndWait()
                return Scene(pane, 1000.0, 250.0)
            } else
                incorrectInput.visibleProperty().set(true)
        }

        return scene
    }

    fun fieldsEmpty(): Boolean {
        var empty = false
        if (studentIDTf.text.isEmpty()) {
            studentIDLb.textFill = Color.RED
            requiredFields.visibleProperty().set(true)
            empty = true
        } else
            studentIDLb.textFill = Color.BLACK

        if (passwordTf.text.isEmpty()) {
            passwordLb.textFill = Color.RED
            requiredFields.visibleProperty().set(true)
            empty = true
        } else
            passwordLb.textFill = Color.BLACK

        if (!studentIDTf.text.isEmpty() && !passwordTf.text.isEmpty()) {
            requiredFields.visibleProperty().set(false)
        }
        return empty
    }
}

class Register {
    val registerPane = GridPane()
    val registerBn = Button("Register")
    val studentIDLb = Label("Student ID: ")
    val passwordLb = Label("Password: ")
    val confirmPassLb = Label("Confirm Password: ")
    val emailLb = Label("Email: ")
    val semesterLb = Label("Semester: ")
    val majorLb = Label("Major")
    val studentIDTf = TextField()
    val passwordTf = PasswordField()
    val confirmPassTf = PasswordField()
    val emailTf = TextField()
    val semesterTf = TextField()
    val majorTf = TextField()

    init {
        setRegisterPane()
    }

    fun setRegisterPane() {
        registerPane.apply {
            add(studentIDLb, 0, 0)
            add(passwordLb, 0, 1)
            add(confirmPassLb, 0, 2)
            add(emailLb, 0, 3)
            add(semesterLb, 0, 4)
            add(majorLb, 0, 5)
            add(studentIDTf, 1, 0)
            add(passwordTf, 1, 1)
            add(confirmPassTf, 1, 2)
            add(emailTf, 1, 3)
            add(semesterTf, 1, 4)
            add(majorTf, 1, 5)
            add(registerBn, 1, 6)
            alignment = Pos.CENTER
            padding = Insets(10.0, 0.0, 10.0, 10.0)
            hgap = 10.0
            vgap = 10.0

            GridPane.setHalignment(registerBn, HPos.RIGHT)
        }


    }

    fun isEmailValid(): Boolean {
        val pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = Pattern.compile(pattern)

        return false
    }

    fun fieldsEmpty(): Boolean {
        return false
    }
}