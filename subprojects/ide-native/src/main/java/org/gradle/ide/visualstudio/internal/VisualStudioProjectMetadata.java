/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.ide.visualstudio.internal;

import org.gradle.api.Task;
import org.gradle.api.Transformer;
import org.gradle.plugins.ide.internal.IdeProjectMetadata;
import org.gradle.util.CollectionUtils;

import java.io.File;
import java.util.List;
import java.util.Set;

public class VisualStudioProjectMetadata implements IdeProjectMetadata {
    private final DefaultVisualStudioProject project;

    public VisualStudioProjectMetadata(DefaultVisualStudioProject project) {
        this.project = project;
    }

    public String getName() {
        return project.getName();
    }

    public File getFile() {
        return project.getProjectFile().getLocation();
    }

    @Override
    public Set<? extends Task> getGeneratorTasks() {
        return project.getBuildDependencies().getDependencies(null);
    }

    public List<String> getConfigurations() {
        return CollectionUtils.collect(project.getConfigurations(), new Transformer<String, VisualStudioProjectConfiguration>() {
            @Override
            public String transform(VisualStudioProjectConfiguration configuration) {
                return configuration.getName();
            }
        });
    }
}
