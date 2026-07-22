# 01-srs.md — Software Requirements Specification

# 1. Tổng quan

## 1.1 Mục đích

Student Calculator là ứng dụng Android hỗ trợ học sinh THCS thực hiện các phép tính toán học và giải các dạng toán thường gặp trong chương trình học.

Ứng dụng hoạt động hoàn toàn offline, không yêu cầu đăng nhập và không sử dụng Internet.

---

## 1.2 Đối tượng sử dụng

- Học sinh THCS
- Giáo viên
- Người cần thực hiện các phép tính nhanh

---

## 1.3 Phạm vi

Ứng dụng hỗ trợ:

- Máy tính khoa học cơ bản
- Giải phương trình
- UCLN & BCNN
- Chuyển đổi đơn vị
- Lưu lịch sử tính toán

---

## 1.4 Ngoài phạm vi

Không hỗ trợ:

- OCR
- Giải toán bằng camera
- AI
- Tài khoản người dùng
- Đồng bộ Cloud
- Firebase
- Đồ thị hàm số
- Ma trận
- Số phức

---

# 2. Functional Requirements

## FR-01 Máy tính

Ứng dụng cung cấp máy tính khoa học với giao diện tương tự Samsung Calculator.

### Chức năng

Hỗ trợ các phép toán:

- +
- -
- ×
- ÷
- %
- ()
- .
- +/-
- x²
- xʸ
- √

### Chức năng hệ thống

- Xóa một ký tự (DEL)
- Xóa toàn bộ (AC)
- Tính kết quả (=)

### Quy tắc

- Dùng thư viện exp4j để tính biểu thức.
- Hiển thị tối đa 4 chữ số thập phân.
- Tự loại bỏ số 0 dư ở cuối.

Ví dụ:

```
5.0000 → 5

3.1400 → 3.14

1.333333 → 1.3333
```

### Edge cases

- Chia cho 0
- Biểu thức sai
- Ngoặc không khớp
- Biểu thức rỗng
- Biểu thức quá dài (30 ký tự)

Không được làm ứng dụng bị crash.

---

## FR-02 Công cụ

Màn hình Công cụ là nơi chứa toàn bộ chức năng hỗ trợ học tập.

### FR-02.1 Giải phương trình bậc nhất

Input

```
a
b
```

Giải:

```
ax+b=0
```

Hiển thị:

- nghiệm
- kết luận

---

### FR-02.2 Giải phương trình bậc hai

Input

```
a
b
c
```

Giải:

```
ax²+bx+c=0
```

Hiển thị

- Δ
- số nghiệm
- nghiệm

---

### FR-02.3 Giải phương trình bậc ba

Input

```
a
b
c
d
```

Giải:

```
ax³+bx²+cx+d=0
```

Hiển thị:

- các nghiệm thực

---

### FR-02.4 Giải hệ phương trình hai ẩn

Input

```
ax+by=c

dx+ey=f
```

Output

```
x

y
```

---

### FR-02.5 UCLN & BCNN

Input

Hai số nguyên dương.

Output

- UCLN
- BCNN

Sử dụng thuật toán Euclid.

---

### FR-02.6 Chuyển đổi đơn vị

Nhóm đơn vị

- Độ dài
- Khối lượng
- Nhiệt độ

---

## FR-03 Lịch sử

Lưu toàn bộ phép tính thành công.

Bao gồm

- Calculator
- Linear Equation
- Quadratic Equation
- Cubic Equation
- System Equation
- GCD/LCM
- Converter

Cho phép

- xem lịch sử
- xóa từng mục
- xóa toàn bộ

Sắp xếp

Mới nhất trước.

---

# 3. Non-functional Requirements

- Android 7.0 trở lên (API 24+)
- Java
- XML
- Material Design 3
- MVVM
- Room Database
- Hoạt động hoàn toàn Offline
- Không yêu cầu Internet
- Không yêu cầu tài khoản

---

# 4. Database

## Bảng History

| Cột | Kiểu | Mô tả |
|------|------|------|
| id | int | Primary Key, Auto Increment |
| type | String | Loại phép tính |
| expression | String | Biểu thức hoặc dữ liệu đầu vào |
| result | String | Kết quả |
| createdAt | long | Thời gian tạo |

### Giá trị của type

- calculator
- linear
- quadratic
- cubic
- system
- gcd_lcm
- converter

---

# 5. Thư viện sử dụng

- AndroidX
- Material Components
- RecyclerView
- Room
- LiveData
- ViewModel
- Navigation Component
- exp4j

Không sử dụng Firebase.