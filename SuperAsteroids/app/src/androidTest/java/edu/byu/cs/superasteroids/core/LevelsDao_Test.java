package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.LevelsDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class LevelsDao_Test extends AndroidTestCase {
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private LevelsDao LevelDAO;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        LevelDAO = new LevelsDao(db);
    }

    public void testAsteroidDAOCorrect() throws Exception {
        assertEquals(LevelDAO.returnlevelinfoarray(), LevelDAO.returnlevelinfoarray());
        assertEquals(LevelDAO.returnlevelinfoarray().size(), LevelDAO.returnlevelinfoarray().size());
        assertEquals(LevelDAO.returnlevelinfoarray().get(1).getHeight(), LevelDAO.returnlevelinfoarray().get(1).getHeight());
        assertNotSame(LevelDAO.returnlevelinfoarray().get(1).getHeight(), LevelDAO.returnlevelinfoarray().get(0).getHint());
        assertNotSame(LevelDAO.returnlevelinfoarray().get(0).getMusic(), LevelDAO.returnlevelinfoarray().get(0).getTitle());
        assertEquals(LevelDAO.returnlevelinfoarray().get(0).getMusic(), LevelDAO.returnlevelinfoarray().get(0).getMusic());
        assertEquals(LevelDAO.returnlevelinfoarray().get(0).getTitle(), LevelDAO.returnlevelinfoarray().get(0).getTitle());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}