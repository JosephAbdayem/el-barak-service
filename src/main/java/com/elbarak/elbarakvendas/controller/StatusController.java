package com.elbarak.elbarakvendas.controller;

import com.elbarak.elbarakvendas.model.Status;
import com.elbarak.elbarakvendas.service.ServiceGenerico;
import com.elbarak.elbarakvendas.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController extends ControllerGenerico<Status> {

    StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    protected ServiceGenerico<Status> getServiceGenerico() {
        return statusService;
    }
}
