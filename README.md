# file-online-preview
此项目为文件文档在线预览项目解决方案，对标业内付费产品有【[永中office](http://dcs.yozosoft.com/)】【[office365](http://www.officeweb365.com/)】【[idocv](https://www.idocv.com/)】等，在取得公司高层同意后以Apache协议开源出来反哺社区，在此特别感谢@唐老大的支持以及@端木详笑的贡献。该项目使用流行的spring boot搭建，易上手和部署，基本支持主流办公文档的在线预览，如doc,docx,Excel,pdf,txt,zip,rar,vsd,rtf,docm图片等等
### 项目特性

1. 支持 office, pdf, cad 等办公文档
1. 支持 txt, xml(渲染), md(渲染), java, php, py, js, css 等所有纯文本
1. 支持 zip, rar, jar, tar, gzip 等压缩包
1. 支持 jpg, jpeg, png, gif, tif, tiff,webp 等图片预览（翻转，缩放，镜像）
1. 支持 eml,ofd,svg  OFD引用来源于 https://gitee.com/Donal/ofd.js 
1. 使用 spring-boot 开发，预览服务搭建部署非常简便
1. rest 接口提供服务，跨语言、跨平台特性(java,php,python,go,php，....)都支持，应用接入简单方便
1. 抽象预览服务接口，方便二次开发，非常方便添加其他类型文件预览支持
1. 最最重要 Apache 协议开源，代码 pull 下来想干嘛就干嘛

### 官网及文档

地址：[https://kkfileview.keking.cn](https://kkfileview.keking.cn)

### 在线体验
> 请善待公共服务，会不定时停用

高雄修改体验地址：[http://qt.cxcp.net](http://qt.cxcp.net)

### 项目文档（Project documentation）
1. 详细wiki文档：https://gitee.com/kekingcn/file-online-preview/wikis/pages

### 联系我们，加入组织
> 我们会用心回答解决大家在项目使用中的问题，也请大家在提问前至少 Google 或 baidu 过，珍爱生命远离无效的交流沟通
QQ群号：~~484680571(已满)~~  3群433403637

### 文档预览效果
#### 1. 文本预览
支持所有类型的文本文档预览， 由于文本文档类型过多，无法全部枚举，默认开启的类型如下 txt,html,htm,asp,jsp,xml,json,properties,md,gitignore,log,java,py,c,cpp,sql,sh,bat,m,bas,prg,cmd  
文本预览效果如下  
![文本预览效果如下](https://kkfileview.keking.cn/img/preview/preview-text.png)      

#### 2. 图片预览
支持jpg，jpeg，png，gif等图片预览（翻转，缩放，镜像），预览效果如下  
![图片预览](https://kkfileview.keking.cn/img/preview/preview-image.png)  

#### 3. word文档预览
支持doc，docx文档预览，word预览有两种模式：一种是每页word转为图片预览，另一种是整个word文档转成pdf，再预览pdf。两种模式的适用场景如下  
* 图片预览：word文件大，前台加载整个pdf过慢
* pdf预览：内网访问，加载pdf快
图片预览模式预览效果如下  
![word文档预览1](https://kkfileview.keking.cn/img/preview/preview-doc-image.png)  
pdf预览模式预览效果如下  
![word文档预览2](https://kkfileview.keking.cn/img/preview/preview-doc-pdf.png)  

#### 4. ppt文档预览
支持ppt，pptx文档预览，和word文档一样，有两种预览模式  
图片预览模式预览效果如下  
![ppt文档预览1](https://kkfileview.keking.cn/img/preview/preview-ppt-image.png)  
pdf预览模式预览效果如下  
![ppt文档预览2](https://kkfileview.keking.cn/img/preview/preview-ppt-pdf.png)  

#### 5. pdf文档预览
支持pdf文档预览，和word文档一样，有两种预览模式   
图片预览模式预览效果如下  
![pdf文档预览1](https://kkfileview.keking.cn/img/preview/preview-pdf-image.png)  
pdf预览模式预览效果如下   
![pdf文档预览2](https://kkfileview.keking.cn/img/preview/preview-pdf-pdf.png)    

#### 6. excel文档预览
支持xls，xlsx文档预览，预览效果如下  
![excel文档预览](https://kkfileview.keking.cn/img/preview/preview-xls.png)  

#### 7. 压缩文件预览
支持zip,rar,jar,tar,gzip等压缩包，预览效果如下  
![压缩文件预览1](https://kkfileview.keking.cn/img/preview/preview-zip.png)  
可点击压缩包中的文件名，直接预览文件，预览效果如下  
![压缩文件预览2](https://kkfileview.keking.cn/img/preview/preview-zip-inner.png)  

#### 8. 多媒体文件预览
理论上支持所有的视频、音频文件，由于无法枚举所有文件格式，默认开启的类型如下  
mp3,wav,mp4,flv  
视频预览效果如下  
![多媒体文件预览1](https://kkfileview.keking.cn/img/preview/preview-video.png)  
音频预览效果如下  
![多媒体文件预览2](https://kkfileview.keking.cn/img/preview/preview-audio.png)  

#### 9. CAD文档预览
支持CAD dwg文档预览，和word文档一样，有两种预览模式  
图片预览模式预览效果如下  
![cad文档预览1](https://kkfileview.keking.cn/img/preview/preview-cad-image.png)  
pdf预览模式预览效果如下  
![cad文档预览2](https://kkfileview.keking.cn/img/preview/preview-cad-pdf.png)  
考虑说明篇幅原因，就不贴其他格式文件的预览效果了，感兴趣的可以参考下面的实例搭建下

### 快速开始
> 项目使用技术
- spring boot： [spring boot开发参考指南](http://www.kailing.pub/PdfReader/web/viewer.html?file=springboot)
- freemarker
- redisson 
- jodconverter
> 依赖外部环境
- redis (可选，默认不用)
- OpenOffice 或者 LibreOffice( Windows 下已内置，Linux 脚本启动模式会自动安装，Mac OS 下需要手动安装)

1. 第一步：pull 项目 https://github.com/kekingcn/file-online-preview.git

3. 第二步：运行 ServerMain 的 main 方法，服务启动后，访问 http://localhost:8012/
会看到如下界面，代表服务启动成功
   
![输入图片说明](https://gitee.com/uploads/images/2017/1213/100221_ea15202e_492218.png "屏幕截图.png")

### 历史更新记录  
2021年12月27日，v4.3.2版本发布:  
1、pdf分页功能  
2、更新PDF.JS插件  

2021年12月24日，v4.3.1版本发布:  
1、新增是否启用首页功能  

2021年12月23日，v4.3版本发布:  
1、修复PPT图片模式下反代没有定义绝对地址问题  
2、修改更名规则对不同类型同名造成的错误  
3、新增下载流图片预览方法(&officePreviewType=imagexz这里不能用加密方法)  

2021年12月21日，v4.2.3版本发布  
1、新增文件名称编码判断  
2、新增dxf、dwf、wmf、emf文件支持  
3、修复% # 特殊符号无法访问问题  

2021年12月21日，v4.2.2版本发布:  
1、修复pdf文件本地路径判断错误  
2、修复某些文件不关闭流  
3、更新log4j为2.17  
4、修复PDF 禁用右键 配置文件不起作用 

2021年12月15日，v4.2.1版本发布:  
1、新增TIF图片转换PDF模式  
2、修复压缩包在LINUX下问题  
3、正式版发布   

2021年12月13日，v4.2版本发布:  
1、修复tif格式文件支持多图浏览  
2、增加odt格式文档  
3、新增全局file协议输出控制 为了安全默认为关闭 如果是局域网可以开启  
4、更新log4j为2.15.0版本  
5、修复文件流问题  
6、新增mht格式文件支持   
7、新增右键F12等限制   

2021年12月10日，v4.1.9.1版本发布:  
1、新增N多格式 obj、3ds、stl、ply、off、3dm、fbx、dae、wrl、3mf、ifc、glb、o3dv  基于开源@https://github.com/kovacsv/Online3DViewer  
2、修改压缩包解压方法  感谢 @BoneBee  
3、新增POI和开源OFFICE切换模式&officePreviewType=poi  &officePreviewType=office  
 

2021年12月9日，v4.1.9版本发布:  
1、修复了特殊文件流不能转换的问题  
2、文件头识别新增了一种格式  

 
2021年12月9日，v4.1.8版本发布:  
1、修复压缩包 解压后PDF 图片问题  
2、调整PDF 压缩包解压模式下载覆盖问题  
3、集成了PDF压缩 功能在OfficeFilePreviewImpl 类里面 没启用 等待继续完善  

2021年12月7日，v4.1.7版本发布：  
1、修复了些BUG  
2、去掉一些重复代码  
3、如果没有发现问题下个版本就是高雄4.2版本了 属于正式版本了  

> 2021年12月04日，v4.1.6版本发布 ：  
1、继续修复调整转换模块友好错误提示  
2、增加文件头判断 目前只是对 OFFICE PDF  OFD做了 以后在对图片压缩文件来识别  
3、修复了一处查看编码BUG 当出现~会出现BUG  

> 2021年12月03日，v4.1.5版本发布 ：  
1、调整KK支持IE10以上版本  
2、修复POI模式文件流不回收问题  
3、调整转换模块友好错误提示  
4、增加源文件删除功能  
5、调整PDF缩放倍数,调整PDF默认手势功能  
6、进一步调整分页功能 还需要继续完善  

> 2021年11月17日，v4.1.4版本发布 ：  
1、新增xlsx输出为JS解析 (配置文件启用)  解析方法luckysheet 地址为:https://gitee.com/mengshukeji/Luckysheet  
2、xlsx输出调用方法 &officePreviewType=xlsx (JS解析方法)  &officePreviewType=html (转换为HTML) 
 
> 2021年11月17日，v4.1.3版本发布 ：    
1、修复4.1.2版本的PDF跨域文件URL被转义错误  
2、增加OFFICE源文件的删除功能 配置文件启用  
> 2021年11月16日，v4.1.2版本发布 ：  
1、修复文件特殊符号错误 比如名称带#号的  
2、首页新增更新和水印调用参数  
2、KK支持跨域功能  

> 2021年11月15日，v4.1.1版本发布 ：  
1、对压缩包ZIP格式调整了下   
2、新增PDF分页功能 只是简单做了测试 如果有问题请大家群里留言  (PDF分页需要在配置文件里面调整 默认开启显示1-2)   
3、修复一处BUG 任意文件读取漏洞   
4、更新OFD到最新版本  

> 2021年11月05日，v4.0.9版本发布 ：  
1、更新了一些组件  
2、更新windows下OFFICE版本7.22  
3、修复文件流截取问题   文件流方式修改为&fullfilename=/test.txt   斜杠必须有   这块以后有时间重新写个方法吧  
4、修复了pdfbox 转图片出现图片元素丢失问题


> 2021年11月03日，v4.0.8版本发布 ：  
1、修复XML、markdown文件预览反义乱码问题  
2、更新PDF.js文件 版本为2.10.377  修复了签名丢失问题  
3、更新aspose-cad为21.8版本

> 2021年10月29日，v4.0.7版本发布 ：  

1、修复openoffice 或lieboffice转换内核模式下PPT PPTX图片页码和水印问题   
2、修复PDF浏览模式有的发票不显示文字    
3、增加OFD模式打印功能    
4、增加转换内核POI  转换为HTML    
5、增加SVG、webp、docm、xlsm格式浏览   
6、增加实时更新功能 在开启缓存模式下 调用参数&gengxin=ok     
 (http://127.0.0.1/onlinePreview?url=地址&gengxin=ok) 地址支持BASE64或者 直接地址  需要在配置文件设置    
7、地址支持base64加密或者不加密模式 需要在配置文件里面设置     
8、对上传文件过滤特殊符号    
9、支持HTTPS 非法证书连接下载    
10、增加eml格式文件的支持    
11、修复OFD、SVG、跨域问题   
12、修复OFFICE 图片预览参数&gengxin=ok  不更新图片问题   
13、新增VSD格式支持   
14、新增CAD、PDF更新缓存参数 调用参数&gengxin=ok     
15、CAD转换模式去掉转换成图片 直接是PDF模式  CAD修改了转换模板支持 多个图层    
16、更新CAD转换模块为21.4   CAD下PDF模式去掉JPG图片显示   
17、修复OFD、PDF、TIFF 文件流跨域问题   
17、更新xstream为1.4.18     
18、修复了跨域BUG  
19、修复了URL空格转码问题  
20、增加POI ppt转换图片清晰功能 在配置文件设置  
21、调整xls/xlsx生成样式  
22、增加PDF转换图片清晰度功能  在配置文件设置  
22、修复图片预览文件名被转码问题   
23、修复预览标题问题    
24、新增rtf格式支持  


> 2021年7月6日，v4.0.0 版本发布 ：

1. 底层集成OpenOffice替换为LibreOffice，Office文件兼容性增强，预览效果提升
2. 修复压缩文件目录穿越漏洞
3. 修复PPT预览使用PDF模式无效
4. 修复PPT图片预览模式前端显示异常
5. 新增功能：首页文件上传功能可通过配置实时开启或禁用
6. 优化增加Office进程关闭日志
7. 优化Windows环境下，查找Office组件逻辑(内置的LibreOffice优先)
8. 优化启动Office进程改同步执行

> 2021年6月17日，v3.6.0 版本发布 ：

ofd 类型文件支持版本，本次版本重要功能均由社区开发贡献，感谢 @gaoxingzaq、@zhangxiaoxiao9527 的代码贡献
1. 新增 ofd 类型文件预览支持，ofd 是国产的类似 pdf 格式的文件
2. 新增了 ffmpeg 视频文件转码预览支持，打开转码功能后，理论上支持所有主流视频的预览，如 rm、rmvb、flv 等
3. 美化了 ppt、pptx 类型文件预览效果，比之前版本好看太多
4. 更新了 pdfbox、xstream、common-io 等依赖的版本

> 2021年1月28日 ：

2020农历年最后一个版本发布，主要包含了部分 UI 改进，和解决了 QQ 群友、 Issue 里反馈的 Bug 修复，最最重要的是发个新版，过个好年

1. 引入galimatias,解决不规范文件名导致文件下载异常
2. 更新index接入演示界面UI风格
3. 更新markdown文件预览UI风格
4. 更新XML文件预览UI风格，调整类文本预览架构，更方便扩展
5. 更新simTxT文件预览UI风格
6. 调整多图连续预览上下翻图的UI
7. 采用apache-common-io包简化所有的文件下载io操作
8. XML文件预览支持切换纯文本模式
9. 增强url base64解码失败时的提示信息
10. 修复导包错误以及图片预览 bug
11. 修复发行包运行时找不到日志目录的问题
12. 修复压缩包内多图连续预览的bug
13. 修复大小写文件类型后缀没通用匹配的问题
14. 指定Base64转码采用Apache Commons-code中的实现，修复base64部分jdk版本下出现的异常
15. 修复类文本类型HTML文件预览的bug
16. 修复：dwg文件预览时无法在jpg和pdf两种类型之间切换
17. escaping of dangerous characters to prevent reflected xss
18. 修复重复编码导致文档转图片预览失败的问题&编码规范

> 2020年12月27日 ：

2020年年终大版本更新，架构全面设计，代码全面重构，代码质量全面提升，二次开发更便捷，欢迎拉源码品鉴，提issue、pr共同建设

1. 架构模块调整,大量的代码重构，代码质量提升N个等级，欢迎品鉴
2. 增强XML文件预览效果，新增XML文档数结构预览
3. 新增markdown文件预览支持，预览支持md渲染和源文本切换支持
4. 切换底层web server为jetty，解决这个issue：https://github.com/kekingcn/kkFileView/issues/168
5. 引入cpdetector，解决文件编码识别问题
6. url采用base64+urlencode双编码，彻底解决各种奇葩文件名预览问题
7. 新增配置项office.preview.switch.disabled，控制offic文件预览切换开关
8. 优化文本类型文件预览逻辑，采用Base64传输内容，避免预览时再次请求文件内容
9. office预览图片模式禁用图片放大效果，达到图片和pdf预览效果一致的体验
10. 直接代码静态设置pdfbox兼容低版本jdk，在IDEA中运行也不会有警告提示 
11. 移除guava、hutool等非必须的工具包，减少代码体积
12. Office组件加载异步化，提速应用启动速度最快到5秒内
13. 合理设置预览消费队列的线程数
14. 修复压缩包里文件再次预览失败的bug
15. 修复图片预览的bug

> 2020年05月20日 ：
1. 新增支持全局水印，并支持通过参数动态改变水印内容
2. 新增支持CAD文件预览
3. 新增base.url配置，支持使用nginx反向代理和使用context-path
4. 支持所有配置项支持从环境变量里读取，方便Docker镜像部署和集群中大规模使用
5. 支持配置限信任站点（只能预览来自信任点的文件源），保护预览服务不被滥用
6. 支持配置自定义缓存清理时间（cron表达式）
7. 全部能识别的纯文本直接预览，不用再转跳下载，如.md .java .py等
8. 支持配置限制转换后的PDF文件下载
9. 优化maven打包配置，解决 .sh 脚本可能出现换行符问题
10. 将前端所有CDN依赖放到本地，方便没有外网连接的用户使用
11. 首页评论服务由搜狐畅言切换到Gitalk
12. 修复url中包含特殊字符可能会引起的预览异常
13. 修复转换文件队列addTask异常
14. 修复其他已经问题
15. 官网建设：[https://kkfileview.keking.cn](https://kkfileview.keking.cn)
16. 官方Docker镜像仓库建设：[https://hub.docker.com/r/keking/kkfileview](https://hub.docker.com/r/keking/kkfileview)

> 2019年06月18日 ：
1. 支持自动清理缓存及预览文件
2. 支持http/https下载流url文件预览
3. 支持FTP url文件预览
4. 加入Docker构建

> 2019年04月08日 ：
1. 缓存及队列实现抽象，提供JDK和REDIS两种实现(REDIS成为可选依赖)
2. 打包方式提供zip和tar.gz包，并提供一键启动脚本

> 2018年01月19日 ：

1. 大文件入队提前处理
1. 新增addTask文件转换入队接口 
1. 采用redis队列，支持kkFIleView接口和异构系统入队两种方式

> 2018年01月17日 ：

1. 优化项目结构，抽象文件预览接口，更方便的加入更多的文件类型预览支持，方便二次开发
1. 新增英文文档说明（@幻幻Fate，@汝辉）贡献
1. 新增图片预览文件支持类型
1. 修复压缩包内轮播图片总是从第一张开始的问题

> 2018年01月12日 ：

1. 新增多图片同时预览 
1. 支持压缩包内图片轮番预览

> 2018年01月02日 ： 

1. 修复txt等文本编码问题导致预览乱码 
1. 修复项目模块依赖引入不到的问题 
1. 新增spring boot profile，支持多环境配置 
1. 引入pdf.js预览doc等文件，支持doc标题生成pdf预览菜单，支持手机端预览

### 使用登记
如果这个项目解决了你的实际问题，可在 https://gitee.com/kekingcn/file-online-preview/issues/IGSBV
登记下，如果节省了你的三方预览服务费用，也愿意支持下的话，可点击下方【捐助】请作者喝杯咖啡，也是非常感谢

### Stars 趋势图
#### Gitee
[![Stargazers over time](https://whnb.wang/img/kekingcn/file-online-preview)](https://whnb.wang/kekingcn/file-online-preview?e=86400)

#### GitHub

[![Stargazers over time](https://starchart.cc/kekingcn/kkFileView.svg)](https://starchart.cc/kekingcn/kkFileView)
