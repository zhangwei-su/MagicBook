https://partnerissuetracker.corp.google.com/issues/192280898

adb shell strace -s 256 -x -f -e network,sendto -p $(adb shell pidof com.android.networkstack.process)

---
```
service call <your_service_name> <number at which the function appears in your_service_name.aidl> <type of the argument like i32 or i64> <argument>
```

[[Android's service call shell command]]
