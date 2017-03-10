package com.cothing.element.HALApi.operation;

import java.io.Serializable;
import org.waveprotocol.box.extension.util.WaveServerUtils;
import org.waveprotocol.box.extension.waveservice.ExtendWaveService;
import org.waveprotocol.wave.model.id.WaveId;
import org.waveprotocol.wave.model.id.WaveletId;
import com.cothing.element.HALApi.resource.HALOperations;
import com.google.wave.api.Blip;
import com.google.wave.api.OperationType;
import com.google.wave.api.Wavelet;

public class WaveletOperations implements Serializable {
	

	private String prefix = "@";
	private String domain = "local.net";
	private String waveletId = "conv+root";
	private ExtendWaveService extendWaveService;

	public void execute(HALOperations operations){
		OperationType type = operations.operationType;
		
		switch (type) {
		case BLIP_CREATE_CHILD:
			createChild(operations);
			break;
		case WAVELET_APPEND_BLIP:
			appendBlip(operations);
			break;
		case BLIP_DELETE:
			delete(operations);
			break;
		default:
			throw new UnsupportedOperationException(
					"This OperationService does not implement operation of type " + type.method());
		}
	}
	private Wavelet findWaveletByWaveId(String id, String author) {
		extendWaveService = WaveServerUtils.getExtendWaveService();
		WaveId waveId = WaveId.of(domain, id);
		WaveletId newWaveletId = WaveletId.of(domain, waveletId);
		Wavelet wavelet = extendWaveService.fetchWave(waveId, newWaveletId,
				author);
		return wavelet;
	}

	private boolean findBlipById(String waveId, String blipId, String author,
			String id) {
		boolean flag = false;
		Wavelet findWavelet = findWaveletByWaveId(waveId, author);
		Blip findBlip = findWavelet.getBlip(blipId);
		String content = findBlip.getContent();
		if (content.contains(id)) {
			flag = true;
		}
		return flag;
	}
	
	private void appendBlip(HALOperations operations){
		Wavelet wavelet = findWaveletByWaveId(operations.waveId,
				operations.author);
		Blip blip = wavelet.appendBlip(operations.blipId,
				"append new blip");
		
	}
	private void delete(HALOperations operations){
		Wavelet wavelet = findWaveletByWaveId(operations.waveId,
				operations.author);
		Blip findBlip = wavelet.getBlip(operations.blipId);
		wavelet.delete(findBlip);
		
	}
	private void createChild(HALOperations operations){
		Wavelet wavelet = findWaveletByWaveId(operations.waveId,
				operations.author);
		
		Blip findBlip = wavelet.getBlip(operations.blipId);
		
		Blip replyBlip = findBlip.reply();
	}
//	public Blip appendBlip(Wavelet wavelet, String blipId, String initialContent) {
//		Blip newBlip = newBlip(wavelet, initialContent, null, blipId, wavelet.getRootThread().getId());
//		return newBlip;
//	}
//	public  Blip newBlip(Wavelet wavelet, String initialContent, String parentBlipId, String blipId,
//			String threadId) {
//		Blip newBlip = new Blip(blipId, initialContent, parentBlipId, threadId, wavelet);
//		if (parentBlipId != null) {
//			Blip parentBlip = wavelet.getBlips().get(parentBlipId);
//			if (parentBlip != null) {
//				parentBlip.getChildBlipIds().add(newBlip.getBlipId());
//			}
//		}
//		wavelet.getBlips().put(newBlip.getBlipId(), newBlip);
//
//		BlipThread thread = wavelet.getThread(threadId);
//		if (thread != null) {
//			thread.appendBlip(newBlip);
//		}
//		return newBlip;
//	}
}
