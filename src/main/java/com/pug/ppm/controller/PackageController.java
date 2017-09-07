package com.pug.ppm.controller;

import com.pug.ppm.model.Package;
import com.pug.ppm.model.Product;
import com.pug.ppm.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mike on 07/09/2017.
 */
@RestController
public class PackageController {

    private PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/packages")
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @GetMapping("/packages/{id}")
    public Package getPackageById(@PathParam("id") String id) {
        return packageService.getPackageById(id);
    }

}
