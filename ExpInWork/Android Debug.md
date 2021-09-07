https://partnerissuetracker.corp.google.com/issues/192280898

adb shell strace -s 256 -x -f -e network,sendto -p $(adb shell pidof com.android.networkstack.process)

