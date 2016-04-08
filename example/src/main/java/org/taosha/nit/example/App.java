/*
 * Copyright 2016 Taosha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.taosha.nit.example;

import java.util.ServiceLoader;

/**
 * Created by San on 4/6/16.
 */
public class App {
    public static void main(String... args) {
        for (GreetingService service : ServiceLoader.load(GreetingService.class)) {
            System.out.printf("Found GreetingService implementation: %s\n", service);
            System.out.println("Greeting:");
            service.greet();
        }
    }
}
