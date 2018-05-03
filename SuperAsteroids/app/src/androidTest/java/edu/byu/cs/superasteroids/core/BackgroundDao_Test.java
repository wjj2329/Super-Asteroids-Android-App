package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.BackgroundDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class BackgroundDao_Test extends AndroidTestCase
{
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private BackgroundDao backgroundDAO;

    @Override public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        backgroundDAO = new BackgroundDao(db);
    }
    public void testAsteroidDAOCorrect() throws Exception
    {
        assertEquals(backgroundDAO.returnbackgroundarray(), backgroundDAO.returnbackgroundarray());
        assertEquals(backgroundDAO.returnbackgroundarray().get(0).getImagelink(), backgroundDAO.returnbackgroundarray().get(0).getImagelink());
        assertNotSame(backgroundDAO.returnbackgroundarray().get(0).getImagelink(), backgroundDAO.returnbackgroundarray().get(1).getImagelink());
    }
    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}
