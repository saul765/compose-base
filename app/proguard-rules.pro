# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn org.slf4j.impl.StaticLoggerBinder
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.


# Preserve all classes annotated with @Serializable
-keep @kotlinx.serialization.Serializable class *
-keepclassmembers class * {
    @kotlinx.serialization.Transient <fields>;
}

# Preserve generated serializer methods
-keepclassmembers class * {
    kotlinx.serialization.KSerializer serializer(...);
}

# Preserve the kotlinx.serialization library classes
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.serialization.internal.** { *; }