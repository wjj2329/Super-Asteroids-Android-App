package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.AsteroidDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class AsteroidDao_Test extends AndroidTestCase
{
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private AsteroidDao asteroidDAO;

    @Override public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        asteroidDAO = new AsteroidDao(db);
    }
    public void testAsteroidDAOCorrect() throws Exception
    {
        assertEquals(asteroidDAO.returnasteroidsarray().size(), asteroidDAO.returnasteroidsarray().size());
        assertEquals(asteroidDAO.returnasteroidsarray().get(0).getImageHeight(), asteroidDAO.returnasteroidsarray().get(0).getImageHeight());
        assertEquals(asteroidDAO.returnasteroidsarray().get(0).getImagepic(), asteroidDAO.returnasteroidsarray().get(0).getImagepic());
        assertNotSame(asteroidDAO.returnasteroidsarray().get(0).getImagepic(), asteroidDAO.returnasteroidsarray().get(0).getImageHeight());
        assertNotSame(asteroidDAO.returnasteroidsarray().get(0).getImageWidth(), asteroidDAO.returnasteroidsarray().get(1).getImagepic());
    }
    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
        dbOpenHelpertest = null;
    }

}
