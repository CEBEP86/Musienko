package io.sever86.controllers;


import io.sever86.dao.Dao;

import io.sever86.domain.Types;
import io.sever86.domain.Viborka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TaskController {
    @Autowired
    Dao dao;

    @RequestMapping(method = RequestMethod.GET, value = "/api/find-all-types")
    @ResponseBody
    public List<Types> findAllTypesClient() {
        return dao.findAllTypes();
    }


    @RequestMapping(method = RequestMethod.GET, path = "/api/viborka/{id}")
    @ResponseBody
    public List<Viborka> viborkaClient(@PathVariable Integer id) {
        return dao.viborka(id);
    }


}
