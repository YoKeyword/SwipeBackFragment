# SwipeBackFragment
An Android library that can finish a Fragment&amp;Activity with swipe-back gesture.

滑动Fragment&Activity边缘即可类似IOS一样，拖动返回。

原理分析：[传送门](http://www.jianshu.com/p/626229ca4dc2),  如果你重度使用Fragment，不妨看看这个库[Fragmentation](https://github.com/YoKeyword/Fragmentation)

# 特性
注: 滑动返回仅支持通过add方式加载的Fragment, replace不支持

1、Activity内Fragment数大于1时，滑动返回的是Fragment，否则滑动返回的是Activity

2、支持左、右、左&右滑动（未来可能会增加更多滑动区域）

3、支持Swipe时的滑动监听

4、帮你处理了Fragment重叠的情况

# Demo演示
<img src="gif/swipe.gif"/>

# 如何使用
1、项目下app的build.gradle中依赖：
````gradle
// appcompat v7包是必须的
compile 'me.yokeyword:swipebackfragment:0.4.0'
````
2、如果Activity也需要支持SwipeBack，则继承SwipeBackActivity:
````java
public class SwipeBackSampleActivity extends SwipeBackActivity {}
````
同时该Activity的theme添加如下属性：
````xml
 <item name="android:windowIsTranslucent">true</item>
````

3、如果Fragment需要支持SwipeBack，则继承SwipeBackFragment:
````java
public class SwipeBackSampleFragment extends SwipeBackFragment {
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xxx, container, false);
        // 需要支持SwipeBack则这里必须调用toSwipeBackFragment(view);
        return attachToSwipeBack(view);
    }
}
````

更多方法:
````java
  // 设置滑动方向
  getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_RIGHT); // EDGE_LEFT(默认),EDGE_ALL
  // 设置侧滑触摸生效区域 MAX,MED,MIN,custom
  setEdgeLevel(SwipeBackLayout.EdgeLevel edgeLevel)
  // 滑动过程监听
  getSwipeBackLayout().addSwipeListener(new SwipeBackLayout.OnSwipeListener() {
            @Override
            public void onDragStateChange(int state) {
                // Drag state
            }

            @Override
            public void onEdgeTouch(int edgeFlag) {
                // 触摸的边缘flag
            }

            @Override
            public void onDragScrolled(float scrollPercent) {
                // 滑动百分比
            }
   });

    // 对于SwipeBackActivity有下面控制SwipeBack优先级的方法:
      /**
        * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
        *
        * 可以通过复写该方法, 自由控制优先级
        *
        * @return true: Activity可以滑动退出, 并且总是优先;  false: Activity不允许滑动退出
        */
        @Override
        public boolean swipeBackPriority() {
           return super.swipeBackPriority();
           // 下面是默认实现:
           // return getSupportFragmentManager().getBackStackEntryCount() <= 1;
        }
````

# 致谢
[ikew0ng/SwipeBackLayout](https://github.com/ikew0ng/SwipeBackLayout)

