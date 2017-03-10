public class TestHALJsonDeserializer {

         static final String HAL_DOC = "{"
                         + "\"_links\":{"
                         + "\"child\":[{\"href\":\"/top/1/child/1\"},{\"href\":\"/top/1/child/2\"}],"
                         + "\"empty:list\":[],"
                         + "\"null:list\":[],"
                         + "\"self\":{\"href\":\"/top/1\"},"
                         + "\"edit\":{\"href\":\"/top/1/edit\"},"
                         + "\"templated\":{\"href\":\"/uri/{id}\",\"templated\":true}"
                         + "},"
                         + "\"_embedded\":{"
                         + "\"child\":["
                         + "{\"_links\":{\"self\":{\"href\":\"/top/1/child/1\"}},\"id\":\"1\"},"
                         + "{\"_links\":{\"self\":{\"href\":\"/top/1/child/2\"}},\"id\":\"2\"}]"
                         + "},"
                         + "\"id\":\"1\","
                         + "\"age\":\"12\","
                         + "\"operationType\":\"WAVELET_APPEND_BLIP\","
                         + "\"waveId\":\"12\","
                         + "\"blipId\":\"12\","
                         + "\"author\":\"333@local.net\","
                         + "\"name\":\"xiaoming\"}";
                        ObjectMapper om = new HALMapper();

        public void testHALJsonDeser() throws JsonParseException, JsonMappingException, IOException {
                HALOperations tr = om.readValue(new StringReader(HAL_DOC), HALOperations.class);
                System.out.println("tr:"+tr);
                System.out.println("trHref:"+tr.self.getHref());
                System.out.println("trTemlated:"+tr.templated.getHref());
                System.out.println("trChildLinks:"+tr.childLinks);
                System.out.println("trChildren:"+tr.children);
                System.out.println("trName:"+tr.name);
                System.out.println("trId:"+tr.id);
                System.out.println("trage:"+tr.age);
                System.out.println("operationType:"+tr.operationType.method());
                System.out.println("edit:"+tr.edit.getHref());

                assertEquals("1",tr.id);
                assertEquals("/top/1",tr.self.getHref());
                assertEquals("/uri/{id}",tr.templated.getHref());
                assertTrue(tr.templated.getTemplated());
                assertEquals(2,tr.childLinks.size());
                assertEquals(2,tr.children.size());
                assertEquals("xiaoming",tr.name);
                assertEquals("wavelet.appendBlip",tr.operationType.method());
                assertEquals("/top/1/edit",tr.edit.getHref());
        }
        @Test
        public  void testHALJsonDeserial(){
              JSONObject jobj = JSONObject.fromObject(HAL_DOC);

                List<Object> list = new ArrayList<Object>();
                List<Object> result = HALJsonUtils.decodeJSONObject(jobj,list);
                for (int i = 0; i < list.size(); i++) {
                        System.out.println(list.get(i));
                }
        }
        @Test
        public void testOperationToWavelet() throws JsonParseException, JsonMappingException, IOException{

                WebConversation webConversation =  new WebConversation();
                WebRequest webreq = new PostMethodWebRequest("http://192.168.1.11:9898/auth/register");//发送请求

                HALOperations operations = om.readValue(new StringReader(HAL_DOC), HALOperations.class);

                WaveletOperations waveletOperations = null;
                waveletOperations.execute(operations);
        }
}

