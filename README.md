1. model提供公共类和接口；
2. serverdemo利用springboot简单实现了一个server，实现了model定义的接口；需要java -jar运行该模块target下的编译jar包
3. clientsimulate模拟UI向server发送http消息（依赖httpClient），并利用Testng对返回数据做校验——即接口级自动化。
4. portDemo.postman_collection.json为PostMan导出的测试用例。

clientsimulate可以继续扩展，做自动化驱动程序；
serverdemo可以替换为任何开放了接口的系统、设备、仪表等。

需要持续改进的点：
1. API接口可以采用yaml或yang定义进行编译生成，尤其是复杂接口下；
2. https的穿通；
3. UI的部分实现，可以学习；
4. springBoot仍然需要深入了解。
