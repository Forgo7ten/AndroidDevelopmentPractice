# Material Design 实践

[TOC]



## Toolbar使用

### Toolbar简单使用

1.   替换`themes.xml`中主题style的parent属性为`Theme.MaterialComponents.Light.NoActionBar`
2.   `activity_main`中添加`<Toolbar>`节点
3.   MainActivity中添加`initToolbar()`方法

简单Toolbar已经完成

-   替换标题栏文字内容
    -   由`AndroidManifest.xml`中`activity`节点的`android:label`属性决定

### 添加action按钮

1.   res创建`menu`文件夹
2.   创建并编写`toolbar.xml`文件
     -   `android:id`指定按钮的id
     -   `android:icon`指定按钮的图标
     -   `android:title`指定按钮的文字
     -   `app:showAsAction`指定按钮的显示位置
         -   always表示永远显示在Toolbar中，如果屏幕空间不够则不显示
         -   ifRoom表示屏幕空间足够的情况下显示在Toolbar中，不够的话就显示在菜单当中
         -   never则表示永远显示在菜单当中
3.   在MainActivity中重写`onCreateOptionsMenu()`和`onOptionsItemSelected()`方法



## 侧滑菜单

### DrawerLayout

#### 简单实现

1.   修改`activity_main.xml`
     1.   最外层套上`<androidx.drawerlayout.widget.DrawerLayout>`节点，内层放两个子元素，第一个子元素为主界面，第二个为侧滑菜单界面
     2.   第二个子控件需要指定`android:layout_gravity`属性：指定left表示滑动菜单在左边，指定right表示滑动菜单在右边；指定start，表示会根据系统语言进行判断。

简单的侧滑菜单完成

#### Toolbar左侧添加导航按钮

1.   修改MainActivity中的`initToolbar()`方法

     1.   查找侧滑菜单DrawerLayout实例
     2.   获得actionBar
     3.   如果actionBar不为空，则将HomeAsUp按钮显示出来
     4.   之后更改按钮图标

     HomeAsUp按钮原本的用意是"返回上一级"，这里对其进行了修改

2.   在MainActivity中的`onOptionsItemSelected()`方法中写点击事件

     ```java
     case android.R.id.home:
         // 将滑动菜单展示出来
         mDrawerLayout.openDrawer(GravityCompat.START);
         break;
     ```

     HomeAsUp按钮的id永远都是`android.R.id.home`

即实现



### NavigationView(菜单页面)

1.   编写menu，用来在NavigationView中显示具体的菜单项

     在`menu`文件夹下新建`nav_menu.xml`文件

     1.   在`<menu>` 中嵌套了一个`<group>` 标签，然后将group的`checkableBehavior` 属性指定为`single`。`group`表示一个组，`checkableBehavior` 指定为`single` 表示组中的所有菜单项只能单选
     2.   `android:id` 属性指定菜单项的`id`，`android:icon` 属性指定菜单项的`图标`，`android:title` 属性指定菜单项`显示的文字`
     
2.   编写headerLayout，用来在NavigationView中显示头部布局

     在`layout`下创建`nav_header.xml`文件

     仅仅在头部布局添加头像、邮箱和昵称

     1.   添加`CircleImageView`将头像进行圆形化（需要gradle导入相关依赖）
     2.   添加两个textView用于显示昵称和邮箱

3.   修改`activity_main.xml`将原本的菜单控件替换为`com.google.android.material.navigation.NavigationView`

     1.   `app:menu`属性用来设置菜单项布局文件
     2.   `app:headerLayout`属性用来设置头部布局

4.   修改`MainActivity`中`initToolbar()`方法代码

     1.   获取nav导航控件实例
     2.   设置默认选中
     3.   设置菜单选中事件监听器




## 悬浮按钮与可交互提示

### FloatingActionButton(悬浮按钮)

1.   在`activity_main`中添加`com.google.android.material.floatingactionbutton.FloatingActionButton`控件
2.   像普通按钮一样为其注册点击事件



### Snackbar(可交互提示)

在FAB的点击事件中编写

```java
// 弹出一条提示
Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT)
        // 设置额外的行动（undo按钮）和点击按钮后的点击事件
        .setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Data restored",
                        Toast.LENGTH_SHORT).show();
            }
        })
        // 展示出来
        .show();
```

make() 方法的第一个参数需要传入一个View，只要是当前界面布局的任意一个View都可以，Snackbar会使用这个View来自动查找最外层的布局



### CoordinatorLayout

是一个加强版的FrameLayout

CoordinatorLayout可以监听其所有子控件的各种事件，然后自动帮助我们做出最为合理的响应。举个简单的例子，刚才弹出的Snackbar提示将悬浮按钮遮挡住了，而如果我们能让CoordinatorLayout监听到Snackbar的弹出事件，那么它会自动将内部的FloatingActionButton向上偏移，从而确保不会被Snackbar遮挡到。

将原先主界面的`<FrameLayout>`替换为`<androidx.coordinatorlayout.widget.CoordinatorLayout>`



## 卡片式布局

### CardView

采用在RecyclerView中实现好多个CardView

1.   在`activity_main`中`<Toolbar>`的下方添加`RecyclerView`
2.   在`layout`下为`RecyclerView`编写子项布局`item_showimg.xml`
     1.   用`CardView`作为根节点
     2.   添加图片和text显示
3.   编写ShowImg实体类，只添加了imgID和name两个属性
4.   编写ShowImgAdapter类
5.   编写`MainActivity`的`initCard()`方法



### AppBarLayout

Toolbar被RecyclerView给挡住了，解决：AppBarLayout是一个垂直方向的LinearLayout，它在内部做了很多滚动事件的封装，并应用了一些Material Design的设计理念。

1.   修改`activity_main`，将Toolbar嵌套到AppBarLayout中
2.   RecyclerView中使用`app:layout_behavior` 属性指定了一个布局行为

已经出现Toolbar

-   设置下滑时Toolbar折叠
    -   给AppBarLayout的子控件Toolbar设置属性`app:layout_scrollFlags="scroll|enterAlways|snap"`
        -   scroll 表示当RecyclerView向上滚动的时候，Toolbar会跟着一起向上滚动并实现隐藏；
        -   enterAlways 表示当RecyclerView向下滚动的时候，Toolbar会跟着一起向下滚动并重新显示。
        -   snap 表示当Toolbar还没有完全隐藏或显示的时候，会根据当前滚动的距离，自动选择是隐藏还是显示。

## 下拉刷新

### SwipeRefreshLayout

1.   修改activity_main，给`RecyclerView`套上`SwipeRefreshLayout`(需要gradle添加依赖)
2.   在MainActivity添加`initRefresh()`方法



## 可折叠式标题栏

### CollapsingToolbarLayout

CollapsingToolbarLayout是一个作用于Toolbar基础之上的布局。CollapsingToolbarLayout是不能独立存在的，它在设计的时候就被限定只能作为AppBarLayout的直接子布局来使用，而AppBarLayout又必须是CoordinatorLayout的子布局

1.   新建DetailActivity，编写每个item的详细信息Activity
2.   编写DetailActivity的布局文件`activity_detail`
3.   编写DetailActivity代码
4.   在ShowImgAdapter中的`onCreateViewHolder()`方法中为`cardView`添加点击事件



## 充分利用系统状态栏空间

想要让**背景图能够和系统状态栏融合**，需要借助android:fitsSystemWindows 这个属性来实现。在CoordinatorLayout、AppBarLayout、CollapsingToolbarLayout这种嵌套结构的布局中，将控件的android:fitsSystemWindows 属性指定成true ，就表示该控件会出现在系统状态栏里。对应到我们的程序，那就是水果标题栏中的ImageView应该设置这个属性了。不过只给ImageView设置这个属性是没有用的，我们必须将ImageView布局结构中的所有父布局都设置上这个属性才可以

1.   给ImageView及所有父组件设置属性`android:fitsSystemWindows="true"`
2.   在程序的主题中将状态栏颜色指定成透明色：在主题中将`android:statusBarColor`属性的值指定成`@android:color/transparent`
     1.   新建`values-v21`目录，编写`styles.xml`，添加`DetailActivityTheme`，父类继承APP主题，设定标题栏透明（Android5.0以上生效）
     2.   在`themes.xml`中添加`DetailActivityTheme`，为空，继承app主题
3.   编辑`AndroidManifest.xml`，为`DetailActivity`添加`theme`属性



另一种方式

```java
if (Build.VERSION.SDK_INT >= 21) {
    View decorView = getWindow().getDecorView();
    decorView.setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    getWindow().setStatusBarColor(Color.TRANSPARENT);
}
// 在setContentView上方调用
setContentView(R.layout.activity_weather);
```

然后在相应控件添加`android:fitsSystemWindows` 属性，设置成true 就表示会为系统状态栏留出空间
