object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 30
    const val COMPILE_SDK_VERSION = 30
}

object Versions {
    const val DATASTORE_VERSION = "1.0.0-alpha04"
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "2.0.4"
    const val CORE_KTX = "1.3.2"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13.1"
    const val KTLINT = "0.37.2"
    const val RETROFIT = "2.9.0"
    const val RETROFIT_SERIAL_ADAPTER = "0.8.0"
    const val OKHTTP = "4.9.0"
    const val COIL = "1.1.0"
    const val LIVEDATAKTX = "2.2.0"
    const val VIEWMODELKTX = "2.2.0"
    const val NAVIGATION = "2.3.0"
    const val ROOM = "2.2.5"
    const val DAGGER = "2.30.1"
    const val TIMBER = "4.7.1"
    const val MATERIAL = "1.3.0-alpha04"
    const val RECYCLER = "1.1.0"
    const val RECYCLER_SELECTION = "1.1.0-rc01"
    const val PERMISSIONS_VER = "2.0.1"
    const val NAV_ARGS = "2.3.0"
    const val FLOWBINDING_VERSION = "1.0.0-alpha02"
    const val JSON_SERIALIZATION = "1.0.1"
    const val APOLLO_VERSION = "2.4.6"

}

object BuildPluginsVersion {
    const val AGP = "4.1.1"
    const val DETEKT = "1.9.1"
    const val KOTLIN = "1.4.20"
    const val KTLINT = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"
    const val SERIALIZATION = KOTLIN

}

object CoreLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val ANDROIDX_NAV_RUNTIME_KTX = "androidx.navigation:navigation-runtime-ktx:${Versions.NAVIGATION}"
    const val ANDROIDX_NAV_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val ANDROIDX_NAV_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    const val TIMBER_LIB = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val MATERIAL_UI_LIB = "com.google.android.material:material:${Versions.MATERIAL}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER}"
    const val RECYCLER_VIEW_SELECTION = "androidx.recyclerview:recyclerview-selection:${Versions.RECYCLER_SELECTION}"
    const val EAZY_PERMISSIONS = "com.sagar:dslpermission:${Versions.PERMISSIONS_VER}"
}

object FlowBindingUI {
    const val FLOWBINDING_CORE = "io.github.reactivecircus.flowbinding:flowbinding-android:${Versions.FLOWBINDING_VERSION}"
    const val FLOWBINDING_BASE = "io.github.reactivecircus.flowbinding:flowbinding-core:${Versions.FLOWBINDING_VERSION}"
    const val FLOWBINDING_RECYCLER = "io.github.reactivecircus.flowbinding:flowbinding-recyclerview:${Versions.FLOWBINDING_VERSION}"
    const val FLOWBINDING_VIEWPAGER2 = "io.github.reactivecircus.flowbinding:flowbinding-viewpager2:${Versions.FLOWBINDING_VERSION}"

}

object Datastore {
    const val DATASTORE ="androidx.datastore:datastore-preferences:${Versions.DATASTORE_VERSION}"
}

object JetPackKTX {
    const val VIEWMODELSCOPE = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.VIEWMODELKTX}"
    const val LIFECYCLESCOPE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIVEDATAKTX}"
    const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIVEDATAKTX}"
    const val LIFECYCLE_EXTENSION = "androidx.lifecycle:lifecycle-extensions:${Versions.LIVEDATAKTX}"
}

object Navigation {
    const val NAVIGATION_COMP = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_DYNAMIC_FEATURE = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.NAVIGATION}"

}

object DILibs {
    const val DAGGER_ANDROID ="com.google.dagger:dagger-android:${Versions.DAGGER}"
    const val DAGGER_ANDROID_COMPILER ="com.google.dagger:dagger-compiler:${Versions.DAGGER}"
}

object NetLibs {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGIN_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.JSON_SERIALIZATION}"

    const val SERIALIZATION_ADAPTER = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.RETROFIT_SERIAL_ADAPTER}"

    object GraphQLApollo{
        const val APOLLO_RUNTIME = "com.apollographql.apollo:apollo-runtime:${Versions.APOLLO_VERSION}"
        const val APOLLO_COROUTINE_SUPPORT = "com.apollographql.apollo:apollo-coroutines-support:${Versions.APOLLO_VERSION}"
    }
}


object CoilImageLib {
    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
}


object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ROOM_TESTING = "androidx.room:room-testing:${Versions.ROOM}"
}

object AndroidTestingLib {

    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val NAVIGATION_TEST = "androidx.navigation:navigation-testing:${Versions.NAVIGATION}"
}
