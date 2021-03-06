// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.developtech.efuelfo.app;

import android.content.SharedPreferences;
import com.developtech.efuelfo.util.SPUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AppModule_GetSpUtilsFactory implements Factory<SPUtils> {
  private final AppModule module;

  private final Provider<SharedPreferences> sharedPreferencesProvider;

  public AppModule_GetSpUtilsFactory(
      AppModule module, Provider<SharedPreferences> sharedPreferencesProvider) {
    assert module != null;
    this.module = module;
    assert sharedPreferencesProvider != null;
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public SPUtils get() {
    return Preconditions.checkNotNull(
        module.getSpUtils(sharedPreferencesProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SPUtils> create(
      AppModule module, Provider<SharedPreferences> sharedPreferencesProvider) {
    return new AppModule_GetSpUtilsFactory(module, sharedPreferencesProvider);
  }
}
