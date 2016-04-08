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

import org.taosha.nit.Service;

/**
 * Created by San on 4/6/16.
 */
@Service(GreetingService.class)
public class EnglishGreetingService implements GreetingService {

    @Override public void greet() {
        System.out.println("Oh hello!");
    }

}
