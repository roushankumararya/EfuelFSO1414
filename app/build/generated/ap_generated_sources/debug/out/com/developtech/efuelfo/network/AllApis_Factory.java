// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.developtech.efuelfo.network;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllApis_Factory implements Factory<AllApis> {
  private final Provider<Api> apiProvider;

  public AllApis_Factory(Provider<Api> apiProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
  }

  @Override
  public AllApis get() {
    return new AllApis(apiProvider.get());
  }

  public static Factory<AllApis> create(Provider<Api> apiProvider) {
    return new AllApis_Factory(apiProvider);
  }

  /** Proxies {@link AllApis#AllApis(Api)}. */
  public static AllApis newAllApis(Api api) {
    return new AllApis(api);
  }
}
