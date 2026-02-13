# 📱 Campus Share & Found – MAT-BASE

A Material Design-based Android application that enables users to post, browse, and manage Lost, Found, and Share requests within a campus community.

---

# 🚀 Use Cases & Application Workflow

---

## 🔹 Use Case 1: App Launch & Navigation (The Entry Point)

### 📌 Workflow
1. User opens the app → `DashboardActivity` is launched.
2. The dashboard displays three main options:
   - **Post Your Need**
   - **View Found Items**
   - **View Requests**

### 🛠 Concepts Used
- **Activity**  
  `DashboardActivity` acts as the UI controller.
  
- **Intents**  
  Explicit Intents are used to navigate between screens (e.g., `ViewFoundActivity`).

- **Material Design**  
  `MaterialCardView` creates a modern and interactive dashboard interface.

---

## 🔹 Use Case 2: Posting a New Request (Lost, Found, or Share)

### 📌 Workflow
1. User taps **"Post Your Need"**.
2. An `AlertDialog` appears to select:
   - Share Request  
   - Report Lost  
   - Report Found  
3. User is navigated to `PostLostActivity`.
4. User enters **Title** and **Description** and clicks **Submit**.
5. Confirmation dialog appears.
6. On clicking **Post**, data is saved and user returns to Dashboard.
7. A success notification is displayed.

### 🛠 Concepts Used
- **AlertDialog**  
  Captures user selection without opening a new screen.

- **Data Passing (Intent Extras)**  
  `POST_TYPE` is passed using `Intent.putExtra()`.

- **Repository Pattern**  
  `MaterialRepository.addItem()` stores data globally.

- **NotificationManager**  
  Displays confirmation notification after successful post.

---

## 🔹 Use Case 3: Browsing All Requests (The Community Feed)

### 📌 Workflow
1. User taps **"View Requests"** → launches `MaterialSharingActivity`.
2. App fetches all items from the repository.
3. Items are displayed in a scrollable list.

### 🛠 Concepts Used
- **RecyclerView**  
  Efficiently displays long lists.

- **Adapter & ViewHolder Pattern**  
  `MaterialAdapter` binds data to UI components.  
  `ViewHolder` holds references to UI elements (Title, Badge, etc.).

- **Dynamic UI with Chips**  
  Badge color changes based on `ItemType`:
  - 🟢 Green → Share  
  - 🔴 Red → Lost  
  - 🔵 Blue → Found  

---

## 🔹 Use Case 4: Updating Status (Lost → Found Loop)

### 📌 Workflow
1. User sees an item marked as **LOST**.
2. User taps the item.
3. A dialog asks:  
   *"Do you want to mark this item as FOUND?"*
4. On confirmation:
   - Badge changes from 🔴 LOST → 🔵 FOUND
   - Notification is triggered.

### 🛠 Concepts Used
- **Mutable Data State**  
  `MaterialRepository.updateItemType()` updates the item.

- **Observer-like Pattern**  
  `adapter.notifyDataSetChanged()` refreshes UI instantly.

- **Activity Lifecycle Awareness**  
  `onResume()` ensures updated data is displayed when returning to the screen.

---

## 🔹 Use Case 5: Specialized Browsing (Found Items Only)

### 📌 Workflow
1. User taps **"View Found Items"**.
2. `ViewFoundActivity` opens.
3. Only items marked as `FOUND` are displayed.

### 🛠 Concepts Used
- **Data Filtering (Kotlin)**  
  ```kotlin
  repositoryList.filter { it.type == ItemType.FOUND }
