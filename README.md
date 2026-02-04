# 📱 Campus Share & Found – Android Application

Campus Share & Found is an Android application designed for campus communities to **share items, report lost items, and view found items** in a simple and efficient way. The app uses core Android components and follows a clean, activity-based workflow.

---

## 🚀 Features

* Post lost, found, or shareable items
* View items reported as **Found**
* Browse and interact with shared/lost/found items
* Confirmation dialogs for user actions
* Notifications for important actions
* RecyclerView-based efficient item listing

---

## 🧩 Core Android Concepts Used

### 1. Activity

**What it is:**
An Activity represents a single screen with a user interface. An Android app is typically made up of multiple activities.

**How it’s used in this app:**

* **DashboardActivity**
  The main entry point of the app. Displays three primary options:

  * Post Your Need
  * View Found Items
  * Share Items

* **MaterialSharingActivity**
  Displays a list of items that are available for sharing or reported as lost/found.

* **PostLostActivity**
  Provides a form for users to post new items (Lost, Found, or Share requests).

* **ViewFoundActivity**
  Dedicated screen to display only items marked as **FOUND**.

---

### 2. RecyclerView

**What it is:**
RecyclerView is an advanced and efficient widget used to display large lists of data by recycling item views.

**How it’s used in this app:**

* Used in **MaterialSharingActivity** and **ViewFoundActivity**
* Displays lists of `MaterialItems`
* A custom adapter (`MaterialAdapter`) binds item data to the UI

---

### 3. Notifications

**What it is:**
Notifications are messages shown outside the app’s UI to inform users about events or actions.

**How it’s used in this app:**

* When a user performs actions like **Accept Request**, a notification is triggered
* `sendActionNotification()` handles notification creation
* `DashboardActivity`:

  * Requests `POST_NOTIFICATIONS` permission
  * Creates a `NotificationChannel` (required for Android 8.0+)

---

### 4. Intent

**What it is:**
An Intent is a messaging object used to request an action from another app component, such as launching an Activity.

**How it’s used in this app:**

* Navigation between activities from the Dashboard
* Example:

  ```kotlin
  startActivity(Intent(this, ViewFoundActivity::class.java))
  ```

---

### 5. AlertDialog

**What it is:**
An AlertDialog prompts the user to confirm an action or make a decision.

**How it’s used in this app:**

* In **MaterialSharingActivity**, tapping an item opens a confirmation dialog
* Confirms actions such as **Accept Request** before proceeding

---

## 🔄 Application Workflow

### 1. Launch

* App starts with **DashboardActivity**
* User sees three main options:

  * **Post Your Need**
    Opens a dialog to choose:

    * Request to Share
    * Post Lost Item
    * Post Found Item
      Then navigates to **PostLostActivity**

  * **View Found Items**
    Opens **ViewFoundActivity** showing all FOUND items

  * **Share Items**
    Opens **MaterialSharingActivity** with mixed item listings

---

### 2. Viewing Items

* Items are displayed using **RecyclerView**
* Smooth scrolling and efficient memory usage

---

### 3. Interacting with Items

* Clicking an item in **MaterialSharingActivity**:

  * Shows an **AlertDialog** for confirmation
  * On confirmation:

    * Displays a **Toast** message
    * Sends a **Notification** to the user

---

### 4. Posting Items

* **PostLostActivity** allows users to:

  * Add new Lost items
  * Add Found items
  * Add items for Sharing

---

## 🏫 Use Case

This application provides a simple yet effective platform for campus communities to:

* Share unused items
* Report lost belongings
* Help others find missing items

By leveraging fundamental Android components, the app ensures clarity, usability, and smooth collaboration between users.

---

## 🛠 Tech Stack

* Language: **Kotlin**
* UI: **XML Layouts**
* Architecture: **Activity-based**
* Components: RecyclerView, Notifications, Intents, AlertDialog

---

## 📌 Future Enhancements (Optional)

* Firebase / Backend integration
* User authentication
* Search & filter functionality
* Real-time notifications
* MVVM architecture

---

**Author:** Yashwanth Kumar D
**Project Type:** Android Mobile Application
