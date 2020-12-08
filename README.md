# KotlinPost
Kotlin project MVP


Tạo project lấy data từ networking trong mô hình MVP 

Ánh xạ view
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'


sử dụng Retrofit và RxJava RxAndroid

implementation 'com.squareup.retrofit2:retrofit:2.9.0'

implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

implementation 'com.squareup.retrofit2:adapter-rxjava2:2.2.0'

implementation 'io.reactivex.rxjava2:rxjava:2.0.1'

implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'


SwipeRefreshLayout

implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'

