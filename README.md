# Offline-First Mobile App (Assignment)

This project is a demo offline-first Android application built using **Kotlin**, **Jetpack Compose**, **Navigation Component**, and **Room**.  
It covers login, static menu, offline data entry, background location tracking, and syncing.

---

## 📌 Features Implemented

### ✅ Question 1 – Login Page
- Username & password input  
- On Login → save a dummy token locally  
- Navigate to Home screen  
- No real API call  

### ✅ Question 2 – Static Menu
- Drawer/Sidebar with 5 menu items  
- Menu loaded from hardcoded JSON  
- No API involved  

### ✅ Question 3 – Offline Form & Local Storage
- Form fields: **Name**, **Age**, **Remarks**  
- Generate UUID on Save  
- Save data locally with `isSynced = false`  
- Show success message  
- Display all saved records below the form  

### ✅ Question 4 – Background Location Tracking
- Requests location permission  
- Background location updates (even when app is closed/killed)
- Saves location every 10 minutes or significant change  
- Stores: `{id, latitude, longitude, timestamp, isSynced}`  
- Shows last 10 records in **Location History** screen  

### ⚠️ Question 5 – Auto Sync (Not Completed)
Due to a **WorkManager version mismatch issue**, auto-sync (15-minute periodic sync) could not be completed.

---

## 📦 Installation

1. Download & install the **debug APK**  
2. Grant all required permissions:
   - Location  
   - Background Location  
3. Enable:
   - GPS  
   - Internet connection  

---

## 🛠️ Tech Stack

- Kotlin (XML)
- Room Database
- Hilt Dependency Injection
- Navigation Component
- Foreground & Background Services
- FusedLocationProviderClient

---

## 🖼️ Screenshots
![WhatsApp Image 2025-11-22 at 04 48 34](https://github.com/user-attachments/assets/ad22cf23-780b-4996-8815-308ed1706e63)

![WhatsApp Image 2025-11-22 at 04 48 3464](https://github.com/user-attachments/assets/1c30e91a-c154-4d44-b229-0328ad9855b8)

![WhatsApp Image 2025-11-22 at 04 48 34564](https://github.com/user-attachments/assets/08e3f180-7eba-4941-8799-a940396e692d)

![WhatsApp Image 2025-11-22 at 04 48 354534](https://github.com/user-attachments/assets/8246b5a7-e3cb-4a7a-a5db-57adf930fa06)

![WhatsApp Image 2025-11-22 at 04 48 3545642](https://github.com/user-attachments/assets/5addf4f8-91c2-4b45-b982-8233fc17bf7e)
