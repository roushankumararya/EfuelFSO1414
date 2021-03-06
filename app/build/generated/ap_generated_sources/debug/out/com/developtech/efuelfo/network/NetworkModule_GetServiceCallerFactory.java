// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.developtech.efuelfo.network;

import android.content.Context;
import com.developtech.efuelfo.util.SPUtils;
import com.developtech.efuelfo.util.UtilFunctions;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.subscriptions.CompositeSubscription;

public final class NetworkModule_GetServiceCallerFactory implements Factory<ServiceCaller> {
  private final NetworkModule module;

  private final Provider<CompositeSubscription> compositeSubscriptionProvider;

  private final Provider<UtilFunctions> utilFunctionsProvider;

  private final Provider<SPUtils> spUtilsProvider;

  private final Provider<Context> contextProvider;

  public NetworkModule_GetServiceCallerFactory(
      NetworkModule module,
      Provider<CompositeSubscription> compositeSubscriptionProvider,
      Provider<UtilFunctions> utilFunctionsProvider,
      Provider<SPUtils> spUtilsProvider,
      Provider<Context> contextProvider) {
    assert module != null;
    this.module = module;
    assert compositeSubscriptionProvider != null;
    this.compositeSubscriptionProvider = compositeSubscriptionProvider;
    assert utilFunctionsProvider != null;
    this.utilFunctionsProvider = utilFunctionsProvider;
    assert spUtilsProvider != null;
    this.spUtilsProvider = spUtilsProvider;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public ServiceCaller get() {
    return Preconditions.checkNotNull(
        module.getServiceCaller(
            compositeSubscriptionProvider.get(),
            utilFunctionsProvider.get(),
            spUtilsProvider.get(),
            contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ServiceCaller> create(
      NetworkModule module,
      Provider<CompositeSubscription> compositeSubscriptionProvider,
      Provider<UtilFunctions> utilFunctionsProvider,
      Provider<SPUtils> spUtilsProvider,
      Provider<Context> contextProvider) {
    return new NetworkModule_GetServiceCallerFactory(
        module,
        compositeSubscriptionProvider,
        utilFunctionsProvider,
        spUtilsProvider,
        contextProvider);
  }
}
