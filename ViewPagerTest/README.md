# ViewPager2使用

## ViewPager2+Fragment

1.   gradle添加ViewPager2依赖

     ```groovy
     dependencies {
         implementation "androidx.viewpager2:viewpager2:1.0.0"
     }
     ```

2.   layout布局中添加控件

     ```xml
     <androidx.viewpager2.widget.ViewPager2
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:id="@+id/viewPager">
     </androidx.viewpager2.widget.ViewPager2>
     ```

3.   编写自定义`MyFragmentPagerAdapter`类

     1.   先继承`FragmentStateAdapter`类
     2.   定义一个字段`List<Fragment> fragmentList`用于存放实例化的fragment列表，同时在构造方法中初始化
     3.   在`createFragment(int position)`中返回相应的`fragmentList.get(position)`
     4.   在`getItemCount()`中返回`fragmentList.size()`

4.   编写各个界面Fragment（方便演示仅用一个Fragment多个实例来显示）

     1.   新建一个`BlankFragment`，AndroidStudio会初始化好模板
     2.   进行修改（获取传入参数并设置到text上

     若不用一个Fragment，该步骤仅需要写好各个Fragment即可

5.   Activity中编写

     1.   根据布局ID获取ViewPager2对象
     2.   实例化一个Fragment列表（步骤4中），里面放入实例化好的Fragment
     3.   实例化 自定义Adapter对象
     4.   给ViewPager2对象设置Adapter

到目前的效果便完成了ViewPager2和Fragment的联动



## ViewPager2+Fragment+Tab

在上述项目中修改

1.   编写table界面xml，新建4个Tab按钮

     1.   编写drawable背景值，设置选中和非选中颜色改变

2.   将table界面包含进main的布局xml

     ```xml
     <include layout="@layout/layout_table"/>
     ```

3.   修改Activity
     1.   添加tab的控件，和tab图片的控件对象
     2.   在`initTabView()`中初始化控件，设置tabs的点击事件（同时切换viewpager），并且设置第一个控件选中
     3.   在`initViewPager()`中添加`viewpager.registerOnPageChangeCallback();`
     4.   在`onPageSelected(int position)`回调方法中调用`changeTab(position);`方法
     5.   编写`changeTab(position)`方法

## 单独ViewPager2

新建一个`dviewpager`包

1.   新建`ShowActivity`，布局中添加ViewPager2控件，在OnCreate方法中为其注册Adapter
2.   为ViewPager2新建一个布局`layout_viewpager`
3.   新建`MyViewPagerAdapter`类，并在步骤一中注册上去

直接在AndroidManifest.xml中改变Activity入口来查看