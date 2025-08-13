# 🎓 Student Result Processing System

## 📌 Overview

The **Student Result Processing System** is a **Java 8 console-based application** designed to manage and process student results efficiently.
It stores student details such as **Roll No., Student ID, Name, Marks, Grade, Percentage, and Pass/Fail status**.
The system also supports **file handling** with **TXT** and **CSV** output, and automatically opens the CSV file in Excel upon saving.

---

## ✨ Features

* ➕ **Add Student Results** with multiple subjects
* 📜 **View All Results** in a clean format
* 🔍 **Search** by **Name** or **Student ID**
* ✏️ **Update Marks** for existing students
* ❌ **Delete** student records
* 🏆 **Show Class Topper**
* 📉 **Show Failed Students**
* 💾 **Save Results to TXT and CSV**
* 📂 **Auto-open CSV file in Excel** after saving
* 📥 **Preloaded with sample student data** for testing

---

## 🛠 Technologies Used

* **Language**: Java 8
* **File Handling**: TXT & CSV
* **Collections**: `ArrayList`
* **OOP Concepts**: Classes, Objects, Methods, Encapsulation

---

## 📂 Project Structure

```
StudentResultProcessingSystem.java
StudentResults.txt   # Generated automatically
StudentResults.csv   # Generated automatically
README.md
```

---

## 📋 How It Works

1. Run the program
2. Select an option from the **menu**
3. Enter required details
4. Data is stored in memory until you **Save & Exit**
5. On saving:

   * **StudentResults.txt** → readable text format
   * **StudentResults.csv** → Excel-compatible file (opens automatically)

---

## 🖥 Menu Example

```
===== Student Result Processing System =====
1. Add Student Result
2. View All Results
3. Search Result
4. Update Student Marks
5. Delete Student Record
6. Show Class Topper
7. Show Failed Students
8. Save & Exit
```

---

## 🧮 Grade Calculation

| Percentage | Grade    |
| ---------- | -------- |
| 90%+       | A+       |
| 80-89%     | A        |
| 70-79%     | B        |
| 60-69%     | C        |
| 50-59%     | D        |
| 40-49%     | E        |
| Below 40%  | F (Fail) |

---

## 📊 Sample CSV Output

```
Roll No,Student ID,Name,Subject1,Subject2,Subject3,Percentage,Grade,Result
1,STU2025001,Ayushi,90,85,95,90.00,A+,Pass
2,STU2025002,Rohit,60,45,50,51.67,D,Pass
```

---

## 🚀 How to Run

1. Install **Java 8 or above**
2. Save the file as `StudentResultProcessingSystem.java`
3. Open terminal/command prompt in the project folder
4. Compile:

   ```
   javac StudentResultProcessingSystem.java
   ```
5. Run:

   ```
   java StudentResultProcessingSystem
   ```

---

## 📄 License

Free to use for educational purposes.

---
