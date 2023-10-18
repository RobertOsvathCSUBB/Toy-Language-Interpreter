package com.assignment2.robi.models.ADTs;

public interface IStack<T> 
{
    void push(T elem);
    T pop();
    Boolean isEmpty();     
}
