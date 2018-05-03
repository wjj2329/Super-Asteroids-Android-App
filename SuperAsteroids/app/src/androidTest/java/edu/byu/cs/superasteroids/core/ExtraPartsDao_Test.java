package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.ExtraPartsDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class ExtraPartsDao_Test extends AndroidTestCase
{
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private ExtraPartsDao ExtraPartsDAO;

    @Override public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        ExtraPartsDAO = new ExtraPartsDao(db);
    }
    public void testAsteroidDAOCorrect() throws Exception
    {
        assertEquals(ExtraPartsDAO.returnextrapartsarray(),ExtraPartsDAO.returnextrapartsarray());
        assertEquals(ExtraPartsDAO.returnextrapartsarray().size(), ExtraPartsDAO.returnextrapartsarray().size());
        assertEquals(ExtraPartsDAO.returnextrapartsarray().get(1).getImage(), ExtraPartsDAO.returnextrapartsarray().get(1).getImage());
        assertNotSame(ExtraPartsDAO.returnextrapartsarray().get(1).getAttachpoint(), ExtraPartsDAO.returnextrapartsarray().get(0).getImage());
        assertNotSame(ExtraPartsDAO.returnextrapartsarray().get(0).getImage(),ExtraPartsDAO.returnextrapartsarray().get(0).getAttachpoint());
        assertEquals(ExtraPartsDAO.returnextrapartsarray().get(0).getAttachpoint(), ExtraPartsDAO.returnextrapartsarray().get(0).getAttachpoint());
        assertEquals(ExtraPartsDAO.returnextrapartsarray().get(0).getImage(), ExtraPartsDAO.returnextrapartsarray().get(0).getImage());
    }
    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}

