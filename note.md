### 1.Wavelet
### 2.Blip
### 3.BlipRefs
- 需要一个类来负责处理JsonNode中的数据

- 需要Wavelet用来创建新的Blip，需要BlipRefs用来更新blip内容中的各种Element

- JsonNode保留字段定义wavelet返回的操作类型。Map字段保存的数据使用FormElement来保存。

**ps:**	
- blip.append(BlipContent)方法使用的时候需要添加"\n"；
- 反序列化过程中，数组的保存形式。



