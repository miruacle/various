package org.waveprotocol.box.extension.server.robots.search;

import static org.waveprotocol.box.server.robots.agent.RobotAgentUtil.appendLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.waveprotocol.box.extension.util.WaveServerUtils;
import org.waveprotocol.box.extension.waveservice.ExtendWaveService;
import org.waveprotocol.box.server.account.RobotAccountData;
import org.waveprotocol.box.server.persistence.AccountStore;
import org.waveprotocol.box.server.persistence.PersistenceException;
import org.waveprotocol.box.server.robots.register.RobotRegistrar;
import org.waveprotocol.wave.model.wave.ParticipantId;
import org.waveprotocol.wave.model.waveref.WaveRef;

import com.google.inject.Inject;
import com.google.wave.api.Annotation;
import com.google.wave.api.Annotations;
import com.google.wave.api.Blip;
import com.google.wave.api.BlipContent;
import com.google.wave.api.BlipContentRefs;
import com.google.wave.api.ElementType;
import com.google.wave.api.FormElement;
import com.google.wave.api.Image;
import com.google.wave.api.Wavelet;
import com.google.wave.api.event.DocumentChangedEvent;
import com.google.wave.api.event.WaveletSelfAddedEvent;
import com.typesafe.config.Config;

@SuppressWarnings("serial")
public class SerialRobot extends AbstractSearchRobot {
	public static final String ROBOT_URI = AGENT_PREFIX_URI + "/serial";
	private ExtendWaveService extendWaveService;

	@Inject
	public SerialRobot(Config config,
			ServerFrontendAddressHolder frontendAddressHolder,
			AccountStore accountStore, RobotRegistrar robotRegistator) {
		super(config.getString("core.wave_server_domain"),
				frontendAddressHolder, accountStore, robotRegistator, config
						.getBoolean("security.enable_ssl"));
	}

	@Override
	public void onWaveletSelfAdded(WaveletSelfAddedEvent event) {
		String robotAddress = event.getWavelet().getRobotAddress();
		// Display a short description.
		appendLine(event.getBlip(), "\n" + robotAddress + ": I am listening.\n");

		try {
			Wavelet wavelet = createWave(event.getModifiedBy(),
					"TBD_local.net/conv+root_testrootblipid");
			// extendWaveService.appendBlip(wavelet,
			// "TBD_local.net/conv+root_testappendblipid",
			// "append blip to new wave" + wavelet.getWaveId());
			wavelet = appendBlipToWavelet(wavelet);
			createElement(wavelet);
			JSONObject json = serial(wavelet);
//			System.out.println("json:" + json);
			appendLine(event.getBlip(), json + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onDocumentChanged(DocumentChangedEvent event) {
	
	}

	public void createElement(Wavelet wavelet) throws IOException {
		RobotAccountData account = null;
		String rpcUrl = getFrontEndAddress() + "/robot/rpc";
		try {
			account = getAccountStore().getAccount(
					ParticipantId
							.ofUnsafe(getRobotId() + "@" + getWaveDomain()))
					.asRobot();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		setupOAuth(account.getId().getAddress(), account.getConsumerSecret(),
				rpcUrl);
		Map<String, Blip> blips = wavelet.getBlips();
		Blip rootBlip = wavelet.getRootBlip();
		Blip secondBlip = wavelet
				.getBlip("TBD_local.net/conv+root_testheiheihei");
		Blip thirdBlip = wavelet
				.getBlip("TBD_local.net/conv+root_testhahaha");

		// System.out.println("rootBlip:"+rootBlip.getCreator()+" : "+rootBlip.getBlipId());
		Map element1 = new HashMap();
		element1.put("factoryName", "KUNE");
		element1.put("address", "高新区");
		FormElement fm1 = new FormElement(ElementType.STATE, element1);
		
		Map element2 = new HashMap();
		element2.put("telphone", "12345");
		element2.put("contact", "67890");
		FormElement fm2 = new FormElement(ElementType.STATE, element2);

		Map element3 = new HashMap();
		element3.put("categoryName", "mock");
		element3.put("categoryType", "dddd");
		FormElement fm3 = new FormElement(ElementType.STATE, element3);
		
		Map element4 = new HashMap();
		element4.put("soldName", "top");
		element4.put("soldType", "goods");
		FormElement fm4 = new FormElement(ElementType.STATE, element4);

		Image image = new Image();
		image.setProperty("url", "/home/liqian/image/tax.jpg");
		Image image1 = new Image();
		image1.setProperty("url", "/home/liqian/image/ddd.jpg");
		image.setHeight(100);
		Image image2 = new Image();
		image2.setProperty("images2", "/home/liqian/image/idCard.jpg");
		image2.setHeight(102);

		BlipContentRefs refs1 = rootBlip.append(fm1);
//		refs1.annotate("KUNE", "http://www.baidu.com");
		BlipContentRefs anno1 = refs1.annotate("link", "http://www.baidu.com");
		anno1.annotate("KUNE","http://www.baidu.com");
		BlipContentRefs refs2 = rootBlip.append(fm2);
		refs2.annotate("12345", "http://www.sinc.com");
		BlipContentRefs refs3 = secondBlip.append(fm3);
		refs3.annotate("mock", "http://www.gogoole.com");
		BlipContentRefs refs4 = thirdBlip.append(fm4);
		refs4.annotate("top", "http://www.top.com");
		BlipContentRefs refs5 = thirdBlip.append(fm4);
		refs5.annotate("goods", "http://www.soldtype.com");
		 rootBlip.append(image1);
		 secondBlip.append(image2);
		 thirdBlip.append(image);


	}

	

	public JSONObject serial(Wavelet wavelet){
		Map start = new HashMap();
		Map embedded = new HashMap();
		Map blips = wavelet.getBlips();
		Set<String> blipIds = blips.keySet();
		
		Iterator iter = blipIds.iterator();
		while(iter.hasNext()){
			Object blipId = iter.next();
			Blip blip = (Blip) blips.get(blipId);
			
			BlipContentRefs refs = blip.all(ElementType.STATE);
			List<BlipContent> contentList = refs.values();
			
			for (int i = 0; i < contentList.size(); i++) {
				BlipContent content = contentList.get(i);
				Map<String,String> properties = content.asElement().getProperties();
				
				Set<String> keys = properties.keySet();
				Iterator it = keys.iterator();
				while (it.hasNext()) {
					Object key = it.next();
					start.put(key, properties.get(key));
					}
			//annotation
				start = serialAnnotation(wavelet,start,properties,blip);
			}	
			//embedded
			
			 start = serialEmbedded(wavelet,start,blip);
		}
		
		JSONObject json = JSONObject.fromObject(start);
		
		return json;
	}
	
	
	public Map serialAnnotation(Wavelet wavelet, Map start,
			Map<String, String> properties,Blip blip) {
		
		Collection<String> values = properties.values();
		// annotation
		Annotations anno = blip.getAnnotations();
		// annotation类型为STATE
		List<Annotation> list = anno.asList();
		for (int i = 0; i < list.size(); i++) {
			Annotation annota = list.get(i);

			if (values.contains(annota.getName())) {
				if (start.get("_link") != null) {
					Map link = (Map) start.get("_link");
					link.put(annota.getName(), annota.getValue());
				} else {
					Map annoa = new HashMap();
					annoa.put(annota.getName(), annota.getValue());
					start.put("_link", annoa);
				}
			}
		}
		return start;
	}
	
	
	
	public Map serialEmbedded(Wavelet wavelet, Map start,Blip blip){
		BlipContentRefs refsi = blip.all(ElementType.IMAGE);
		Map embedded = new HashMap();

		// 获取图片properties
		List<BlipContent> imageContentList = refsi.values();
		for (int i = 0; i < imageContentList.size(); i++) {
			BlipContent imageContent = imageContentList.get(i);
			Map<String, String> properties = imageContent.asElement().getProperties();
			
			Set<String> keys = properties.keySet();
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				Object key = it.next();
				if(key.equals("url")){
				
					if (start.get("_embedded")!=null) {
						Map em = (Map) start.get("_embedded");
						System.out.println("logo:"+em.get("logo"));
						Map logo = null;
						if(em.get("logo")!=null){
							logo = (Map) em.get("logo");
								List link = (List) logo.get("_link");
								link.add(properties.get(key));
							
						
						}else{
							logo = new HashMap();
							List link = new ArrayList();
							link.add( properties.get(key));
							logo.put("_link", link);
							em.put("logo", logo);
						}
							
					} else {
						Map logo = new HashMap();
						List link = new ArrayList();
						
						link.add(properties.get(key));
						logo.put("_link", link);
						embedded.put("logo", logo);
						start.put("_embedded", embedded);
					}
				}else{
					if (start.get("_embedded")!=null) {
						Map em = (Map) start.get("_embedded");
						em.put(key, properties.get(key));
					}else{
						embedded.put(key,  properties.get(key));
						start.put("_embedded", embedded);
					}
					
				}
		}
		}
		
		
		return start;
	}
	
	
	
	
	
	public Wavelet appendBlipToWavelet(Wavelet wavelet) throws IOException {
		RobotAccountData account = null;
		String rpcUrl = getFrontEndAddress() + "/robot/rpc";
		try {
			account = getAccountStore().getAccount(
					ParticipantId
							.ofUnsafe(getRobotId() + "@" + getWaveDomain()))
					.asRobot();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		setupOAuth(account.getId().getAddress(), account.getConsumerSecret(),
				rpcUrl);
		Wavelet newWavelet = fetchWavelet(wavelet.getWaveId(),
				wavelet.getWaveletId(), rpcUrl);
		newWavelet.appendBlip("TBD_local.net/conv+root_testheiheihei",
				"append second blip to new wave" + wavelet.getWaveId());
		newWavelet.appendBlip("TBD_local.net/conv+root_testhahaha",
				"append Third blip to new wave" + wavelet.getWaveId());
		submit(newWavelet, rpcUrl);
		return newWavelet;
	}

	public Wavelet createWave(String creator, String blipId) throws IOException {
		RobotAccountData account = null;
		String rpcUrl = getFrontEndAddress() + "/robot/rpc";
		try {
			account = getAccountStore().getAccount(
					ParticipantId
							.ofUnsafe(getRobotId() + "@" + getWaveDomain()))
					.asRobot();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		setupOAuth(account.getId().getAddress(), account.getConsumerSecret(),
				rpcUrl);
		extendWaveService = WaveServerUtils.getExtendWaveService();
		WaveRef waveRef = extendWaveService.createWave("HeHe", "root blip id: "
				+ blipId, blipId, ParticipantId.ofUnsafe("ser-bot@local.net"),
				ParticipantId.ofUnsafe(creator));
		Wavelet wavelet = extendWaveService.fetchWave(waveRef, creator);
		submit(wavelet, rpcUrl);
		return wavelet;
	}

	@Override
	public String getRobotName() {
		return "Ser-Bot";
	}

	@Override
	public String getRobotUri() {
		return ROBOT_URI;
	}

	@Override
	public String getRobotId() {
		return "ser-bot";
	}

}
