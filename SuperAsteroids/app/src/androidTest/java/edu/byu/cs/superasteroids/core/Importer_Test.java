package edu.byu.cs.superasteroids.core;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.test.AndroidTestCase;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import edu.byu.cs.superasteroids.importer.MyDataImporter;

/**
 * Created by williamjones on 3/6/16.
 */
public class Importer_Test extends AndroidTestCase {
    private MyDataImporter importer;
    private AssetManager assetManager;
    private Resources resources;

    /**
     * Overriding setUp method to initialize everything.
     *
     * @throws Exception
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context c = getContext();
        importer = new MyDataImporter(c);
        resources = c.getResources();
        assetManager = resources.getAssets();
    }

    /**
     * Overriding tearDown method.
     *
     * @throws Exception
     */
    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
        importer = null;
    }
    public void testLoadingModels() throws Exception {
        boolean tryImport = false;
        try {
            tryImport = importer.importData(new InputStreamReader
                    (new BufferedInputStream(assetManager.open("gamedata.json"))));
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        assertTrue("THIS IS NOT TRUE", tryImport);

    }

}