###RESTful
- JAX-RS
- JAX-RS 建立了一种特殊的语言来描述资源，有五种主要条目：根资源、子资源、资源方法、子资源方法以及子资源定位器。
- 序列化/反序列化 MessageBodyWriter和MessageBodyReader

```
@Provider 
 @Produces("application/json") 
 @Consumes("application/json") 
 public class GsonProvider implements MessageBodyWriter<Object>, 
    MessageBodyReader<Object> { 

    private final Gson gson; 

    public GsonProvider() { 
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat( 
                "yyyy-MM-dd").create(); 
    } 

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, 
            MediaType mediaType) { 
        return true; 
    } 

    public Object readFrom(Class<Object> type, Type genericType, 
            Annotation[] annotations, MediaType mediaType, 
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream) 
            throws IOException, WebApplicationException { 
        return gson.fromJson(new InputStreamReader(entityStream, "UTF-8"), type); 
    } 

    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, 
            MediaType mediaType) { 
        return true; 
    } 

    public long getSize(Object obj, Class<?> type, Type genericType, 
            Annotation[] annotations, MediaType mediaType) { 
        return -1; 
    } 

    public void writeTo(Object obj, Class<?> type, Type genericType, 
            Annotation[] annotations, MediaType mediaType, 
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) 
            throws IOException, WebApplicationException { 
        entityStream.write(gson.toJson(obj, type).getBytes("UTF-8")); 
    } 

 }
```

- [JAX-RS Tutoral](http://docs.oracle.com/javaee/6/tutorial/doc/gilik.html）
