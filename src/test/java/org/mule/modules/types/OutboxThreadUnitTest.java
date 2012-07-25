/**
 * Mule Facebook Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

 package org.mule.modules.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mule.module.facebook.types.OutboxThread;
 
public class OutboxThreadUnitTest
{
    private OutboxThread outboxThread;
    
    @Before
    public void setup()
    {
        outboxThread = new OutboxThread();
    }
    
    @Test
    public void testGettersAndSetters()
    {
        final String message = "hello world!";
        outboxThread.setMessage(message);
        assertEquals(outboxThread.getMessage(), message);
    }
}

