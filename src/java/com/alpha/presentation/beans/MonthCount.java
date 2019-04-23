/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.presentation.beans;

/**
 *
 * @author Tsotsoh
 */
public class MonthCount {
    private int count;
    private int month;

    public MonthCount() {
    }

    public MonthCount(int count, int month) {
        this.count = count;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
