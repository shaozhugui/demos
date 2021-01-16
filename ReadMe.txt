1. model提供公共类和接口；
2. serverdemo利用springboot简单实现了一个server，实现了model定义的接口；需要java -jar运行该模块target下的编译jar包
3. clientsimulate模拟UI向server发送http消息（依赖httpClient），并利用Testng对返回数据做校验——即接口级自动化。
4. portDemo.postman_collection.json为PostMan导出的测试用例。

可启发的领域：
1. springboot简单实现web服务端；
2. Restful接口交互，自动化测试时获取任何服务端的数据，https的ssl设置需要再研究；

需要改进的点：
1. API接口可以采用yaml或yang定义进行编译生成，尤其是复杂系统下；
2. https的穿通；
3. UI的部分实现，可以学习；
4. springBoot仍然需要深入了解。
