package com.iontrading.cat.foureyes.client_lib.api.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.exceptions.UnsupportedOperationException;
import com.iontrading.cat.foureyes.client_lib.mock.MockSubmitterFunctions;
import com.iontrading.cat.foureyes.client_lib.notification.FourEyesRequestImpl;
import com.iontrading.cat.foureyes.client_lib.notification.ModifiableFourEyesRequest;
import com.iontrading.cat.foureyes.client_lib.submitter.PendingRequestSubmissionBuilderImpl;
import com.iontrading.isf.log.support.MockITracer;

public class FourEyesServiceImplTest {

	private FourEyesServiceImpl fourEyesService;
	private MockSubmitterFunctions functions;
	private ModifiableFourEyesRequest fourEyesRequest;

	@Before
	public void setUp() {
		functions = new MockSubmitterFunctions();
		fourEyesService = new FourEyesServiceImpl(functions, null, 999, new MockITracer());
		fourEyesRequest = new FourEyesRequestImpl();
	}

	@Test
	public void testRequestForEntity() {
		Assert.assertEquals(PendingRequestSubmissionBuilderImpl.class,
				fourEyesService
						.createPendingRequest("entitlementFeature", "entitlementNamespace", "entitlementResource",
								"entityCategory", "entityType", "entityDescription", "entityName", "requestedBy")
						.getClass());
	}

	@Test
	public void testAcknowledgeApproval() throws UnsupportedOperationException {
		fourEyesRequest.setStatus(RequestStatus.Approved);
		fourEyesService.acknowledgeUpdate(fourEyesRequest, "Comment");

		Assert.assertTrue(functions.isFourEyesRequestProcessed());
	}

	@Test
	public void testRejectApproval() throws UnsupportedOperationException {
		fourEyesRequest.setStatus(RequestStatus.Approved);
		fourEyesService.denyUpdate(fourEyesRequest, "Comment");

		Assert.assertTrue(functions.isFourEyesRequestDenied());

	}

	@Test
	public void testAcknowledgeRejction() throws UnsupportedOperationException {
		fourEyesRequest.setStatus(RequestStatus.Rejected);
		fourEyesService.acknowledgeUpdate(fourEyesRequest, "Comment");

		Assert.assertTrue(functions.isFourEyesRequestProcessed());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDenyRejction() throws UnsupportedOperationException {
		fourEyesRequest.setStatus(RequestStatus.Rejected);
		fourEyesService.denyUpdate(fourEyesRequest, "Comment");
	}
	
	@Test
	public void testCancelPendingRequest() {
		fourEyesService.withdrawPendingRequest("Id","Comment");
		Assert.assertTrue(functions.isWithdrawPendingRequestCalled());
	}

}
