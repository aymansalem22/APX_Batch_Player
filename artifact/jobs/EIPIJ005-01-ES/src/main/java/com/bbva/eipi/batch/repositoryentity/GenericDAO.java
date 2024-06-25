package com.bbva.eipi.batch.repositoryentity;

import java.util.List;

public interface GenericDAO<E>{

    public E create(E t);

    //operations
    public E update(E t);

    public E get(Object id);

    public void delete(Object id);

    public List<E> listAll();

    public long count();
}
