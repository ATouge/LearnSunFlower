# SpannableString和SpannableStringBuilder用法总结

## 1. 概述

Android中用的最多的控件 TextView 有时候有需要在文本中展示不同格式，这时候如果单用 TextView.setText("xxx")，实现不了，这时候需要用到 SpannableString类

![image-20200423152619457](F:\Develop\LearningSampke\LearnSunFlower\document\SpannableString截图.png)

## 2. SpannableString

具体用法如下

```Java
SpannableString spannableString = new SpannableString("今天天气真不错");
// 设置前景色
ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
spannableString.setSpan(foregroundColorSpan, 2, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
textView.setText(spannableString);
```

运行效果如下：

![image-20200423153953789](F:\Develop\LearningSampke\LearnSunFlower\document\SpannableString效果图.png)

用法很简单，主要就是调用 spannableString.setSpan(foregroundColorSpan, 2, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

```Java
源码：
public void setSpan(Object what, int start, int end, int flags) {
	super.setSpan(what, start, end, flags);
}
```



参数解释：

​	what: 需要的改变动作，如：字体颜色、粗体、斜体等等

​	start: 开始的字符索引值

​	end:  结束的字符索引值

​	flag: start-end的范围，解释起来就像集合区间，是开区间还是闭区间。根据flags来区分

INCLUSIVE: 表示 闭合

EXCLUSIVE: 表示 开放

		1. Spannable.SPAN_INCLUSIVE_EXCLUSIVE: [a,b)
  		2. Spannable.SPAN_INCLUSIVE_INCLUSIVE: [a,b]
    		3. Spannable.SPAN_EXCLUSIVE_INCLUSIVE: (a,b]
      		4. Spannable.SPAN_EXCLUSIVE_EXCLUSIVE: (a,b)

本例中，改变的是前景色，用到的是 ForegroundColorSpan ，相应的其他的改动都类似

```java
SpannableString spannableString = new SpannableString("今天天气真不错");
// 设置前景色
ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
/*
* 其他的设置方法也类似
* 背景色：BackgroundColorSpan
* 下划线：UnderlineSpan
* 删除线：StrikethroughSpan
* 字体大小：AbsoluteSizeSpan(绝对大小)，RelativeSizeSpan(相对大小，相对于TextView本身的文字大小)
* 字体样式：StyleSpan(粗体、斜体)
* 上标：SuperscriptSpan
* 下标：SubscriptSpan
* 插入图片：ImageSpan
* 部分文字的点击事件：ClickableSpan, 需要设置TextView.setMovementMethod(LinkMovement.getInstance()), 否则不生效
* 可多次调用 setSpan() 方法
*/
spannableString.setSpan(foregroundColorSpan, 2, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
textView.setText(spannableString);
```



## 3. SpannableStringBuilder

SpannableStringBuilder 的用法和 SpannableString 用法很相似，只是可以有 append() 方法追加字符

看代码：

```java
// 设置前景色
ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
// 使用方法类似与 SpannableString
SpannableStringBuilder builder = new SpannableStringBuilder()
.append("测试 ")
.append("SpannableStringBuilder", foregroundColorSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
.append(" 结果");
binding.textContent.setText(builder);
```

在 append() 的时候就将 你需要设置的格式指定好

效果：

![image-20200423173433093](F:\Develop\LearningSampke\LearnSunFlower\document\SpannableString效果图2.png)



## 4. 总结

SpannableString 和 SpannableStringBuilder 都可以改变字体样式，但是SpannableStringBuilder思路更清晰一些，不需要去计算字符长度，简单一些，具体采用哪种方式改变字体，应当根据实际项目需求去选择。