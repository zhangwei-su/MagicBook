In Short

> Code related to service call command are just the arguments of the function and order at which the function occur in the aidl file of that service.Here is a syntax
> 
> service call <your_service_name> \<number at which the function appears in your_service_name.aidl> <type of the argument like i32 or i64> \<argument>

In Detail

I faced a lot of problems to know about it and hence I will share the solution with the help of clipboard service.

First you need to know about the service you are interested in -

For that you need to look for all the service that is there for particular android system by typing
```
adb shell service list
```
Here is what you will get -
```
.

.

.

59 ethernet: [android.net.IEthernetManager]

60 wifip2p: [android.net.wifi.p2p.IWifiP2pManager]

61 rttmanager: [android.net.wifi.IRttManager]

62 wifiscanner: [android.net.wifi.IWifiScanner]

63 wifi: [android.net.wifi.IWifiManager]

64 overlay: [android.content.om.IOverlayManager]

65 netpolicy: [android.net.INetworkPolicyManager]

66 netstats: [android.net.INetworkStatsService]

67 network_score: [android.net.INetworkScoreService]

68 textservices: [com.android.internal.textservice.ITextServicesManager]

69 network_management: [android.os.INetworkManagementService]

70 clipboard: [android.content.IClipboard]

71 statusbar: [com.android.internal.statusbar.IStatusBarService]

.

.

.
```
As I am interested in clipboard service, here is how it look

70 clipboard: [android.content.IClipboard]

So from here we can summarise that the service name is clipboard service and the package path is android.content.IClipboard

Then you need to know the complete path where the IClipboard.aidl is.

To know that you need to search on google for IClipboard.aidl.

You need to look for something from android.googlesource.com website in the results, like in my case-

https://android.googlesource.com/platform/frameworks/base.git/+/android-4.2.2_r1/core/java/android/content/IClipboard.aidl

So after +/android-4.2.2_r1 is where your path lies.Let that path be path_of_clipboard.aidl=

/core/java/android/content/IClipboard.aidl

As these service call codes are dependent on the android system, hence you need to know your android os name- In my case it is 8.1.0

So I will go to the following website where google puts there code and select my os version from the left hand side for the page -

[https://android.googlesource.com/platform/frameworks/base/](https://android.googlesource.com/platform/frameworks/base/)

In my case it is android-8.1.0_r50. Here r50 is not important. You can choose any revision. Now I will click on the link and then after that my url will look like this

https://android.googlesource.com/platform/frameworks/base/+/android-8.1.0_r51

And then after adding path_of_clipboard.aidl, my complete url will look like

https://android.googlesource.com/platform/frameworks/base/+/android-8.1.0_r51/core/java/android/content/IClipboard.aidl

Here there will be many methods in the interface.Like in my case
```java
void setPrimaryClip(in ClipData clip, String callingPackage);

ClipData getPrimaryClip(String pkg);

ClipDescription getPrimaryClipDescription(String callingPackage);

boolean hasPrimaryClip(String callingPackage);

void addPrimaryClipChangedListener(in IOnPrimaryClipChangedListener listener,

String callingPackage);

void removePrimaryClipChangedListener(in IOnPrimaryClipChangedListener listener);

/**

* Returns true if the clipboard contains text; false otherwise.

*/

boolean hasClipboardText(String callingPackage);
```
So the code for the first method i.e. setPrimaryClip will be 1 as it occured at first place and that for the last method i.e hasClipboardText will be 7 as it occured at seventh place in the aidl file. Similarly for the other methods.

So if I want to call the seventh method I will type
```
adb shell service call clipboard 7
```
As you might have seen that I have not put the callingPackage name as it is not required.

If the method need arguments, then you can pass it like as show in this example.

Let us assume a method whose code is 8 in clipboard and that looks like this -
```
getDemo(String arg1, int arg2, boolean arg3)
```
So I will call it like this
```
adb shell call clipboard 8 s16 "first_argument" i32 12 i32 1
```
Here i32 stands for 32 bit integer and s16 for the string. We can, even pass boolean value as an integer as shown in the example.

In boolean integer 1 stands for true and 0 for false.

[Source](https://gist.github.com/tniessen/ea3d68e7d572ed7c607b81d715798800)

TIP Keep the logcat open(like in android studio) to check for any error that occured while executing that adb command.