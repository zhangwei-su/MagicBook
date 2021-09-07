## better way
```java
public abstract static class MotoNetworkPolicyListener {

        public void onUidRulesChanged(int uid, int uidRules) {};

        public void onUidPoliciesChanged(final int uid, final int uidPolicies) {};

        public void onMeteredIfacesChanged(String[] strings) {};

        public void onRestrictBackgroundChanged(final boolean isDataSaving) {};

        public void onSubscriptionOverride(int subId, int overrideMask, int overrideValue) {};

        public void onSubscriptionPlansChanged(int subId, SubscriptionPlan[] plans) {};

    }

    @GuardedBy("mMotoNetPolicyListeners")

    private final ArrayMap<MotoNetworkPolicyListener, INetworkPolicyListener>

        mMotoNetPolicyListeners = new ArrayMap<>();

    @RequiresPermission(android.Manifest.permission.MANAGE_NETWORK_POLICY)

    public void registerListener(MotoNetworkPolicyListener listener) {

        if (listener == null) {

            throw new IllegalArgumentException("listener must not be null");

        }

        final INetworkPolicyListener fwkListener = new INetworkPolicyListener.Stub() {

            @Override

            public void onUidRulesChanged(int uid, int uidRules) throws RemoteException {

                listener.onUidRulesChanged(uid, uidRules);

            }

            @Override

....

        };

  

  

        synchronized (mMotoNetPolicyListeners) {

            mMotoNetPolicyListeners.put(listener, fwkListener);

            try {

                mService.registerListener(fwkListener);

            } catch (RemoteException ex) {

                throw ex.rethrowFromSystemServer();

            }

        }

    }

    @RequiresPermission(android.Manifest.permission.MANAGE_NETWORK_POLICY)

    public void unregisterListener(MotoNetworkPolicyListener listener) {

        if (listener == null) {

            throw new IllegalArgumentException("listener must not be null");

        }

        synchronized (mMotoNetPolicyListeners) {

            final INetworkPolicyListener fwkListener =

                mMotoNetPolicyListeners.remove(listener);

            try {

                mService.unregisterListener(fwkListener);

            } catch (RemoteException ex) {

                throw ex.rethrowFromSystemServer();

            }

        }

    }
```
	
## Wrong way
```java
	public interface MotoNetworkPolicyListener {

        @NonNull Handler getHandler();

        void onUidRulesChanged(int uid, int uidRules);

....................

    }

    private List<MotoNetworkPolicyListener> mMotoNetPolicyListeners = new ArrayList<>();

    private boolean mRegisteredListenerInFwk = false;

    private INetworkPolicyListener mListenerOnFwk = new INetworkPolicyListener.Stub() {

        private void run(MotoNetworkPolicyListener listener, Runnable r) {

            if (listener.getHandler() != null) {

                listener.getHandler().post(r);

            } else {

                r.run();

            }

        }

        @Override

        public void onUidRulesChanged(int uid, int uidRules) throws RemoteException {

            synchronized (mMotoNetPolicyListeners) {

                mMotoNetPolicyListeners.forEach((listener)-> {

                            run(listener, ()-> listener.onUidRulesChanged(uid, uidRules));

                        });

            }

        }

        @Override

.................

    }

    @RequiresPermission(android.Manifest.permission.MANAGE_NETWORK_POLICY)

    public void registerListener(MotoNetworkPolicyListener listener) {

        if (listener == null) {

            throw new IllegalArgumentException("listener must not be null");

        }

        synchronized (mMotoNetPolicyListeners) {

            if (!mMotoNetPolicyListeners.contains(listener)) {

                mMotoNetPolicyListeners.add(listener);

            } else {

                Log.w(TAG, "The " + listener + " have been registered");

            }

            updateRegisterNetworkPolicyListenerLocked();

        }

    }

  

  

    @RequiresPermission(android.Manifest.permission.MANAGE_NETWORK_POLICY)

    public void unregisterListener(MotoNetworkPolicyListener listener) {

        if (listener == null) {

            throw new IllegalArgumentException("listener must not be null");

        }

        synchronized (mMotoNetPolicyListeners) {

            if (mMotoNetPolicyListeners.contains(listener)) {

                mMotoNetPolicyListeners.remove(listener);

            } else {

                Log.w(TAG, "The " + listener + " have been unregistered");

            }

            updateRegisterNetworkPolicyListenerLocked();

        }

    }

    private void updateRegisterNetworkPolicyListenerLocked() {

        if (mMotoNetPolicyListeners.isEmpty() && mRegisteredListenerInFwk) {

            try {

                mService.unregisterListener(mListenerOnFwk);

                mRegisteredListenerInFwk = false;

            } catch (RemoteException ex) {

                throw ex.rethrowFromSystemServer();

            }

        } else if (!mMotoNetPolicyListeners.isEmpty() && !mRegisteredListenerInFwk) {

            try {

                mService.registerListener(mListenerOnFwk);

                mRegisteredListenerInFwk = true;

            } catch (RemoteException ex) {

                throw ex.rethrowFromSystemServer();

            }

        }

    }
```