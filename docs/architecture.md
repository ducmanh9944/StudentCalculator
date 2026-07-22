# ARCHITECTURE.md

# Student Calculator - Technical Architecture

Version: 1.0

---

# 1. Overview

## 1.1 Purpose

This document describes the technical architecture of the **Student Calculator** Android application.

The application is designed for Vietnamese middle school students and provides mathematical calculation tools in a simple, fast and completely offline environment.

The architecture follows the **MVVM (Model - View - ViewModel)** pattern to ensure the project is easy to develop, maintain and extend.

---

## 1.2 Design Goals

The application should be:

- Offline-first
- Lightweight
- Modular
- Easy to maintain
- Easy to extend
- Beginner-friendly
- Suitable for Android development using Java and XML

---

# 2. Technology Stack

| Component | Technology |
|------------|------------|
| Language | Java |
| UI | XML (View System) |
| Architecture | MVVM |
| Local Database | Room |
| State Management | LiveData |
| Background Tasks | ExecutorService |
| Navigation | Navigation Component |
| UI Library | Material Design 3 |
| Expression Parser | exp4j |
| Build Tool | Gradle |
| compileSdk | 36 |
| targetSdk | 36 |
| minSdk | 24 |

---

# 3. Architecture Pattern

The application follows the MVVM architecture.

```
                UI Layer
        (Activity / Fragment)
                 │
                 ▼
            ViewModel Layer
                 │
                 ▼
          Repository Layer
                 │
        ┌────────┴────────┐
        ▼                 ▼
   Room Database      Utility Classes
```

---

## 3.1 UI Layer

Responsible for:

- Displaying data
- Receiving user input
- Observing LiveData
- Navigation

The UI layer must **NOT**:

- Access Room directly
- Perform mathematical calculations
- Contain business logic

---

## 3.2 ViewModel Layer

Responsible for:

- Managing UI state
- Validating user input
- Calling repositories
- Returning data to UI

The ViewModel must **NOT**:

- Hold Activity references
- Hold Fragment references
- Access Views directly

---

## 3.3 Repository Layer

Responsible for:

- Reading data
- Writing data
- Hiding the data source

Current repository:

- HistoryRepository

All Room operations should be executed using ExecutorService.

---

## 3.4 Database Layer

The application uses **Room Database**.

Current database:

- History

Future database tables can be added without affecting existing modules.

---

## 3.5 Utility Layer

The utils package contains pure Java classes.

Examples:

- EquationSolver
- CubicEquationSolver
- SystemEquationSolver
- MathUtils
- UnitConverter
- ExpressionEvaluator
- NumberFormatter
- ValidationUtils

Rules:

- No Android dependency
- No Context
- No Activity
- No Fragment
- Reusable
- Easy to test

Every mathematical algorithm must be implemented inside the utils package.

Neither Fragment nor ViewModel should implement mathematical algorithms directly.

---

# 4. Project Structure

```
app/

├── activity/
│   └── MainActivity.java
│
├── fragment/
│   ├── CalculatorFragment.java
│   ├── ToolsFragment.java
│   ├── LinearEquationFragment.java
│   ├── QuadraticEquationFragment.java
│   ├── CubicEquationFragment.java
│   ├── SystemEquationFragment.java
│   ├── NumberToolsFragment.java
│   ├── ConverterFragment.java
│   └── HistoryFragment.java
│
├── viewmodel/
│   ├── CalculatorViewModel.java
│   ├── EquationViewModel.java
│   ├── NumberToolsViewModel.java
│   ├── ConverterViewModel.java
│   └── HistoryViewModel.java
│
├── repository/
│   └── HistoryRepository.java
│
├── database/
│   ├── AppDatabase.java
│   ├── HistoryDao.java
│   └── HistoryEntity.java
│
├── adapter/
│   └── HistoryAdapter.java
│
├── utils/
│   ├── EquationSolver.java
│   ├── CubicEquationSolver.java
│   ├── SystemEquationSolver.java
│   ├── MathUtils.java
│   ├── UnitConverter.java
│   ├── ExpressionEvaluator.java
│   ├── NumberFormatter.java
│   └── ValidationUtils.java
│
├── model/
│
└── listener/
```

---

# 5. Feature Structure

```
Calculator

Tools
    ├── Linear Equation
    ├── Quadratic Equation
    ├── Cubic Equation
    ├── System of Two Linear Equations
    ├── GCD & LCM
    └── Unit Converter

History
```

The application contains only three main modules accessible from the Bottom Navigation:

- Calculator
- Tools
- History

Each tool is implemented as an independent Fragment to improve maintainability and future scalability.

---

# 6. Navigation

The application uses:

- Single Activity architecture
- Multiple Fragments
- Navigation Component
- Bottom Navigation View

Navigation hierarchy:

```
MainActivity
│
├── Calculator
│
├── Tools
│     ├── Linear Equation
│     ├── Quadratic Equation
│     ├── Cubic Equation
│     ├── System of Two Linear Equations
│     ├── GCD & LCM
│     └── Unit Converter
│
└── History
```

Bottom Navigation contains only three tabs:

- Calculator
- Tools
- History

The Calculator screen is the start destination.

---

# 7. Package Responsibilities

| Package | Responsibility |
|-----------|----------------|
| activity | Application entry point |
| fragment | Display UI and receive user interaction |
| viewmodel | Manage UI state and communicate with Repository |
| repository | Data access layer |
| database | Room Database implementation |
| adapter | RecyclerView adapters |
| utils | Mathematical algorithms and helper classes |
| model | Data models |
| listener | Callback interfaces |

---

# 8. Database Design

The application currently contains only one Room table.

## History

| Field | Type | Description |
|--------|------|-------------|
| id | INTEGER | Primary Key (Auto Increment) |
| type | TEXT | Calculation type |
| expression | TEXT | Original expression or input |
| result | TEXT | Display result |
| createdAt | INTEGER | Unix timestamp |

### History Types

- calculator
- linear
- quadratic
- cubic
- system
- gcd_lcm
- converter

Future features should continue using the History table whenever possible instead of creating additional history tables.

---

# 9. Coding Conventions

## Naming Convention

| Item | Convention | Example |
|------|------------|---------|
| Activity | PascalCase + Activity | MainActivity |
| Fragment | PascalCase + Fragment | CalculatorFragment |
| ViewModel | PascalCase + ViewModel | CalculatorViewModel |
| Repository | PascalCase + Repository | HistoryRepository |
| Adapter | PascalCase + Adapter | HistoryAdapter |
| Utility | PascalCase | MathUtils |
| XML Layout | snake_case | fragment_calculator.xml |
| XML ID | camelCase | btnEquals |
| String Resource | snake_case | calculator_divide_zero |

---

## General Rules

- One class has only one responsibility.
- Keep methods short and readable.
- Avoid duplicated code.
- Avoid magic numbers.
- Avoid hardcoded strings.
- Prefer reusable methods.
- Use meaningful class and variable names.
- Follow MVVM consistently.

---

## Utility Layer Rules

All mathematical logic must be implemented inside the **utils** package.

Examples:

- Expression evaluation
- Equation solving
- Unit conversion
- GCD & LCM
- Number formatting

Fragments and ViewModels must call utility classes instead of implementing algorithms themselves.

---

# 10. UI Guidelines

The application follows **Material Design 3**.

Design principles:

- Simple
- Minimal
- Clean
- Student-friendly
- Responsive
- Easy to use

Main Components:

- MaterialButton
- MaterialCardView
- RecyclerView
- TextInputLayout
- TextInputEditText
- MaterialAlertDialog

Application Theme:

- Light Theme only

General UI Rules:

- White background
- Consistent spacing
- Rounded corners (12dp)
- Material Icons
- No illustrations
- No unnecessary animations
- Large touch targets
- Responsive on different screen sizes

The calculator screen should have a layout similar to Samsung Calculator while maintaining Material Design principles.

---

# 11. Error Handling

The application must never crash because of invalid user input.

All inputs should be validated before processing.

Common validation cases:

- Empty input
- Invalid number format
- Division by zero
- Invalid mathematical expression
- Mismatched parentheses
- Overflow
- Invalid equation coefficients

User-friendly error messages should always be displayed instead of application crashes.

Examples:

| Situation | Message |
|------------|---------|
| Division by zero | Cannot divide by zero |
| Empty input | Please enter a value |
| Invalid expression | Invalid expression |
| Invalid equation | Invalid equation |
| Invalid number | Invalid number format |

---

# 12. History Strategy

History is used to store successful calculations.

History is saved only when an operation completes successfully.

Failed operations must never be stored.

History records include:

- Calculation type
- Original expression or input
- Result
- Timestamp

History is sorted by newest first.

Users can:

- View history
- Delete a single record
- Clear all history

The History module uses Room Database with LiveData to automatically update the UI whenever the database changes.

---

# 13. Feature Mapping

| Feature | Fragment | ViewModel | Repository | Database |
|----------|----------|-----------|------------|----------|
| Calculator | CalculatorFragment | CalculatorViewModel | — | — |
| Linear Equation | LinearEquationFragment | EquationViewModel | — | — |
| Quadratic Equation | QuadraticEquationFragment | EquationViewModel | — | — |
| Cubic Equation | CubicEquationFragment | EquationViewModel | — | — |
| System Equation | SystemEquationFragment | EquationViewModel | — | — |
| GCD & LCM | NumberToolsFragment | NumberToolsViewModel | — | — |
| Unit Converter | ConverterFragment | ConverterViewModel | — | — |
| History | HistoryFragment | HistoryViewModel | HistoryRepository | History |

---

# 14. Future Extension

The architecture is designed to support future modules with minimal modification.

Possible future features include:

- Scientific Calculator
- Formula Library
- Graph Plotter
- OCR (Image to Math)
- AI Assistant
- Cloud Synchronization
- Multi-language Support
- Favorite Calculations
- Calculation Export (PDF / Image)

New features should be implemented as independent modules following the existing MVVM architecture.

Whenever possible:

- Reuse existing utility classes.
- Reuse the History table.
- Avoid modifying stable modules.

This approach minimizes maintenance costs and keeps the project scalable.

---

# 15. Development Principles

The following principles should be followed throughout the project:

### Architecture

- Follow MVVM consistently.
- Separate UI, business logic and data layers.
- Keep modules loosely coupled.

### Code Quality

- One class has one responsibility.
- Keep methods small and readable.
- Avoid duplicated code.
- Write reusable components.
- Use meaningful names.
- Keep code clean and maintainable.

### Performance

- Avoid unnecessary object creation.
- Execute database operations on background threads.
- Observe LiveData only when necessary.

### Offline First

- The application must work completely offline.
- No Internet connection is required.
- No user account is required.
- No cloud synchronization is required.

### Documentation

Whenever a new feature is added:

- Update SRS.md if requirements change.
- Update ARCHITECTURE.md if the architecture changes.
- Update TASK.md to reflect implementation progress.

Keeping documentation synchronized with the source code helps ensure long-term maintainability and easier collaboration among team members.