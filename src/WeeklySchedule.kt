
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.Tab
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.BorderPane

/**
 * This file is where the Tab for the Weekly Schedule will be implemented
 * Created by Kristian Recio on 5/12/2016.
 */


class WeeklySchedule {
    val tab = Tab()
    val pane = BorderPane()
    val table = TableView<RowData>()

    init {
        setTheTab()
    }

    fun setTheTab() {
        setThePane()
        tab.apply {
            text = "Weekly Schedule"
            content = pane
            closableProperty().set(false)
        }
    }

    fun setThePane() {
        setTheTable()
        pane.center = table
    }

    fun setTheTable() {
        val timeColumn = TableColumn<RowData, String>("").apply {
            setCellValueFactory(PropertyValueFactory<RowData, String>("time"))}
        val monColumn = TableColumn<RowData, String>("Monday").apply {
            setCellValueFactory(PropertyValueFactory<RowData, String>("mon"))}
        val tuesColumn = TableColumn<RowData, String>("Tuesday").apply {
            setCellValueFactory(PropertyValueFactory<RowData, String>("tues"))}
        val wedColumn = TableColumn<RowData, String>("Wednesday").apply {
            setCellValueFactory(PropertyValueFactory<RowData, String>("wed"))}
        val thurColumn = TableColumn<RowData, String>("Thursday").apply {
            setCellValueFactory(PropertyValueFactory<RowData, String>("thur"))}
        val friColumn = TableColumn<RowData, String>("Friday").apply {
            setCellValueFactory(PropertyValueFactory<RowData, String>("fri"))}

        table.columns.addAll(timeColumn, monColumn, tuesColumn, wedColumn, thurColumn, friColumn)
        table.items = getItems()
    }

    fun getItems() : ObservableList<RowData> {
        val items = FXCollections.observableArrayList<RowData>()
        val rows = arrayOfNulls<RowData>(57)

        return items
    }

    fun getTheTab(): Tab = tab
}

data class RowData(val time: String, val mon: String, val tues: String, val wed: String, val thur: String, val fri: String)