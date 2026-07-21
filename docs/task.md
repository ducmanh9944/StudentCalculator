# TASK.md

# Kế hoạch thực hiện dự án Student Calculator

> Tài liệu này dùng để theo dõi tiến độ dự án.
>
> Khi hoàn thành một công việc:
> - Đánh dấu `[x]`
> - Cập nhật ngày hoàn thành (nếu cần)
> - Chuyển sang task tiếp theo.
>
> Mọi thay đổi lớn về yêu cầu phải cập nhật trong **SRS.md**.
> Mọi thay đổi về kiến trúc phải cập nhật trong **ARCHITECTURE.md**.

---

# Thông tin dự án

**Tên dự án:** Student Calculator

**Nền tảng:** Android

**Ngôn ngữ:** Java + XML

**Kiến trúc:** MVVM

**Database:** Room

**Offline:** 100%

---

# Tiến độ

**Sprint hiện tại:** Sprint 1

**Tiến độ:** 0 / 25 Task

**Task đang thực hiện:**
> TASK-001 - Khởi tạo project

---

# Sprint 1 - Khởi tạo dự án

## TASK-001 Khởi tạo project

**Mục tiêu**

- Tạo project Android.
- Cấu hình compileSdk, targetSdk, minSdk.
- Chạy thành công trên Emulator.

**Checklist**

- [ ] Tạo project
- [ ] Đổi package name
- [ ] Cấu hình Material 3
- [ ] Build thành công
- [ ] Emulator chạy thành công

**Trạng thái**

> TODO

---

## TASK-002 Chuẩn bị cấu trúc project

**Checklist**

- [ ] Tạo package activity
- [ ] Tạo package fragment
- [ ] Tạo package adapter
- [ ] Tạo package database
- [ ] Tạo package repository
- [ ] Tạo package model
- [ ] Tạo package utils
- [ ] Tạo package listener
- [ ] Tạo package viewmodel

**Trạng thái**

> TODO

---

## TASK-003 Thiết lập Navigation

**Checklist**

- [ ] MainActivity
- [ ] Navigation Component
- [ ] Bottom Navigation
- [ ] Calculator Fragment
- [ ] Tools Fragment
- [ ] History Fragment
- [ ] Settings Fragment

**Trạng thái**

> TODO

---

## TASK-004 Tạo Database

**Checklist**

- [ ] HistoryEntity
- [ ] HistoryDao
- [ ] AppDatabase

**Trạng thái**

> TODO

---

## TASK-005 Chuẩn bị Theme

**Checklist**

- [ ] Light Theme
- [ ] Dark Theme
- [ ] Material Theme

**Trạng thái**

> TODO

---

# Sprint 2 - Máy tính

## TASK-006 Thiết kế giao diện Calculator

**Checklist**

- [ ] Màn hình Calculator
- [ ] Màn hình responsive
- [ ] Bàn phím máy tính
- [ ] Ô hiển thị biểu thức
- [ ] Ô hiển thị kết quả

**Trạng thái**

> TODO

---

## TASK-007 Logic Calculator

**Checklist**

- [ ] Phép cộng
- [ ] Phép trừ
- [ ] Phép nhân
- [ ] Phép chia
- [ ] %
- [ ] ()
- [ ] +/-
- [ ] Dấu chấm
- [ ] C
- [ ] Backspace
- [ ] =

**Trạng thái**

> TODO

---

## TASK-008 Xử lý lỗi Calculator

**Checklist**

- [ ] Chia cho 0
- [ ] Biểu thức sai
- [ ] Biểu thức rỗng
- [ ] Overflow
- [ ] Format kết quả

**Trạng thái**

> TODO

---

# Sprint 3 - Các công cụ toán học

## TASK-009 Phương trình bậc nhất

- [ ] UI
- [ ] Logic
- [ ] Validate
- [ ] Lưu History

---

## TASK-010 Phương trình bậc hai

- [ ] UI
- [ ] Logic
- [ ] Validate
- [ ] Lưu History

---

## TASK-011 UCLN & BCNN

- [ ] UI
- [ ] Logic
- [ ] Validate
- [ ] Lưu History

---

## TASK-012 Phân tích thừa số nguyên tố

- [ ] UI
- [ ] Logic
- [ ] Validate
- [ ] Lưu History

---

## TASK-013 Giai thừa

- [ ] UI
- [ ] Logic
- [ ] Validate
- [ ] Lưu History

---

## TASK-014 Đổi đơn vị

- [ ] UI
- [ ] Logic
- [ ] Validate
- [ ] Lưu History

---

## TASK-015 Thư viện công thức

- [ ] RecyclerView
- [ ] Adapter
- [ ] Dữ liệu
- [ ] Hình minh họa

---

# Sprint 4 - History

## TASK-016 Giao diện History

- [ ] RecyclerView
- [ ] Adapter
- [ ] Item Layout

---

## TASK-017 Logic History

- [ ] Thêm lịch sử
- [ ] Xóa 1 mục
- [ ] Xóa tất cả
- [ ] Sắp xếp theo thời gian

---

# Sprint 5 - Settings

## TASK-018 Theme

- [ ] Light Mode
- [ ] Dark Mode

---

## TASK-019 Thông tin ứng dụng

- [ ] Version
- [ ] Tác giả

---

## TASK-020 Xóa toàn bộ History

- [ ] Dialog xác nhận
- [ ] Xóa Database

---

# Sprint 6 - Kiểm thử

## TASK-021 Kiểm thử Calculator

- [ ] Tất cả phép tính
- [ ] Edge Case

---

## TASK-022 Kiểm thử Tools

- [ ] Equation
- [ ] GCD
- [ ] LCM
- [ ] Factorial
- [ ] Converter

---

## TASK-023 Kiểm thử History

- [ ] Insert
- [ ] Delete
- [ ] Clear

---

## TASK-024 Kiểm thử Theme

- [ ] Light
- [ ] Dark
- [ ] Restart App

---

## TASK-025 Hoàn thiện

- [ ] Fix bug
- [ ] Refactor
- [ ] Clean code
- [ ] Review project
- [ ] Hoàn thành tài liệu

---

# Ghi chú

## Quy tắc làm việc

- Không sửa SRS nếu không thay đổi yêu cầu.
- Không sửa Architecture nếu không thay đổi kiến trúc.
- Mỗi task chỉ hoàn thành khi build thành công.
- Không commit khi project đang lỗi.
- Luôn kiểm tra trên Emulator trước khi kết thúc ngày làm việc.

---

# Nhật ký

Ngày | Nội dung
----- | --------
| ... | ...