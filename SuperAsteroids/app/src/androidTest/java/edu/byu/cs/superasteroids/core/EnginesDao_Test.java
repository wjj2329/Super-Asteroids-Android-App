package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.EnginesDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class EnginesDao_Test extends AndroidTestCase
{
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private EnginesDao EnginesDAO;

    @Override public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        EnginesDAO = new EnginesDao(db);
    }
    public void testAsteroidDAOCorrect() throws Exception
    {
        assertEquals(EnginesDAO.returnaenginesarray(),EnginesDAO.returnaenginesarray());
        assertEquals(EnginesDAO.returnaenginesarray().size(), EnginesDAO.returnaenginesarray().size());
        assertEquals(EnginesDAO.returnaenginesarray().get(0).getImageWidth(), EnginesDAO.returnaenginesarray().get(0).getImageWidth());
        assertNotSame(EnginesDAO.returnaenginesarray().get(1).getImageWidth(), EnginesDAO.returnaenginesarray().get(0).getImage());
        assertNotSame(EnginesDAO.returnaenginesarray().get(0).getImageWidth(),EnginesDAO.returnaenginesarray().get(0).getImage());
        assertEquals(EnginesDAO.returnaenginesarray().get(0).getImageHeight(), EnginesDAO.returnaenginesarray().get(0).getImageHeight());
        assertEquals(EnginesDAO.returnaenginesarray().get(0).getImage(), EnginesDAO.returnaenginesarray().get(0).getImage());
    }
    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}
