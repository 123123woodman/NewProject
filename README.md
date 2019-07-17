闲的蛋疼，搞着玩

    此框架采用了Arouter组件化构建分为:
        (一)app:仅用于主页展示，模块衔接等功能
        (二)ijkplayer-java: ijkplayer工具包内含ijkplayer相关api
        (三)library: 工具库模块，提供共用api，base类，网络、数据库、图片处理等都继承与此库
        (四)loginfo: 就是一个RecyclerView构建的数据显示列表想用自己改
        (五)video:视频播放模块

    架构设计
    设计模式 MVP(Module + view + presenter)
    页面跳转分组化管理采用了 Arouter
    依赖注入框架采用了 dagger2
    view注入框架 ButterKnife
    网络采用了RxJava + OkHttp3 + retrofit 检测控制其生命周期采用了 rxlifecycle
    图片处理采用了 Fresco
    数据库 greenDao


