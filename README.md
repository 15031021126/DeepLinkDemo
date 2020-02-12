# DeepLinkDemo
app之间的跳转
###deeplink是什么？
Deeplink，简单讲，就是你在手机上点击一个链接之后，可以直接链接到app内部的某个页面，而不是app正常打开时显示的首页。不似web，一个链接就可以直接打开web的内页，app的内页打开，必须用到deeplink技术
##使用
###1.在清单文件配置
![代码.png](https://upload-images.jianshu.io/upload_images/16910903-5e8978c3064eed9c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
主要代码BROWSABLE意思是可以被外部打开，host和scheme类似http://xxxx，在跳转的时候会用
```
 <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data
                    android:host="share"
                    android:scheme="cc" />
            </intent-filter>
```
写一个html链接过去```<a href="cc://share/20">尝试打开deeplink界面</a>```要与上面data里面的字段的对应起来
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<a href="cc://share/20">尝试打开deeplink界面</a>
注意：cc就是自己写的scheme，share是host可以自定义任意字符串，20是模拟传递的值

</body>
</html>
```
在MainActivity写一个webview用户加载刚写的网页
```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_view);
        webView.loadUrl("file:///android_asset/" + "deeplink.html");
    }
}
```
在deeplinkActivity获取传递的值
```
public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
        TextView tv = findViewById(R.id.tv);
        //获得网页传递过来的
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            String scheme = uri.getScheme();//cc
            String host = uri.getHost();//share
            if ("share".equals(host)) {
                // 跳转到对应详情页面
                Toast.makeText(this, "host:"+host+"scheme:"+scheme+"值："+uri.getPathSegments(), Toast.LENGTH_SHORT).show();
                tv.setText("host:"+host+"\nscheme:"+scheme+"\n值："+uri.getPathSegments());
            }
        }
    }
}
```
效果点击后就打开我们写的详情页了，我是写在一个app里的，当然只要是webview就可以打开对应的详情页，两个应用之间的跳转就完成了
![image.png](https://upload-images.jianshu.io/upload_images/16910903-b4a93b978835a6c5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/16910903-13dd0c4475d92e34.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


