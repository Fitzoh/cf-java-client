/*
 * Copyright 2013-2017 the original author or authors.
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

package org.cloudfoundry.bosh.deployments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cloudfoundry.Nullable;
import org.cloudfoundry.QueryParameter;
import org.immutables.value.Value;

import java.util.List;

/**
 * The request payload for the Create Deployment operation
 */
@Value.Immutable
abstract class _CreateDeploymentRequest {

    /**
     * The manifest
     */
    @JsonIgnore
    abstract String getManifest();

    /**
     * Whether to recreate deployment
     */
    @Nullable
    @QueryParameter("recreate")
    abstract Boolean getRecreate();

    /**
     * Whether to skip drain on jobs
     */
    @Nullable
    @QueryParameter("skip_drain")
    abstract List<String> getSkipDrain();

}
