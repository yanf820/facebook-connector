/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.module.facebook.automation.testcases;

import com.restfb.types.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.*;

public class GetNoteTestCases extends FacebookTestParent {

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getNoteTestData");

        String profileId = getProfileId();
        upsertOnTestRunMessage("profileId", profileId);

        String msg = getTestRunMessageValue("msg").toString();
        String subject = getTestRunMessageValue("subject").toString();

        String noteid = publishNote(getProfileId(), msg, subject);
        upsertOnTestRunMessage("note", noteid);
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testGetNote() {
        try {
            String noteId = (String) getTestRunMessageValue("note");
            String msg = (String) getTestRunMessageValue("msg");
            String subject = (String) getTestRunMessageValue("subject");

            Note note = runFlowAndGetPayload("get-note");

            assertEquals(noteId, note.getId());
            assertTrue(note.getMessage().contains(msg));
            assertEquals(note.getSubject(), subject);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        String noteId = (String) getTestRunMessageValue("note");
        deleteObject(noteId);
    }
}
