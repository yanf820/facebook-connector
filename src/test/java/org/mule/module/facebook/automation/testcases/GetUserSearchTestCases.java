/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.module.facebook.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.tests.ConnectorTestUtils;

import com.restfb.types.Post;

public class GetUserSearchTestCases extends FacebookTestParent {

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("getUserSearchTestData");
		
		String profileId = getProfileId();
		upsertOnTestRunMessage("user", profileId);
		
		String messageId = publishMessage(profileId, (String) getTestRunMessageValue("msg"));
		Thread.sleep(2000);
		upsertOnTestRunMessage("objectId", messageId);
	}
	
	@SuppressWarnings("unchecked")
	@Category({RegressionTests.class})
	@Test
	public void testGetUserSearch() {
		try {
			List<Post> result = runFlowAndGetPayload("get-user-search");
			
			String messageId = (String) getTestRunMessageValue("objectId");
			boolean found = false;
			for(Post post : result) {
				if(messageId.equals(post.getId())) {
					found = true;
					break;
				}
 			}
			
			assertTrue(found);
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		String objectId = (String) getTestRunMessageValue("objectId");
		deleteObject(objectId);
	}
}