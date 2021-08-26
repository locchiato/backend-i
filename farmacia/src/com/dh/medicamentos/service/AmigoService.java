package com.dh.medicamentos.service;

import com.dh.medicamentos.dao.IDao2;
import com.dh.medicamentos.model.Amigo;
import java.util.List;

public class AmigoService {

    private IDao2<Amigo> dao;

    public AmigoService(IDao2<Amigo> dao)
    {
        this.dao = dao;
    }

    public void setDao(IDao2<Amigo> dao) {
        this.dao = dao;
    }

    public Amigo guardarAmigo(Amigo amigo)
    {
        return dao.guardar(amigo);
    }

    public List<Amigo> listarAmigos()
    {
        return dao.buscarTodos();
    }






}
