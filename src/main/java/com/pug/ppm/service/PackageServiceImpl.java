package com.pug.ppm.service;

import com.pug.ppm.model.Package;
import com.pug.ppm.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Package Service implementation which allows for CRUD operations of packages
 *
 * @author Michael Pugh
 */
@Service
public class PackageServiceImpl implements PackageService {

    private List<Package> packagesList;

    public PackageServiceImpl() {
        this.packagesList = new ArrayList<>();
    }

    @Override
    public Package createPackage(String id, String name, String description, List<Product> products) {
        final Package p = new Package(id, name, description, products);

        // Duplicate check
        for (Package pkg : packagesList) {
            if (pkg.getId().equalsIgnoreCase(p.getId())) {
                return null;
            }
        }

        this.packagesList.add(p);

        return p;
    }

    @Override
    public Package getPackageById(String id) {
        for (Package pkg : packagesList) {
            if (pkg.getId().equalsIgnoreCase(id)) {
                return pkg;
            }
        }

        return null;
    }

    @Override
    public List<Package> getAllPackages() {
        return packagesList;
    }

    @Override
    public void updatePackage(String id, Package newPackage) {

        // look through list for the package and remove it if found
        packagesList.stream()
            .filter(p -> id.equalsIgnoreCase(p.getId()))
            .findFirst()
            .ifPresent(p -> packagesList.remove(p));

        // add the new package
        packagesList.add(newPackage);
    }

    @Override
    public boolean deletePackage(String id) {
        return packagesList.remove(getPackageById(id));
    }

    @Override
    public boolean deleteAllPackages() {
        packagesList.clear();
        return packagesList.isEmpty();
    }
}
