此项目以闲暇自玩写东西测试的项目为蓝本，重构封装进行中（主体架构，网络框架，图片加载框架等已封装完成），重构开发作为礼物送女朋友
因时间紧迫散碎，部分为之前所加绝对路径，以及部分模块未上传，MainActivity为之前测试用功能用业务逻辑未转移，
部分未按命名规范修改，故现阶段仅做部分代码管理封装思想参考。

    此框架采用了Arouter组件化构建分为:
        (一)app:仅用于主页展示，模块衔接等功能
        (二)ijkplayer-java: ijkplayer编译定制工具包内含ijkplayer相关api（重新编库ijkplayer加字幕）
        (三)library: 工具库模块，提供共用api，base类，网络、数据库、图片处理等都继承与此库
        (四)loginfo: 就是一个RecyclerView构建的数据显示列表(后期修改)
        (五)video:视频播放模块(记录生活视频)
        (六)lovetree:主自定义View + 动画(正在进行中)，满足条件换取礼物督促自律
    架构设计
    设计模式 MVP + dagger2
    页面跳转分组化管理采用了 Arouter
    view注入框架 ButterKnife
    网络采用了RxJava + OkHttp3 + retrofit
    图片处理采用了 Fresco
    ...


