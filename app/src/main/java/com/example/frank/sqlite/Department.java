package com.example.frank.sqlite;

/**
 * Created by Frank on 10/23/2017.
 */

public class Department {
    //private variables
    int _deptId;
    String _deptName;
    String _deptHead;

    //empty constructor
    public Department(){

    }

    //constructor
    public Department(int deptId, String deptName, String deptHead){
        this._deptId = deptId;
        this._deptHead = deptHead;
        this._deptName = deptName;
    }

    //another constructor
    public Department(String deptName, String deptHead){
        this._deptName = deptName;
        this._deptHead = deptHead;
    }

    public int get_deptId() {
        return _deptId;
    }

    public void set_deptId(int _deptId) {
        this._deptId = _deptId;
    }

    public String get_deptName() {
        return _deptName;
    }

    public void set_deptName(String _deptName) {
        this._deptName = _deptName;
    }

    public String get_deptHead() {
        return _deptHead;
    }

    public void set_deptHead(String _deptHead) {
        this._deptHead = _deptHead;
    }
}
