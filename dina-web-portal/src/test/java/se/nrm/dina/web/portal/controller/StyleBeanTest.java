package se.nrm.dina.web.portal.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class StyleBeanTest {

    private StyleBean instance;

    private final String home = "home";
    private final String collections = "collections";

    private final String current = "current";
    private final String activelink = "activelink";
    private final String inactivelink = "inactivelink";

    public StyleBeanTest() {
    }

    @Before
    public void setUp() {
        instance = new StyleBean();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getEnLink method, of class StyleBean.
     */
    @Test
    public void testGetEnLink() {
        System.out.println("getEnLink");

        String result = instance.getEnLink();
        assertNull(result);
    }

    /**
     * Test of getSvLink method, of class StyleBean.
     */
    @Test
    public void testGetSvLink() {
        System.out.println("getSvLink");
        String result = instance.getSvLink();
        assertEquals(activelink, result);
    }

    /**
     * Test of getTabStart method, of class StyleBean.
     */
    @Test
    public void testGetTabStart() {
        System.out.println("getTabStart");
        String result = instance.getTabStart();
        assertEquals(current, result);
    }

    /**
     * Test of getTabCollections method, of class StyleBean.
     */
    @Test
    public void testGetTabCollections() {
        System.out.println("getTabCollections");
        String result = instance.getTabCollections();
        assertNull(result);
    }

    /**
     * Test of setTabStyle method, of class StyleBean.
     */
    @Test
    public void testSetTabStyle1() {
        System.out.println("setTabStyle");

        instance.setTabStyle(home);
        assertEquals(current, instance.getTabStart());
        assertNull(instance.getTabCollections());
    }

    @Test
    public void testSetTabStyle3() {
        System.out.println("setTabStyle");

        instance.setTabStyle(collections);
        assertNull(instance.getTabStart());
        assertEquals(current, instance.getTabCollections());
    }

    @Test
    public void testSetTabStyle7() {
        System.out.println("setTabStyle");
        instance.setTabStyle("test");

        assertEquals(current, instance.getTabStart());
        assertNull(instance.getTabCollections());
    }

    /**
     * Test of setLanguageStyle method, of class StyleBean.
     */
    @Test
    public void testSetLanguageStyleSv() {
        String locale = "sv";
        instance.setLanguageStyle(locale);
        assertEquals(instance.getSvLink(), activelink);
        assertEquals(instance.getEnLink(), inactivelink);
    }

    @Test
    public void testSetLanguageStyleEn() {
        String locale = "en";
        instance.setLanguageStyle(locale);
        assertEquals(instance.getSvLink(), inactivelink);
        assertEquals(instance.getEnLink(), activelink);
    }

}
