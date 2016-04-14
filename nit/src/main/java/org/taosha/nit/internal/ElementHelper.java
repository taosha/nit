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

package org.taosha.nit.internal;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by San on 4/6/16.
 */
class ElementHelper {

    /**
     * See http://stackoverflow.com/questions/7687829/java-6-annotation-processing-getting-a-class-from-an-annotation
     */
    public static TypeMirror getAnnotationFieldValue(TypeElement element, Class<? extends Annotation> clazz, String field) {
        AnnotationMirror annotationMirror = getAnnotationMirror(element, clazz);
        if (annotationMirror == null)
            return null;

        AnnotationValue av = getAnnotationFieldValue(annotationMirror, field);
        if (av == null)
            return null;

        return (TypeMirror) av.getValue();
    }

    private static AnnotationValue getAnnotationFieldValue(AnnotationMirror annotationMirror, String field) {
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet()) {
            if (entry.getKey().getSimpleName().toString().equals(field)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static AnnotationMirror getAnnotationMirror(TypeElement typeElement, Class<?> clazz) {
        String clazzName = clazz.getName();
        for (AnnotationMirror m : typeElement.getAnnotationMirrors()) {
            if (m.getAnnotationType().toString().equals(clazzName)) {
                return m;
            }
        }
        return null;
    }

    /**
     * @param e
     * @param suElement
     * @return true if e represents a subtype of the type representing by suElement
     */
    public static boolean isSubtypeOf(Element e, TypeElement suElement) {
        // FIXME: 4/6/16
        return true;
//        while (e instanceof TypeElement) {
//            if (e.equals(suElement))
//                return true;
//
//            TypeMirror su = ((TypeElement) e).getSuperclass();
//
//            if (!(su instanceof DeclaredType))
//                return false;
//
//            e = ((DeclaredType) su).asElement();
//        }
//        return false;
    }
}
