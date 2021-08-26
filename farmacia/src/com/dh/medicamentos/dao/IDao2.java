package com.dh.medicamentos.dao;
import java.util.List;

public interface IDao2 <T>{

    public T guardar(T t);
    public List<T> buscarTodos();
}
