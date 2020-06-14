package com.dogypedia;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.dogypedia.localStorage.Dao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

public class DaoTest {
    private Dao dao;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        dao = Dao.getInstance();
    }
    @Test
    public void changeColor() {
        dao.setColor("blue");
        assertEquals("blue", dao.getColor().getValue());
    }
    @Test
    public void changeImageUrl() {
        dao.changeImageURL("https://www.google.dk/");
        assertEquals("https://www.google.dk/", dao.getImageURL().getValue());
    }
}