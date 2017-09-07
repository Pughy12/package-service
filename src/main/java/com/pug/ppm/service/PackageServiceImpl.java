package com.pug.ppm.service;

import com.pug.ppm.model.Package;
import com.pug.ppm.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Package Service implementation which allows for CRUD operations of packages
 *
 * @author Michael Pugh
 */
@Service
public class PackageServiceImpl implements PackageService {

    public PackageServiceImpl() {
        // TEST DATA - REMOVE THIS LATER
        Arrays.asList(
                new Product("1", "Jam", 100),
                new Product("2", "Marmalade", 99),
                new Product("3", "Peanut Butter", 120)
        );
    }

    //TODO: Create some sort of repository and use it here to do CRUD operations on packages

    @Override
    public Package createPackage(String id, String name, String description, List<Product> products) {
        final Package p = new Package(id, name, description, products, getPriceFromProductsList(products));

        // Save package to a repository

        // Return some success message saying it's been saved?
        return p;
    }

    @Override
    public Package getPackageById(String id) {
        return null;
    }

    @Override
    public List<Package> getAllPackages() {
        return null;
    }

    @Override
    public Package updatePackage(String id, Package newPackage) {
        return null;
    }

    @Override
    public void deletePackage(String id) {

    }

    private static double getPriceFromProductsList(List<Product> products) {
        double prices = 0;

        for (Product product : products) {
            prices += (double) product.getPrice();
        }

        return prices;
    }
}
