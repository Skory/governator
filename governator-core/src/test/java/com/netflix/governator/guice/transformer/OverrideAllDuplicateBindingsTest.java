package com.netflix.governator.guice.transformer;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.netflix.governator.guice.LifecycleInjector;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OverrideAllDuplicateBindingsTest {
    public static interface Foo {
        
    }
    public static class Foo1 implements Foo {
        
    }
    public static class Foo2 implements Foo {
        
    }
    
    public static class MyModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(Foo.class).to(Foo1.class);
        }
    }
    
    public static class MyOverrideModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(Foo.class).to(Foo2.class);
        }
    }
    
    @Test(expectedExceptions={RuntimeException.class})
    public void testShouldFailOnDuplicate() {
        LifecycleInjector.builder()
            .withModuleClasses(MyModule.class, MyOverrideModule.class)
            .build()
            .createInjector();
        Assert.fail("Should have failed with duplicate binding");
    }
    
    @Test
    public void testShouldInstallDuplicate() {
        Injector injector = LifecycleInjector.builder()
            .withModuleTransformer(new OverrideAllDuplicateBindings())
            .withModuleClasses(MyModule.class, MyOverrideModule.class)
            .build()
            .createInjector();
        
        Foo foo = injector.getInstance(Foo.class);
        Assert.assertTrue(foo.getClass().equals(Foo2.class));
    }
}
