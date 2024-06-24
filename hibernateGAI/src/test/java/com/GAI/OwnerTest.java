package com.GAI;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @org.junit.jupiter.api.Test
    void testGetName() {
        Owner owner = new Owner();
        owner.setOid(0);
        assertEquals(0,owner.getOid());
    }

    @org.junit.jupiter.api.Test
    void testSetName() {
        Owner owner = new Owner();
        String name = "Petr";
        owner.setName(name);
        assertEquals(name,owner.getName());
    }

    @org.junit.jupiter.api.Test
    void testGetSurname() {
        Owner owner = new Owner("Petr","Petrov","22.05.2002");
        assertEquals("Petrov",owner.getSurname());
    }
    @org.junit.jupiter.api.Test
    void testSetSurname() {
    }

    @org.junit.jupiter.api.Test
    void testGetBirthday() {
    }

    @org.junit.jupiter.api.Test
    void testSetBirthday() {
    }

    @org.junit.jupiter.api.Test
    void testGetFullInfo() {
    }
}


