package com.cothing.element.HALApi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cothing.element.HALApi.operation.WaveletOperations;
import com.google.inject.Inject;

public class HALWaveletOperateServlet extends HttpServlet {
	private WaveletOperations waveletOperations;
	
	@Inject
	public HALWaveletOperateServlet(WaveletOperations waveletOperations) {
		this.waveletOperations = waveletOperations;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
	

}
