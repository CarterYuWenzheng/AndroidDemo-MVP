package com.carter.javaAndroid.di.module;

import com.carter.javaAndroid.di.component.BaseActivityComponent;
import com.carter.javaAndroid.modules.knowledge.ui.KnowledgeActivity;
import com.carter.javaAndroid.modules.login.ui.LoginActivity;
import com.carter.javaAndroid.modules.main.ui.activity.ArticleDetailActivity;
import com.carter.javaAndroid.modules.main.ui.activity.CommonActivity;
import com.carter.javaAndroid.modules.main.ui.activity.MainActivity;
import com.carter.javaAndroid.modules.main.ui.activity.SearchActivity;
import com.carter.javaAndroid.modules.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributersSplashActivityInject();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributersLoginActivityInject();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributersMainActivityInject();

    @ContributesAndroidInjector(modules = ArticleDetailActivityModule.class)
    abstract ArticleDetailActivity contributersArticleDetailActivityInject();

    @ContributesAndroidInjector(modules = CommonActivityModule.class)
    abstract CommonActivity contributersCommonActivityInject();

    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity contributersSearchActivityInject();

    @ContributesAndroidInjector(modules = KnowledgeActivityModule.class)
    abstract KnowledgeActivity contributersKnowledgeActivityInject();
}
