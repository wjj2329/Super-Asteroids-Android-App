package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.LevelBackgroundDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class LevelBackgroundDao_Test extends AndroidTestCase {
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private LevelBackgroundDao LevelBackgroundDAO;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        LevelBackgroundDAO = new LevelBackgroundDao(db);
    }

    public void testAsteroidDAOCorrect() throws Exception {
        assertEquals(LevelBackgroundDAO.returnlevelobject(), LevelBackgroundDAO.returnlevelobject());
        assertEquals(LevelBackgroundDAO.returnlevelobject().size(), LevelBackgroundDAO.returnlevelobject().size());
        assertEquals(LevelBackgroundDAO.returnlevelobject().get(1).getLevelid(), LevelBackgroundDAO.returnlevelobject().get(1).getLevelid());
        assertNotSame(LevelBackgroundDAO.returnlevelobject().get(1).getLevelid(), LevelBackgroundDAO.returnlevelobject().get(0).getPosition());
        assertNotSame(LevelBackgroundDAO.returnlevelobject().get(0).getLevelid(), LevelBackgroundDAO.returnlevelobject().get(0).getScale());
        assertEquals(LevelBackgroundDAO.returnlevelobject().get(0).getLevelid(), LevelBackgroundDAO.returnlevelobject().get(0).getLevelid());
        assertEquals(LevelBackgroundDAO.returnlevelobject().get(0).getObjectId(), LevelBackgroundDAO.returnlevelobject().get(0).getObjectId());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}