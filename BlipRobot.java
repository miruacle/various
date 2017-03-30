package com.kale.exercise;

public class App {

	public void onWaveletSelfAdded(String event) {
		// Create a new wavelet.
		createWave();
		
		// Append blips to the new wavelet.
		appendBlip();
		
		// Create FormElement and append it to blip.
		createElement();
				
	}
	
	public void createWave(){
		// SetupOAuth		
		// Use ExtendWaveService to create a new wave?
		
	}
	
	public void appendBlip() {
		/**
		 * Invoke wavelet.appendBlip() method to append a new blip
		 * and submit this operation to the server.
		 */
	}
	
	public void createElement() {
		// SetupOAuth
		// Get blips from current wavelet 
		// Create FormElements and append them to corrospanding blips
		// Try to save the BlipContentRefs object returned.
	}
}
