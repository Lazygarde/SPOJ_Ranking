package com.example.spojranking.data

import com.example.spojranking.R


fun getListOfUser(): List<User> {
    val list: MutableList<User> = mutableListOf()
    list.add(User(R.drawable._7, "Đỗ Lý Minh Anh","@AnhDLM", 540, 500))
    list.add(User(R.drawable._6, "Trần Đức Chính", "@ChinhTD",90, 600))
    list.add(User(R.drawable._5, "Nguyễn Thành Công","@CongNT", 85, 550))
    list.add(User(R.drawable._4, "Phan Văn Duy","@DuyPV", 80, 520))
    list.add(User(R.drawable._3, "Bùi Quang Đạt", "@DatBQ",75, 500))
    list.add(User(R.drawable._2, "Lê Hải Đăng", "@DangLH",70, 490))
    list.add(User(R.drawable._1, "Lê Hương Giang", "@GiangLH",65, 475))
    list.add(User(R.drawable._8, "Lê Đình Hiệp", "@HiepLD",60, 460))
    list.add(User(R.drawable._9, "Hoàng Trung Hiếu", "@HieuHT",55, 445))
    list.add(User(R.drawable._10, "Nguyễn Bá Việt Hoàng", "@HoangNBV",50, 425))
    list.add(User(R.drawable._11, "Vũ Mạnh Hùng", "@HungVH",45, 420))
    list.add(User(R.drawable._12, "Mộc Thu Huyền", "@HuyenMT",40, 415))
    list.add(User(R.drawable._13, "Trần Tiểu Long", "@LongTT",35, 370))
    list.add(User(R.drawable._14, "Hoàng Hải Long", "@LongHH",32, 350))
    list.add(User(R.drawable._15, "Phạm Quang Minh", "@MinhPQ",30, 340))
    list.add(User(R.drawable._16, "Lê Tuấn Ngọc","@NgocLT", 27, 335))
    list.add(User(R.drawable._17, "Bùi Thế Vĩnh Nguyên","@NguyenBTV", 25, 310))
    list.add(User(R.drawable._18, "Nguyễn Thị Như Quỳnh","@QuynhNTN", 22, 300))
    list.add(User(R.drawable._19, "Bùi Thái Sĩ", "@SiBT",20, 280))
    list.add(User(R.drawable._20, "Trần Xuân Sơn", "@SonTX",18, 275))
    list.add(User(R.drawable._21, "Phan Thanh Tân", "@TanPT",15, 255))
    list.add(User(R.drawable._22, "Nguyễn Nhật Thành", "@ThanhNN",13, 240))
    list.add(User(R.drawable._23, "Phan Thị Hồng Thắm", "@ThamPTH",10, 235))
    list.add(User(R.drawable._24, "Nguyễn Hữu Tiến", "@TienNH",8, 230))
    list.add(User(R.drawable._25, "Đặng Huyền Trang", "@TrangDH",5, 220))
    list.add(User(R.drawable._26, "Nguyễn Hoa Thanh Tùng", "@TungNHT",3, 105))
    list.add(User(R.drawable._27, "Đoàn Thảo Vân", "@VanDT",1, 200))
    return list
}