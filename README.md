之前学东西的项目，重构封装进行中（主体架构，网络框架等已封装完成），些许地方比较乱，内部有些许bug持续更新中，以及部分路径写的决对路径，重构作为礼物送女朋友

    此框架采用了Arouter组件化构建分为:
        (一)app:仅用于主页展示，模块衔接等功能
        (二)ijkplayer-java: ijkplayer编译定制工具包内含ijkplayer相关api（重新编库ijkplayer加字幕）
        (三)library: 工具库模块，提供共用api，base类，网络、数据库、图片处理等都继承与此库
        (四)loginfo: 就是一个RecyclerView构建的数据显示列表(后期修改)
        (五)video:视频播放模块(记录生活视频)
        (六)lovetree:主自定义View + 动画(正在进行中)，满足条件抽奖，礼物督促自律
    架构设计
    设计模式 MVP + dagger2
    页面跳转分组化管理采用了 Arouter
    view注入框架 ButterKnife
    网络采用了RxJava + OkHttp3 + retrofit
    图片处理采用了 Fresco
    ...


