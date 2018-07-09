# ⚡️thrunder-rpc
#### 🔥缘起 -  为什么要写这个项目❓
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去年秋,工作之余断断续续读完了[《架构探险：从零开始写分布式服务框架》](http://www.broadview.com.cn/book/4830) 非常感谢作者, 书用大约2千行代码实现了一个`RPC`框架 .  读完书之后就像看看生产级的框架的实现,想比较一下和生产级的`RPC`框架的差距, 于是拉了一份`dubbo`代码,统计一下代码量看了一下去除 `注释` `空格`等无效的代码后剩余`97097行`, 虽然之前有使用经验并且文档很丰富, 但是要想从源码实现的层面掌握`成本`太高完全没有可行性,于是果断放弃.<br>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;只能退而求其次在`github`上开始找其他大厂有没有类似的框架, 于是发现了`motan` 从`代码量`和`代码质量`上来说都是非常合适研读.参照`motan`并写一个自己的一套框架以加深理解.事实上现在回过头来看`motan`就是`dubbo`的裁剪版本. 所以我建议在看`dubbo`源码之前,`motan` 是个不错的选择😊.
#### 🍎收获 - 过程中的心得体会
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去年秋,工作之余断断续续读完了[《架构探险：从零开始写分布式服务框架》](http://www.broadview.com.cn/book/4830) 非常感谢作者, 书用大约2千行代码实现了一个`RPC`框架 .  读完书之后就像看看生产级的框架的实现,想比较一下和生产级的`RPC`框架的差距, 于是拉了一份`dubbo`代码,统计一下代码量看了一下去除 `注释` `空格`等无效的代码后剩余`97097行`, 虽然之前有使用经验并且文档很丰富, 但是要想从源码实现的层面掌握`成本`太高完全没有可行性,于是果断放弃.<br>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;只能退而求其次在`github`上开始找其他大厂有没有类似的框架, 于是发现了`motan` 从`代码量`和`代码质量`上来说都是非常合适研读.参照`motan`并写一个自己的一套框架以加深理解.事实上现在回过头来看`motan`就是`dubbo`的裁剪版本. 所以我建议在看`dubbo`源码之前,`motan` 是个不错的选择😊.
#### 📚参考框架实现
*  [motan(新浪微博)](https://github.com/weibocom/motan) 
*  [dubbo(阿里巴巴)](https://github.com/apache/incubator-dubbo)
*   [sofa-rpc(蚂蚁金服)](https://github.com/alipay/sofa-rpc)


