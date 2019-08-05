package com.carter.javaAndroid.di.module;

import com.carter.javaAndroid.di.component.BaseFragmentComponent;
import com.carter.javaAndroid.modules.homepager.ui.HomePagerFragment;
import com.carter.javaAndroid.modules.knowledge.ui.KnowledgeFragment;
import com.carter.javaAndroid.modules.knowledge.ui.KnowledgeListFragment;
import com.carter.javaAndroid.modules.login.ui.LoginFragment;
import com.carter.javaAndroid.modules.login.ui.RegisterFragment;
import com.carter.javaAndroid.modules.main.ui.fragment.CollectFragment;
import com.carter.javaAndroid.modules.main.ui.fragment.SearchResultFragment;
import com.carter.javaAndroid.modules.main.ui.fragment.UsefulSiteFragment;
import com.carter.javaAndroid.modules.navigation.ui.NavigationFragment;
import com.carter.javaAndroid.modules.project.ui.ProjectFragment;
import com.carter.javaAndroid.modules.project.ui.ProjectListFragment;
import com.carter.javaAndroid.modules.wxarticle.ui.WxArticleFragment;
import com.carter.javaAndroid.modules.wxarticle.ui.WxArticleListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {


    @ContributesAndroidInjector(modules = LoginFragmentModule.class)
    abstract LoginFragment contributesLoginFragmentInject();

    @ContributesAndroidInjector(modules = RegisterFragmentModule.class)
    abstract RegisterFragment contributesRegisterFragmentInject();

    @ContributesAndroidInjector(modules = HomePagerFragmentModule.class)
    abstract HomePagerFragment contributesHomePagerFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeFragmentModule.class)
    abstract KnowledgeFragment contributesKnowledgeFragmentInject();

    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributesNavigationFragmentInject();

    @ContributesAndroidInjector(modules = WxArticleFragmentModule.class)
    abstract WxArticleFragment contributesWxArticleFragmentInject();

    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributesProjectFragmentInject();

    @ContributesAndroidInjector(modules = WxArticleListFragmentModule.class)
    abstract WxArticleListFragment contributesWxArticleListFragmentInject();

    @ContributesAndroidInjector(modules = ProjectListFragmentModule.class)
    abstract ProjectListFragment contributesProjectListFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeListFragmentModule.class)
    abstract KnowledgeListFragment contributesKnowledgeListFragmentInject();

    @ContributesAndroidInjector(modules = SearchResultFragmentModule.class)
    abstract SearchResultFragment contributesSearchResultFragmentInject();

    @ContributesAndroidInjector(modules = UsefulSiteFragmentModule.class)
    abstract UsefulSiteFragment contributesUsefulSiteFragmentInject();

    @ContributesAndroidInjector(modules = CollectFragmentModule.class)
    abstract CollectFragment contributesCollectFragmentInject();
}
