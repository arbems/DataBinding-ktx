DataBinding-ktx
=====

`DataBinding-ktx` make easy declaring DataBinding and ViewBinding.

## Problems in DataBinding and ViewBinding
- Differences in the way of declaring a `binding` variable in Activity and Fragment.
    - In Activity, you can declare the `binding` variable using `by lazy`.
    - In Fragment, you can't declare the `binding` variable using `by lazy` because of recreating Fragment's view.
- Wasted memory unless you set the `binding` variable to null after onDestroyView
    - If you use Navigation component, BackStack or detach in Fragment, Fragment is still alive but Fragment's view is dead after onDestroyView.
      Because the `binding` variable has view tree, your app wasted memory.
      For saving memory, you should set the `binding` variable to null after onDestroyView.
- Forgetting to call `setLifecycleOwner` in DataBinding.
- In Activity, lazy binding is lazy setContentView. 

## Overview
- `DataBinding-ktx` provide the unified way of declaring the `binding` variable in Activity and Fragment.
- `DataBinding-ktx` is saving memory because of cleaning up the `binding` variable having the view tree after onDestroyView.
- `DataBinding-ktx` is automatically calling `setLifecycleOwner` in DataBinding.
- `DataBinding-ktx` needs one of the following
    - calling `setContentView` in Activity and calling `inflater.inflate` in `onCreateView` of Fragment.
    - calling Activity/Fragment's secondary constructor passing layout res id.

## Usage 
### DataBinding
```kotlin
private val binding: DataBindingActivityBinding by dataBinding()
```
### ViewBinding
```kotlin
private val binding by viewBinding { ViewBindingActivityBinding.bind(it) }
```
### Activity
- Use `setContentView` before using the `binding` variable.
```kotlin
class DataBindingActivity : FragmentActivity() {
    // Declare the `binding` variable using `by dataBinding()`.
    private val binding: DataBindingActivityBinding by dataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_binding_activity)
        // You can use binding
    }
}
```
- Use Activity's secondary constructor passing layout res id.
```kotlin
class DataBindingActivity : AppCompatActivity(R.layout.data_binding_activity) {
    // Declare the `binding` variable using `by dataBinding()`.
    private val binding: DataBindingActivityBinding by dataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // You can use binding
    }
}
```

### Fragment
- Use `inflater.inflate` in `onCreateView`.
```kotlin
class DataBindingFragment : Fragment() {
    // Declare the `binding` variable using `by dataBinding()`.
    private val binding: DataBindingFragmentBinding by dataBinding()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.data_binding_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You can use binding
    }
}
```
- Use Fragment's secondary constructor passing layout res id.
```kotlin
class DataBindingFragment : Fragment(R.layout.data_binding_fragment) {
    // Declare the `binding` variable using `by dataBinding()`.
    private val binding: DataBindingFragmentBinding by dataBinding()
    // DO NOT override onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You can use binding
    }
}
```


## Note
### Activity
- If you forget to use Activity's secondary constructor passing layout res id, your app crash.

#### DataBinding
```kotlin
// You can define and use DataBindingAppCompatActivity for not forgetting.
open class DataBindingAppCompatActivity<T : ViewDataBinding>(@LayoutRes contentLayoutId : Int) : AppCompatActivity(contentLayoutId) {
    protected val binding: T by dataBinding()
} 
class YourActivity : DataBindingAppCompatActivity<YourActivityBinding>(R.layout.your_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // You can use binding
    }
}
```
#### ViewBinding
```kotlin
// You can define and use ViewBindingAppCompatActivity for not forgetting.
open class ViewBindingAppCompatActivity<T : ViewBinding>(@LayoutRes contentLayoutId: Int, bind: (View) -> T) : AppCompatActivity(contentLayoutId) {
    protected val binding by viewBinding(bind)
}

class YourActivity : ViewBindingAppCompatActivity<YourActivityBinding>(R.layout.your_activity, YourActivityBinding::bind) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // You can use binding
    }
}
```

### Fragment
- If you forget to use Fragment's secondary constructor passing layout res id, Fragment is not shown.

#### DataBinding
```kotlin
// You can define and use DataBindingFragment for not forgetting.
open class DataBindingFragment<T : ViewDataBinding>(@LayoutRes contentLayoutId : Int) : Fragment(contentLayoutId) {
    protected val binding: T by dataBinding()
} 
class YourFragment : DataBindingFragment<YourFragmentBinding>(R.layout.your_fragment) {
    // DO NOT override onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You can use binding
    }
}
```
#### ViewBinding
```kotlin
// You can define and use ViewBindingFragment for not forgetting.
open class ViewBindingFragment<T : ViewBinding>(@LayoutRes contentLayoutId : Int, bind: (View) -> T) : Fragment(contentLayoutId) {
    protected val binding: T by viewBinding(bind)
} 
class YourFragment : ViewBindingFragment<YourFragmentBinding>(R.layout.your_fragment) {
    // DO NOT override onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // You can use binding
    }
}
```

## Gradle

[![](https://jitpack.io/v/wada811/DataBinding-ktx.svg)](https://jitpack.io/#wada811/DataBinding-ktx)

```groovy
repositories {
    maven { url "https://www.jitpack.io" }
}

dependencies {
    implementation 'com.github.wada811:DataBinding-ktx:x.y.z'
}
```

## License

Copyright (C) 2019 wada811

Licensed under the Apache License, Version 2.0
