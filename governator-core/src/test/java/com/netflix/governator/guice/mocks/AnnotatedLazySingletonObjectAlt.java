/*
 * Copyright 2013 Netflix, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.netflix.governator.guice.mocks;

import com.netflix.governator.guice.lazy.LazySingleton;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@LazySingleton
public class AnnotatedLazySingletonObjectAlt
{
    public static final AtomicInteger           constructorCount = new AtomicInteger(0);
    public static final AtomicInteger           postConstructCount = new AtomicInteger(0);

    public AnnotatedLazySingletonObjectAlt()
    {
        constructorCount.incrementAndGet();
    }

    @PostConstruct
    public void         postConstruct()
    {
        postConstructCount.incrementAndGet();
    }
}
