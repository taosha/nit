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

package org.taosha.nit;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * Created by San on 4/6/16.
 */
@Service(javax.annotation.processing.Processor.class)
@SupportedAnnotationTypes("org.taosha.nit.Service")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Processor extends AbstractProcessor {

    private Filer filer;
    private Types types;
    private Elements elements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        elements = processingEnv.getElementUtils();
    }

    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(Service.class)) {
            process((TypeElement) e);
        }
        return false;
    }

    private void process(TypeElement e) {
        TypeElement service = (TypeElement) types.asElement(ElementHelper.getAnnotationFieldValue(e, Service.class, "value"));

        if (!ElementHelper.isSubtypeOf(e, service)) {
            throw new IllegalArgumentException(String.format("%1$s is not an implementation(subtype) of nit %2$s", e, service));
        }

        try {
            FileObject r = filer.createResource(StandardLocation.CLASS_OUTPUT, "META_INF.services", service.getQualifiedName());
            Writer writer = r.openWriter();
            writer.append(e.getQualifiedName());
            writer.flush();
            writer.close();
            File file = new File(r.toUri().getPath());
            while (!"META_INF".equals(file.getName())) {
                file = file.getParentFile();
            }
            mv(file, new File(file.getParentFile(), "META-INF"));
        } catch (IOException ex) {
            throw new RuntimeException("Failed to generate nit declaration file " + service, ex);
        }
    }

    private static void mv(File src, File dest) {
        if (!dest.exists() || dest.isFile()) {
            src.renameTo(dest);
        } else {
            for (File f : src.listFiles()) {
                mv(f, new File(dest, f.getName()));
            }
        }
    }

}
