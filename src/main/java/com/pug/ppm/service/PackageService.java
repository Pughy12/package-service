package com.pug.ppm.service;

import com.pug.ppm.model.Package;
import com.pug.ppm.model.Product;

import java.util.List;

/**
 * Created by Mike on 07/09/2017.
 */
//TODO: Can we use Spring Rest Repository so we don't need to manually create CRUD operations?
public interface PackageService {

    // create
    Package createPackage(String id, String name, String description, List<Product> products);

    // retrieve
    Package getPackageById(String id);
    List<Package> getAllPackages();

    // update
    Package updatePackage(String id, Package newPackage);

    // delete
    void deletePackage(String id);
}
