package com.example.myapplication.model;

import java.io.Serializable;

public class Nhanvien implements Serializable {
    private int manv;
    private String ten;
    private int luongcb;
    private float songaycong;

    public Nhanvien() {
    }

    public Nhanvien(int manv, String ten, int luongcb, float songaycong) {
        this.manv = manv;
        this.ten = ten;
        this.luongcb = luongcb;
        this.songaycong = songaycong;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getLuongcb() {
        return luongcb;
    }

    public void setLuongcb(int luongcb) {
        this.luongcb = luongcb;
    }

    public float getSongaycong() {
        return songaycong;
    }

    public void setSongaycong(float songaycong) {
        this.songaycong = songaycong;
    }

    @Override
    public String toString() {
        return "Ma: " + manv +
                "\n Ten: '" + ten +
                "\n Luong Cb: " + luongcb +
                "\n So ngay cong: " + songaycong;
    }
}
