package org.solent.com504.factoryandfacade.model;

import java.util.List;

public interface FarmFacade {

    public List<Animal> getAllAnimals();

    public void addDog(String name);

    public void addCat(String name);

    public void addCow(String name);

    public boolean addAnimal(String animalType, String name);

    public List<Animal> getAnimalsOfType(String animalType);

    public Animal getAnimal(String Name);

    public void removeAnimal(String Name);
}
