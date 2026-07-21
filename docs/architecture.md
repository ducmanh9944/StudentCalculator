# ARCHITECTURE.md

# Student Calculator - Technical Architecture

Version: 1.0

---

# 1. Overview

This document describes the technical architecture of the Student Calculator Android application.

The application follows a clean MVVM architecture and is designed to be:

- Modular
- Offline-first
- Easy to maintain
- Easy to extend
- Beginner-friendly

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

The application follows the MVVM pattern.

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

### Responsibilities

### UI Layer

Responsible for:

- Displaying data
- Receiving user input
- Observing LiveData
- Navigation

The UI layer must NOT:

- Access Room directly
- Contain business logic
- Perform mathematical calculations

---

### ViewModel Layer

Responsible for:

- Managing UI state
- Calling repositories
- Validating user input
- Preparing data for UI

The ViewModel must NOT:

- Hold Activity or Fragment references
- Access Views directly

---

### Repository Layer

Responsible for:

- Reading data
- Writing data
- Hiding the data source

Currently implemented:

- HistoryRepository

Future repositories may include:

- FormulaRepository

---

### Database Layer

Room is used for local persistence.

Current tables:

- History

Future versions may add more tables without affecting existing modules.

---

### Utility Layer

Contains pure Java classes.

Examples:

- EquationSolver
- MathUtils
- UnitConverter
- ExpressionEvaluator
- NumberFormatter
- ValidationUtils

Utility classes must:

- Be independent of Android APIs
- Be reusable
- Be easy to test

---

# 4. Project Structure

```
app/

├── activity/
│
├── adapter/
│
├── database/
│   ├── AppDatabase.java
│   ├── HistoryDao.java
│   └── HistoryEntity.java
│
├── fragment/
│
├── listener/
│
├── model/
│
├── repository/
│
├── utils/
│
└── viewmodel/
```

---

# 5. Feature Structure

```
Calculator

Equation Solver
    ├── Linear Equation
    └── Quadratic Equation

Math Tools
    ├── GCD
    ├── LCM
    ├── Prime Factorization
    └── Factorial

Formula Library

Unit Converter

History

Settings
```

---

# 6. Navigation

The application uses:

- Single Activity
- Multiple Fragments
- Navigation Component

Navigation flow:

```
MainActivity

│

├── Calculator

├── Tools
│     ├── Equation
│     ├── Math Tools
│     ├── Converter
│     └── Formula

├── History

└── Settings
```

Bottom Navigation contains four tabs:

- Calculator
- Tools
- History
- Settings

---

# 7. Package Responsibilities

| Package | Responsibility |
|-----------|----------------|
| activity | Application entry points |
| fragment | User interface |
| adapter | RecyclerView adapters |
| database | Room database |
| repository | Data access |
| model | Data models |
| utils | Business utilities |
| listener | Callback interfaces |
| viewmodel | UI state |

---

# 8. Database Design

Current database contains one table.

History

| Field | Type | Description |
|--------|------|-------------|
| id | INTEGER | Primary Key |
| type | TEXT | Calculator module |
| expression | TEXT | Original input |
| result | TEXT | Display result |
| createdAt | INTEGER | Unix timestamp |

History types:

- calculator
- linear_eq
- quadratic_eq
- gcd_lcm
- prime_factor
- factorial
- converter

Future modules should add new types instead of creating new history tables whenever possible.

---

# 9. Coding Conventions

## Naming

| Item | Convention |
|------|------------|
| Activity | MainActivity |
| Fragment | CalculatorFragment |
| ViewModel | CalculatorViewModel |
| Adapter | HistoryAdapter |
| Utility | MathUtils |
| XML | fragment_calculator.xml |
| Layout ID | btnEquals |
| String Resource | calculator_divide_zero |

---

## General Rules

- One class has one responsibility.
- Keep methods short and readable.
- Avoid duplicated code.
- Prefer reusable components.
- Use meaningful names.
- Avoid magic numbers.
- Avoid hardcoded strings.
- Keep business logic outside UI.

---

# 10. UI Guidelines

The application follows Material Design 3.

General principles:

- Simple
- Clean
- Student-friendly
- Responsive

Components:

- MaterialButton
- MaterialCardView
- RecyclerView
- TextInputLayout
- MaterialAlertDialog

Theme:

- Light Mode
- Dark Mode

Theme selection is persisted locally.

---

# 11. Error Handling

The application must never crash because of invalid user input.

Validation should occur before processing.

Examples:

- Empty input
- Division by zero
- Invalid expression
- Overflow
- Invalid equation
- Invalid number format

Errors should always be displayed using user-friendly messages.

---

# 12. History Strategy

History is saved only when an operation succeeds.

Failed calculations must not be stored.

History is sorted by newest first.

Users can:

- View history
- Delete one record
- Delete all records

---

# 13. Feature Mapping

| Feature | Fragment | ViewModel | Repository | Database |
|----------|----------|-----------|------------|----------|
| Calculator | CalculatorFragment | CalculatorViewModel | — | — |
| Equation Solver | EquationFragment | EquationViewModel | — | — |
| Math Tools | MathToolsFragment | MathToolsViewModel | — | — |
| Formula Library | FormulaFragment | FormulaViewModel | — | — |
| Unit Converter | ConverterFragment | ConverterViewModel | — | — |
| History | HistoryFragment | HistoryViewModel | HistoryRepository | History |
| Settings | SettingsFragment | — | — | — |

---

# 14. Future Extension

The architecture is designed to support future modules with minimal modification.

Possible future features:

- Scientific Calculator
- OCR
- AI Assistant
- Graph Plotter
- Cloud Sync
- Multi-language
- Favorite Formulas

New features should be added as independent modules following the existing architecture.

---

# 15. Development Principles

- Keep the application offline-first.
- Follow MVVM consistently.
- Reuse existing utility classes whenever possible.
- Maintain backward compatibility with existing data.
- Update documentation whenever architecture changes.