package com.pug.ppm.controller;

import com.pug.ppm.model.Package;
import com.pug.ppm.service.PackageService;
import com.pug.ppm.util.CustomErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mike on 07/09/2017.
 */
@RestController
public class PackageController {

    private PackageService packageService;

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/packages")
    public ResponseEntity<List<Package>> getAllPackages() {
        final List<Package> packages = packageService.getAllPackages();

        if (packages.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(packages);
        }
    }

    @GetMapping("/packages/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable("id") String id) {
        final Package p = packageService.getPackageById(id);

        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/packages/")
    public ResponseEntity<Package> createPackage(@Valid @RequestBody Package pkg, BindingResult result) throws URISyntaxException {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        final Package createdPackage = packageService.createPackage(pkg.getId(), pkg.getName(), pkg.getDescription(), pkg.getProducts());

        // TODO: Clean this up so it gets the location in a nicer way
        if (createdPackage != null) {
            return ResponseEntity.created(new URI("http://localhost:8080/packages/" + createdPackage.getId())).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @DeleteMapping("/packages/{id}")
    public ResponseEntity deletePackageById(@PathVariable String id) {
        final Package p = packageService.getPackageById(id);

        if (p == null) {
            logger.error("Unable to delete. Package with id '{}' not found.", id);
            return new ResponseEntity<>(new CustomErrorMessage("Unable to update. Package with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        final boolean result = packageService.deletePackage(id);

        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/packages/")
    public ResponseEntity deleteAllPackages() {
        final boolean result = packageService.deleteAllPackages();

        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity updatePackage(@Valid @RequestBody Package pkg, BindingResult result) {

        // Return 400 if request is bad
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        // Return 404 if package isn't available to update at all
        final Package p = packageService.getPackageById(pkg.getId());

        if (p == null) {
            logger.error("Unable to update. Package with id '{}' not found.", pkg.getId());
            return new ResponseEntity<>(new CustomErrorMessage("Unable to update. Package with id " + pkg.getId() + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        packageService.updatePackage(pkg.getId(), pkg);

        return ResponseEntity.ok().build();
    }

}
