package io.sever86.dao;

import io.sever86.domain.Types;
import io.sever86.domain.Viborka;

import java.util.List;

/**
 * Created by Администратор on 24.11.2016.
 */
public interface
Dao {

    List<Types> findAllTypes();

    List<Viborka> viborka(Integer id);
}
