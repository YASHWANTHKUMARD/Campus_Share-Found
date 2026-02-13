📱 Campus Share & Found – MAT-BASE

A Material Design-based Android application that enables users to post, browse, and manage Lost, Found, and Share requests within a campus community.

🚀 Use Cases & Application Workflow
🔹 Use Case 1: App Launch & Navigation (The Entry Point)
📌 Workflow

User opens the app → DashboardActivity is launched.

The dashboard displays three main options:

Post Your Need

View Found Items

View Requests

🛠 Concepts Used

Activity
DashboardActivity acts as the UI controller.

Intents
Explicit Intents are used to navigate between screens (e.g., ViewFoundActivity).

Material Design
MaterialCardView creates a modern and interactive dashboard interface.

🔹 Use Case 2: Posting a New Request (Lost, Found, or Share)
📌 Workflow

User taps "Post Your Need".

An AlertDialog appears to select:

Share Request

Report Lost

Report Found

User is navigated to PostLostActivity.

User enters Title and Description and clicks Submit.

Confirmation dialog appears.

On clicking Post, data is saved and user returns to Dashboard.

A success notification is displayed.

🛠 Concepts Used

AlertDialog
Captures user selection without opening a new screen.

Data Passing (Intent Extras)
POST_TYPE is passed using Intent.putExtra().

Repository Pattern
MaterialRepository.addItem() stores data globally.

NotificationManager
Displays confirmation notification after successful post.

🔹 Use Case 3: Browsing All Requests (The Community Feed)
📌 Workflow

User taps "View Requests" → launches MaterialSharingActivity.

App fetches all items from the repository.

Items are displayed in a scrollable list.

🛠 Concepts Used

RecyclerView
Efficiently displays long lists.

Adapter & ViewHolder Pattern
MaterialAdapter binds data to UI components.
ViewHolder holds references to UI elements (Title, Badge, etc.).

Dynamic UI with Chips
Badge color changes based on ItemType:

🟢 Green → Share

🔴 Red → Lost

🔵 Blue → Found

🔹 Use Case 4: Updating Status (Lost → Found Loop)
📌 Workflow

User sees an item marked as LOST.

User taps the item.

A dialog asks:
"Do you want to mark this item as FOUND?"

On confirmation:

Badge changes from 🔴 LOST → 🔵 FOUND

Notification is triggered.

🛠 Concepts Used

Mutable Data State
MaterialRepository.updateItemType() updates the item.

Observer-like Pattern
adapter.notifyDataSetChanged() refreshes UI instantly.

Activity Lifecycle Awareness
onResume() ensures updated data is displayed when returning to the screen.

🔹 Use Case 5: Specialized Browsing (Found Items Only)
📌 Workflow

User taps "View Found Items".

ViewFoundActivity opens.

Only items marked as FOUND are displayed.

🛠 Concepts Used

Data Filtering (Kotlin)

repositoryList.filter { it.type == ItemType.FOUND }


Code Reusability
Reuses:

MaterialAdapter

item_material.xml layout
Demonstrates modular and maintainable architecture.

🏗 Core Architecture Overview
Concept	Purpose in MAT-BASE
Activities	Separate screens for Dashboard, Posting, and Viewing
MaterialRepository	Single Source of Truth for consistent data
RecyclerView	Efficient scrolling for large data sets
Notifications	Background feedback even when app is minimized
Intents	Navigation glue between different screens
📌 Key Features

Material Design UI

Repository Pattern Architecture

Dynamic Badge System

Notification Integration

Efficient RecyclerView Implementation

Clean Activity Navigation using Explicit Intents

🛠 Tech Stack

Kotlin

Android SDK

Material Design Components

RecyclerView

NotificationManager

📈 Future Improvements

Firebase Database Integration

User Authentication

Real-time updates

Search & Filter functionality

Image upload support
