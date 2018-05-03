package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.MainBodiesDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class MainBodiesDao_Test extends AndroidTestCase
{
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private MainBodiesDao MainBodiesDAO;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        MainBodiesDAO = new MainBodiesDao(db);
    }

    public void testAsteroidDAOCorrect() throws Exception {
        assertEquals(MainBodiesDAO.returnmainbodiesarray(), MainBodiesDAO.returnmainbodiesarray());
        assertEquals(MainBodiesDAO.returnmainbodiesarray().size(), MainBodiesDAO.returnmainbodiesarray().size());
        assertEquals(MainBodiesDAO.returnmainbodiesarray().get(1).getCannonAttach(), MainBodiesDAO.returnmainbodiesarray().get(1).getCannonAttach());
        assertNotSame(MainBodiesDAO.returnmainbodiesarray().get(1).getCannonAttach(), MainBodiesDAO.returnmainbodiesarray().get(0).getImage());
        assertNotSame(MainBodiesDAO.returnmainbodiesarray().get(0).getExtraAttach(), MainBodiesDAO.returnmainbodiesarray().get(0).getImageWidth());
        assertEquals(MainBodiesDAO.returnmainbodiesarray().get(0).getImageWidth(), MainBodiesDAO.returnmainbodiesarray().get(0).getImageWidth());
        assertEquals(MainBodiesDAO.returnmainbodiesarray().get(0).getCannonAttach(), MainBodiesDAO.returnmainbodiesarray().get(0).getCannonAttach());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}