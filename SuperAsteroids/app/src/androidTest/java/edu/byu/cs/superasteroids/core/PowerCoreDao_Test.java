package edu.byu.cs.superasteroids.core;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.data_base.PowerCoresDao;
import edu.byu.cs.superasteroids.data_base.dbOpenHelper;

/**
 * Created by williamjones on 3/6/16.
 */
public class PowerCoreDao_Test extends AndroidTestCase
{
    private SQLiteDatabase db;
    private dbOpenHelper dbOpenHelpertest;
    private PowerCoresDao PowerCoresDAO;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        dbOpenHelpertest = new dbOpenHelper(getContext());
        db = dbOpenHelpertest.getWritableDatabase();
        PowerCoresDAO = new PowerCoresDao(db);
    }

    public void testAsteroidDAOCorrect() throws Exception {
        assertEquals(PowerCoresDAO.returnpowercoresarray(), PowerCoresDAO.returnpowercoresarray());
        assertEquals(PowerCoresDAO.returnpowercoresarray().size(), PowerCoresDAO.returnpowercoresarray().size());
        assertEquals(PowerCoresDAO.returnpowercoresarray().get(1).getCannonBoost(), PowerCoresDAO.returnpowercoresarray().get(1).getCannonBoost());
        assertNotSame(PowerCoresDAO.returnpowercoresarray().get(1).getEngineBoost(), PowerCoresDAO.returnpowercoresarray().get(0).getImage());
        assertNotSame(PowerCoresDAO.returnpowercoresarray().get(0).getCannonBoost(), PowerCoresDAO.returnpowercoresarray().get(0).getImage());
        assertEquals(PowerCoresDAO.returnpowercoresarray().get(0).getEngineBoost(), PowerCoresDAO.returnpowercoresarray().get(0).getEngineBoost());
        assertEquals(PowerCoresDAO.returnpowercoresarray().get(0).getImage(), PowerCoresDAO.returnpowercoresarray().get(0).getImage());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        dbOpenHelpertest = null;
    }
}