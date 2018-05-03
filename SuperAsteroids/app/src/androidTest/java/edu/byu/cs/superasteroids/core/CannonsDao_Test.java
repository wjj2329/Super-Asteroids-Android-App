package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.CannonsDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class CannonsDao_Test extends AndroidTestCase
{
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private CannonsDao CannonsDAO;

    @Override public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        CannonsDAO = new CannonsDao(db);
    }
    public void testAsteroidDAOCorrect() throws Exception
    {
        assertEquals(CannonsDAO.returncannonsarray(), CannonsDAO.returncannonsarray());
        assertEquals(CannonsDAO.returncannonsarray().size(), CannonsDAO.returncannonsarray().size());
        assertEquals(CannonsDAO.returncannonsarray().get(0).getImageWidth(), CannonsDAO.returncannonsarray().get(0).getImageWidth());
        assertNotSame(CannonsDAO.returncannonsarray().get(1).getImageWidth(), CannonsDAO.returncannonsarray().get(0).getImage());
        assertNotSame(CannonsDAO.returncannonsarray().get(0).getImageWidth(), CannonsDAO.returncannonsarray().get(0).getAttackimage());
        assertEquals(CannonsDAO.returncannonsarray().get(0).getImageHeight(), CannonsDAO.returncannonsarray().get(0).getImageHeight());
        assertEquals(CannonsDAO.returncannonsarray().get(0).getImage(), CannonsDAO.returncannonsarray().get(0).getImage());
    }
    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}
