/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.module.facebook.automation.testcases;

import com.restfb.types.Video;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class GetUserVideosUploadedTestCases extends FacebookTestParent {

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getUserVideosUploadedTestData");

        String profileId = getProfileId();
        upsertOnTestRunMessage("user", profileId);
    }

    @SuppressWarnings("unchecked")
    @Category({RegressionTests.class})
    @Test
    public void testGetUserVideosUploaded() {
        try {
            List<Video> result = runFlowAndGetPayload("get-user-videos-uploaded");
            assertNotNull(result);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

}
