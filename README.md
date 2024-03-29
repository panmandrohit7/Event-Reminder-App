# Event-Reminder-App

This a an Event Reminder Android App using the concepts of JAVA.
Here's a breakdown of the components used:

ExampleInstrumentedTest: This class is for testing purposes, utilizing Android's testing framework to ensure proper functionality of the app.

TimePickerFragment: This fragment provides a time picker dialog for selecting the time of the event.

DatePickerFragment: Similar to TimePickerFragment, this fragment provides a date picker dialog for selecting the date of the event.

DatabaseHelper: Manages the SQLite database for storing event details such as time, date, and description. It includes methods for creating, updating, and retrieving data from the database.

ViewEvent: An activity responsible for displaying the list of events. Currently, it has a basic layout (view_events.xml) which needs to be populated with event data retrieved from the database.

Comments: There are commented-out sections of code which likely were intended for populating a list view with event data from the database. These sections are currently inactive but suggest functionality for displaying events in the app interface.

Overall, the application allows users to add, update, and view events, utilizing date and time pickers for input and storing event data in an SQLite database.
