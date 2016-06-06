
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color

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
                Program.user = database.getUser(studentIDTf.text)
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
    val nameLb = Label("Name: ")
    val emailLb = Label("Email: ")
    val semesterLb = Label("Semester: ")
    val majorLb = Label("Major")
    val studentIDTf = TextField()
    val passwordTf = PasswordField()
    val confirmPassTf = PasswordField()
    val nameTf = TextField()
    val emailTf = TextField()
    val semesterTf = TextField()
    val majorTf = TextField()
    val requiredFields = Label("*Missing info in\n required fields.")
    val differentPass = Label("Passwords entered\n do not match.")

    init {
        setRegisterPane()
    }

    fun setRegisterPane() {
        registerPane.apply {
            add(studentIDLb, 0, 0)
            add(passwordLb, 0, 1)
            add(confirmPassLb, 0, 2)
            add(nameLb, 0, 3)
            add(emailLb, 0, 4)
            add(semesterLb, 0, 5)
            add(majorLb, 0, 6)
            add(studentIDTf, 1, 0)
            add(passwordTf, 1, 1)
            add(confirmPassTf, 1, 2)
            add(nameTf, 1, 3)
            add(emailTf, 1, 4)
            add(semesterTf, 1, 5)
            add(majorTf, 1, 6)
            add(registerBn, 1, 7)
            add(requiredFields, 0, 7)
            add(differentPass, 0, 7)
            alignment = Pos.CENTER
            padding = Insets(10.0, 0.0, 10.0, 10.0)
            hgap = 10.0
            vgap = 10.0
        }

        requiredFields.apply {
            textFill = Color.RED
            visibleProperty().set(false)
        }

        differentPass.apply {
            textFill = Color.RED
            visibleProperty().set(false)
        }
        GridPane.setHalignment(registerBn, HPos.RIGHT)

    }

    fun register(database: Database, pane: Pane, scene: Scene) : Scene {
        if (!fieldsEmpty()) {
            if (passwordTf.text.equals(confirmPassTf.text)) {
                confirmPassLb.textFill = Color.BLACK
                passwordLb.textFill = Color.BLACK
                differentPass.visibleProperty().set(false)

                val semester = if (semesterTf.text.isEmpty()) "" else semesterTf.text
                val major = if (majorTf.text.isEmpty()) "" else majorTf.text

                val user: User = User(-1, studentIDTf.text, passwordTf.text, nameTf.text, emailTf.text, semester, major, 15)
                database.addUser(user)
                Program.user = user

                return Scene(pane, 1000.0, 250.0)
            } else {
                confirmPassLb.textFill = Color.RED
                passwordLb.textFill = Color.RED
                differentPass.visibleProperty().set(true)
            }
        }

        return scene
    }

    fun isEmailValid(email: String): Boolean {
        return false

        // Got to work on this
    }

    fun fieldsEmpty(): Boolean {
        var empty = false
        if(studentIDTf.text.isEmpty()) {
            studentIDLb.textFill = Color.RED
            empty = true
        } else
            studentIDLb.textFill = Color.BLACK

        if (passwordTf.text.isEmpty()) {
            passwordLb.textFill = Color.RED
            empty = true
        } else
            passwordLb.textFill = Color.BLACK

        if (confirmPassTf.text.isEmpty()) {
            confirmPassLb.textFill = Color.RED
            empty = true
        } else
            confirmPassLb.textFill = Color.BLACK

        if (emailTf.text.isEmpty()) {
            emailLb.textFill = Color.RED
            empty = true
        } else
            emailLb.textFill = Color.BLACK

        if (studentIDTf.text.isEmpty() && passwordTf.text.isEmpty() && confirmPassTf.text.isEmpty() && emailTf.text.isEmpty())
            requiredFields.visibleProperty().set(true)
        else
            requiredFields.visibleProperty().set(false)

        return empty
    }
}