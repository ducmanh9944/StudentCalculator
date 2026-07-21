# SRS.md — Software Requirements Specification

# Student Calculator

Version: 1.0

---

# 1. Introduction

## 1.1 Purpose

Student Calculator is an Android application designed for Vietnamese middle school students (THCS). The application provides a simple calculator, equation solving, mathematical tools, formula reference, unit conversion, and calculation history.

The application is designed to work completely offline and focuses on simplicity, usability, and educational value.

---

## 1.2 Scope

The application supports:

- Basic calculator
- Linear equation solver
- Quadratic equation solver
- Mathematical tools
- Formula reference
- Unit conversion
- Calculation history
- Application settings

The application does NOT require an Internet connection.

---

## 1.3 Out of Scope

The following features are intentionally excluded:

- OCR / Camera math recognition
- AI-powered math solving
- User accounts
- Cloud synchronization
- Firebase
- Scientific calculator
- Graph plotting
- Cubic equations
- Systems of equations

---

# 2. Users

Primary users:

- Vietnamese middle school students (Grade 6–9)

User goals:

- Perform daily calculations quickly
- Solve common school equations
- Review mathematical formulas
- Convert common units
- Review previous calculations

---

# 3. Functional Requirements

---

## FR-01 Basic Calculator

### Description

The application shall provide a standard calculator.

### Functions

- Addition (+)
- Subtraction (-)
- Multiplication (×)
- Division (÷)
- Percentage (%)
- Parentheses
- Decimal numbers
- Positive / Negative
- Backspace
- Clear
- Equals

### Validation

- Maximum expression length: 30 characters
- Decimal separator: "."
- Empty expression is ignored

### Error Handling

- Division by zero
- Invalid expression
- Overflow
- Unmatched parentheses

The application must display a friendly error message instead of crashing.

### Output

- Display formatted result
- Remove trailing zeros
- Maximum 4 decimal places

Example:

```
5.0000 -> 5

3.1400 -> 3.14

1.333333 -> 1.3333
```

---

## FR-02 Calculation History

### Description

Store successful calculations.

### Functions

- View history
- View detail
- Delete one record
- Delete all records
- Sort by newest first

Each history record contains:

- Module type
- Expression
- Result
- Timestamp

---

## FR-03 Linear Equation Solver

Equation:

```
ax + b = 0
```

### Input

- a
- b

### Output

Possible results:

- One solution
- No solution
- Infinite solutions

Display:

- Formula
- Result
- Short explanation

Maximum precision:

- 4 decimal places

---

## FR-04 Quadratic Equation Solver

Equation

```
ax² + bx + c = 0
```

### Input

- a
- b
- c

### Validation

If

```
a = 0
```

Display

```
This is not a quadratic equation.
```

### Output

Display

- Δ
- Number of solutions
- Solution(s)
- Short explanation

Maximum precision:

- 4 decimal places

---

## FR-05 Mathematical Tools

The application shall provide the following tools.

### GCD

Input:

Two positive integers.

Output:

Greatest Common Divisor.

---

### LCM

Input:

Two positive integers.

Output:

Least Common Multiple.

---

### Prime Factorization

Input:

Positive integer (>1)

Output

Example

```
120

=

2³ × 3 × 5
```

---

### Factorial

Input

Non-negative integer

Maximum

```
20
```

Output

```
7!

=

5040
```

---

## FR-06 Unit Converter

Supported categories

### Length

- mm
- cm
- dm
- m
- km

### Weight

- mg
- g
- kg
- ton

### Temperature

- Celsius
- Fahrenheit
- Kelvin

### Rules

Maximum precision:

- 2 decimal places

Kelvin cannot be negative.

---

## FR-07 Formula Library

Provide static mathematical formulas.

Categories

- Square
- Rectangle
- Triangle
- Circle
- Rectangular Prism
- Cylinder

Each formula contains

- Name
- Formula
- Illustration

No calculation is performed in this module.

---

## FR-08 Settings

The application shall provide:

- Dark mode
- Light mode
- Delete all history
- Application information

Theme settings must persist after restarting the application.

---

# 4. General Validation Rules

The application shall:

- Reject invalid input
- Reject unsupported number format
- Ignore leading/trailing spaces
- Prevent application crashes caused by invalid input
- Display user-friendly error messages

---

# 5. Non-functional Requirements

Platform

- Android 7.0+
- API 24+

Technology

- Java
- XML

Performance

- Offline only
- Fast response
- Smooth UI

Compatibility

- Different Android phone sizes
- Portrait mode

Usability

- Simple interface
- Easy for middle school students
- Material Design 3

Reliability

- Data stored locally
- History preserved after restarting

---

# 6. Database

History

| Field | Type | Description |
|--------|------|-------------|
| id | INTEGER | Primary Key |
| type | TEXT | Calculator module |
| expression | TEXT | Original expression |
| result | TEXT | Display result |
| createdAt | INTEGER | Unix timestamp |

---

# 7. Main Modules

- Calculator
- Equation Solver
- Mathematical Tools
- Formula Library
- Unit Converter
- History
- Settings

---

# 8. Acceptance Criteria

The project is considered complete when:

- All functional requirements are implemented.
- No critical crashes occur during normal usage.
- All calculations produce correct results.
- History is stored correctly.
- Theme settings persist after restarting.
- The application works completely offline.
- The application runs successfully on Android API 24 and above.

---

# 9. Future Enhancements

The following features are not included in Version 1.0 but may be implemented in future releases.

- Scientific calculator
- Favorite formulas
- OCR math recognition
- AI assistant
- Graph plotting
- Cloud synchronization
- Multi-language support