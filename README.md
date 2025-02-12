# Ray

## 简介
Ray是一款提供了内置和Server的轻量级ID生成器，提供以下保证：
* 按照时间有序递增
* 能够保证全局唯一
* 能够处理时钟回调的问题
* 提供更强的并发性能

## 设计
Ray参考了SnowFlake的设计，解决了时钟回调和序列号不足的问题，并且提供了更好的并发性能

首先看一下SnowFlake的实现：
![avatar](https://raw.githubusercontent.com/KeshawnVan/Ray/master/image/SnowFlake.png)
SnowFlake使用10位的机器标识保证每个实例的ID绝不会相同，所以只需要在单个实例里使用时间戳和序列号保证实例内ID唯一即可

但是SnowFlake存在以下几个问题：
* 时钟偏斜问题
* 单位毫秒内生成的ID数不能超过12位的序列位

也就是说SnowFlake严重依赖于当前时间戳，并且只能处理当前时间戳大于等于上次时间戳的情况，对于当前时间戳小于上次时间戳和大于当前时间戳的情况都无法处理。

Ray使用Max（上次时间戳，本次时间戳）为时间戳进行递增序列，如果序列超出序列位，对时间戳做进位。从而解决时钟回拨和单位毫秒内的序列号限制。

对于机器重启导致的上次时间戳丢失，在时钟回拨时可能会存在的问题，Ray通过中间件定时同步上次时间戳去保证，并在初始化时进行恢复。

Ray的结构如下：
![avatar](https://raw.githubusercontent.com/KeshawnVan/Ray/master/image/Ray.png)
可以看到Ray从时间戳和机器标识中各抽走了一位新增了分区位，新增两位的分区位可以提高并发性能。并且Ray内的整个并发都是使用CAS进行，本次测试每秒支持1000万/s的ID生成性能。

https://developer.huawei.com/consumer/cn/forum/topic/0201228641279680051?fid=23
